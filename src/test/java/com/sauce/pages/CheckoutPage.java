package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.HelperFunctions;

public class CheckoutPage {
	WebDriver driver;
	ExtentReport report;
	HelperFunctions helper;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.report = new ExtentReport(driver);
		this.helper = new HelperFunctions(driver);
	}

	public void startReportFromHere(ExtentReport report) {
		this.report = report;
	}

	By checkoutButton = By.id("checkout");
	By firstNameField = By.id("first-name");
	By lastNameField = By.id("last-name");
	By postalCodeField = By.id("postal-code");
	By continueButton = By.id("continue");

	public void proceedToCheckout() {
		String btnXpath = "//button[@id='checkout']";
		try {
			helper.customExplicitWaitTillElementBecomesClickable(btnXpath, 3, 0.5);
			driver.findElement(checkoutButton).click();
			report.testLogAndScr("Able to Checkout", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to checkout", "FAIL", driver);
		}
	}

	public void enterCheckoutDetails(String firstName, String lastName, String postalCode) {
		try {
			driver.findElement(firstNameField).sendKeys(firstName);
			driver.findElement(lastNameField).sendKeys(lastName);
			driver.findElement(postalCodeField).sendKeys(postalCode);
			driver.findElement(continueButton).click();
			report.testLogAndScr("Able to input checkout details", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unale to input checkout details", "FAIL", driver);
		}
	}
}
