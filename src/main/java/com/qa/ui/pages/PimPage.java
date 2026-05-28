package com.qa.ui.pages;

import com.qa.ui.factory.DriverFactory;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.qa.utilities.ActionUtilities.*;

public class PimPage {

    private final By firstNameInput = By.name("firstName");
    private final By lastNameInput = By.name("lastName");
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By cancelButton = By.cssSelector("button.oxd-button--ghost");
    private final By validationErrors = By.cssSelector(".oxd-input-field-error-message");
    private final By addButton = By.xpath("//button[contains(.,'Add')]");
    private final By dashboardLink = By.xpath("//span[text()='Dashboard']");

    public boolean isPimPage() {
        return DriverFactory.getDriver().getCurrentUrl().contains("pim");
    }

    public PimPage clickAddEmployee() {
        click(addButton);
        waitForPageLoad();
        waitForElementVisible(firstNameInput);
        return this;
    }

    public boolean isAddEmployeeFormDisplayed() {
        return isDisplayed(firstNameInput) && isDisplayed(lastNameInput);
    }

    public PimPage enterFirstName(String firstName) {
        sendKey(firstNameInput, firstName);
        return this;
    }

    public PimPage enterLastName(String lastName) {
        sendKey(lastNameInput, lastName);
        return this;
    }

    public PimPage fillEmployeeForm(String firstName, String lastName) {
        enterFirstName(firstName);
        enterLastName(lastName);
        return this;
    }

    public PimPage clickSave() {
        click(saveButton);
        waitForPageLoad();
        return this;
    }

    public PimPage clickCancel() {
        click(cancelButton);
        waitForPageLoad();
        return this;
    }

    public List<String> getValidationErrors() {
        try {
            return DriverFactory.getDriver().findElements(validationErrors).stream()
                    .map(element -> element.getText())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }

    public boolean hasValidationErrors() {
        return !getValidationErrors().isEmpty();
    }

    public PimPage submitEmptyForm() {
        DriverFactory.getDriver().findElement(firstNameInput).clear();
        DriverFactory.getDriver().findElement(lastNameInput).clear();
        clickSave();
        return this;
    }

    public boolean isFirstNameFieldDisplayed() {
        return isDisplayed(firstNameInput);
    }

    public boolean isLastNameFieldDisplayed() {
        return isDisplayed(lastNameInput);
    }

    public boolean isSaveButtonDisplayed() {
        return isDisplayed(saveButton);
    }

    public DashboardPage goBackToDashboard() {
        click(dashboardLink);
        waitForPageLoad();
        waitForUrlContains("dashboard");
        return new DashboardPage();
    }

    public String getCurrentUrl() {
        return DriverFactory.getDriver().getCurrentUrl();
    }
}
