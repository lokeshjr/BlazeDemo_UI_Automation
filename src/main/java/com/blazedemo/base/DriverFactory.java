package com.blazedemo.base;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.blazedemo.exception.AutomationException;
import com.blazedemo.utils.AutomationConstants;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;

	/**
	 * Start the web browser
	 * 
	 * @param browserName
	 * @return
	 * @throws AutomationException
	 * @throws InterruptedException
	 */
	public WebDriver startBrowser(String browserName) throws AutomationException, InterruptedException {
		switch (browserName.toLowerCase()) {
		case "chrome":
		case "headless":
			startChrome(browserName);
			break;

		case "firefox":
			startFirefox();
			break;

		case "edge":
			startEdge();
			break;

		case "safari":
			startSafari();
			break;

		default:
			System.out.println(AutomationConstants.CHECKBROWSER_MESSAGE);
			break;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	/**
	 * Launch Safari browser
	 * 
	 * @throws InterruptedException
	 */
	private void startSafari() throws InterruptedException {
		Process process;
		try {
			process = Runtime.getRuntime().exec("killall safaridriver");
			process.waitFor();
			process.destroy();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SafariOptions options = new SafariOptions();
		// options.useCleanSession(true);
		options.setAutomaticInspection(true);
		options.setUseTechnologyPreview(true);
		driver = new SafariDriver(options);
	}

	/**
	 * Launch Edge browser
	 * 
	 * @throws AutomationException
	 */
	private void startEdge() throws AutomationException {
		try {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Launch Chrome browser
	 * 
	 * @throws AutomationException
	 */
	private void startChrome(String browserName) throws AutomationException {
		try {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);
			if (browserName.equalsIgnoreCase("headless")) {
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Launch Firefox browser
	 * 
	 * @throws AutomationException
	 */
	private void startFirefox() throws AutomationException {
		try {
			WebDriverManager.firefoxdriver().setup();
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			FirefoxOptions firefoxOptions = new FirefoxOptions(capabilities);
			driver = new FirefoxDriver(firefoxOptions);
			driver.manage().window().maximize();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Get the Exception message, to pass the message whenever an exception is
	 * encountered
	 * 
	 */
	public static String getExceptionMessage() {
		StringBuffer message = new StringBuffer();

		try {
			message.append("Exception in ");
			message.append(Thread.currentThread().getStackTrace()[2].getClassName());
			message.append(".");
			message.append(Thread.currentThread().getStackTrace()[2].getMethodName());
			message.append("()");
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}

		return message.toString();
	}

	/**
	 * Set driver for Web applications
	 * 
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Get current running driver session
	 * 
	 */
	public WebDriver getDriver() {
		return this.driver;
	}
}
