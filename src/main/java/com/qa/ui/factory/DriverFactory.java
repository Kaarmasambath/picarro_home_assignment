package com.qa.ui.factory;

import com.qa.config.ReadConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    public static final ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public WebDriver init(String browser, String url) {
        switch (browser.toLowerCase()) {
            case "chrome":
                tlDriver.set(new ChromeDriver());
                break;
            case "edge":
                tlDriver.set(new EdgeDriver());
                break;
            case "firefox":
                tlDriver.set(new FirefoxDriver());
                break;
            default:
                System.out.println("Browser not supported. Please check the configuration file.");
                break;
        }
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(ReadConfig.getPageLoadTimeoutSeconds()));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(ReadConfig.getImplicitWaitSeconds()));
        getDriver().get(url);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }

    public static void quitDriver() {
        if (tlDriver.get() != null) {
            tlDriver.get().quit();
            tlDriver.remove();
        }
    }
}
