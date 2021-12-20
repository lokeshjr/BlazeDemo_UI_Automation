package com.blazedemo.testcases;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.blazedemo.action.UtilityActions;
import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;
import com.blazedemo.reporting.AutomationReport;

@Listeners(AutomationReport.class)
public class BaseTest extends DriverFactory {
	@BeforeClass
	@Parameters("browserName")
	public void setup(String browserName) throws Exception {
		startBrowser(browserName);
	}

	@AfterSuite
	public void tearDownMethod() throws AutomationException, InterruptedException {
		UtilityActions uActions = new UtilityActions();
		uActions.quitBrowser(driver);
	}
}
