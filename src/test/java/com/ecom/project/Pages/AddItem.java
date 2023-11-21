package com.ecom.project.Pages;

import com.ecom.core.SeleniumUtils;

public class AddItem  extends SeleniumUtils {
	public String alertText;
	
	public void addItem() {
		
		clickOnElement("xpath", "//a[text()='Phones']");
		clickOnElement("xpath", "//a[text()='Iphone 6 32gb']");
		clickOnElement("xpath", "//a[text()='Add to cart']");
		alertText = getAlert("getText");
		}
}