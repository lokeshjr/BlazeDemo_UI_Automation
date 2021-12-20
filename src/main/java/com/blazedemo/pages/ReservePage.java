package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.base.DriverFactory;

public class ReservePage extends DriverFactory {
	
	/**locators of Reserve Page
	 * 
	 */

	@FindBy(xpath = "//div/h3")
	public WebElement FlightsFromToText;

	@FindBy(xpath = "//thead/tr/th[4]")
	public WebElement departsCityHeaderTxt;

	@FindBy(xpath = "//thead/tr/th[5]")
	public WebElement destinationCityHeaderTxt;

	@FindBy(xpath = "//input[@type=\"submit\"]")
	public WebElement chooseThisFlightBtn;

	/**
	 * constructor method
	 * @param driver
	 */
	public ReservePage(WebDriver driver) {

		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	/**
	 *  re-usable method for finding flights
	 * @param number
	 */

	public void chooseThisFlight(int number) {

		driver.findElement(By.xpath("(//input[@type=\'submit\'])[" + number + "]")).click();

	}

}
