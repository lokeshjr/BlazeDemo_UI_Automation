package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.action.PropertyDataHandler;
import com.blazedemo.action.UtilityActions;
import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;
import com.blazedemo.utils.AutomationConstants;

public class TravelTheWorldPage extends DriverFactory {

	UtilityActions uActions = new UtilityActions();

	/**
	 * locators
	 * 
	 */

	@FindBy(xpath = "//div/h1")
	WebElement welcomeText;

	@FindBy(css = "select[name=\"fromPort\"]")
	WebElement departureCityDropDown;

	@FindBy(css = "select[name=\"toPort\"]")
	WebElement destinationCityDropDown;

	@FindBy(css = "input[type=\"submit\"]")
	WebElement findFlights;

	/**
	 * constructor method
	 * 
	 * @param driver
	 */
	public TravelTheWorldPage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);

	}

	/**
	 * get url
	 * 
	 * @return
	 * @throws AutomationException
	 */
	public String getUrl() throws AutomationException {

		String url = new PropertyDataHandler().getProperty(AutomationConstants.AUTOMATION_TEST_CONFIG, "baseUrl");

		System.out.println("url is : " + url);

		return url;

	}

	/**
	 * navigate to travel page
	 * 
	 * @param url
	 * @throws AutomationException
	 */

	public void navigateToTravelPage() throws AutomationException {

		uActions.loadWebApplication(driver, getUrl());
	}

	/**
	 * re-usable method for finding flights
	 * 
	 * @param number
	 * @throws AutomationException
	 */

	public void selectFlights(int number) throws AutomationException {

		uActions.selectDropDownValueByIndex(driver, departureCityDropDown, number);
		uActions.selectDropDownValueByIndex(driver, destinationCityDropDown, number);

	}

	/**
	 * re-usable method for getting departure city
	 * 
	 * @param number
	 * @return
	 * @throws AutomationException
	 */

	public String departureCity() throws AutomationException {

		return uActions.getElementText(driver, departureCityDropDown);
	}

	/**
	 * re-usable method for getting destination city
	 * 
	 * @param number
	 * @throws AutomationException
	 */

	public String destinationCity() throws AutomationException {

		return uActions.getElementText(driver, destinationCityDropDown);

	}

	/**
	 * re-usable method for clicking on find flights
	 * 
	 * @param number
	 * @throws AutomationException
	 */

	public void findFlights() throws AutomationException {

		uActions.tap(driver, findFlights);

	}

}
