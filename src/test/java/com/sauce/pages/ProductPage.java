package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.HelperFunctions;

public class ProductPage {
	WebDriver driver = null;
	HelperFunctions helper = null;
	ExtentReport report;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		this.helper = new HelperFunctions(driver);
		this.report = new ExtentReport(driver);
	}

	public void startReportFromHere(ExtentReport report) {
		this.report = report;
	}

	String classNameOfSearchBox = "product_sort_container";
	By productSearchBox = By.className(classNameOfSearchBox);

	public void clickProductNameField() {
		String xpath = "//select[@class='product_sort_container']";
		try {
			helper.customExplicitWaitTillElementBecomesClickable(xpath, 3, 0.5);
			driver.findElement(productSearchBox).click();
			report.testLogAndScr("Able to click on Product Name", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to click on Product Name", "FAIL", driver);
		}
	}

	public void enterProductName(String productName) {
		try {
			driver.findElement(productSearchBox).sendKeys(productName);
			report.testLogAndScr("Able to send keys to Product Name", "Pass", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Able to send keys into Product name field", "FAIL", driver);
		}

	}

	public void submitSearch() {
		try {
			new Actions(driver).sendKeys(Keys.ENTER).build().perform();
			report.testLogAndScr("Able to submit search", "PASS", driver);

		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to send search", "FAIL", driver);
		}
	}

	public void endReport() {
		report.endReport();
	}
}
