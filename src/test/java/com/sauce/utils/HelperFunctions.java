package com.sauce.utils;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperFunctions {
	private WebDriver driver = null;
	private WebDriverWait explicitWait = null;

	public HelperFunctions(WebDriver driver) {
		this.driver = driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;

	}
	
	public void customImplicitWait(long seconds) {
		try {
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public <T> void customExplicitWaitTillElementBecomesClickable(T argument, double timeOutInSeconds,
			double pollFreqInSeconds) throws Exception {
		WebElement element = null;
		Exception error = null;
		long init = 0;
		long timeoutInMilliseconds = (long) (timeOutInSeconds * 1000.000);
		long pollFreqInMillis = (long) (pollFreqInSeconds * 1000.000);
		boolean result = false;
		if (argument.getClass().toString().contains("String")) {
			element = driver.findElement(By.xpath((String) argument));
		} else if (argument.getClass().toString().contains("WebElement")) {
			element = (WebElement) argument;
		} else {
			throw new IllegalArgumentException("Argument must be of type String or WebElement");
		}

		do {
			try {
				if (element.isDisplayed() && element.isEnabled()) {
					result = true;
					break;
				}
			} catch (Exception e) {
				error = e;
			} finally {
				Thread.sleep(pollFreqInMillis);
				init = pollFreqInMillis + init + 1;
			}
		} while (init < timeoutInMilliseconds);

		if (result == false) {
			throw error;
		} else {
			System.out.println("It took " + init + "ms for element (" + element + ") to become clickable.");
		}
	}

	public void customExplicitWaitTillElementBecomesActive(String argument, double timeOutInSeconds,
			double pollFreqInSeconds) throws Exception {
		WebElement element = null;
		long init = 0;
		long timeoutInMilliseconds = (long) (timeOutInSeconds * 1000.000);
		long pollFreqInMillis = (long) (pollFreqInSeconds * 1000.000);
		boolean result = false;
		Exception error = null;

		do {
			try {
				if (driver.findElement(By.xpath((String) argument)) != null) {
					result = true;
					element = driver.findElement(By.xpath((String) argument));
					break;
				}
			} catch (Exception e) {
				error = e;
			} finally {
				Thread.sleep(pollFreqInMillis);
				init = pollFreqInMillis + init + 1;
			}
		} while (init < timeoutInMilliseconds);

		if (result == false) {
			throw error;
		} else {
			System.out.println("It took " + init + "ms for element (" + element + ") to be present on screen");
		}
	}

	public void explicitWaitForElementClick(String xpath, double timeOutInSeconds, double pollFreqInSeconds) {
		long timeOutInMillis = (long) (timeOutInSeconds * 1000.000);
		long pollFreqInMillis = (long) (pollFreqInSeconds * 1000.000);
		explicitWait = new WebDriverWait(driver, (long) timeOutInSeconds);
		explicitWait.withTimeout(Duration.ofMillis(timeOutInMillis)).pollingEvery(Duration.ofMillis(pollFreqInMillis))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
	}

	public void explicitWaitForEleemntClick(WebElement element, double timeOutInSeconds, double pollFreqInSeconds) {
		long timeOutInMillis = (long) (timeOutInSeconds * 1000.000);
		long pollFreqInMillis = (long) (pollFreqInSeconds * 1000.000);
		explicitWait = new WebDriverWait(driver, (long) timeOutInSeconds);
		explicitWait.withTimeout(Duration.ofMillis(timeOutInMillis)).pollingEvery(Duration.ofMillis(pollFreqInMillis))
				.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);
	}

	public void scrollIntoView(String xpath) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath(xpath)));
	}

	public void scrollVerticallySlowly(int pixels) {
		int i;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		for (i = 0; i <= pixels; i += 20) {
			js.executeScript("window.scrollBy(0, " + i + ");");
		}
	}

	public void clickUsingJS(WebElement element) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public void clickUsingJS(String xpath) {
		WebElement element = null;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		element = driver.findElement(By.xpath(xpath));
		executor.executeScript("arguments[0].click();", element);
	}

	public <T> void print(T argument) {
		System.out.println(argument);
	}

	public void waitALittle(double seconds) {
		try {
			long millis = (long) (seconds * 1000.000);
			Thread.sleep(millis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
