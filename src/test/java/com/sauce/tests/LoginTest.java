package com.sauce.tests;

import com.sauce.base.TestBase;
import com.sauce.pages.LoginPage;
import com.sauce.pages.ProductPage;
import com.sauce.test.reporting.ExtentReport;
import com.sauce.utils.TestDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    private LoginPage loginPage;
    private ProductPage productsPage;
    private ExtentReport report;
    @Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void testLogin(String username, String password, String expectedTitle) {
        loginPage = new LoginPage(driver);
        loginPage.setReport("Login Test");
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        loginPage.waitAFewSeconds(5);
        loginPage.endReport();
        // Example validation
    }
}
