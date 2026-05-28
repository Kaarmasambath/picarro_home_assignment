package com.qa.report;

import com.aventstack.extentreports.MediaEntityBuilder;

/**
 * Logger utility class for Extent Reports.
 * Provides methods to log test steps with pass/fail/skip/info status.
 *
 * @author Karthikeyan Sambath
 * @version 1.0
 */
public final class ExtentLogger {

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private ExtentLogger() {
    }

    /**
     * Logs a passed step in the report.
     *
     * @param message Message to log
     */
    public static void pass(String message) {
        ExtentManager.getExtent().pass(message);
    }

    /**
     * Logs a failed step in the report.
     *
     * @param message Message to log
     */
    public static void fail(String message) {
        ExtentManager.getExtent().fail(message);
    }

    /**
     * Logs a skipped step in the report.
     *
     * @param message Message to log
     */
    public static void skip(String message) {
        ExtentManager.getExtent().skip(message);
    }

    /**
     * Logs an info step in the report.
     *
     * @param message Message to log
     */
    public static void info(String message) {
        ExtentManager.getExtent().info(message);
    }

    /**
     * Logs a passed step with screenshot in the report.
     *
     * @param message          Message to log
     * @param base64Screenshot Base64 encoded screenshot string
     */
    public static void pass(String message, String base64Screenshot) {
        ExtentManager.getExtent().pass(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }

    /**
     * Logs a failed step with screenshot in the report.
     *
     * @param message          Message to log
     * @param base64Screenshot Base64 encoded screenshot string
     */
    public static void fail(String message, String base64Screenshot) {
        ExtentManager.getExtent().fail(message,
                MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    }
}
