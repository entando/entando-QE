package com.mycompany.seleniumtest;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class AssortedUtilities {

	/**
	 * This method generates a unique string based on the current system
	 * date/time.
	 * 
	 * @return
	 */
	public static String generateUniqueText(){
	    Date date = new Date();
	
	    SimpleDateFormat UNIQUE_DATE_TIME_STRING_FORMAT = new SimpleDateFormat(
	            "yyyyMMddHHmmSS");
	    String dateTimeString = UNIQUE_DATE_TIME_STRING_FORMAT.format(date);
	
	    return dateTimeString;
	}

	/**
	 * This method drags and drops an element the specified amount.
	 * 
	 * Note: Can also be used to drag/drop other elements.  Might make
	 * a drag and drop method and use it in a horizontal slider method and a vertical slider method.  
	 * 
	 * @param driver
	 * @param locator
	 * @param xOffset
	 * @param yOffset
	 */
	public static void dragAndDropElement(WebDriver driver, By locator, int xOffset, int yOffset){
	    WebElement slider = driver.findElement(locator);
	    Actions move = new Actions(driver);
	    Action action = (Action) move.dragAndDropBy(slider, xOffset, yOffset).build();
	    action.perform();
	}

	/**
	 * This method captures and saves a screenshot.
	 * 
	 * Note: If the page loads asynchronously, you will need a wait until 
	 * last element is loaded to make sure that the ajax has finished.
	 * 
	 * @param driver WebDriver instance
	 * @param fileName name and location to save screenshot
	 */
	public static void captureScreenshot(WebDriver driver, String fileName){
	    try{
	        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	        Log.trace("Screenshot Taken");
	        FileUtils.copyFile(screenshot, new File(fileName));
	        Log.trace("Screenshot Saved");
	    }
	    catch(Exception e){
	        Log.error("Failure to take/save screenshot" + e);
	    }
	}

	/**
	 * This method extracts the identifying String from a By object.
	 * 
	 * @param by the by object to extract the String from
	 * @return the identifying String of the By object
	 */
	public static String stringFromBy(By by){
		Log.debug("To String of By: " + by.toString());
	    String[] parts = by.toString().split(" ");
	    Arrays.toString(parts);
	    return parts[1];
	}

	/**
	 * 
	 * @param driver WebDriver instance
	 * @param locators locators for elements from which to get text
	 * @return the text from the specified elements
	 */
	public static String[] getTextFromElements(WebDriver driver, By[] locators){
		String[]text = new String[locators.length];
	    for(int i = 0; i < locators.length; i++){
	    	text[i] = driver.findElement(locators[i]).getText();
	    }
	    return text;
	}

	public static void segmentedTextInput(WebDriver driver, By locator, String s, int n){
		String[] segments = AssortedUtilities.segmentString(s, 12);
	    
	    for(int i = 0; i < segments.length; i++){
	    	driver.findElement(locator).sendKeys(segments[i]);
	    	WaitUtilities.sleep(2000);
	    }
	}

	public static String[] segmentString(String s, int n){
		 String[] segments = new String[s.length()/n + 1];
		 
		 int counter = 0;
		 while(!(s.length() < n)){
			 segments[counter] = s.substring(0, n);
			 s = s.substring(n);
			 counter ++;
		 }
		segments[counter] = s;	
		return segments;
	 }

	public static boolean elementPresent(WebDriver driver, By locator){
		try{
			driver.findElement(locator);
			return true;
		}
		catch(NoSuchElementException e){
			return false;
		}
	}

	/**
	 * This method searches the input string for matches to a regular expression
	 * and returns them in an ArrayList.
	 * 
	 * @param regex regular expression to match
	 * @param input string to search
	 * @return matches found
	 */
	public static ArrayList<String> getMatches(String regex, String input){
		ArrayList<String> matches = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(regex); 
	    Matcher m = pattern.matcher(input);
	    int count = 0;
	    while(m.find()){
	    	count++;
	    	String match = input.substring(m.start(), m.end());
	    	Log.debug("Match number: " + count);
	    	Log.debug(input.substring(m.start(), m.end()));
	    	Log.debug("Starts at " + m.start());
	    	Log.debug("Starts at " + m.end());
	    	matches.add(match);
	    }
		
		return matches;
	}

	/**
	 * This method searches the input string for matches to a regular expression
	 * and returns them in an ArrayList.
	 * 
	 * @param regex regular expression to match
	 * @param input string to search
	 * @return matches found
	 */
	public static ArrayList<String> getMatchGroup(String regex, String input, int groupNum){
		ArrayList<String> matches = new ArrayList<String>();
		
		Pattern pattern = Pattern.compile(regex); 
	    Matcher m = pattern.matcher(input);
	    int count = 0;
	    while(m.find()){
	    	count++;
	    	String match = input.substring(m.start(), m.end());
	    	String group = m.group(1);
	    	Log.debug("Match number: " + count);
	    	Log.debug(input.substring(m.start(), m.end()));
	    	Log.debug("Starts at " + m.start());
	    	Log.debug("Starts at " + m.end());
	    	matches.add(group);
	    }
		
		return matches;
	}

}
