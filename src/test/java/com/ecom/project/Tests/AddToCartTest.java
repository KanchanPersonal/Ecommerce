package com.ecom.project.Tests;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.AddItem;
import com.ecom.project.Pages.Login;

public class AddToCartTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException {
		
		DriverUtility.setUp(browser);
		//AssertTrue(loginUser())
	}
	
	
	@Test(description = "Verify item is added to card", priority = 1, groups = {"smoke"})
	public void addToItemTest() {
		AddItem item = new AddItem();
		item.addItem();
		
		
	}
}

