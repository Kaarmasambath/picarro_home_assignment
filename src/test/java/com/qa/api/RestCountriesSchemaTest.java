package com.qa.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static com.qa.api.TestDataProvider.*;

public class RestCountriesSchemaTest {

    private RestCountriesClient client;

    @BeforeClass
    public void setup() {
        client = new RestCountriesClient();
    }

    @Test(description = "Verify GET /name/{name} response matches JSON schema")
    public void testGetCountryByName_ResponseMatchesSchema() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/country-response-schema.json"));
    }

    @Test(description = "Verify GET /alpha/{code} response matches JSON schema")
    public void testGetCountryByCode_ResponseMatchesSchema() {
        Response response = client.getCountryByCode(getAlpha2Code("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/country-response-schema.json"));
    }

    @Test(description = "Verify GET /region/{region} response matches JSON schema")
    public void testGetCountriesByRegion_ResponseMatchesSchema() {
        Response response = client.getCountriesByRegion("europe");

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath("schemas/country-response-schema.json"));
    }

    @Test(description = "Verify country response contains required fields")
    public void testCountryResponse_ContainsRequiredFields() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("[0].name.common"), "name.common should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].name.official"), "name.official should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].cca2"), "cca2 should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].cca3"), "cca3 should not be null");
        Assert.assertNotNull(response.jsonPath().getString("[0].region"), "region should not be null");
    }

    @Test(description = "Verify alpha-2 code format is correct")
    public void testAlpha2Code_FormatIsValid() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        String cca2 = response.jsonPath().getString("[0].cca2");
        Assert.assertNotNull(cca2);
        Assert.assertEquals(cca2.length(), 2, "Alpha-2 code should be 2 characters");
        Assert.assertTrue(cca2.matches("^[A-Z]{2}$"), "Alpha-2 code should be uppercase letters");
    }

    @Test(description = "Verify alpha-3 code format is correct")
    public void testAlpha3Code_FormatIsValid() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        String cca3 = response.jsonPath().getString("[0].cca3");
        Assert.assertNotNull(cca3);
        Assert.assertEquals(cca3.length(), 3, "Alpha-3 code should be 3 characters");
        Assert.assertTrue(cca3.matches("^[A-Z]{3}$"), "Alpha-3 code should be uppercase letters");
    }

    @Test(description = "Verify region value is from allowed list")
    public void testRegion_ValueIsValid() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        String region = response.jsonPath().getString("[0].region");
        String[] validRegions = {"Africa", "Americas", "Antarctic", "Asia", "Europe", "Oceania"};

        boolean isValid = false;
        for (String validRegion : validRegions) {
            if (validRegion.equals(region)) {
                isValid = true;
                break;
            }
        }
        Assert.assertTrue(isValid, "Region should be one of: Africa, Americas, Antarctic, Asia, Europe, Oceania");
    }

    @Test(description = "Verify population is non-negative integer")
    public void testPopulation_IsNonNegative() {
        Response response = client.getCountryByName(getCountryName("Germany"));

        int population = response.jsonPath().getInt("[0].population");
        Assert.assertTrue(population >= 0, "Population should be non-negative");
    }
}
