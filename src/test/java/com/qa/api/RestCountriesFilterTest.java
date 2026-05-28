package com.qa.api;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.qa.api.TestDataProvider.*;


public class RestCountriesFilterTest {

    private RestCountriesClient client;

    @BeforeClass
    public void setup() {
        client = new RestCountriesClient();
    }

    @Test(description = "Verify GET /region/europe returns European countries")
    public void testGetCountriesByRegion_Europe_ReturnsArray() {
        Response response = client.getCountriesByRegion(getRegion("Europe").toLowerCase());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());

        List<String> regions = response.jsonPath().getList("region");
        for (String region : regions) {
            Assert.assertEquals(region, getRegion("Europe"));
        }
    }

    @Test(description = "Verify GET /region/americas returns American countries")
    public void testGetCountriesByRegion_Americas_ReturnsArray() {
        Response response = client.getCountriesByRegion(getRegion("Americas").toLowerCase());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /region/asia returns Asian countries")
    public void testGetCountriesByRegion_Asia_ReturnsArray() {
        Response response = client.getCountriesByRegion(getRegion("Asia").toLowerCase());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /region/{invalid} returns 404")
    public void testGetCountriesByRegion_InvalidRegion_Returns404() {
        Response response = client.getCountriesByRegion(getRegion("invalid"));

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /currency/usd returns countries using USD")
    public void testGetCountriesByCurrency_USD_ReturnsArray() {
        Response response = client.getCountriesByCurrency(getCurrencyCode("usd"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());

        List<String> countryNames = response.jsonPath().getList("name.common");
        Assert.assertTrue(countryNames.contains(getCurrencyExpectedCountry("usd")));
    }

    @Test(description = "Verify GET /currency/eur returns countries using Euro")
    public void testGetCountriesByCurrency_EUR_ReturnsArray() {
        Response response = client.getCountriesByCurrency(getCurrencyCode("eur"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /currency/jpy returns countries using JPY")
    public void testGetCountriesByCurrency_JPY_ReturnsArray() {
        Response response = client.getCountriesByCurrency(getCurrencyCode("jpy"));

        Assert.assertEquals(response.getStatusCode(), 200);

        List<String> countryNames = response.jsonPath().getList("name.common");
        Assert.assertTrue(countryNames.contains(getCurrencyExpectedCountry("jpy")));
    }

    @Test(description = "Verify GET /currency/{invalid} returns 404")
    public void testGetCountriesByCurrency_InvalidCurrency_Returns404() {
        Response response = client.getCountriesByCurrency(getCurrencyCode("invalid"));

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /lang/spanish returns Spanish-speaking countries")
    public void testGetCountriesByLanguage_Spanish_ReturnsArray() {
        Response response = client.getCountriesByLanguage(getLanguageCode("Spanish"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /lang/english returns English-speaking countries")
    public void testGetCountriesByLanguage_English_ReturnsArray() {
        Response response = client.getCountriesByLanguage(getLanguageCode("English"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(response.jsonPath().getList("$").isEmpty());
    }

    @Test(description = "Verify GET /lang/french returns French-speaking countries")
    public void testGetCountriesByLanguage_French_ReturnsArray() {
        Response response = client.getCountriesByLanguage(getLanguageCode("french"));

        Assert.assertEquals(response.getStatusCode(), 200);

        List<String> countryNames = response.jsonPath().getList("name.common");
        Assert.assertTrue(countryNames.contains(getLanguageExpectedCountry("french")));
    }

    @Test(description = "Verify GET /lang/{invalid} returns 404")
    public void testGetCountriesByLanguage_InvalidLanguage_Returns404() {
        Response response = client.getCountriesByLanguage(getLanguageCode("invalid"));

        Assert.assertEquals(response.getStatusCode(), 404);
    }

    @Test(description = "Verify GET /capital/berlin returns Germany")
    public void testGetCountriesByCapital_Berlin_ReturnsGermany() {
        Response response = client.getCountriesByCapital(getCapitalName("Berlin"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCapitalExpectedCountry("Berlin"));
    }

    @Test(description = "Verify GET /capital/paris returns France")
    public void testGetCountriesByCapital_Paris_ReturnsFrance() {
        Response response = client.getCountriesByCapital(getCapitalName("Paris"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("[0].name.common"), getCapitalExpectedCountry("Paris"));
    }

    @Test(description = "Verify GET /capital/{invalid} returns 404")
    public void testGetCountriesByCapital_InvalidCapital_Returns404() {
        Response response = client.getCountriesByCapital(getCapitalName("invalid"));

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
