package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.action.UtilityActions;
import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;

public class PurchasePage extends DriverFactory {

	UtilityActions uActions = new UtilityActions();
	
	/**
	 * locators of Purchase Page
	 */

	@FindBy(id = "inputName")
	WebElement name;

	@FindBy(id = "address")
	WebElement address;

	@FindBy(id = "city")
	WebElement city;

	@FindBy(id = "state")
	WebElement state;

	@FindBy(id = "zipCode")
	WebElement zipCode;

	@FindBy(id = "cardType")
	WebElement cardType;

	@FindBy(id = "creditCardNumber")
	WebElement creditCardNumber;

	@FindBy(id = "creditCardMonth")
	WebElement month;

	@FindBy(id = "creditCardYear")
	WebElement year;

	@FindBy(id = "nameOnCard")
	WebElement nameOnCard;

	@FindBy(xpath = "//p[contains(text() , 'Airline')]")
	WebElement airline;

	@FindBy(xpath = "//p[contains(text() , 'Flight')]")
	WebElement flightNumber;

	@FindBy(xpath = "//p[contains(text() , 'Price')]")
	WebElement price;

	@FindBy(xpath = "//p[contains(text() , 'Fees')]")
	WebElement feesAndTaxes;

	@FindBy(xpath = "//p[contains(text() , 'Total')]")
	WebElement totalCost;

	@FindBy(id = "rememberMe")
	WebElement rememberMeCheckBox;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement purchaseFlight;

	// constructor method
	public PurchasePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	/**
	 *  re-usable method for User details
	 * @param userName
	 * @param userAddress
	 * @param userCity
	 * @param userState
	 * @param userZipCode
	 * @param cardNumber
	 * @param userCreditCardNumber
	 * @param ccMonth
	 * @param ccYear
	 * @throws AutomationException
	 */

	public void enterUserDetails(String userName, String userAddress, String userCity, String userState,
			String userZipCode, int cardNumber, String userCreditCardNumber, int ccMonth, int ccYear)
			throws AutomationException {

		uActions.type(driver, name, userName);
		uActions.type(driver, address, userAddress);
		uActions.type(driver, city, userCity);
		uActions.type(driver, state, userState);
		uActions.type(driver, zipCode, userZipCode);
		uActions.selectDropDownValueByIndex(driver, cardType, cardNumber);
		uActions.type(driver, creditCardNumber, userCreditCardNumber);
		uActions.type(driver, month, Integer.toString(ccMonth));
		uActions.clearAndType(driver, year, Integer.toString(ccYear));
		uActions.clearAndType(driver, nameOnCard, userName);

	}
	
	/**
	 * check Remember Me option
	 * 
	 * @throws AutomationException
	 */
	public void checkRememberMeOption() throws AutomationException {
		
		uActions.tap(driver, rememberMeCheckBox);
	}
	
	/**
	 * click on purchase flight
	 * 
	 * @throws AutomationException
	 */
	public void clickOnPurchaseFlight() throws AutomationException {
		
		uActions.tap(driver, purchaseFlight);
	}

}
