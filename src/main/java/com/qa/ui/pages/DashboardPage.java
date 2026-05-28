package com.qa.ui.pages;

import com.qa.ui.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.qa.utilities.ActionUtilities.*;

public class DashboardPage {

    private final By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private final By userDropdownName = By.cssSelector(".oxd-userdropdown-name");
    private final By logoutLink = By.linkText("Logout");
    private final By menuItems = By.cssSelector(".oxd-main-menu-item");
    private final By pimLink = By.xpath("//span[text()='PIM']");
    private final By leaveLink = By.xpath("//span[text()='Leave']");
    private final By adminLink = By.xpath("//span[text()='Admin']");
    private final By recruitmentLink = By.xpath("//span[text()='Recruitment']");

    public boolean isLoggedIn() {
        waitForUrlContains("dashboard");
        return isDisplayed(userDropdown);
    }

    public String getLoggedInUserName() {
        return getText(userDropdownName);
    }

    public LoginPage logoutFunctionality() {
        click(userDropdown);
        waitForElementClickable(logoutLink);
        click(logoutLink);
        waitForPageLoad();
        waitForUrlContains("login");
        return new LoginPage();
    }

    public PimPage navigateToPim() {
        click(pimLink);
        waitForPageLoad();
        waitForUrlContains("pim");
        return new PimPage();
    }

    public void navigateToLeave() {
        click(leaveLink);
        waitForPageLoad();
        waitForUrlContains("leave");
    }

    public void navigateToAdmin() {
        click(adminLink);
        waitForPageLoad();
        waitForUrlContains("admin");
    }

    public void navigateToRecruitment() {
        click(recruitmentLink);
        waitForPageLoad();
        waitForUrlContains("recruitment");
    }

    public boolean isMenuItemVisible(String menuItemName) {
        List<WebElement> items = DriverFactory.getDriver().findElements(menuItems);
        for (WebElement menuItem : items) {
            if (menuItem.getText().equalsIgnoreCase(menuItemName)) {
                return menuItem.isDisplayed();
            }
        }
        return false;
    }

    public List<WebElement> getAllMenuItems() {
        return DriverFactory.getDriver().findElements(menuItems);
    }

    public boolean isDashboardPage() {
        return DriverFactory.getDriver().getCurrentUrl().contains("dashboard");
    }

    public String getCurrentUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
