package com.qa.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class TestDataProvider {

    private static JsonNode testData;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        loadTestData();
    }

    private static void loadTestData() {
        try (InputStream input = TestDataProvider.class.getClassLoader()
                .getResourceAsStream("testdata/api-testdata.json")) {
            if (input != null) {
                testData = mapper.readTree(input);
            } else {
                throw new RuntimeException("api-testdata.json not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load api-testdata.json", e);
        }
    }

    public static String getCountryName(String key) {
        return testData.path("countries").path(key).path("name").asText();
    }

    public static String getCountryCapital(String key) {
        return testData.path("countries").path(key).path("capital").asText();
    }

    public static String getCountryRegion(String key) {
        return testData.path("countries").path(key).path("region").asText();
    }

    public static String getAlpha2Code(String key) {
        return testData.path("countries").path(key).path("alpha2Code").asText();
    }

    public static String getAlpha3Code(String key) {
        return testData.path("countries").path(key).path("alpha3Code").asText();
    }

    public static String getRegion(String key) {
        return testData.path("regions").path(key).asText();
    }

    public static String getCurrencyCode(String key) {
        return testData.path("currencies").path(key).path("code").asText();
    }

    public static String getCurrencyExpectedCountry(String key) {
        return testData.path("currencies").path(key).path("expectedCountry").asText();
    }

    public static String getLanguageCode(String key) {
        return testData.path("languages").path(key).path("code").asText();
    }

    public static String getLanguageExpectedCountry(String key) {
        return testData.path("languages").path(key).path("expectedCountry").asText();
    }

    public static String getCapitalName(String key) {
        return testData.path("capitals").path(key).path("name").asText();
    }

    public static String getCapitalExpectedCountry(String key) {
        return testData.path("capitals").path(key).path("expectedCountry").asText();
    }

    public static String getInvalidCountryName() {
        return testData.path("invalidData").path("countryName").asText();
    }

    public static String getInvalidCountryCode() {
        return testData.path("invalidData").path("countryCode").asText();
    }

    public static String[] getFields() {
        JsonNode fields = testData.path("fields");
        String[] result = new String[fields.size()];
        for (int i = 0; i < fields.size(); i++) {
            result[i] = fields.get(i).asText();
        }
        return result;
    }
}
