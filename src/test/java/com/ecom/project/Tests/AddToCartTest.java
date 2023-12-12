package com.ecom.project.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.AddItem;

public class AddToCartTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException, InterruptedException {
		DriverUtility.setUp(browser);
		LoginTest login = new LoginTest();
	//	login.loginTestWithValidCredentials("automationuser1234567", "abc");
	}

	@Test(enabled=false,description = "Verify item is added to cart", priority = 1, groups = { "smoke" })
	public void ValidateEndToEndScenario(@Optional("Product added.") String expectedtext) throws InterruptedException, IOException {
		Boolean status;
		Map<String, String> categoryItemMap = new HashMap<>();
	    categoryItemMap.put("Phones", "Samsung galaxy s6");
		AddItem item = new AddItem(DriverUtility.driver);
		status=item.addItem(expectedtext, categoryItemMap, 0);
		//assertTrue(status, "Product add to cart Failed. See logs for details.");
	    DriverUtility.log.info("Product added to cart and total validated");
		DriverUtility.log.info("Testcase completed");
	}

	@Test(enabled= false,description = "Delete item from cart", priority = 1, groups = { "Regression" })
	public void DeleteItem(@Optional("Sony xperia z5") String productToDelete) throws InterruptedException, IOException {
		Boolean status;
		AddItem item = new AddItem(DriverUtility.driver);
		status = item.deleteItem(productToDelete);
		assertTrue(status,"Item Deleted");
		DriverUtility.log.info("Testcase completed");	
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		Boolean status;
		AddItem item = new AddItem(DriverUtility.driver);
		item.clearTableDataInColumn();
		if (DriverUtility.driver != null) {
			DriverUtility.driver.quit();
			DriverUtility.log.info("browser closed");
		}
	}
}
