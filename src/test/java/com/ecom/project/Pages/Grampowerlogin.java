package com.ecom.project.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ecom.core.DriverUtility;
import com.ecom.core.SeleniumUtils;

public class Grampowerlogin extends SeleniumUtils {

	public Grampowerlogin(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public String actualWelcomeMsg;

	public Boolean performLogin(String username, String password, String expectedText) throws InterruptedException {
		//DriverUtility.driver.switchTo().newWindow(WindowType.TAB);
		//DriverUtility.driver.get("https://data.grampower.com/hes/");
		switchToNewTabOrWindow("tab");
		gotoURL("https://data.grampower.com/hes/");
		boolean flag = false;
		typeText("cssSelector", "input[placeholder='Username']", username);
		typeText("cssSelector", "input[placeholder='Password']", password);
		clickOnElement("cssSelector", "input[value='LOG IN']");
		try {
			//if (expectedText.equals(username)) {
				actualWelcomeMsg = getText("cssSelector", "label[type='label']");
			//	getAlert("dismiss");//failing at alert
				clickOnElement("id", "onesignal-slidedown-cancel-button");
			     if (actualWelcomeMsg.equals(expectedText)) {
			            flag = true;
			            DriverUtility.log.info("Login is successful");
			        } else {
			            flag = false;
			        }
			    } catch (Exception e) {
			        // Handle specific exceptions and log accordingly
			        DriverUtility.log.error("Login failed with exception: " + e.getMessage());
			        flag = false;
			    }
			    return flag;
			}
	
	public void validateFlow()
	{ 
		switchToFrame("//iframe[@src='/hes/retail_dashboard?project=office']","FRAME_BY_ELEMENT");
		clickOnElement("xpath","//div[@title='Click to navigate to MAP View']");
		WebDriverWait wait = new WebDriverWait(DriverUtility.driver, Duration.ofSeconds(30));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//i[text()='Click here to close statics box'])")));
		element.click();
		//clickOnElement("xpath","//i[text()='Click here to close statics box']");
		clickOnElement("xpath","//img[@src='https://maps.gstatic.com/mapfiles/transparent.png']");
	}
}
