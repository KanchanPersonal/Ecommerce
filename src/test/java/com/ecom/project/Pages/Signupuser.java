package com.ecom.project.Pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;

import com.ecom.core.DriverUtility;
import com.ecom.core.SeleniumUtils;
import com.ecom.core.utilites.Utlities;

public class Signupuser extends SeleniumUtils {
	public String alertText;
//	public String actualWelcomeMsg;
	Utlities utility = new Utlities();

	public Signupuser(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public boolean performRegistration(String username, String password, String expectedText) {
		boolean flag = false;
		try {
			clickOnElement("linktext", "Sign up");
			typeText("id", "sign-username", username);
			typeText("id", "sign-password", password);
			clickOnElement("xpath", "//button[@onclick='register()']");
			// log message succesfully enter the details in the registration 
			DriverUtility.log.info("User Registration Successful");
			String actualWelcomeMsg= getAlert("getText");
			// Text on the alert is actualWelcom
			flag = actualWelcomeMsg.equals(expectedText); //write log with actual expected values
			if(!flag)
			{
				 DriverUtility.log.error("User Registration Failed. Expected Value: '{}', Actual Value: '{}'", "Sign up successful.",actualWelcomeMsg);
			}
		} catch (Exception e) {
			// Handle exceptions if needed
		}
		return flag;
	}
	
	
	public boolean register() {
		return performRegistration(utility.generateRandomString("alphabets", 7),
				utility.generateRandomString("alphanumeric", 7), "Sign up1 successful.");
	}
	
	//public boolean verifyRegister(String username, String password, String expectedText) {
	  //  return performRegistration(username, password, expectedText);
	    // validations here
	}

	// Single Method.. registration(u, p, expText)

