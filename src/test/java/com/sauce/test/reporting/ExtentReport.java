package com.sauce.test.reporting;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sauce.utils.ReadProperty;

//use this class for generating extent reports
public class ExtentReport {
	public ExtentReports report;
	public ExtentTest test;
	public WebDriver driver;
	String reportPath = System.getProperty("user.dir") + "\\ExtentReports\\";
	String screenshotPath = System.getProperty("user.dir") + "\\Test_Screenshots\\";
	String extentReportXmlPath = System.getProperty("user.dir") + "\\XML\\extent-config.xml";
	ReadProperty prop;

	public ExtentReport(WebDriver driver) {
		this.driver = driver;
	}

	public String testName;

	public void startTest(String testName, boolean choice) {
		try {
			prop = new ReadProperty();
			report = new ExtentReports(reportPath + testName + "_" + prop.getBrowser().toUpperCase() + ".html", choice);
			report.addSystemInfo("Enviroment", prop.getEnviroment()).addSystemInfo("PC Name", prop.getPcName())
					.addSystemInfo("Browser", prop.getBrowser());
			report.loadConfig(new File(extentReportXmlPath));
			test = report.startTest(testName);
			this.testName = testName;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testLogAndScr(String msg, String status, WebDriver driver) {
		try {
			if (status.equalsIgnoreCase("PASS"))
				test.log(LogStatus.PASS, msg, test.addScreenCapture(capture(driver)));
			else if (status.equalsIgnoreCase("FAIL"))
				test.log(LogStatus.FAIL, msg, test.addScreenCapture(capture(driver)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String capture(WebDriver driver) throws IOException {
		// To get current threads present in stack
		StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		// we need third element as it contains our callee method reference
		String methodName = stack[3].getMethodName();
		// Screen Capturing
		File scrCapture = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(
				screenshotPath + testName + "_" + methodName + "_" + prop.getBrowser().toUpperCase() + ".png");
		String returnPath = dest.getAbsolutePath();
		FileUtils.copyFile(scrCapture, dest);
		return returnPath;
	}

	public void endReport() {
		report.endTest(test);
		report.flush();
	}
}
