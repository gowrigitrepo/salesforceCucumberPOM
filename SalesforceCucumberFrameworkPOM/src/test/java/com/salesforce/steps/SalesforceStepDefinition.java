package com.salesforce.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import com.salesforce.pages.homepages.HomePage;
import com.salesforce.pages.loginpages.LoginCheckEmailPage;
import com.salesforce.pages.loginpages.LoginForgetPasswordPage;
import com.salesforce.pages.loginpages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesforceStepDefinition {

	WebDriver driver;
	LoginPage login;
	HomePage home;
	LoginForgetPasswordPage loginForgetPassword;
	LoginCheckEmailPage loginCheckEmailPage;

	@Before
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Given("User open Salesforce Application")
	public void user_open_salesforce_application() {
		driver.get("https://automation-5a-dev-ed.my.salesforce.com/");
	}

	@When("User on {string}")
	public void user_on(String page) {
		if (page.equalsIgnoreCase("loginpage"))
			login = new LoginPage(driver);
		else if (page.equalsIgnoreCase("homepage"))
			home = new HomePage(driver);
		else if (page.equalsIgnoreCase("LoginForgetPasswordPage"))
			loginForgetPassword = new LoginForgetPasswordPage(driver);
		else if (page.equalsIgnoreCase("LoginCheckEmailPage"))
			loginCheckEmailPage = new LoginCheckEmailPage(driver);
		else
			System.out.println("Page Not Found");
	}

	@When("User enters value into username field as {string}")
	public void user_enters_value_into_username_field_as(String username) {
		login.enterUserName(username);
	}

	@When("User enters value into password field as {string}")
	public void user_enters_value_into_password_field_as(String password) {
		login.enterPassword(password);
	}

	@When("Click on login button")
	public void click_on_login_button() {
		login.clickLoginButton();
	}
	
	@Then("Verify page title as {string} on {string}")
	public void verify_page_title_as_on(String expected, String page) {
		if (page.equalsIgnoreCase("homepage")) {
			System.out.println("Page : "+page);
			String actualHome = home.getTitleFromHomePage();
			System.out.println("Home Title : "+actualHome);
			Assert.assertEquals(actualHome, expected);
		}else if (page.equalsIgnoreCase("LoginCheckEmailPage")) {
			System.out.println("Page : "+page);
			String actual = loginCheckEmailPage.getCheckEmailTitle();
			System.out.println("Chk Email Title : "+actual);
			Assert.assertEquals(actual, expected);
		}else {
			System.out.println("Title not Found");
		}
	}

	@When("User clears value from password field")
	public void user_clears_value_from_password_field() {
		login.clearPassword();
	}

	@Then("Error message is shown with text {string}")
	public void error_message_is_shown_with_text(String expectedError) {
		String actualError = login.getErrorMessage();
		Assert.assertEquals(actualError, expectedError);
	}

	@When("click on remember me")
	public void click_on_remember_me() {
		login.clickRememberCheck();
	}
	
	@Then("go to user menu and Click on logout")
	public void go_to_user_menu_and_click_on_logout() {
	   home.logout();
	}

	@Then("User name field is shown with text {string}")
	public void user_name_field_is_shown_with_text(String expectedUserName) {
		String actualUserName = home.getUserNameAfterLogout();
		Assert.assertEquals(actualUserName, expectedUserName);
	}

	@When("click on forget password link")
	public void click_on_forget_password_link() {
		login.clickForgetPasswordLink();
	}
	
	@Then("User enters value into username field on forget password page as {string}")
	public void user_enters_value_into_username_field_on_forget_password_page_as(String userNameFP) {
	    loginForgetPassword.enterUserName(userNameFP);
	}

	@Then("Click on continue button")
	public void click_on_continue_button() {
		loginForgetPassword.clickContinue();;
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}
