package com.qa.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.qa.api.TestDataProvider.*;

public class RestCountriesApiTest {

    private RestCountriesClient client;

    @BeforeClass
    public void setup() {
        client = new RestCountriesClient();
    }

    @Test(description = "Verify GET /name/{name} returns 200 for valid name")
    public void testGetCountryByName_ValidName_Returns200() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
        Assert.assertNotNull(response.jsonPath().getString("[0].name.common"));
    }
    @Test(description = "Verify GET /name/{name} returns expected fields")
    public void testGetCountryByName_ValidName_ReturnsExpectedFields() {
        Response response = client.getCountryByName(getCountryName("France"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCountryName("France"));
        Assert.assertTrue(response.jsonPath().getList("[0].capital").contains(getCountryCapital("France")));
        Assert.assertEquals(response.jsonPath().getString("[0].region"), getCountryRegion("France"));
    }

    @Test(description = "Verify GET /name/{name}?fullText=true returns exact match")
    public void testGetCountryByFullName_ExactMatch_Returns200() {
        Response response = client.getCountryByFullName(getCountryName("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getList("$").size(), 1);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCountryName("Germany"));
    }

    @Test(description = "Verify GET /name/{name}?fullText=true returns 404 for partial name")
    public void testGetCountryByFullName_PartialName_Returns404() {
        Response response = client.getCountryByFullName("Germ");

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /alpha/{code} returns 200 for alpha-2 code")
    public void testGetCountryByCode_Alpha2_Returns200() {
        Response response = client.getCountryByCode(getAlpha2Code("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCountryName("Germany"));
    }

    @Test(description = "Verify GET /alpha/{code} returns 200 for alpha-3 code")
    public void testGetCountryByCode_Alpha3_Returns200() {
        Response response = client.getCountryByCode(getAlpha3Code("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCountryName("Germany"));
    }

    @Test(description = "Verify GET /alpha?codes=... returns multiple countries")
    public void testGetCountriesByCodes_MultipleCodes_ReturnsArray() {
        Response response = client.getCountriesByCodes(
                getAlpha2Code("Germany"),
                getAlpha2Code("France"),
                getAlpha2Code("unitedStates"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getList("$").size(), 3);
    }

    @Test(description = "Verify GET /all?fields=... returns 200")
    public void testGetAllCountries_WithFields_Returns200() {
        Response response = client.getAllCountries(getFields());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /all?fields=... returns requested fields")
    public void testGetAllCountries_WithFields_ReturnsRequestedFields() {
        Response response = client.getAllCountries(getFields());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.jsonPath().getMap("[0]").containsKey("name"));
    }

    @Test(description = "Verify GET /name/{name} returns 404 for invalid name")
    public void testGetCountryByName_InvalidName_Returns404() {
        Response response = client.getCountryByName(getInvalidCountryName());

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /alpha/{code} returns 404 for invalid code")
    public void testGetCountryByCode_InvalidCode_Returns404() {
        Response response = client.getCountryByCode(getInvalidCountryCode());

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /all without fields returns 400")
    public void testGetAllCountries_NoFields_Returns400() {
        Response response = client.getAllCountries();

        Assert.assertEquals(response.getStatusCode(), 400);
    }

    @Test(description = "Data consistency: Verify Germany response")
    public void testDataConsistency_GermanyResponse_MatchesExpectedData() {
        Response response = client.getCountryByName(getCountryName("Germany").toLowerCase());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCountryName("Germany"));
        Assert.assertEquals(response.jsonPath().getString("[0].capital[0]"), getCountryCapital("Germany"));
        Assert.assertEquals(response.jsonPath().getString("[0].region"), getCountryRegion("Germany"));
    }

    @Test(description = "Data consistency: Verify response is valid JSON array")
    public void testDataConsistency_ResponseStructure_IsValidJsonArray() {
        Response response = client.getCountryByName(getCountryName("japan").toLowerCase());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(response.getContentType().contains("application/json"));
        Assert.assertNotNull(response.jsonPath().getList("$"));
    }
}
