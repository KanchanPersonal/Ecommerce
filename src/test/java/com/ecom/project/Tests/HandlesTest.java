package com.ecom.project.Tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.project.Pages.WindowHandles;

public class HandlesTest {
	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException {
		// public void launchBrowser() {
		DriverUtility.setUp(browser);
	}
	
	@Test(description = "Validate Window Handles", priority = 1, groups = { "smoke" })
	public void newWindow() throws InterruptedException, IOException {
	    // Visit the Registration page and register a new user
		boolean status;
		
        }
	}


