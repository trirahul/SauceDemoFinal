package com.sauce.tests;

import org.testng.annotations.Test;

import com.sauce.base.TestBase;
import com.sauce.pages.CartPage;
import com.sauce.pages.CheckoutPage;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductPage;
import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.TestDataProvider;

public class ProductCheckoutTest extends TestBase {
	LoginPage loginPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ExtentReport report;

	@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
	public void testCheckout(String username, String password, String expectedTitle) {
		report = new ExtentReport(driver);
		report.startTest("Product Checkout Test", true);

		loginPage = new LoginPage(driver);
		loginPage.setReport("Product Sort Test");
		loginPage.enterUsername(username);
		loginPage.enterPassword(password);
		loginPage.clickLoginButton();
		// Product Page
		productPage = new ProductPage(driver);
		productPage.startReportFromHere(report);
		productPage.clickProductNameField();
		// Add assertions here
		productPage.enterProductName("Sauce Labs Backpack");
		productPage.submitSearch();
		// Cart Page
		cartPage = new CartPage(driver);
		cartPage.startReportFromHere(report);
		cartPage.startReportFromHere(report);
		cartPage.clickAddToCartButton();
		cartPage.checkItemCount(1);

		//
		checkoutPage = new CheckoutPage(driver);
		checkoutPage.startReportFromHere(report);
		checkoutPage.proceedToCheckout();
		checkoutPage.enterCheckoutDetails("John", "Doe", "12345");
		cartPage.endReport();
	}
}
