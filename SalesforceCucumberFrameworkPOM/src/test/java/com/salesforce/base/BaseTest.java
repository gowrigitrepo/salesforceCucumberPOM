package com.salesforce.base;

import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.salesforce.utility.CommonUtilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;

	@BeforeMethod
	public static void setUp(Method method) {
		System.out.println("before method started");
		String url = CommonUtilities.getApplicationProperty("url");
		goToChromeDriver();
		gotoUrl(url);
	}

	@AfterMethod
	public static void tearDown() {
		System.out.println("after method started");
		closeAllDriver();
	}
	
	public static void goToChromeDriver() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	public static void gotoUrl(String url) {
		System.out.println("URL :" + url);
		driver.get(url);
	}

	public static void closeDriver() {
		driver.close();
	}

	public static void closeAllDriver() {
		driver.quit();
	}

	public static WebDriver getDriverInstance() {
		return driver;
	}

}
