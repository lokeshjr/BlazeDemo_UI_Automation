package com.blazedemo.reporting;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.xml.XmlTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.blazedemo.action.UtilityActions;
import com.blazedemo.base.DriverFactory;
import com.blazedemo.action.PropertyDataHandler;
import com.blazedemo.utils.AutomationConstants;

public class AutomationReport implements ITestListener {
	int successCount = 0, failureCount = 0, skippedCount = 0, testCount = 0;
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	String reportPath = System.getProperty("user.dir") + "/Reports/";

	/**
	 * Set up the execution report
	 * 
	 * @param testContext
	 * @throws Exception
	 */
	public void onStart(ITestContext testContext) {
		try {
			String testEnvironment = new PropertyDataHandler().getProperty(AutomationConstants.AUTOMATION_TEST_CONFIG,
					AutomationConstants.TEST_ENVIRONMENT);
			sparkReporter = new ExtentSparkReporter(reportPath);
			XmlTest test = testContext.getCurrentXmlTest();
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Operating System", System.getProperty("os.name"));
			extent.setSystemInfo("Browser Name", test.getParameter("browserName").toString());
			if (testEnvironment.equalsIgnoreCase("")) {
				testEnvironment = "QA";
			}
			extent.setSystemInfo("Test Environment", testEnvironment);
			extent.setSystemInfo("Host Name", InetAddress.getLocalHost().getHostName());
			extent.setSystemInfo("IP address", InetAddress.getLocalHost().getHostAddress());

			sparkReporter.config().setDocumentTitle("Test Automation");
			sparkReporter.config().setReportName("Automation Execution Report");
			sparkReporter.config().setTheme(Theme.DARK);
			sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
		} catch (Exception lException) {
			lException.printStackTrace();
		}
	}

	/**
	 * Collect the test names
	 * 
	 * @param result
	 */
	public void onTestStart(ITestResult result) {
		try {
			String testName = result.getMethod().getMethodName();
			test = extent.createTest(testName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Track the test steps
	 *
	 * @param stepInformation
	 */
	public void trackSteps(String stepInformation) {
		try {
			test.log(Status.INFO, MarkupHelper.createLabel(stepInformation, ExtentColor.PURPLE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Track the test category
	 * 
	 * @param categoryName
	 */
	public void assignCategory(String categoryName) {
		try {
			test.assignCategory(categoryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the pass result of the execution
	 * 
	 * @param result
	 * @throws IOException
	 */
	public void onTestSuccess(ITestResult result) {
		successCount++;
		try {
			test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
			System.out.println("********************************************");
			System.out.println("TEST CASE: " + result.getName() + " IS PASS");
			System.out.println("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the fail result of the execution
	 * 
	 * @param result
	 * @throws IOException
	 */
	public void onTestFailure(ITestResult result) {
		failureCount++;
		try {
			Object currentClass = result.getInstance();
			WebDriver driver = ((DriverFactory) currentClass).getDriver();

			test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			test.fail(result.getThrowable());
			test.fail("Please find the screenshot below");
			test.addScreenCaptureFromPath(new UtilityActions().takeScreenshot(driver, result.getName()),
					result.getName());
			System.out.println("********************************************");
			System.out.println("TEST CASE: " + result.getName() + " IS FAIL");
			System.out.println("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the skip result of the execution
	 * 
	 * @param result
	 * @throws IOException
	 */
	public void onTestSkipped(ITestResult result) {
		skippedCount++;
		try {
			test.log(Status.SKIP,
					MarkupHelper.createLabel(result.getName() + " Test Case SKIPPED", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
			System.out.println("********************************************");
			System.out.println("TEST CASE: " + result.getName() + " IS SKIPPED");
			System.out.println("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Teardown the report
	 * 
	 * @param testContext
	 */
	public void onFinish(ITestContext testContext) {
		try {
			extent.flush();
			String testEnvironment = new PropertyDataHandler().getProperty(AutomationConstants.AUTOMATION_TEST_CONFIG,
					AutomationConstants.TEST_ENVIRONMENT);
			if (testEnvironment.equalsIgnoreCase("")) {
				testEnvironment = "QA";
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			String actualReportPath = reportPath + "index.html";
			new File(actualReportPath).renameTo(new File(
					System.getProperty("user.dir") + "/Reports/" + "Automation_Report_" + filePathdate + ".html"));

			System.out.println("********************************************");
			System.out.println("TEST CASE EXECUTION COMPLETED ON " + testEnvironment);
			System.out.println("********************************************");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
