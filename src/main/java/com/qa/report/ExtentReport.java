package com.qa.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private static final String EXTENT_REPORT_PATH = System.getProperty("user.dir") + "/ExtentReport/ExtentReportResults.html";

    private ExtentReport() {
    }

    private static ExtentReports extent;

    public static void initReports() {
        if (Objects.isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter(EXTENT_REPORT_PATH);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("QA Automation Test Report");
            extent.attachReporter(spark);
        }
    }

    public static void flushReport() throws IOException {
        if (extent != null) {
            extent.flush();
            ExtentManager.unLoad();
            Desktop.getDesktop().browse(new File(EXTENT_REPORT_PATH).toURI());
        }
    }

    public static void createTest(String testCaseName, String deviceName, String authorName) {
        ExtentManager.setExtentTest(extent.createTest(testCaseName).assignAuthor(authorName).assignDevice(deviceName));
    }
}
