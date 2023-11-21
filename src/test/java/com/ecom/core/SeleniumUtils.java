package com.ecom.core;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtils  {

	// Finding the elements - single, multiple
	// SendKeys
	// Click
	// Handling Alerts
	// handleAlert() -- Accept, Dismiss, get the text, enter the text
	// Waits
	// Handle the Mouse Actions
	// Current URL, Title
	// Handling Multiple browser tabs - not
	// Handling Frames - done
	// get text
	// Reusable and Generic methods used by all the pages
	// Scrolling - Scroll to an element, scroll down, scroll top - done
	//fileupload

	public WebElement getElement(String locatoryType, String locValue) {
		List<WebElement> list = getElements(locatoryType, locValue);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public List<WebElement> getElements(String locatoryType, String locValue) {
	//	return DriverUtility.driver.findElements(getByObject(locatoryType, locValue));
		WebDriverWait wait = new WebDriverWait(DriverUtility.driver, Duration.ofSeconds(30));
	    List<WebElement> elements = null;

	    try {
	        elements = waitForElement(getByObject(locatoryType, locValue), "visibility", 30);
	    } catch (Exception e) {
	        System.out.println("An exception occurred: " + e.getMessage());
	    }

	    return elements;

	}
	
	//reusable explicit wait conditions for comman used methods like usability,clickable,alertis present,urlcontains
	

	public void clickOnElement(String locatoryType, String locValue) {
		getElement(locatoryType, locValue).click();
	}

	private By getByObject(String locatorType, String locatorValue) {
		By byObject = null;

		switch (locatorType) {
		case "id":
			byObject = By.id(locatorValue);
			break;
		case "name":
			byObject = By.name(locatorValue);
			break;
		case "className":
			byObject = By.className(locatorValue);
			break;
		case "xpath":
			byObject = By.xpath(locatorValue);
			break;
		case "linktext":
			byObject = By.linkText(locatorValue);
			break;
		case "partiallink":
			byObject = By.partialLinkText(locatorValue);
			break;
		case "cssSelector":
			byObject = By.cssSelector(locatorValue);
			break;
		case "tagname":
			byObject = By.tagName(locatorValue);
			break;
		default:
			System.out.println("Locator type not found.");
		}

		return byObject;
	}

	public void typeText(String locatorType, String locatorValue, String text) {
		WebElement element = getElement(locatorType, locatorValue);

		if (element != null) {
			element.clear(); // Optional if needed
			element.sendKeys(text);
		}
	}
	
	public void typeText(WebElement element, String text) {

		if (element != null) {
			element.clear(); // Optional if needed
			element.sendKeys(text);
		}
	}

	public String getAlert(String action) { //change name to HandleAlert
		WebDriverWait wait = new WebDriverWait(DriverUtility.driver, Duration.ofSeconds(30));
		Alert alert = DriverUtility.driver.switchTo().alert();
		wait.until(ExpectedConditions.alertIsPresent());
		if (action.equalsIgnoreCase("accept")) {
			alert.accept();
			return null;
		} else if (action.equals("dismiss")) {
			alert.dismiss();
			return null;
		} else if (action.equals("getText")) {
			return alert.getText();

		}
		return null;
	}
	
	public String getText(String locatorType, String locatorValue) {
	    List<WebElement> elements = getElements(locatorType, locatorValue);
	    if ( elements.size() > 0) {
	        return elements.get(0).getText();
	    } else {
	        return null;
	    }
	}
	   
	    public static void performMouseAction(String action, WebElement element) {
	        switch(action) {
	            case "click":
	            	DriverUtility.action.click(element).build().perform();
	                break;
	            case "rightClick":  // Add this case for right-click
	            	DriverUtility.action.contextClick(element).build().perform();
	                break;
	            case "click_and_hold":
	            	DriverUtility.action.clickAndHold(element).build().perform();
	                break;
	            case "single_click":
	            	DriverUtility.action.click(element).build().perform();
	                break;
	            case "double_click":
	            	DriverUtility.action.doubleClick(element).build().perform();
	                break;
	            case "move_to_element":
	            	DriverUtility.action.moveToElement(element).build().perform();
	                break;
	            default:
	                System.out.println("Invalid action specified");
	        }
	    }
	    
	    
	
	public List<WebElement> waitForElement(By locator, String waitType, int timeoutInSeconds) {
	    WebDriverWait wait = new WebDriverWait(DriverUtility.driver, Duration.ofSeconds(timeoutInSeconds)); // Use Duration for timeout

	    List<WebElement> element = new ArrayList<>(); 
	//    wait.until(new Function<>{})
	    
	    
	    
	    // Comparator Interface - java collections framework 
	    // To compare the objects in the list -- List<Integers>, List<Employee>
	        
	      switch (waitType) {
	          case "visibility":
	              element.add( wait.until(ExpectedConditions.visibilityOfElementLocated(locator)));
	              break;
	          case "clickable":
	              element.add(wait.until(ExpectedConditions.elementToBeClickable(locator)));
	              break;
	          case "presence":
	              element.add(wait.until(ExpectedConditions.presenceOfElementLocated(locator)));
	              break;
	          case "visibilityall":
	              element.addAll(wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)));
	              break;
	          default:
	              System.out.println("Invalid wait type specified");
	      }
	        
	      return element;
	  }
	
	 public static String getCurrentURL() {
	        return DriverUtility.driver.getCurrentUrl();
	    }
	    
	    public static String getPageTitle() {
	        return DriverUtility.driver.getTitle();
	    }
	
	public String getCurrentWindowID()
	{
		return DriverUtility.driver.getWindowHandle();	
	}
	
	public void scrollToElement(WebElement element)
	{
		// By object --> 
		  //Actions actions = new Actions(DriverUtility.driver);
		DriverUtility.action.scrollToElement(element).perform();	
	}
	public void scrollToElementByAmount(int x, int y)
	{
		DriverUtility.action.scrollByAmount(x,y).perform();	
	}
	
//pass type 
    public void switchToFrame(String frameIdOrName) {
    	DriverUtility.driver.switchTo().frame(frameIdOrName);
    }

    public void switchToFrame(WebElement frameElement) {
    	DriverUtility.driver.switchTo().frame(frameElement);
    }

    public void switchToDefaultContent() {
    	DriverUtility.driver.switchTo().defaultContent();
    }
	
    public void switchToNewTab() {
    	DriverUtility.driver.switchTo().newWindow(WindowType.TAB);
    	// opening the new tab with empty page
    }
    public void switchToNewWindow() {
    	DriverUtility.driver.switchTo().newWindow(WindowType.WINDOW);
    }
	
    // Click on Button a new tab will open with new url
    
    public void test() {
    	square(10);
    //	int res = x -> x*x;
    }
    
    public int square(int x) {
    	int res = x * x;
    	return res;
    }
	
}
