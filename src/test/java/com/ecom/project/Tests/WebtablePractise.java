package com.ecom.project.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ecom.core.DriverUtility;
import com.ecom.core.SeleniumUtils;

//Print only first row, last row etc.,
//Print only one column data

public class WebtablePractise extends SeleniumUtils {

//	public WebtablePractise(WebDriver driver) {
		// TODO Auto-generated constructor stub
//	}

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void launchBrowser(String browser) throws IOException {
		// public void launchBrowser() {
		// String browser = System.getProperty("browser");
		DriverUtility.setUp(browser);
	}

	// Print only headers
	@Test(enabled = false ,description = "print headers", priority = 1, groups = { "smoke" })
	public void AddItem() throws InterruptedException, IOException {
		// Visit the Registration page and register a new user

		// Map<String, String> categoryItemMap = new HashMap<>();//take list for
		// phones,laptops as items under same category will overwrite
		Map<String, List<String>> categoryItemMap = new HashMap<>();
		categoryItemMap.put("Phones", List.of("Samsung galaxy s6", "Nokia lumia 1520"));
		categoryItemMap.put("Laptops", List.of("Sony vaio i5"));
		for (Map.Entry<String, List<String>> entry : categoryItemMap.entrySet()) {
			String category = entry.getKey();
			List<String> items = entry.getValue();
			for (String itemName : items) {
				// Add item to the cart
				clickOnElement("xpath", "//a[text()='" + category + "']");
				clickOnElement("xpath", "//a[text()='" + itemName + "']");
				clickOnElement("xpath", "//a[text()='Add to cart']");
				getAlert("accept");
				navigateTo("Home");
			}
		}
	}

	public void printTableHeader() {
		navigateTo("Cart");
		List<WebElement> headerCells = getElements("xpath", "//table//th");
		for (WebElement headerCell : headerCells) {
			DriverUtility.log.info("Table Header: " + headerCell.getText());
		}
		DriverUtility.log.info(" first row, last row are");
	}

	public void printTableRow() {
		navigateTo("Cart");
		List<WebElement> rows = getElements("xpath", "//table//tr");
		for (WebElement row : rows) {
			List<WebElement> Cols = row.findElements(By.tagName("td")); // cant use generic findelements
			for (WebElement Cel : Cols) {
				DriverUtility.log.info(Cel.getText() + " ");
			}
		}
	}

	public void printColumn() {
		navigateTo("Cart");
		List<WebElement> headerCells = getElements("xpath", "//table//th");
		List<String> Colname = new ArrayList<String>();
		String ColumnName;
		for (WebElement headerCell : headerCells) {
			ColumnName = headerCell.getText();
			Colname.add(ColumnName);
		}
		int index = Colname.indexOf("Title");
		List<WebElement> ColumnData = getElements("xpath", "//table//td[" + (index + 1) + "]");
		for (WebElement coldata : ColumnData) {
			DriverUtility.log.info(coldata.getText() + " ");
		}
	}

	public void printFirstLastrow() {
		navigateTo("Cart");
		List<WebElement> rows = getElements("xpath", "//table//tr[1]//td");
		for (WebElement row : rows) {
			DriverUtility.log.info(row.getText() + " ");
		}
		List<WebElement> lastrow = getElements("xpath", "//table//tr[last()]//td");
		for (WebElement lasrow : lastrow) {
			DriverUtility.log.info(lasrow.getText() + " ");
		}
	}

	// Dynamic webtable
	public void dynamicTable() throws InterruptedException {
		switchToNewTabOrWindow("tab");//doesnot work in incognito mode
		gotoURL("https://seleniumpractise.blogspot.com/2021/08/webtable-in-html.html");
		List<WebElement> getHeaders = getElements("xpath", "//table[@id='customers']//th");// .size will give header
		boolean status = false;
		// verify if column name exists
		for (WebElement ele : getHeaders) {
			String value = ele.getText();
			if (value.contains("Country")) {
				status = true;
				break;
			}
		}
		Assert.assertTrue(status);
		List<WebElement> noOfRows = getElements("xpath", "//table[@id='customers']//tr");// .size will give rows
		int rowsize = noOfRows.size();
		List<WebElement> allData = getElements("xpath", "//table[@id='customers']//tr//td[2]"); // all 2nd column values- company
		for (WebElement ele : allData) {
			String value = ele.getText();
			System.out.println(value);
			if (value.contains("ola")) {
				status = true;
				break;
			}
		}
		//to click on checkbox before selenium-//td[text()='Selenium']//preceding-sibling::td//input-only giving preceding or following it will identify all before or after the word, so use sibling
		//td[text()='Selenium']//following-sibling::td[3]//a
		

	}
	@Test(description = "Delete item from cart", priority = 1, groups = { "smoke" })
	public void Dynamictable() throws InterruptedException, IOException {
		WebtablePractise item = new WebtablePractise();
		item.dynamicTable();
		//assertTrue(status,"Item Deleted");
		DriverUtility.log.info("Testcase completed");	
	}
	
}
