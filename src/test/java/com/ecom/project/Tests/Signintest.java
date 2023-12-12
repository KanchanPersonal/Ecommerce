package com.ecom.project.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.Signupuser;

public class Signintest {

	// WebDriver setup code

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException {
		// public void launchBrowser() {
		// String browser = System.getProperty("browser");
		DriverUtility.setUp(browser);

	}

	@Test(description = "Verify registration happens correctly", priority = 1, groups = { "smoke" })
	public void newUserSignIn() throws InterruptedException, IOException {
	    // Visit the Registration page and register a new user
		boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 1: Verify New User Registration");
	    Signupuser registration = new Signupuser(DriverUtility.driver);
	    status= registration.register();
        DriverUtility.log.info("Testcase completed");
        assertTrue(status, "Sign up failed. See logs for details.");
	   // tearDown();
        
	}

	@Test(enabled= false,description = "Verify if user is already registered", priority = 1, groups = { "Regression" })
	@Parameters({ "TC1_Username", "TC1_Password","ExpectedText" })
	public void verifyAlreadyRegistered(@Optional("automationuser") String TC1_Username,
			@Optional("abc@123") String TC1_Password, @Optional("This user already existo.") String ExpectedText) throws InterruptedException, IOException {
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 2: Verify if user is already registered");
		Signupuser registration = new Signupuser(DriverUtility.driver);
		status=registration.performRegistration(TC1_Username, TC1_Password,ExpectedText);
	    assertTrue(status, "Cannot signin for already present user. See logs for details.");
	    DriverUtility.log.info("Testcase completed");
	    tearDown();
	        }	

	@Test(enabled= false,description = "Verify if any field is null", priority = 1, groups = { "Regression" })
	@Parameters({ "TC1_Username", "TC1_Password","ExpectedText" })
	public void verifyNullFields(@Optional("") String TC1_Username, @Optional("abc@123") String TC1_Password,@Optional("Please fill out Username and Password.") String ExpectedText)
			throws InterruptedException, IOException {
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 3: Verify if any field is null");
		Signupuser registration = new Signupuser(DriverUtility.driver);
		status = registration.performRegistration(TC1_Username, TC1_Password,ExpectedText);
        assertTrue(status, "Null Fields Testcase Failed. See logs for details.");
        DriverUtility.log.info("Testcase completed");
        }

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (DriverUtility.driver != null) {
			DriverUtility.driver.close();
			DriverUtility.log.info("browser closed");
		}
	}

}
