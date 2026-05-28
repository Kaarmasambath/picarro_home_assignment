package com.qa.utilities;

import com.qa.config.ReadConfig;
import com.qa.ui.factory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class ActionUtilities {

    private static FluentWait<WebDriver> getFluentWait() {
        return new FluentWait<>(DriverFactory.getDriver())
                .pollingEvery(Duration.ofSeconds(1))
                .withTimeout(Duration.ofSeconds(ReadConfig.getExplicitWaitSeconds()))
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    public static void waitForElementVisible(By locator) {
        getFluentWait()
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    return element.isDisplayed() ? element : null;
                });
    }

    public static void waitForElementClickable(By locator) {
        getFluentWait()
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    return (element.isDisplayed() && element.isEnabled()) ? element : null;
                });
    }

    public static void click(By locator) {
        getFluentWait()
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    if (element.isDisplayed() && element.isEnabled()) {
                        element.click();
                        return true;
                    }
                    return false;
                });
    }

    public static void sendKey(By locator, String text) {
        getFluentWait()
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    if (element.isDisplayed()) {
                        element.clear();
                        element.sendKeys(text);
                        return true;
                    }
                    return false;
                });
    }

    public static String getText(By locator) {
        return getFluentWait()
                .until(driver -> {
                    WebElement element = driver.findElement(locator);
                    if (element.isDisplayed()) {
                        return element.getText();
                    }
                    return null;
                });
    }

    public static boolean isDisplayed(By locator) {
        try {
            return getFluentWait()
                    .until(driver -> {
                        WebElement element = driver.findElement(locator);
                        return element.isDisplayed();
                    });
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitForPageLoad() {
        getFluentWait()
                .until(driver -> {
                    try {
                        driver.findElement(By.cssSelector(".oxd-loading-spinner"));
                        return false;
                    } catch (NoSuchElementException e) {
                        return true;
                    }
                });
    }

    public static void waitForUrlContains(String urlFragment) {
        getFluentWait()
                .until(driver ->
                        driver.getCurrentUrl().contains(urlFragment));
    }

    public static void waitForElementInvisible(By locator) {
        getFluentWait()
                .until(driver -> {
                    try {
                        WebElement element = driver.findElement(locator);
                        return !element.isDisplayed();
                    } catch (NoSuchElementException | StaleElementReferenceException e) {
                        return true;
                    }
                });
    }
}
