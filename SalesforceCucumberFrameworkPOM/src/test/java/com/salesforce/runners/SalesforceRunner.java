package com.salesforce.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
							"json:target/cucumber-reports/cucumber.json" }, 
				features = {"src/test/resources/com/salesforce/features/SalesforceApplication.feature" }, 
				glue = { "com.salesforce.steps" })

public class SalesforceRunner extends AbstractTestNGCucumberTests{

}
