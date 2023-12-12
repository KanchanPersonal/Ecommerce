package com.ecom.project.Pages;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.ecom.core.DriverUtility;
import com.ecom.core.SeleniumUtils;

public class AddItem extends SeleniumUtils {

	public AddItem(WebDriver driver) {
		// TODO Auto-generated constructor stub
	}

	public boolean addItem(String expectedText, Map<String, String> categoryItemMap, int expectedTotal) {
	    try {
	        for (Map.Entry<String, String> entry : categoryItemMap.entrySet()) {
	            String category = entry.getKey();
	            String itemName = entry.getValue();

	            // Add item to the cart
	            clickOnElement("xpath", "//a[text()='" + category + "']");
	            clickOnElement("xpath", "//a[text()='" + itemName + "']");
	            clickOnElement("xpath", "//a[text()='Add to cart']");

	            // Wait for the Alert
	            String actualAlert = getAlert("getText");

	            // Log information about the success or failure for each product
	            if (actualAlert.equals(expectedText)) {
	                DriverUtility.log.info("Product added successfully. Actual Alert: " + actualAlert);
	            } else {
	                DriverUtility.log.error("Failed to add product. Actual Alert: " + actualAlert);
	                return false;  // Return false immediately if adding the product fails
	            }

	            // Navigate to the Home page
	            navigateTo("Home");
	        }

	        // Validate the total in the cart after adding items
	        navigateTo("Cart"); // Assuming this method navigates to the cart
	        List<WebElement> columnCells = getElements("xpath", "//table//tr/td[3]");

	        if (!columnCells.isEmpty()) {
	            int actualTotal = Integer.parseInt(getText("id", "totalp"));
	            int sumOfColumn = columnCells.stream().mapToInt(cell -> Integer.parseInt(cell.getText())).sum();
	            //columnCells.stream()
	            			//.map(cell -> cell.getText())
	            			//.reduce(0, ));.
	            DriverUtility.log.info("Actual total from the webpage: " + actualTotal);
	            DriverUtility.log.info("Number of cells in the third column: " + columnCells.size());
	            DriverUtility.log.info("Sum of cell values in the specified column: " + sumOfColumn);

	            boolean totalValidationFlag = actualTotal == sumOfColumn && actualTotal == expectedTotal;
	            DriverUtility.log.info("Validation result - Total matches sum of column values and matches expected total: " + totalValidationFlag);

	            if (!totalValidationFlag) {
	                DriverUtility.log.error(
	                        "Validation failed - Total does not match sum of column values or does not match expected total. Expected Total: '{}', Actual Total: '{}'",
	                        expectedTotal, actualTotal);
	                return false;  // Return false immediately if total validation fails
	            }
	        }

	        return true;  // Return true if all products are added and total validation is successful
	    } catch (Exception e) {
	        DriverUtility.log.error("Error adding product to cart or validating total: " + e.getMessage());
	        return false;
	    }
	}


/*	public boolean validateTotal() {
		navigateTo("Cart"); // Assuming this method navigates to the cart
		int sumOfColumn = 0;
		List<WebElement> columnCells = getElements("xpath", "//table//tr/td[3]");
		int actualTotal = Integer.parseInt(getText("id", "totalp"));
		DriverUtility.log.info("Actual total from the webpage: " + actualTotal);
		int numberOfCells = columnCells.size();
		for (WebElement cell : columnCells) {
			int cellValue = Integer.parseInt(cell.getText());
			sumOfColumn += cellValue;
		}
		DriverUtility.log.info("Number of cells in the third column: " + columnCells.size());
		DriverUtility.log.info("Sum of cell values in the specified column: " + sumOfColumn);

		boolean flag = actualTotal == sumOfColumn;//validate with expected total from user
		DriverUtility.log.info("Validation result - Total matches sum of column values: " + flag);
		if (!flag) {
			DriverUtility.log.error(
					"Validation failed - Total does not match sum of column values. Expected Total: '{}', Actual Total: '{}'",
					sumOfColumn, actualTotal);
		}
		return flag;
	}
	*/
	// Navigate to Cart

	public Boolean deleteItem(String itemName) {
	    List<WebElement> columnCells = getElements("xpath", "//table//td[2]");
	    int beforeDelete = Integer.parseInt(getText("id", "totalp"));
	    // Check if the columnCells list is not empty
	    if (!columnCells.isEmpty()) {
	        boolean itemFound = false;

	        for (WebElement cell : columnCells) {
	            String cellText = cell.getText();

	            if (cellText.equals(itemName)) {
	                clickOnElement("xpath", "//table//a[text()='Delete']");
	                itemFound = true;
	                break;
	            }
	        }

	        // Check if the item was not found or the columnCells list is empty
	        if (!itemFound) {
	            DriverUtility.log.warn("Item '" + itemName + "' not found for deletion.");
	            return false;
	        }
	    } else {
	        DriverUtility.log.warn("Column cells are empty.");
	        return false;
	    }

	    // Move the 'afterdelete' declaration outside the if block
	    int afterDelete = Integer.parseInt(getText("id", "totalp"));
	    
	    boolean totalChanged = beforeDelete != afterDelete;
	    
	    // Logging the total before and after deletion
	    DriverUtility.log.info("Total before deletion: " + beforeDelete);
	    DriverUtility.log.info("Total after deletion: " + afterDelete);
	    
	    return totalChanged;
	}
	
	public void clearTableDataInColumn() {
		 List<WebElement> deleteLinks = getElements("xpath", "//a[text()='Delete']");

		    if (!deleteLinks.isEmpty()) {
		        for (WebElement deleteLink : deleteLinks) {
		            deleteLink.click();
		        }
		    } else {
		        // Log a message or take appropriate action if the list is empty
		        DriverUtility.log.info("no items to delete");
		    		}	    
	}
}

