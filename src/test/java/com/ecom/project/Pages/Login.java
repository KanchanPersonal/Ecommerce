package com.ecom.project.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ecom.core.DriverUtility;
import com.ecom.core.SeleniumUtils;
import com.ecom.core.utilites.Result;

public class Login extends SeleniumUtils {
	public String alertText;
	public String actualWelcomeMsg;

	@FindBy(id = "loginusername")
	public WebElement userNameTxtBox;
	
	
	public Login(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}


	public void enterUserName(String userName) {
		//userName.sendKeys(userName);
		typeText(userNameTxtBox,  userName);
	}
	

	public boolean performLogin(String username, String password, String expectedText) {
	    boolean flag = false;
	  //  Result res = new Result();//another approch of returning res obj
	    try {
	        clickOnElement("linktext", "Log in");
	        typeText("id", "loginusername", username);
	        typeText("id", "loginpassword", password);
	        clickOnElement("xpath", "//button[@onclick='logIn()']");

	        actualWelcomeMsg = null;
	        if (expectedText.equals("Welcome "+username)) {
	            // If logging in successfully, look for the welcome message
	        	// Login is successful wainting for the message Welcome musername
	            actualWelcomeMsg = getText("id", "nameofuser");
	        } else {
	            // If there's an error, check for the error message in the alert
	            actualWelcomeMsg = getAlert("getText");
	        }
	        flag = actualWelcomeMsg.equals(expectedText);
	        DriverUtility.log.info("Login is succesful");

	      //  res.setFlag(flag);
	     //   res.setMessage("Expected Result = "+expectedText+" Actual Result = "+actualWelcomeMsg);
	    } catch (Exception e) {
	        // Handle exceptions if needed
	    }
	  //  return res;
		return flag;
	}

	public boolean loginUser(String username, String password) {
	    return performLogin(username, password, "Welcome");
	}
	
	public boolean wrongUser(String username, String password) {
	    return performLogin(username, password, "Wrong password.");
	}


	}

