package com.qa.api;

import com.qa.config.ReadConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

/**
 * REST API client for REST Countries API.
 * Provides methods to interact with various endpoints of the REST Countries v3.1 API.
 *
 * @author Karthikeyan Sambath
 * @version 1.0
 * @see <a href="https://restcountries.com">REST Countries API Documentation</a>
 */
public class RestCountriesClient {

    /**
     * Constructor initializes the base URI from configuration.
     */
    public RestCountriesClient() {
        RestAssured.baseURI = ReadConfig.getRestCountriesBaseUrl();
    }

    /**
     * Gets country information by name (partial match supported).
     *
     * @param name Country name or partial name to search
     * @return Response containing matching countries
     */
    public Response getCountryByName(String name) {
        return given()
                .contentType("application/json")
                .when()
                .get("/name/" + name);
    }

    /**
     * Gets country information by exact full name match.
     *
     * @param name Exact full country name
     * @return Response containing the matching country
     */
    public Response getCountryByFullName(String name) {
        return given()
                .contentType("application/json")
                .queryParam("fullText", true)
                .when()
                .get("/name/" + name);
    }

    /**
     * Gets country information by alpha-2 or alpha-3 code.
     *
     * @param code Country code (e.g., "US", "USA", "DE", "DEU")
     * @return Response containing the country
     */
    public Response getCountryByCode(String code) {
        return given()
                .contentType("application/json")
                .when()
                .get("/alpha/" + code);
    }

    /**
     * Gets multiple countries by their codes.
     *
     * @param codes Variable number of country codes
     * @return Response containing all matching countries
     */
    public Response getCountriesByCodes(String... codes) {
        return given()
                .contentType("application/json")
                .queryParam("codes", String.join(",", codes))
                .when()
                .get("/alpha");
    }

    /**
     * Gets all countries with specified fields only.
     * If no fields specified, returns 400 (API requirement).
     *
     * @param fields Fields to include in response (e.g., "name", "capital", "currencies")
     * @return Response containing all countries with specified fields
     */
    public Response getAllCountries(String... fields) {
        if (fields == null || fields.length == 0) {
            return given()
                    .contentType("application/json")
                    .when()
                    .get("/all");
        }
        return given()
                .contentType("application/json")
                .queryParam("fields", String.join(",", fields))
                .when()
                .get("/all");
    }

    /**
     * Gets countries by region.
     *
     * @param region Region name (e.g., "europe", "asia", "americas")
     * @return Response containing countries in the region
     */
    public Response getCountriesByRegion(String region) {
        return given()
                .contentType("application/json")
                .when()
                .get("/region/" + region);
    }

    /**
     * Gets countries by currency code.
     *
     * @param currency Currency code (e.g., "usd", "eur", "jpy")
     * @return Response containing countries using the currency
     */
    public Response getCountriesByCurrency(String currency) {
        return given()
                .contentType("application/json")
                .when()
                .get("/currency/" + currency);
    }

    /**
     * Gets countries by language.
     *
     * @param language Language name (e.g., "spanish", "english", "french")
     * @return Response containing countries speaking the language
     */
    public Response getCountriesByLanguage(String language) {
        return given()
                .contentType("application/json")
                .when()
                .get("/lang/" + language);
    }

    /**
     * Gets countries by capital city.
     *
     * @param capital Capital city name (e.g., "berlin", "paris", "tokyo")
     * @return Response containing countries with the capital
     */
    public Response getCountriesByCapital(String capital) {
        return given()
                .contentType("application/json")
                .when()
                .get("/capital/" + capital);
    }
}
