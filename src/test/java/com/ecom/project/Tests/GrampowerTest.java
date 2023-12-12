package com.ecom.project.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.AddItem;
import com.ecom.project.Pages.Grampowerlogin;

public class GrampowerTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException, InterruptedException {
		DriverUtility.setUp(browser);
		Grampowerlogin login = new Grampowerlogin(null);
		login.performLogin("Automationuser", "grampower","Automationuser");
	}


	@Test(enabled=false,description = "Verify login happens correctly", priority = 1, groups = { "smoke" })
	public void loginTestWithValidCredentials(@Optional("Automationuser") String TC1_Username,
			@Optional("grampower") String TC1_Password) throws InterruptedException, IOException {
		// Perform the login action
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 1: Verify login happens correctly");
		Grampowerlogin login = new Grampowerlogin(DriverUtility.driver);
		status = login.performLogin(TC1_Username, TC1_Password, TC1_Username);
		if (status) {
			// Log success message
			DriverUtility.log.info("Test Case Completed: User login Successful");
		} else {
			// Log failure message with actual and expected values
			assertTrue(false, "Login failed. See logs for details.");
			DriverUtility.log.error("Test Case Failed: User login Failed. Expected Value: '{}', Actual Value: '{}'",
					"Welcome", login.actualWelcomeMsg);
			// assertTrue(res.isFlag(), res.getMessage())

		}
	}
	@Test(description = "Verify the flow ", priority = 1, groups = { "smoke" })
	public void ValidateFlow(@Optional("Product added.") String expectedtext) throws InterruptedException, IOException {
		Boolean status;
		DriverUtility.log.info("Start TestCase", "Test Case 2: Verify flow happens correctly");
		Grampowerlogin flow = new Grampowerlogin(DriverUtility.driver);
		flow.validateFlow();
		//assertTrue(status, "Product add to cart Failed. See logs for details.");
	    DriverUtility.log.info("Product added to cart and total validated");
		DriverUtility.log.info("Testcase completed");
	}

}
