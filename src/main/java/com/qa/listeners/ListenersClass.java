package com.qa.listeners;

import com.qa.report.ExtentLogger;
import com.qa.report.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG Listener class for test execution events.
 * Integrates with Extent Reports for test reporting.
 * Implements ISuiteListener for suite-level events and ITestListener for test-level events.
 *
 * @author Karthikeyan Sambath
 * @version 1.0
 */
public class ListenersClass implements ITestListener, ISuiteListener {

    /**
     * Called when test suite starts.
     * Initializes Extent Reports.
     *
     * @param suite ISuite instance
     */
    @Override
    public void onStart(ISuite suite) {
        ExtentReport.initReports();
    }

    /**
     * Called when test suite finishes.
     * Flushes Extent Reports and generates the report file.
     *
     * @param suite ISuite instance
     */
    @Override
    public void onFinish(ISuite suite) {
        try {
            ExtentReport.flushReport();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Called when a test method starts.
     * Creates a new test entry in Extent Reports.
     *
     * @param result ITestResult containing test method details
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getMethod().getMethodName(), "Windows", "Karthikeyan Sambath");
    }

    /**
     * Called when a test method passes.
     * Logs pass status in Extent Reports.
     *
     * @param result ITestResult containing test method details
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getMethod().getMethodName() + " is passed");
    }

    /**
     * Called when a test method fails.
     * Logs failure status and exception details in Extent Reports.
     *
     * @param result ITestResult containing test method and failure details
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getMethod().getMethodName() + " is failed");
        if (result.getThrowable() != null) {
            ExtentLogger.fail(result.getThrowable().getMessage());
        }
    }

    /**
     * Called when a test method is skipped.
     * Logs skip status in Extent Reports.
     *
     * @param result ITestResult containing test method details
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getMethod().getMethodName() + " is skipped");
    }
}
