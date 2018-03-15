package com.mycompany.seleniumtest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FormControlUtilities {

	/**
	 * This method overwrites the current contents of a text field
	 * 
	 * @param driver WebDriver instance
	 * @param locator locator for textfield
	 * @param value value to put in textfield
	 */
	public static void overwriteTextField(WebDriver driver, By locator, String value){
		ScrollUtilities.scrollToElement(driver, locator);
	    WebElement field = driver.findElement(locator);
	    field.click(); //Work around for problem where clear wasn't working in iedriver
	    field.clear();
	    field.sendKeys(value);
	}
	
	/**
	 * Returns number of options in combobox.  Useful for iterating
	 * through all options.
	 * 
	 * @param driver
	 * @param locator locator of combobox
	 * @return number of options in combobox
	 */
	public static int getNumOptions(WebDriver driver, By locator){
		ScrollUtilities.scrollToElement(driver, locator);
		Select fieldBox = new Select(driver.findElement(locator));
		return fieldBox.getOptions().size();
	}
	
	/**
	 * This method selects an option in a combo box based on it's visible or
	 * value text. Note: It is VERY picky, if something isn't working, go in and
	 * copy paste text from Firebug.
	 * 
	 * @param driver WebDriver instance
	 * @param locator locator for the combobox
	 * @param option option to be selected
	 */
	public static void selectFromComboBox(WebDriver driver, By locator, String option){
		ScrollUtilities.scrollToElement(driver, locator);
	    Select fieldBox = new Select(driver.findElement(locator));
	    try {
	        fieldBox.selectByVisibleText(option);
	    } catch (NoSuchElementException e) {
	        fieldBox.selectByValue(option);
	    }        
	}
	
	/**
	 * This method selects the option from a combobox based on the
	 * specified index.
	 * 
	 * @param driver WebDriver instance
	 * @param locator locator for the combobox
	 * @param option option to be selected
	 */
	public static void selectFromComboBox(WebDriver driver, By locator, int index){
		ScrollUtilities.scrollToElement(driver, locator);
	    Select fieldBox = new Select(driver.findElement(locator));
	    fieldBox.selectByIndex(index);       
	}
	
	public static String findSelectedRadioButton(WebDriver driver, By locator){
		ScrollUtilities.scrollToElement(driver, locator);
		List<WebElement> radioButtons = driver.findElements(locator);
	    int num = radioButtons.size();
	    String selected = "...";
	    for (int i = 0; i < num; i ++){
	        if(radioButtons.get(i).isSelected()){
	        	selected = radioButtons.get(i).getAttribute("value");
	        }
	    }
	    return selected;
	}

	/**
	 * This method selects a radio button from a set
	 * 
	 * @param driver WebDriver instance
	 * @param locator the locator for the SET of radio buttons (must be By.name())
	 * @param valueToSelect the value of the radio button to be selected
	 */
	public static void chooseRadioButton(WebDriver driver, By locator, String valueToSelect){
		ScrollUtilities.scrollToElement(driver, locator);
	    List<WebElement> radioButtons = driver.findElements(locator);
	    int num = radioButtons.size();
	    for (int i = 0; i < num; i ++){
		        String value = radioButtons.get(i).getAttribute("value");
		        if(value != null &&  value.equalsIgnoreCase(valueToSelect)){
		            radioButtons.get(i).click();
		        }
	    }
	}

	/**
	 * This method checks or unchecks a specified checkbox
	 * 
	 * @param driver WebDriver instance
	 * @param locator locator for the checkbox to be checked/unchecked
	 * @param checked true if the checkbox should be checked, false otherwise
	 */
	public static void setCheckbox(WebDriver driver, By locator,  boolean checked){
		ScrollUtilities.scrollToElement(driver, locator);
	    WebElement checkbox = driver.findElement(locator);
	    if(checkbox.isSelected() != checked){
	        checkbox.click();
	    }
	}
	
	/**
	 * This method checks or unchecks a specified checkbox
	 * 
	 * @param driver WebDriver instance
	 * @param checkbox the checkbox to be checked/unchecked
	 * @param checked true if the checkbox should be checked, false otherwise
	 */
	public static void setCheckbox(WebDriver driver, WebElement checkbox, boolean checked){
		ScrollUtilities.scrollToElement(driver, checkbox);
		if(checkbox.isSelected() != checked){
	        checkbox.click();
	    }
	}

}
