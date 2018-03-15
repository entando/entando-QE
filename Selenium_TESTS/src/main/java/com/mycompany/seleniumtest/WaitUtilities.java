/**
 * This class contains utility methods relating to waiting.
 */
package com.mycompany.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtilities {

	/**
	 * Waits for a given element to be visible
	 * 
	 * @param driver WebDriver instance
	 * @param locator By of the element to wait for
	 */
	public static void waitForElementVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	/**
	 * Waits for a given element to not be visible
	 * 
	 * @param driver WebDriver instance
	 * @param locator By of the element to wait for
	 */
	public static void waitForElementNotVisible(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    Boolean visible = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	/**
	 * Waits for a given element to be visible
	 * 
	 * @param driver WebDriver instance
	 * @param e element to wait for
	 */
	public static void waitForElementVisible(WebDriver driver, WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOf(e));
	}

	
	/**
	 * Waits for a given element to be selected
	 * 
	 * @param driver WebDriver instance
	 * @param locator By of the element to wait for
	 */
	public static void waitForElementSelected(WebDriver driver, By locator){
	    WebDriverWait wait = new WebDriverWait(driver, 60);
	    Boolean bool = wait.until(ExpectedConditions.elementToBeSelected(locator));
	}
	
	/**
	 * Waits for a given element to be selected
	 * 
	 * @param driver WebDriver instance
	 * @param e element to wait for
	 */
	public static void waitForElementSelected(WebDriver driver, WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		Boolean bool = wait.until(ExpectedConditions.elementToBeSelected(e));
	}
	
	/**
	 * Waits for a given element to be clickable
	 * 
	 * @param driver WebDriver instance
	 * @param locator By to locate element to wait for
	 */
	public static void waitForElementClickable(WebDriver driver, By locator){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement e = wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	/**
	 * Waits for given element to be clickable
	 *  
	 * @param driver WebDriver instance
	 * @param e element to wait for
	 */
	public static void waitForElementClickable(WebDriver driver, WebElement e){
		WebDriverWait wait = new WebDriverWait(driver, 120);
		e = wait.until(ExpectedConditions.elementToBeClickable(e));
	}
	
	/**
	 * Waits for the page to have a given title
	 * 
	 * This method is an attempt to address a problem where Chrome/IE drivers
	 * are trying to check the page title on page load before the title has
	 * changed to that of the new page. 
	 * 
	 * @param driver WebDriver instance
	 * @param title title the page should have
	 */
	public static boolean waitForPageTitle(WebDriver driver, String title){
		WebDriverWait wait = new WebDriverWait(driver, 60);
		return wait.until(ExpectedConditions.titleContains(title));
	}
	
	

	/**
	 * Sleep script for the specified length
	 * (generally not an ideal solution)
	 * 
	 * @param length
	 */
	public static void sleep(long length){
	    try {
	        Thread.sleep(length);
	    } catch (InterruptedException e) {
	        Log.error("Sleep Interrupted");
	        e.printStackTrace();
	    }
	}
	
	

}
