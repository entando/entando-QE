package com.mycompany.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class ScrollUtilities {

	public static void scrollDown(WebDriver driver, int pixels){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, "+pixels+")", "");
	}
	
	public static void scrollUp(WebDriver driver, int pixels){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll("+pixels+", 0)", "");
	}

	/**
	 * Could potentially go infinite if there is a different WebDriverException from
	 * ChromeDriver element not clickable.  Maybe find out how to turn that into its
	 * own exception?
	 * 
	 * @param driver
	 * @param e
	 */
	public static void scrollToAndClick(WebDriver driver, WebElement e){
		ScrollUtilities.scrollToTop(driver);
		try{
			e.click();
		}
		catch(WebDriverException wde){
			if(wde.getMessage().contains("Element is not clickable at point")){
				scrollDown(driver, 50);
				scrollToAndClick(driver, e);
			}
			else{
				Log.error(wde.getMessage());
				wde.printStackTrace();
			}
		}
	}

	public static void scrollToTop(WebDriver driver){
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(document.body.scrollHeight, 0)", "");
	}
	
	/**
	 * This method is only really necessary for IEDriver and ChromeDriver.
	 *   
	 * In the IEDriver, viewport will scroll to an element before clicking, but if there
	 * are fixed page elements in the way, those will be clicked.
	 * 
	 * In ChromeDriver, element can't be interacted with until it is visible in the viewport.
	 * 
	 * @param driver WebDriver instance
	 * @param by locator for WebElement to be scrolled to
	 * @return
	 */
	public static WebElement scrollToElement(WebDriver driver, By by)
    {
        WebElement element = driver.findElement(by);
        if(driver instanceof ChromeDriver || driver instanceof InternetExplorerDriver){
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript(("window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 100) + ")"));
        }
        if(driver instanceof ChromeDriver){
        	WaitUtilities.sleep(500);
        }
        return element;
    }
	
	/**
	 * This method is only really necessary for IEDriver and ChromeDriver.
	 *   
	 * In the IEDriver, viewport will scroll to an element before clicking, but if there
	 * are fixed page elements in the way, those will be clicked.
	 * 
	 * In ChromeDriver, element can't be interacted with until it is visible in the viewport.
	 * 
	 * @param driver WebDriver instance
	 * @param element WebElement to be scrolled to
	 * @return
	 */
	public static WebElement scrollToElement(WebDriver driver, WebElement element)
    {
        if(driver instanceof ChromeDriver || driver instanceof InternetExplorerDriver){
        	JavascriptExecutor js = (JavascriptExecutor) driver;
        	js.executeScript(("window.scrollTo(" + element.getLocation().x + "," + (element.getLocation().y - 100) + ")"));
        }
        return element;
    }
	
	
	/**
	 * This method is only really necessary for IEDriver and ChromeDriver.
	 *   
	 * In the IEDriver, viewport will scroll to an element before clicking, but if there
	 * are fixed page elements in the way, those will be clicked.
	 * 
	 * In ChromeDriver, element can't be interacted with until it is visible in the viewport.
	 * 
	 * @param driver WebDriver instance
	 * @param by locator for WebElement to be scrolled to
	 */
	public static void scrollToAndClick(WebDriver driver, By by){
		scrollToElement(driver, by).click();
	}
}
