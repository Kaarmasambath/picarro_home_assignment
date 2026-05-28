package com.qa.report;

import com.aventstack.extentreports.ExtentTest;

/**
 * Thread-safe manager for ExtentTest instances.
 * Uses ThreadLocal to support parallel test execution.
 *
 * @author Karthikeyan Sambath
 * @version 1.0
 */
public class ExtentManager {

    /**
     * Private constructor to prevent instantiation.
     * This is a utility class with only static methods.
     */
    private ExtentManager() {
    }

    /** ThreadLocal storage for ExtentTest instances */
    private static ThreadLocal<ExtentTest> extTest = new ThreadLocal<>();

    /**
     * Gets the ExtentTest instance for the current thread.
     *
     * @return ExtentTest instance for current thread
     */
    public static ExtentTest getExtent() {
        return extTest.get();
    }

    /**
     * Sets the ExtentTest instance for the current thread.
     *
     * @param test ExtentTest instance to set
     */
    public static void setExtentTest(ExtentTest test) {
        extTest.set(test);
    }

    /**
     * Removes the ExtentTest instance from current thread.
     * Should be called after test completion to prevent memory leaks.
     */
    public static void unLoad() {
        extTest.remove();
    }
}
