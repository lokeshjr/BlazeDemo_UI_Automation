package com.blazedemo.testcases;

import org.testng.annotations.Test;

import com.blazedemo.testdata.DataGenerator;
import com.blazedemo.action.UtilityActions;
import com.blazedemo.action.ValidationActions;
import com.blazedemo.exception.AutomationException;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.pages.TravelTheWorldPage;

public class Blaze_Demo_Test extends BaseTest {
	DataGenerator dataGenerator;
	TravelTheWorldPage travelPage;
	ReservePage reservePage;
	PurchasePage purchaseFlightPage;
	ConfirmationPage confirmationPage;
	ValidationActions vActions = new ValidationActions();
	UtilityActions uActions = new UtilityActions();

	@Test(enabled = true, description = "Verify booking flight functionality")
	public void verifyBookingFlight() throws AutomationException {

		travelPage = new TravelTheWorldPage(driver);
		reservePage = new ReservePage(driver);
		purchaseFlightPage = new PurchasePage(driver);
		confirmationPage = new ConfirmationPage(driver);
		dataGenerator = new DataGenerator();
		travelPage.navigateToTravelPage();
		travelPage.selectFlights(dataGenerator.cityNumber);
		travelPage.findFlights();
		reservePage.chooseThisFlight(dataGenerator.chooseFlightNumber);
		purchaseFlightPage.enterUserDetails(dataGenerator.name, dataGenerator.address, dataGenerator.city,
				dataGenerator.state, dataGenerator.zipCode, dataGenerator.cardNumber, dataGenerator.creditCard,
				dataGenerator.month, dataGenerator.year);
		purchaseFlightPage.clickOnPurchaseFlight();
		String jsonBookingDetails = confirmationPage.bookingDetails(confirmationPage.jsonBookingDetails);
		System.out.println(uActions.jsonObject(jsonBookingDetails, "id"));
		vActions.verifyEquals(confirmationPage.bookingDetails(confirmationPage.bookingId),
				uActions.jsonObject(jsonBookingDetails, "id"), "Booking ID's are not equal");

	}
	
	@Test(enabled = true, description = "Verify booking flight functionality with Remember Me option checked")
	public void verifyBookingFlightWithRememberMe() throws AutomationException {

		travelPage = new TravelTheWorldPage(driver);
		reservePage = new ReservePage(driver);
		purchaseFlightPage = new PurchasePage(driver);
		confirmationPage = new ConfirmationPage(driver);
		dataGenerator = new DataGenerator();
		travelPage.navigateToTravelPage();
		travelPage.selectFlights(dataGenerator.cityNumber);
		travelPage.findFlights();
		reservePage.chooseThisFlight(dataGenerator.chooseFlightNumber);
		purchaseFlightPage.enterUserDetails(dataGenerator.name, dataGenerator.address, dataGenerator.city,
				dataGenerator.state, dataGenerator.zipCode, dataGenerator.cardNumber, dataGenerator.creditCard,
				dataGenerator.month, dataGenerator.year);
		purchaseFlightPage.checkRememberMeOption();
		purchaseFlightPage.clickOnPurchaseFlight();
		String jsonBookingDetails = confirmationPage.bookingDetails(confirmationPage.jsonBookingDetails);
		System.out.println(uActions.jsonObject(jsonBookingDetails, "id"));
		vActions.verifyEquals(confirmationPage.bookingDetails(confirmationPage.bookingId),
				uActions.jsonObject(jsonBookingDetails, "id"), "Booking ID's are not equal");

	}
	
	@Test(enabled = true, description = "Verify booking flight functionality without entering user details")
	public void verifyBookingFlightWithoutUserDetails() throws AutomationException {

		travelPage = new TravelTheWorldPage(driver);
		reservePage = new ReservePage(driver);
		purchaseFlightPage = new PurchasePage(driver);
		confirmationPage = new ConfirmationPage(driver);
		dataGenerator = new DataGenerator();
		travelPage.navigateToTravelPage();
		travelPage.selectFlights(dataGenerator.cityNumber);
		travelPage.findFlights();
		reservePage.chooseThisFlight(dataGenerator.chooseFlightNumber);
		purchaseFlightPage.clickOnPurchaseFlight();
		String jsonBookingDetails = confirmationPage.bookingDetails(confirmationPage.jsonBookingDetails);
		System.out.println(uActions.jsonObject(jsonBookingDetails, "id"));
		vActions.verifyEquals(confirmationPage.bookingDetails(confirmationPage.bookingId),
				uActions.jsonObject(jsonBookingDetails, "id"), "Booking ID's are not equal");

	}

}
