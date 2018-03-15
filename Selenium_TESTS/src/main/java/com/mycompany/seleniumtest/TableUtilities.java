package com.mycompany.seleniumtest;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TableUtilities {
	
	public static int getNumRows(WebDriver driver, By tableLocator){
		WebElement table = driver.findElement(tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows.size();
	}
	
	/**
	 * This method returns the first row in which the specified text can be found in the specified
	 * column, or -1 if it can not be found
	 * 
	 * @param driver
	 * @param tableLocator locator of the table to search
	 * @param col column to search
	 * @param text text to search for
	 * @return the first row in which the text occurs, or -1
	 */
	public static int getRowNum(WebDriver driver, By tableLocator,  int col, String text){ 
		if(!cellTextExists(driver, tableLocator, col, text)){
			//Log.warn("Text '" + text + "' does not exist in specified column");
			return -1;
		}
		//WebElement table = driver.findElement(tableLocator);
		WebElement table = ScrollUtilities.scrollToElement(driver, tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i = 1; i <= rows.size(); i++){
			String cellText = getCellText(driver, tableLocator, i, col);
			if(cellText.equals(text)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * This method establishes whether or not a cell with the given text exists in 
	 * the given column.
	 * 
	 * @param driver a WebDriver instance
	 * @param tableLocator the locator for the table
	 * @param col the column to check
	 * @param value the value to look for
	 * @return whether or not the value occurs in the given column.
	 */
	public static boolean cellTextExists(WebDriver driver, By tableLocator, int col, String value){
		WebElement table = driver.findElement(tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		WebElement cell;
		for(int i = 1; i <= rows.size(); i++){
			cell = getCell(driver, tableLocator, i, col);
			if(cell.getText().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method gets a specified cell from a table.  
	 * 
	 * @param driver a WebDriver instance
	 * @param tableLocator the locator of the table
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return the desired cell
	 */
	public static WebElement getCell(WebDriver driver, By tableLocator, int row, int col){
		WebElement cell = ScrollUtilities.scrollToElement(driver, By.xpath(AssortedUtilities.stringFromBy(tableLocator)+ "/tr["+row+"]/td["+col+"]"));
		return cell;
	}
	
	/**
	 * This method returns the xpath which can be used to locate a particular cell.
	 * It can be useful for locating elements within a cell.
	 * 
	 * @param driver a WebDriver instance
	 * @param tableLocator the locator of the table
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return xpath which can be used to locate cell
	 */
	public static String getCellXpath(WebDriver driver, By tableLocator, int col){
		return AssortedUtilities.stringFromBy(tableLocator) + "/tr/td["+col+"]";
	}
	
	/**
	 * This method returns the xpath which can be used to locate a particular cell
	 * in a table with only one row.
	 * It can be useful for locating elements within a cell.
	 * 
	 * @param driver a WebDriver instance
	 * @param tableLocator the locator of the table
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return xpath which can be used to locate cell
	 */
	public static String getCellXpath(WebDriver driver, By tableLocator, int row, int col){
		return AssortedUtilities.stringFromBy(tableLocator) + "/tr["+row+"]/td["+col+"]";
	}

	/**
	 * This method gets the text in a specified cell from a table.  
	 * 
	 * @param driver a WebDriver instance
	 * @param tableLocator the locator of the table
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return the desired cell
	 */
	public static String getCellText(WebDriver driver, By tableLocator, int row, int col){
		return getCell(driver, tableLocator, row, col).getText();
	}

	/**
	 * This method goes through each row of a table looking for a particular value in a particular
	 * column.  It returns the corresponding cell from the second specified column from the first
	 * matching row.  
	 * 
	 * If no match is found, returns null.
	 * 
	 * @param driver WebDriver instance
	 * @param tableLocator locator of the table
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public static WebElement getCorrespondingCell(WebDriver driver, By tableLocator, int keyCol,
												  String keyVal, int checkCol){
		WebElement table = ScrollUtilities.scrollToElement(driver, tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i = 1; i <= rows.size(); i++){
			WebElement cell = getCell(driver, tableLocator, i, keyCol);
			if(cell.getText().equals(keyVal)){
				return getCell(driver, tableLocator, i, checkCol);
			}
		}
		return null;
	}
	
	/**
	 * This method goes through each row of a table looking for a particular value in a particular
	 * column.  It returns the text from the corresponding cell from the second specified column from the first
	 * matching row.  
	 * 
	 * If no match is found, returns null.
	 * 
	 * @param driver WebDriver instance
	 * @param tableLocator locator of the table
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public static String getCorrespondingCellText(WebDriver driver, By tableLocator, int keyCol,
			                                      String keyVal, int checkCol){
		return getCorrespondingCell(driver, tableLocator, keyCol, keyVal, checkCol).getText();
	}

	/**
	 * This method checks a cell in each row for a particular value and returns the cells
	 * in a particular column for rows which match.
	 * 
	 * If no matches are found, it returns an empty array list.
	 * 
	 * @param driver WebDriver instance
	 * @param tableLocator locator of the table
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public static ArrayList<WebElement> getCorrespondingCells(WebDriver driver, By tableLocator, int keyCol, String keyVal, int checkCol){
		//WebElement table = driver.findElement(tableLocator);
		WebElement table = ScrollUtilities.scrollToElement(driver, tableLocator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		ArrayList<WebElement> matches = new ArrayList<WebElement>();
		for(int i = 1; i <= rows.size(); i++){
			WebElement cell = getCell(driver, tableLocator, i, keyCol);
			if(cell.getText().equals(keyVal)){
				matches.add(getCell(driver, tableLocator, i, checkCol));
			}
		}
		return matches;
	}

	/**
	 * This method checks a cell in each row for a particular value and returns the text from
	 * the cells in a particular column for rows which match.
	 * 
	 * If no matches are found, it returns an empty String[].     
	 * 
	 * @param driver WebDriver instance
	 * @param tableLocator locator of the table
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public static String[] getCorrespondingCellsText(WebDriver driver, By tableLocator, int keyCol, String keyVal, int checkCol){
		ArrayList<WebElement> specifiedCells = getCorrespondingCells(driver, tableLocator, keyCol, keyVal, checkCol);
		String[] specifiedCellsText = new String[specifiedCells.size()];
		for(int i = 0; i < specifiedCells.size(); i++){
			specifiedCellsText[i] = specifiedCells.get(i).getText();
		}
		return specifiedCellsText;
	}
	
	public static WebElement getCellByText(WebDriver driver, By tableLocator, String cellText){
		List<WebElement> cells = driver.findElements(By.tagName("td"));
		for(WebElement cell:cells){
			if(cell.getText().equals(cellText)){
				return cell;
			}
		}
		//Log.warn("Cell with correct text not found in table");
		return null;
	}
	
	public static String getCellXpath(String tableXpath, int row, int col){
		return tableXpath + "/tr["+row+"]/td["+col+"]";
	}

}
