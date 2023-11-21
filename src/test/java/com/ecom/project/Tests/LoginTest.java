package com.ecom.project.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.Login;

public class LoginTest {

	// WebDriver setup code
	/*
	 * @BeforeMethod public void setUp() { WebDriver driver = new ChromeDriver();
	 * driver.manage().window().maximize();
	 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	 * this.driver = driver; }
	 */
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException {
		// public void launchBrowser() {
		// String browser = System.getProperty("browser");
		DriverUtility.setUp(browser);
	}

	@Test(description = "Verify login happens correctly", priority = 1, groups = {"smoke"})
	public void loginTestWithValidCredentials(@Optional("automationuser1234567") String TC1_Username,
			@Optional("abc") String TC1_Password) throws InterruptedException, IOException {
		// Perform the login action
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 4: Verify login happens correctly");
		Login login = new Login(DriverUtility.driver);
		status=login.performLogin(TC1_Username, TC1_Password,"Welcome "+TC1_Username);
		if (status) {
	            // Log success message
	            DriverUtility.log.info("Test Case Completed: User login Successful");
	        } else {
	            // Log failure message with actual and expected values
	        	assertTrue(false, "Login failed. See logs for details.");
	            DriverUtility.log.error("Test Case Failed: User login Failed. Expected Value: '{}', Actual Value: '{}'", "Welcome", login.actualWelcomeMsg);
		//assertTrue(res.isFlag(), res.getMessage())
	
	}
	}

	@Test(description = "Verify if password is correct", priority = 2, groups = {"Regression"})
	public void loginWithInvalidCredentials(@Optional("automationuser1234567") String TC1_Username,
			@Optional("abcyy") String TC1_Password) throws InterruptedException, IOException {
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 5: Verify incorrect login credentials");
		Login login = new Login(DriverUtility.driver);
		status=login.performLogin(TC1_Username, TC1_Password,"Wrong password.");
		if (status) {
            // Log success message
            DriverUtility.log.info("Test Case Completed: Cannot login for wrong password");
        } else {
            // Log failure message with actual and expected values
        	assertTrue(false, "Test failed. See logs for details.");
            DriverUtility.log.error("Test Case Failed: User login Failed. Expected Value: '{}', Actual Value: '{}'","Wrong password.", login.actualWelcomeMsg);
	}
	}

	@AfterMethod
	public void tearDown() {
		if (DriverUtility.driver != null) {
			DriverUtility.driver.quit();
		}
	}

}