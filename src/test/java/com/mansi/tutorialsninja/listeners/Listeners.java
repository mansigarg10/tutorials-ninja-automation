package com.mansi.tutorialsninja.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mansi.tutorialsninja.utils.ExtentReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

/**
 * This class implements ITestListener to capture test events and log them in ExtentReports.
 * It logs the start, success, failure, skip, and finish events of tests.
 *
 * @author Mansi Garg
 */
public class Listeners extends BaseTest implements ITestListener {

    private final Logger LOG = LogManager.getLogger(BaseTest.class);

    private final ThreadLocal<ExtentTest> eTest = new ThreadLocal<>();
    private final ExtentReports extent = ExtentReporter.getExtentReport();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getName() + " is started");
        eTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {

        eTest.get().log(Status.PASS, result.getName() + " is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String filePath;
        eTest.get().fail(result.getThrowable());
        try {
             driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
        } catch (Exception ex) {
            LOG.error("Exception occurred while getting driver instance: " + ex.getMessage());
        }
        try {
            filePath = getScreenshot(result.getMethod().getMethodName(), driver);
        } catch (IOException e) {
            LOG.error("Exception occurred while capturing screenshot: " + e.getMessage());
            throw new RuntimeException(e);
        }
        eTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        eTest.get().log(Status.SKIP, result.getName() + " is skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        eTest.get().log(Status.INFO, " test is finished");
        extent.flush();
    }

}
