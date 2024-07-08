package com.sauce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.HelperFunctions;

public class LoginPage {
	private WebDriver driver;
	private HelperFunctions helper = null;
	// Locators
	private By usernameField = By.id("user-name");
	private By passwordField = By.id("password");
	private By loginButton = By.id("login-button");
	private ExtentReport report;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		helper = new HelperFunctions(driver);
		report = new ExtentReport(driver);
	}
	
	public void  setReport(String name) {
		report.startTest(name, true);
	}
	public void enterUsername(String username) {
		try {
			driver.findElement(usernameField).sendKeys(username);
			report.testLogAndScr("Successfully entered username", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Not able to enter username in username field", "FAIL", driver);
		}
	}

	public void enterPassword(String password) {
		try {
			driver.findElement(passwordField).sendKeys(password);
			report.testLogAndScr("Able to enter password", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to enter password", "FAIL", driver);
		}
	}

	public void clickLoginButton() {
		try {
			driver.findElement(loginButton).click();
			report.testLogAndScr("Clicked on Login Button", "PASS", driver);
		} catch (Exception e) {
			e.printStackTrace();
			report.testLogAndScr("Unable to click on Login Button", "FAIL", driver);
		}
	}

	public void waitAFewSeconds(double seconds) {
		helper.waitALittle(seconds);
	}
	
	public void endReport() {
		report.endReport();
	}

}
