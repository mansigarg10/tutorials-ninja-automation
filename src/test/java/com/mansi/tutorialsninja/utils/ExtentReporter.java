package com.mansi.tutorialsninja.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.io.File;

/**
 * This class is used to generate the Extent Report.
 *
 * @author Mansi Garg
 */
public class ExtentReporter {

    public static ExtentReports getExtentReport() {
        String path = TutorialsNinjaTestConstants.EXTENT_REPORT_PATH;
        File file = new File(path);
        ExtentSparkReporter reporter = new ExtentSparkReporter(file);
        reporter.config().setDocumentTitle(TutorialsNinjaTestConstants.REPORT_TITLE);
        reporter.config().setReportName(TutorialsNinjaTestConstants.REPORT_NAME);
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo(TutorialsNinjaTestConstants.K_CREATED_BY, TutorialsNinjaTestConstants.V_CREATED_BY);
        extent.setSystemInfo(TutorialsNinjaTestConstants.K_OPERATING_SYS, TutorialsNinjaTestConstants.V_OPERATING_SYS);
        extent.setSystemInfo(TutorialsNinjaTestConstants.K_JAVA_VERSION, TutorialsNinjaTestConstants.V_JAVA_VERSION);
        return extent;
    }

}
