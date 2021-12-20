package com.blazedemo.action;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.Alert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;
import com.blazedemo.utils.AutomationConstants;

public class UtilityActions extends DriverFactory {

	public WebDriverWait wait;

	/**
	 * Click on an object
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void tap(WebDriver driver, WebElement element) throws AutomationException {
		try {
			if (element != null) {

				element.click();
				Reporter.log("Successfully clicked on " + element);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + element + "'");
			}
		} catch (Exception e) {
			try {

				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				Reporter.log("Successfully clicked on " + element);
			} catch (Exception ex) {
				throw new AutomationException(getExceptionMessage(), ex);
			}
		}
	}

	/**
	 * Right click on an object
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void rightClick(WebDriver driver, WebElement element) throws AutomationException {
		try {
			if (element != null) {
				Actions action = new Actions(driver);
				action.contextClick(element).perform();
				Reporter.log("Successfully right clicked on " + element);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + element + "'");
			}
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Double click on an object
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void doubleClick(WebDriver driver, WebElement element) throws AutomationException {
		try {
			if (element != null) {
				Actions action = new Actions(driver);
				action.doubleClick(element).perform();
				Reporter.log("Successfully double clicked on " + element);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + element + "'");
			}
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0].click();", element);
				executor.executeScript("arguments[0].click();", element);
				Reporter.log("Successfully clicked on " + element);
			} catch (Exception ex) {
				throw new AutomationException(getExceptionMessage(), ex);
			}

		}
	}

	/**
	 * Enter values to an input field
	 * 
	 * @param driver
	 * @param element
	 * @param typeValue
	 * @throws AutomationException
	 */
	public void type(WebDriver driver, WebElement element, String typeValue) throws AutomationException {
		try {
			if (driver != null && element != null) {
				element.sendKeys(typeValue);
				Reporter.log("Data " + typeValue + " successfully entered into " + element);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + element + "'");
			}
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				executor.executeScript("arguments[0]. value='" + typeValue + "';", element);
				Reporter.log("Data " + typeValue + " successfully entered into " + element);
			} catch (Exception ex) {
				throw new AutomationException(getExceptionMessage(), ex);
			}
		}
	}

	/**
	 * Clear an input field and enter values to that input field
	 * 
	 * @param driver
	 * @param expression
	 * @param typeValue
	 * @throws AutomationException
	 */
	public void clearAndType(WebDriver driver, WebElement element, String typeValue) throws AutomationException {
		try {
			if (element != null) {
				element.clear();
				element.sendKeys(typeValue);
				Reporter.log("Data " + typeValue + " successfully entered into " + element);
			} else {
				throw new AutomationException(AutomationConstants.OBJECT_NOT_FOUND + "'" + element + "'");
			}
		} catch (Exception e) {
			try {
				JavascriptExecutor executor = (JavascriptExecutor) driver;
				element.clear();
				executor.executeScript("arguments[0]. value='" + typeValue + "';", element);
				Reporter.log("Data " + typeValue + " successfully entered into " + element);
			} catch (Exception ex) {
				throw new AutomationException(getExceptionMessage(), ex);
			}
		}
	}

	/**
	 * Load the web application
	 * 
	 * @param driver
	 * @param webApplicationUrl
	 * @throws AutomationException
	 */
	public void loadWebApplication(WebDriver driver, String webApplicationUrl) throws AutomationException {
		try {

			driver.get(webApplicationUrl);
			Reporter.log(webApplicationUrl + " is loaded successfully");
		} catch (Exception lException) {
			throw new AutomationException(AutomationConstants.EXCEPTION_MESSAGE_FOR_LOAD_URL);
		}
	}

	/**
	 * Navigate to the new web application URL
	 * 
	 * @param driver
	 * @param webApplicationUrl
	 * @throws AutomationException
	 */
	public void navigateToWebApplication(WebDriver driver, String webApplicationUrl) throws AutomationException {
		try {
			driver.navigate().to(webApplicationUrl);
		} catch (Exception e) {
			throw new AutomationException(AutomationConstants.EXCEPTION_MESSAGE_FOR_LOAD_URL);
		}
	}

	/**
	 * Get the current web URL
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public String getWebUrl(WebDriver driver) throws AutomationException {
		String currentURL = "";
		try {
			currentURL = driver.getCurrentUrl();
		} catch (final Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
		return currentURL;
	}

	/**
	 * Get the title of the web page
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public String getWebApplicationTitle(WebDriver driver) throws AutomationException {
		String title = "";
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return title;
	}

	/**
	 * Refresh the web page
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void refreshPage(WebDriver driver) throws AutomationException {
		try {
			driver.navigate().refresh();
			Thread.sleep(3000);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Get the list of window handles
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public ArrayList<String> getWindowHandles(WebDriver driver) throws AutomationException {
		try {
			ArrayList<String> windowHandles = new ArrayList<String>();
			windowHandles.addAll(driver.getWindowHandles());
			return windowHandles;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Switch to browser tab
	 *
	 * @param driver
	 * @param tabIndex
	 * @throws AutomationException
	 */
	public void switchToBrowserTab(WebDriver driver, int tabIndex) throws AutomationException {
		try {
			if (driver != null) {
				Object[] browserwindows = driver.getWindowHandles().toArray();
				driver.switchTo().window(browserwindows[tabIndex].toString());
				Thread.sleep(2000);
			}
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Switch to browser tab, then close it and switch back to parent tab
	 * 
	 * @param driver
	 * @param windowIndex
	 * @throws AutomationException
	 */
	public void closeTabAndSwitchToParentTab(WebDriver driver, int windowIndex) throws AutomationException {
		try {
			if (driver != null) {
				ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(tabs.get(windowIndex).toString());
				driver.close();
				driver.switchTo().window(tabs.get(0));
				Thread.sleep(1500);
			}
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Switch to frame in the web page based on index
	 * 
	 * @param driver
	 * @param tabIndex
	 * @throws AutomationException
	 */
	public void switchToFrame(WebDriver driver, int index) throws AutomationException {
		try {
			if (driver != null) {
				driver.switchTo().frame(index);
				Thread.sleep(2000);
			}
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Switch to frame in the web page based on frame name or Id
	 * 
	 * @param driver
	 * @param frameNameOrId
	 * @throws AutomationException
	 */
	public void switchToFrame(WebDriver driver, String frameNameOrId) throws AutomationException {
		try {
			if (driver != null) {
				driver.switchTo().frame(frameNameOrId);
				Thread.sleep(2000);
			}
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Switch to frame in the web page based on WebElement, you can use
	 * getWebElement() of UtilityActions to get the WebElement value
	 * 
	 * @param driver
	 * @param elementName
	 * @throws AutomationException
	 */
	public void switchToFrame(WebDriver driver, WebElement elementName) throws AutomationException {
		try {
			if (driver != null) {
				driver.switchTo().frame(elementName);
				Thread.sleep(2000);
			}
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Accept the browser alert
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void acceptAlert(WebDriver driver) throws AutomationException {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Dismiss the browser alert
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void dismissAlert(WebDriver driver) throws AutomationException {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();

		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Get the text or label from the browser alert
	 *
	 * @param driver
	 * @throws AutomationException
	 */
	public String getAlertText(WebDriver driver) throws AutomationException {
		String alertText = "";
		try {
			Alert alert = driver.switchTo().alert();
			alertText = alert.getText();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return alertText;
	}

	/**
	 * Close the browser window
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void closeBrowser(WebDriver driver) throws AutomationException {
		try {

			driver.close();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Quit the browser session
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public void quitBrowser(WebDriver driver) throws AutomationException {
		try {
			driver.quit();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Wait for an element based on the visibility and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @return isElementVisible
	 * @throws AutomationException
	 */
	public boolean waitForElement(WebDriver driver, WebElement element) throws AutomationException {
		boolean isElementVisible = false;
		PropertyDataHandler objPropertyData = new PropertyDataHandler();
		try {
			long timeout = Long.parseLong(
					objPropertyData.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, "SHORT_LOADING"));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			isElementVisible = true;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return isElementVisible;
	}

	/**
	 * Wait for elements based on the visibility and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @return isElementVisible
	 * @throws AutomationException
	 */
	public int waitForElements(WebDriver driver, WebElement element) throws AutomationException {
		int count = 0;
		PropertyDataHandler objPropertyData = new PropertyDataHandler();
		try {
			long timeout = Long.parseLong(
					objPropertyData.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, "SHORT_LOADING"));
			wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElements(element));
			count = elements.size();

		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return count;
	}

	/**
	 * Wait for the page load complete
	 * 
	 * @param driver
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void waitForPageLoadComplete(WebDriver driver) throws NumberFormatException, Exception {
		try {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			long timeout = Long.parseLong(new PropertyDataHandler()
					.getProperty(AutomationConstants.AUTOMATION_FRAMEWORK_CONFIG, AutomationConstants.SHORT_LOADING));
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(pageLoadCondition);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Set delay between test steps
	 * 
	 * @param driver
	 * @param delayInSeconds
	 * @param doubleValue
	 * @throws AutomationException
	 */
	public void delay(int delayInSeconds) throws AutomationException {
		try {
			Thread.sleep(delayInSeconds * 1000);
		} catch (Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Set delay between test steps based on driver session
	 * 
	 * @param driver
	 * @param delayInSeconds
	 * @param doubleValue
	 * @throws AutomationException
	 */
	public void delay(WebDriver driver, int delayInSeconds) throws AutomationException {
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(delayInSeconds));
		} catch (Exception lException) {
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Get the text or label of the WebElement
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public String getElementText(WebDriver driver, WebElement element) throws AutomationException {
		String elementText = "";
		try {

			elementText = element.getText();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return elementText;
	}

	/**
	 * Get the attribute value of the WebElement
	 * 
	 * @param driver
	 * @param element
	 * @param attributeName
	 * @throws AutomationException
	 */
	public String getElementAttributeValue(WebDriver driver, WebElement element, String attributeName)
			throws AutomationException {
		String elementAttribute = "";
		try {
			elementAttribute = element.getAttribute(attributeName);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
		return elementAttribute;
	}

	/**
	 * Scroll to an element
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void scrollToElement(WebDriver driver, WebElement element) throws AutomationException {
		try {

			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Mouse move to an element
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void moveToElement(WebDriver driver, WebElement element) throws AutomationException {
		try {
			Actions actions = new Actions(driver);
			actions.moveToElement(element).perform();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Clear an input field
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void clearInputField(WebDriver driver, WebElement element) throws AutomationException {
		try {
			element.clear();
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Select the value from the drop down using the visible text
	 * 
	 * @param driver
	 * @param element
	 * @param visibleText
	 * @throws AutomationException
	 */
	public void selectDropDownValueByVisibleText(WebDriver driver, WebElement element, String visibleText)
			throws AutomationException {
		try {
			Select select = new Select(element);
			select.selectByVisibleText(visibleText);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Select the value from the drop down using the index
	 * 
	 * @param driver
	 * @param element
	 * @param index
	 * @throws AutomationException
	 */
	public void selectDropDownValueByIndex(WebDriver driver, WebElement element, int index) throws AutomationException {
		try {
			Select select = new Select(element);
			select.selectByIndex(index);
			Reporter.log("Element: "+element+ "is selected");
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Select the value from the drop down using value
	 * 
	 * @param driver
	 * @param elementName
	 * @param valueToSelect
	 * @throws AutomationException
	 */
	public void selectDropDownValueByValue(WebDriver driver, WebElement element, String valueToSelect)
			throws AutomationException {
		try {
			Select select = new Select(element);
			select.selectByValue(valueToSelect);
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Take a screenshot of the current screen and store into screenshots folder in
	 * the project structure
	 * 
	 * @param driver
	 * @throws AutomationException
	 */
	public String takeScreenshot(WebDriver driver, String fileName) throws AutomationException {
		try {
			TakesScreenshot screenShot = ((TakesScreenshot) driver);
			File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/screenshots/" + fileName + "_" + getCurrentDate()
					+ "_" + "screenshot.jpg";
			File destinationFile = new File(destination);
			FileUtils.copyFile(sourceFile, destinationFile);
			return destination;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage(), e);
		}
	}

	/**
	 * Get current date
	 * 
	 * @throws AutomationException
	 */
	public String getCurrentDate() throws AutomationException {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy_HH-mm-ss");
			Date date = new Date();
			String filePathdate = dateFormat.format(date).toString();
			return filePathdate;
		} catch (Exception e) {
			throw new AutomationException(getExceptionMessage() + "\n" + AutomationConstants.CAUSE + e.getMessage());
		}
	}

	public String jsonObject(String jsonString, String key) {

		JSONObject jsonObj = new JSONObject(jsonString);

		return jsonObj.getString(key);

	}

}
