@tag
Feature: Login to Salesforce Application
  
  Background:
  	Given User open Salesforce Application

  Scenario: Login with correct user name and correct password
    When User on "LoginPage"
    When User enters value into username field as "training@automation.com"
    When User enters value into password field as "testing@123"
    And Click on login button
    Then User on "HomePage"
    Then Verify page title as "Welcome to your free trial." on "HomePage"

	Scenario: Login with correct user name and clear password field
    When User on "LoginPage"
    When User enters value into username field as "training@automation.com"
    When User clears value from password field
    And Click on login button
    Then Error message is shown with text "Please enter your password."

  Scenario: Login with correct user name and password and click remember check
  	When User on "LoginPage"
    When User enters value into username field as "training@automation.com"
    When User enters value into password field as "testing@123"
    And click on remember me
    And Click on login button
    Then User on "HomePage"
    Then go to user menu and Click on logout
    Then User name field is shown with text "training@automation.com"
    
  Scenario: Login with correct user name and click forget password link
    When User on "LoginPage"
    When User enters value into username field as "training@automation.com"
    And click on forget password link
    Then User on "LoginForgetPasswordPage"
    Then User enters value into username field on forget password page as "training@automation.com"
    Then Click on continue button
    Then User on "LoginCheckEmailPage"
    Then Verify page title as "Check Your Email | Salesforce" on "LoginCheckEmailPage"
      
 Scenario: Login with wrong user name and wrong password
    When User on "LoginPage"
    When User enters value into username field as "training@autom.com"
    When User enters value into password field as "test@123"
    And Click on login button
    Then Error message is shown with text "Please check your username and password. If you still can't log in, contact your Salesforce administrator."
    