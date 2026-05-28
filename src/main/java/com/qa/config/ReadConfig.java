package com.qa.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfig {

    public static final Properties properties = new Properties();

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream input = ReadConfig.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config.properties not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String getRestCountriesBaseUrl() {
        String baseUrl = getProperty("api.rest-countries.baseurl");
        String version = getProperty("api.rest-countries.version");
        return baseUrl + "/" + version;
    }

    public static String getOrangeHrmUsername() {
        return getProperty("ui.orangehrm.username");
    }

    public static String getOrangeHrmPassword() {
        return getProperty("ui.orangehrm.password");
    }

    public static String getOrangeHrmBaseUrl() {
        return getProperty("ui.orangehrm.baseurl");
    }

    public static String getBrowser() {
        return getProperty("browser.name");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("browser.headless"));
    }

    public static int getExplicitWaitSeconds() {
        return getIntProperty("timeout.explicit", 15);
    }

    public static int getImplicitWaitSeconds() {
        return getIntProperty("timeout.implicit", 10);
    }

    public static int getPageLoadTimeoutSeconds() {
        return getIntProperty("timeout.page-load", 30);
    }

    private static String getProperty(String key) {
        return properties.getProperty(key);
    }

    private static int getIntProperty(String key, int defaultValue) {
        String value = properties.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }
}
