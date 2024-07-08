package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.HelperFunctions;

public class CartPage {
	WebDriver driver;
	ExtentReport report;
	HelperFunctions helper;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		this.report = new ExtentReport(driver);
		this.helper = new HelperFunctions(driver);
	}

	public void startReportFromHere(ExtentReport report) {
		this.report = report;
	}

	By addToCartButton = By.id("add-to-cart-sauce-labs-backpack");
	String addToCartButtonXpath = "//button[@data-test='add-to-cart-sauce-labs-backpack']";
	By cartItemCount = By.className("shopping_cart_badge");

	public void clickAddToCartButton() {
		try {
			helper.customExplicitWaitTillElementBecomesClickable(addToCartButtonXpath, 3, 0.5);
			driver.findElement(addToCartButton).click();
			report.testLogAndScr("Able to click on Add to cart", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to click on Add to cart", "FAIL", driver);
		}
	}

	public int getCartItemCount() {
		String itemCount = null;
		try {
			itemCount = driver.findElement(cartItemCount).getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(itemCount);
	}

	public void endReport() {
		report.endReport();
	}

	public void checkItemCount(int expected) {
		try {
			int itemCount = getCartItemCount();
			Assert.assertEquals(itemCount, expected);
			report.testLogAndScr("Cart count matches with expected value", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Cart count matches with expected value", "FAIL", driver);
		}
	}
}
