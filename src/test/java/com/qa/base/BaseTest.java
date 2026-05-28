package com.qa.base;

import com.qa.config.ReadConfig;
import com.qa.ui.factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class for all UI test classes.
 * Provides WebDriver initialization and teardown functionality.
 * All UI test classes should extend this class to inherit browser setup/cleanup.
 *
 * @author Karthikeyan Sambath
 * @version 1.0
 */
public class BaseTest {

    /** Factory instance for WebDriver creation */
    protected DriverFactory driverFactory;

    /** WebDriver instance for browser automation */
    protected WebDriver driver;

    /**
     * Initializes the WebDriver before each test method.
     * Reads browser configuration from config.properties and launches the browser.
     * Navigates to the OrangeHRM base URL.
     */

    @BeforeMethod(alwaysRun = true)
    public void init() {
        driverFactory = new DriverFactory();
        String browser = ReadConfig.getBrowser();
        String url = ReadConfig.getOrangeHrmBaseUrl();
        driver = driverFactory.init(browser, url);
    }

    /**
     * Cleans up WebDriver resources after each test method.
     * Quits the browser and removes the WebDriver instance from ThreadLocal.
     */
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
