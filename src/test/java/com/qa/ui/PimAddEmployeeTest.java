package com.qa.ui;

import com.github.javafaker.Faker;
import com.qa.base.BaseTest;
import com.qa.ui.pages.DashboardPage;
import com.qa.ui.pages.LoginPage;
import com.qa.ui.pages.PimPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PimAddEmployeeTest extends BaseTest {
    PimPage pimPage;
    Faker faker = new Faker();

    @Test(description = "Verify Add Employee form has required fields")
    public void testAddEmployeeForm_HasRequiredFields() {
        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee();

        Assert.assertTrue(pimPage.isAddEmployeeFormDisplayed(), "Add Employee form should be displayed");
        Assert.assertTrue(pimPage.isFirstNameFieldDisplayed(), "First Name field should be present");
        Assert.assertTrue(pimPage.isLastNameFieldDisplayed(), "Last Name field should be present");
        Assert.assertTrue(pimPage.isSaveButtonDisplayed(), "Save button should be present");
    }

    @Test(description = "Verify PIM page loads correctly")
    public void testPimPage_LoadsCorrectly() {
        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim();

        Assert.assertTrue(pimPage.isPimPage(), "Should be on PIM page");
    }

    @Test(description = "Verify adding employee with valid data shows success")
    public void testAddEmployee_ValidData_ShowsSuccess() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .fillEmployeeForm(firstName, lastName)
                .clickSave();

        Assert.assertTrue(pimPage.getCurrentUrl().contains("pim"), "Should be on PIM page after save");
    }

    @Test(description = "Verify added employee - Data Consistency Validation")
    public void testAddEmployee_DataConsistency_EmployeeAppearsInSystem() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        DashboardPage dashboard = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .fillEmployeeForm(firstName, lastName)
                .clickSave()
                .goBackToDashboard();

        PimPage pimPage = dashboard.navigateToPim();

        Assert.assertTrue(pimPage.isPimPage(), "Should be on PIM page");
    }

    @Test(description = "Verify missing first name shows validation error")
    public void testAddEmployee_MissingFirstName_ShowsValidation() {
        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .enterLastName(faker.name().lastName())
                .clickSave();

        List<String> errors = pimPage.getValidationErrors();
        Assert.assertFalse(errors.isEmpty(), "Validation errors should be displayed");
    }

    @Test(description = "Verify missing last name shows validation error")
    public void testAddEmployee_MissingLastName_ShowsValidation() {
        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .enterFirstName(faker.name().firstName())
                .clickSave();

        List<String> errors = pimPage.getValidationErrors();
        Assert.assertFalse(errors.isEmpty(), "Validation errors should be displayed");
    }

    @Test(description = "Verify empty form shows validation errors")
    public void testAddEmployee_EmptyForm_ShowsValidation() {
        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .submitEmptyForm();

        Assert.assertTrue(pimPage.hasValidationErrors(), "Validation errors should be displayed");
    }

    @Test(description = "Verify cancel button returns to employee list")
    public void testAddEmployee_Cancel_ReturnsToList() {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        pimPage = new LoginPage()
                .employeeLogin()
                .navigateToPim()
                .clickAddEmployee()
                .fillEmployeeForm(firstName, lastName)
                .clickCancel();

        Assert.assertTrue(pimPage.getCurrentUrl().contains("pim"), "Cancel should return to PIM");
    }
}
