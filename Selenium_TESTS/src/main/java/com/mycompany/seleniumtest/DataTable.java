/**
 * This class creates objects to represent a data table on a web page
 */
package com.mycompany.seleniumtest;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataTable {
	WebDriver driver;
	By locator;
	//int numColumns;
	String tableXpath;
	
	public DataTable(WebDriver driver, By locator, String tableXpath){
		this.driver = driver;
		this.locator = locator;
		this.tableXpath = tableXpath;
		//this.numColumns = numColumns; Maybe add this and isValidColumn method later
	}
	
	/**
	 * Finds the number of rows in a table
	 * 
	 * @param driver
	 * @return number of rows in table
	 */
	public int getNumRows(){
		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		return rows.size();
	}
	
	
	public WebElement getColumnHeader(int col){
		WebElement headerCell = ScrollUtilities.scrollToElement(driver, By.xpath(AssortedUtilities.stringFromBy(locator)
				   				                                                 + "/tr/td["+col+"]"));
		return headerCell;
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
	public int getRowNum(int col, String text){ 
		if(!cellTextExists(col, text)){
			Log.warn("Text '" + text + "' does not exist in specified column");
			return -1;
		}
		//WebElement table = driver.findElement(tableLocator);
		WebElement table = ScrollUtilities.scrollToElement(driver, locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i = 1; i <= rows.size(); i++){
			String cellText = getCellText(i, col);
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
	public boolean cellTextExists(int col, String value){
		WebElement table = driver.findElement(locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		WebElement cell;
		for(int i = 1; i <= rows.size(); i++){
			cell = getCell(i, col);
			if(cell.getText().equals(value)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method gets a specified cell from a table.  
	 * 
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return the desired cell
	 */
	public WebElement getCell(int row, int col){
		WebElement cell = ScrollUtilities.scrollToElement(driver, By.xpath(AssortedUtilities.stringFromBy(locator)
																		   + "/tr["+row+"]/td["+col+"]"));
		return cell;
	}
	
	/**
	 * This method returns the xpath which can be used to locate a particular cell
	 * It can be useful for locating elements within a cell.
	 * 
	 * @param row the row of the desired cell
	 * @param col the column of the desired cell
	 * @return xpath which can be used to locate cell
	 */
	public String getCellXpath(int row, int col){
		return AssortedUtilities.stringFromBy(locator) + "/tr["+row+"]/td["+col+"]";
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
	public String getCellText(int row, int col){
		return getCell(row, col).getText();
	}
	
	/**
	 * This method goes through each row of a table looking for a particular value in a particular
	 * column.  It returns the corresponding cell from the second specified column from the first
	 * matching row.  
	 * 
	 * If no match is found, returns null.
	 * 
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public WebElement getCorrespondingCell(int keyCol, String keyVal, int checkCol){
		WebElement table = ScrollUtilities.scrollToElement(driver, locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i = 1; i <= rows.size(); i++){
			WebElement cell = getCell(i, keyCol);
			if(cell.getText().equals(keyVal)){
				return getCell(i, checkCol);
			}
		}
		return null;
	}
	
	/**
	 * This method checks a cell in each row for a particular value and returns the cells
	 * in a particular column for rows which match.
	 * 
	 * If no matches are found, it returns an empty array list.
	 * 
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public ArrayList<WebElement> getCorrespondingCells(int keyCol, String keyVal, int checkCol){														      
		//WebElement table = driver.findElement(tableLocator);
		WebElement table = ScrollUtilities.scrollToElement(driver, locator);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		ArrayList<WebElement> matches = new ArrayList<WebElement>();
		for(int i = 1; i <= rows.size(); i++){
			WebElement cell = getCell(i, keyCol);
			if(cell.getText().equals(keyVal)){
				matches.add(getCell(i, checkCol));
			}
		}
		return matches;
	}
	
	/**
	 * This method goes through each row of a table looking for a particular value in a particular
	 * column.  It returns the text from the corresponding cell from the second specified column from the first
	 * matching row.  
	 * 
	 * If no match is found, returns null.
	 * 
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public String getCorrespondingCellText(int keyCol, String keyVal, int checkCol){
		return getCorrespondingCell(keyCol, keyVal, checkCol).getText();
	}
	
	/**
	 * This method checks a cell in each row for a particular value and returns the text from
	 * the cells in a particular column for rows which match.
	 * 
	 * If no matches are found, it returns an empty String[].     
	 * 
	 * @param keyCol column you are checking for the key value
	 * @param keyVal value you are checking for
	 * @param checkCol column whose value you want returned
	 * @return the value from the cell in the matching row.
	 */
	public String[] getCorrespondingCellsText(int keyCol, String keyVal, int checkCol){
		ArrayList<WebElement> specifiedCells = getCorrespondingCells(keyCol, keyVal, checkCol);																				 																				
		String[] specifiedCellsText = new String[specifiedCells.size()];
		for(int i = 0; i < specifiedCells.size(); i++){
			specifiedCellsText[i] = specifiedCells.get(i).getText();
		}
		return specifiedCellsText;
	}
	
	public WebElement getCellByText(WebDriver driver, By tableLocator, String cellText){
		List<WebElement> cells = driver.findElements(By.tagName("td"));
		for(WebElement cell:cells){
			if(cell.getText().equals(cellText)){
				return cell;
			}
		}
		Log.warn("Cell with correct text not found in table");
		return null;
	}
	
	public By getLocator(){
		return locator;
	}
	
	public String getTableXpath(){
		return tableXpath;
	}
	
	public String toString(){
		return locator.toString();
	}
	
}
