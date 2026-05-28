package com.qa.ui.pages;

import com.qa.config.ReadConfig;
import com.qa.ui.factory.DriverFactory;
import org.openqa.selenium.By;

import static com.qa.utilities.ActionUtilities.*;

public class LoginPage {

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.cssSelector("button[type='submit']");
    private final By errorMessage = By.cssSelector(".oxd-alert-content--error");
    private final By loginTitle = By.cssSelector(".orangehrm-login-title");

    public DashboardPage employeeLogin() {
        sendKey(usernameField, ReadConfig.getOrangeHrmUsername());
        sendKey(passwordField, ReadConfig.getOrangeHrmPassword());
        click(loginButton);
        waitForPageLoad();
        waitForUrlContains("dashboard");
        return new DashboardPage();
    }

    public LoginPage invalidLogin(String username, String password) {
        sendKey(usernameField, username);
        sendKey(passwordField, password);
        click(loginButton);
        waitForPageLoad();
        return this;
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isLoginPage() {
        return isDisplayed(loginTitle);
    }

    public boolean isUsernameFieldDisplayed() {
        return isDisplayed(usernameField);
    }

    public boolean isPasswordFieldDisplayed() {
        return isDisplayed(passwordField);
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    public String getCurrentUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
