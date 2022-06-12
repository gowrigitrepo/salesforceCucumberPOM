package com.salesforce.base;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	public WebDriver driver;
	public WebDriverWait wait;


	public BasePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public static void sendText(WebElement textElement, String sendText) {
		if (textElement.isDisplayed()) {
			textElement.clear();
			textElement.sendKeys(sendText);
			//report.logTestInfo("Passed Sending text : " + sendText);
		} else {
			System.out.println("Failed Sending text : " + sendText);
			//report.logTestInfo("Failed Sending text : " + sendText);
		}
	}

	public void sendText(WebElement textElement, String sendText, String whatText) {
		waitUntilVisibleElement(textElement, "Send Text");
		if (textElement.isDisplayed()) {
			textElement.clear();
			textElement.sendKeys(sendText);
			//report.logTestInfo("Passed Sending text : " + sendText + "To" + whatText);
		} else {
			System.out.println("Failed Sending text : " + sendText + "To" + whatText);
			//report.logTestInfo("Failed Sending text : " + sendText + "To" + whatText);
		}
	}
	
	public String readText(WebElement element,String objectName) {
		//report.logTestInfo("readText");
		waitUntilVisibleElement(element, objectName);
		String text=element.getText();
		return text;
	}
	
	public static void clearText(WebElement element, String clearText) {
		if (element.isDisplayed()) {
			element.clear();
			//report.logTestInfo("Cleared Text on " + clearText);
		} else {
			System.out.println("Failed to Clear text on " + clearText);
			//report.logTestInfo("Failed to Clear text on " + clearText);
		}
	}

	public static void clickButton(WebElement buttonElement, String buttonText) {
		if (buttonElement.isDisplayed()) {
			buttonElement.click();
			//report.logTestInfo("Passed Clicking Button : " + buttonText);
		} else {
			System.out.println("Failed Click Button");
			//report.logTestInfo("Failed Clicking Button : " + buttonText);
		}
	}

	public void switchToElement(WebElement switchTo, String switchText) {
		Actions action = new Actions(driver);
		action.moveToElement(switchTo).build().perform();
		//report.logTestInfo("Switched to Tab : " + switchText);
	}

	public String switchToWindow(String mainWindowHandle) {
		//report.logTestInfo("Inside switchToWindow");
		String returnHandle = null;
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle)) {
				driver.switchTo().window(handle);
			}
			returnHandle = handle;
		}
		return returnHandle;
	}

	public void mouseOver(WebElement element, String mouseOverText) {
		if (element.isDisplayed()) {
			waitUntilVisibleElement(element, mouseOverText);
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			//report.logTestInfo("Mouse Over on : " + mouseOverText);
		} else {
			System.out.println("Mouse over failed on" + mouseOverText);
			//report.logTestInfo("Mouse over failed on" + mouseOverText);
		}
	}

	private Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		//report.logTestInfo("Alert : " + alert.getText());
		return alert;
	}

	public void acceptAlert() {
		waitUntilAlertIsPresent();
		switchToAlert().accept();
		//report.logTestInfo("Alert Accepted");
	}

	public void dismisAlert() {
		waitUntilAlertIsPresent();
		switchToAlert().dismiss();
		//report.logTestInfo("Alert Dismissed");
	}

	public void waitUntilVisibilityLocated(By byLocator) {
		//report.logTestInfo("Inside waitUntilVisibilityLocated");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byLocator));
	}

	public void waitUntilVisibleElement(WebElement element, String objName) {
		//report.logTestInfo("Inside waitUntilVisibleElement");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilAlertIsPresent() {
		//report.logTestInfo("Inside waitUntilAlertIsPresent");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitUntilElementToBeClickable(By locator, String objName) {
		//report.logTestInfo("Inside waitUntilElementToBeClickable");
		wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void fluentWait(By byLocator) {
		//report.logTestInfo("Inside fluentWait");
		Wait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
		fluentWait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	
	
}
