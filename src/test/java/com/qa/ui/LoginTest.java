package com.qa.ui;

import com.qa.base.BaseTest;
import com.qa.config.ReadConfig;
import com.qa.ui.pages.DashboardPage;
import com.qa.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {
    LoginPage loginPage;
    DashboardPage dashboard;

    @Test(description = "Verify valid credentials navigate to dashboard")
    public void testValidLogin_NavigatesToDashboard() {
        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isLoggedIn(), "User should be logged in");
        Assert.assertTrue(dashboard.isDashboardPage(), "Should be on dashboard page");
    }

    @Test(description = "Verify logged-in user sees their username")
    public void testValidLogin_DisplaysUserInfo() {
        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isLoggedIn());
        String userName = dashboard.getLoggedInUserName();
        Assert.assertNotNull(userName, "Username should not be null");
    }

    @Test(description = "Verify login and logout flow")
    public void testLoginAndLogout() {
        new LoginPage()
                .employeeLogin()
                .logoutFunctionality();

        Assert.assertTrue(new LoginPage().isLoginPage(), "Should be back on login page");
    }

    @Test(description = "Verify invalid credentials show error message")
    public void testInvalidLogin_ShowsError() {
        loginPage = new LoginPage().invalidLogin("invalidUser", "invalidPass");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }

    @Test(description = "Verify invalid login remains on login page")
    public void testInvalidLogin_RemainsOnLoginPage() {
        loginPage = new LoginPage().invalidLogin("wrongUser", "wrongPass");

        Assert.assertTrue(loginPage.isLoginPage(), "Should remain on login page");
    }

    @Test(description = "Verify empty credentials show error")
    public void testEmptyCredentials_ShowsError() {
        loginPage = new LoginPage().invalidLogin("", "");

        Assert.assertTrue(loginPage.isLoginPage(), "Should remain on login page");
    }

    @Test(description = "Verify valid username with wrong password shows error")
    public void testValidUsernameWrongPassword_ShowsError() {
        loginPage = new LoginPage().invalidLogin(ReadConfig.getOrangeHrmUsername(), "wrongPassword");

        Assert.assertTrue(loginPage.isErrorMessageDisplayed(), "Error message should be displayed");
    }

    @Test(description = "Verify login page has all required elements")
    public void testLoginPage_HasRequiredElements() {
         loginPage = new LoginPage();

        Assert.assertTrue(loginPage.isUsernameFieldDisplayed(), "Username field should be displayed");
        Assert.assertTrue(loginPage.isPasswordFieldDisplayed(), "Password field should be displayed");
        Assert.assertTrue(loginPage.isLoginButtonDisplayed(), "Login button should be displayed");
    }
}
