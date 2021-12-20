package com.blazedemo.action;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;

import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;

public class ValidationActions extends DriverFactory {

	UtilityActions utilityActionHelper = new UtilityActions();


	/**
	 * Verify that the object is present
	 * 
	 * @param driver
	 * @param element
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementPresent(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean elementPresent = false;
		try {
			int count = utilityActionHelper.waitForElements(driver, element);
			if (count != 0) {
				elementPresent = true;
			} else {
				elementPresent = false;
			}
			Assert.assertTrue(elementPresent, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementPresent, messageOnFailure);
		}
	}

	/**
	 * Verify that the object is not present
	 * 
	 * @param driver
	 * @param elementName
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementNotPresent(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean elementNotPresent = false;
		try {
			int count = utilityActionHelper.waitForElements(driver, element);
			if (count != 0) {
				elementNotPresent = true;
			} else {
				elementNotPresent = false;
			}
			Assert.assertFalse(elementNotPresent, messageOnFailure);
		} catch (Exception e) {
			Assert.assertFalse(elementNotPresent, messageOnFailure);
		}
	}

	/**
	 * Verify that the object is visible and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public boolean verifyElementVisible(WebDriver driver, WebElement element) throws AutomationException {
		boolean elementVisible = false;
		try {
			if (element.isDisplayed()) {
				elementVisible = true;
			} else {
				elementVisible = false;
			}
		} catch (Exception e) {
			elementVisible = false;
		}
		return elementVisible;
	}

	/**
	 * Verify that the object is visible
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public void verifyElementVisible(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean elementVisible = false;
		try {
			if (element.isDisplayed()) {
				elementVisible = true;
			} else {
				elementVisible = false;
			}
			Assert.assertTrue(elementVisible, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementVisible, messageOnFailure);
		}
	}



	/**
	 * Verify that the object is enabled and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public boolean verifyElementEnabled(WebDriver driver, WebElement element) throws AutomationException {
		boolean elementEnabled = false;
		try {
			if (element.isEnabled()) {
				elementEnabled = true;
			} else {
				elementEnabled = false;
			}
		} catch (Exception e) {
			elementEnabled = false;
		}
		return elementEnabled;
	}

	/**
	 * Verify that the object is enabled
	 * 
	 * @param driver
	 * @param element
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementEnabled(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean elementEnabled = false;
		try {
			if (element.isEnabled()) {
				elementEnabled = true;
			} else {
				elementEnabled = false;
			}
			Assert.assertTrue(elementEnabled, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementEnabled, messageOnFailure);
		}
	}

	/**
	 * Verify that the object is selected and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @throws AutomationException
	 */
	public boolean verifyElementSelected(WebDriver driver, WebElement element) throws AutomationException {
		boolean elementSelected = false;
		try {
			if (element.isSelected()) {
				elementSelected = true;
			} else {
				elementSelected = false;
			}
		} catch (Exception e) {
			elementSelected = false;
		}
		return elementSelected;
	}

	/**
	 * Verify that the object is selected
	 * 
	 * @param driver
	 * @param element
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementSelected(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean elementSelected = false;
		try {
			if (element.isSelected()) {
				elementSelected = true;
			} else {
				elementSelected = false;
			}
			Assert.assertTrue(elementSelected, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(elementSelected, messageOnFailure);
		}
	}

	/**
	 * Verify that the checkbox selected and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @return
	 * @throws AutomationException
	 */
	public boolean verifyCheckboxSelected(WebDriver driver, WebElement element) throws AutomationException {
		boolean isCheckboxSelected = false;
		try {
			if (driver != null) {
				if (element != null) {
					if (element.getAttribute("checked") != null) {
						if (element.getAttribute("checked").equals("true")) {
							isCheckboxSelected = true;
						}
					} else {
						isCheckboxSelected = false;
					}
				}
			}
		} catch (Exception e) {
			isCheckboxSelected = false;
		}
		return isCheckboxSelected;
	}

	/**
	 * Verify that the checkbox selected
	 *
	 * @param driver
	 * @param element
	 * @param messageOnFailure
	 * @return
	 * @throws AutomationException
	 */
	public void verifyCheckboxSelected(WebDriver driver, WebElement element, String messageOnFailure)
			throws AutomationException {
		boolean isCheckboxSelected = false;
		try {
			if (driver != null) {
				if (element != null) {
					if (element.getAttribute("checked") != null) {
						if (element.getAttribute("checked").equals("true")) {
							isCheckboxSelected = true;
						}
					} else {
						isCheckboxSelected = false;
					}
					Assert.assertTrue(isCheckboxSelected, messageOnFailure);
				}
			}
		} catch (Exception e) {
			Assert.assertTrue(isCheckboxSelected, messageOnFailure);
		}
	}

	/**
	 * Verify that text or label present and return true or false
	 * 
	 * @param driver
	 * @param element
	 * @param expectedText
	 * @throws AutomationException
	 */
	public boolean verifyElementText(WebDriver driver, WebElement element, String expectedText)
			throws AutomationException {
		boolean textVerified = false;
		try {
			String actualText = element.getText();
			if (actualText.contentEquals(expectedText)) {
				textVerified = true;
			} else {
				textVerified = false;
			}
		} catch (Exception e) {
			textVerified = false;
		}
		return textVerified;
	}

	/**
	 * Verify that text or label present
	 * 
	 * @param driver
	 * @param element
	 * @param expectedText
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementText(WebDriver driver, WebElement element, String expectedText, String messageOnFailure)
			throws AutomationException {
		boolean textVerified = false;
		try {
			String actualText = element.getText();
			if (actualText.contentEquals(expectedText)) {
				textVerified = true;
			} else {
				textVerified = false;
			}
			Assert.assertTrue(textVerified, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textVerified, messageOnFailure);
		}
	}

	/**
	 * Verify that the object contains text or label
	 * 
	 * @param driver
	 * @param element
	 * @param expectedText
	 * @throws AutomationException
	 */
	public void verifyElementContainsText(WebDriver driver, WebElement element, String expectedText,
			String messageOnFailure) throws AutomationException {
		boolean textContains = false;
		try {
			String actualText = element.getText();
			if (actualText.contains(expectedText)) {
				textContains = true;
			} else {
				textContains = false;
			}
			Assert.assertTrue(textContains, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textContains, messageOnFailure);
		}
	}

	/**
	 * Verify that the object attribute has text or label present
	 * 
	 * @param driver
	 * @param element
	 * @param attributeName
	 * @param expectedText
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyElementAttributeHasText(WebDriver driver, WebElement element, String attributeName,
			String expectedText, String messageOnFailure) throws AutomationException {
		boolean textVerified = false;
		try {
			String actualAttributeValue = element.getAttribute(attributeName);
			if (actualAttributeValue.contentEquals(expectedText)) {
				textVerified = true;
			} else {
				textVerified = false;
			}
			Assert.assertTrue(textVerified, messageOnFailure);
		} catch (Exception e) {
			Assert.assertTrue(textVerified, messageOnFailure);
		}
	}

	/**
	 * Verify that the URL contains text or label and return true or false
	 * 
	 * @param driver
	 * @param expectedText
	 * @throws AutomationException
	 */
	public boolean verifyURLContainsText(WebDriver driver, String expectedText) throws AutomationException {
		boolean urlConatinsText = false;
		try {
			String actualURL = driver.getCurrentUrl();
			if (actualURL.contains(expectedText)) {
				urlConatinsText = true;
			} else {
				urlConatinsText = false;
			}
		} catch (Exception e) {
			urlConatinsText = false;
		}
		return urlConatinsText;
	}

	/**
	 * Verify whether the actual and expected results are equal or not, based on
	 * that it will show the message that given as parameter
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyEquals(String actual, String expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertEquals((String) actual, (String) expected, (String) messageOnFailure);
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Verify whether the actual and expected results are equal or not for boolean,
	 * based on that it will show the message that given as parameter
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyEquals(boolean actual, boolean expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertEquals((boolean) actual, (boolean) expected, (String) messageOnFailure);
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Verify whether the actual and expected results are not equal or not for
	 * String, based on that it will show the message that given as parameter
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyNotEquals(String actual, String expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertNotEquals((String) actual, (String) expected, (String) messageOnFailure);
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}

	/**
	 * Verify whether the actual and expected results are equal or not for boolean,
	 * based on that it will show the message that given as parameter
	 * 
	 * @param actual
	 * @param expected
	 * @param messageOnFailure
	 * @throws AutomationException
	 */
	public void verifyNotEquals(boolean actual, boolean expected, String messageOnFailure) throws AutomationException {
		try {
			Assert.assertNotEquals((boolean) actual, (boolean) expected, (String) messageOnFailure);
		} catch (Exception lException) {
			lException.printStackTrace();
			throw new AutomationException(getExceptionMessage(), lException);
		}
	}
}
