package com.blazedemo.testdata;

import com.github.javafaker.*;

public class DataGenerator {

	public String name, address, city, state, zipCode, creditCard;
	public int month, year, chooseFlightNumber, cityNumber, cardNumber;

	/**
	 * Returns data randome data object creation
	 */
	public DataGenerator() {

		Faker faker = new Faker();
		this.name = faker.name().fullName();
		this.address = faker.address().fullAddress();
		this.city = faker.address().cityName();
		this.state = faker.address().state();
		this.zipCode = faker.address().zipCode();
		this.creditCard = faker.business().creditCardNumber();
		this.month = faker.number().numberBetween(1, 12);
		this.year = faker.number().numberBetween(2000, 2024);
		this.chooseFlightNumber = faker.number().numberBetween(1, 5);
		this.cityNumber = faker.number().numberBetween(0, 6);
		this.cardNumber = faker.number().numberBetween(0, 2);

	}

}
