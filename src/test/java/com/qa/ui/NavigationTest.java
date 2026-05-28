package com.qa.ui;

import com.qa.base.BaseTest;
import com.qa.ui.pages.DashboardPage;
import com.qa.ui.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class NavigationTest extends BaseTest {
    DashboardPage dashboard;
    LoginPage loginPage;

    @Test(description = "Verify PIM menu item is visible and clickable")
    public void testNavigateToPim_FromDashboard() {

        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isMenuItemVisible("PIM"), "PIM menu should be visible");

        dashboard.navigateToPim();

        Assert.assertTrue(dashboard.getCurrentUrl().contains("pim"), "Should navigate to PIM");
    }

    @Test(description = "Verify Leave menu item is visible and clickable")
    public void testNavigateToLeave_FromDashboard() {
        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isMenuItemVisible("Leave"), "Leave menu should be visible");

        dashboard.navigateToLeave();

        Assert.assertTrue(dashboard.getCurrentUrl().contains("leave"), "Should navigate to Leave");
    }

    @Test(description = "Verify Admin menu item is visible and clickable")
    public void testNavigateToAdmin_FromDashboard() {
        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isMenuItemVisible("Admin"), "Admin menu should be visible");

        dashboard.navigateToAdmin();

        Assert.assertTrue(dashboard.getCurrentUrl().contains("admin"), "Should navigate to Admin");
    }

    @Test(description = "Verify Recruitment menu item is visible and clickable")
    public void testNavigateToRecruitment_FromDashboard() {
        dashboard = new LoginPage().employeeLogin();

        Assert.assertTrue(dashboard.isMenuItemVisible("Recruitment"), "Recruitment menu should be visible");

        dashboard.navigateToRecruitment();

        Assert.assertTrue(dashboard.getCurrentUrl().contains("recruitment"), "Should navigate to Recruitment");
    }

    @Test(description = "Verify main navigation has multiple menu items")
    public void testMainNavigation_HasMultipleMenuItems() {
        dashboard = new LoginPage().employeeLogin();

        int menuItemCount = dashboard.getAllMenuItems().size();

        Assert.assertTrue(menuItemCount > 5, "Navigation should have multiple menu items");
    }

    @Test(description = "Verify user can logout from dashboard")
    public void testLogout_FromDashboard() {
        loginPage = new LoginPage()
                .employeeLogin()
                .logoutFunctionality();

        Assert.assertTrue(loginPage.getCurrentUrl().contains("login"), "Should return to login page");
    }
}
