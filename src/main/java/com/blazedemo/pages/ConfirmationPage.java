package com.blazedemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.action.UtilityActions;
import com.blazedemo.base.DriverFactory;
import com.blazedemo.exception.AutomationException;

public class ConfirmationPage extends DriverFactory{

	UtilityActions uActions = new UtilityActions();
	
	/**locators of Confirmation Page
	 * 
	 */

	@FindBy(xpath = "//div/h1")
	public WebElement thankyouText;

	@FindBy(xpath = "//tbody/tr[1]/td[2]")
	public WebElement bookingId;

	@FindBy(xpath = "//tbody/tr[7]/td[2]")
	public WebElement bookingDate;
	
	@FindBy(xpath = "//pre")
	public WebElement jsonBookingDetails;

	// constructor method
	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	/** book details
	 * 
	 * @param element
	 * @return
	 * @throws AutomationException
	 */
	
	public String bookingDetails(WebElement element) throws AutomationException {
		
		return uActions.getElementText(driver, element);
	}

}
