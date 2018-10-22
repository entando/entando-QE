/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
*/

package org.entando.selenium.utils;


import java.util.List;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    public static void insertInField(WebElement webelement, String content) {
        webelement.click();
        webelement.clear();
        webelement.sendKeys(content);
    }

    public static void selectSetByValue(WebElement webelement, String selectValue) {
        Select select = new Select(webelement);
        select.selectByVisibleText(selectValue);
    }
    

    public static int numberOfRowsInTable(WebElement tableBody) {
        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();
        System.out.println("number of rows: " + rows_count);
        return rows_count;
    }
    

    public static int numberOfColumnsInTable(WebElement tableBody) {
        int columns_count = 0;

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));
            //To calculate no of columns (cells). In that specific row.
            columns_count = cell.size();

        }
        System.out.println("number of columns: " + columns_count);
        return columns_count;

    }

    public static Boolean checkButtonPresenceByName(WebDriver driver, String buttonName) {
        String buttonLocator = String.format("//button[contains(.,'%s')]", buttonName);

        try {
            return driver.findElement(By.xpath(buttonLocator)).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }

        //WebElement button = driver.findElement(By.xpath(buttonLocator));
    }

    public static WebElement getButtonElementByName(WebDriver driver, String buttonName) {
        String buttonLocator = String.format("//button[contains(.,'%s')]", buttonName);
        return driver.findElement(By.xpath(buttonLocator));

    }
    
    
    

    //WORK IN PROGRESS DA RIVEDERE E COMPLETARE
    public static int firstColumnValues(WebElement tableBody) {
        int columns_count = 0;

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));
        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));
            for (WebElement we : cell) {
                we.findElement(By.tagName("a"));
            }

        }
        System.out.println("number of columns: " + columns_count);
        return columns_count;
    }
    
    
    /**
     * 
     * @param driver
     * @param table
     * @return 
     */
    public static List<WebElement> expandAllRowsOnTable(WebDriver driver, SimpleTable table) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> rows = table.getRowsList();
        boolean allExpanded = false;
        int size;
        while(!allExpanded){
            size = rows.size();
            for(WebElement row: rows){
                WebElement cell = row.findElement(table.getCellTag());
                WebElement icon = cell.findElement(By.className("PageExpandedIcon"));
                if(!hasClass(cell, "PageTreeSelector__column-td--empty") && hasClass(icon, "fa-angle-right")){
                    WebElement button = cell.findElement(By.className("PageTreeSelector__expand-area"));
                    button.click();
                    waitUntilSizeChange(wait, button, "//tbody/tr", size);
                }
            }
            //rows = table.findElements(By.xpath("//tbody/tr"));
            allExpanded = rows.size() == size;
        }
        return rows;
    }
    
    
    public static boolean hasClass(WebElement element, String klass) {
        String classes = element.getAttribute("class");
        for (String c : classes.split(" ")) {
            if (c.equals(klass)) {
                return true;
            }
        }
        return false;
    }
    
    
    private static final String innerText = "innerText"; 
    private static final String value = "value"; 
    
    public static String getText(WebElement item){
        return item.getAttribute(innerText);
    }
    
    
    public static String[] getText(List<WebElement> items) {
        String[] result = new String[items.size()];
        for(int i = 0; i < items.size(); i++){
            result[i] = items.get(i).getAttribute(innerText);
        }
        return result;
    }
    
    public static String getValue(WebDriver driver, WebElement element){
        waitUntilHasValue(driver, element);
        return element.getAttribute(value);
    }
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsPresent(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
    
    
   
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsDisappears(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, 8, 50);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsDisappears(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 8, 50);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsAppearsAndDisappears(WebDriver driver, By element){
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        WebDriverWait wait2 = new WebDriverWait(driver, 2, 100);
        wait2.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsAppearsAndDisappears(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 8);
        wait.until(ExpectedConditions.visibilityOf(element));
        WebDriverWait wait2 = new WebDriverWait(driver, 2, 100);
        wait2.until(ExpectedConditions.invisibilityOf(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element 
     */
    public static void waitUntilIsClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 8, 50);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    
    
    
    /**
     * 
     * @param driver
     * @param element
     * @param attribute 
     */
    public static void waitUntilAttributeToBeNotEmpty(WebDriver driver, WebElement element, String attribute)
    {
        WebDriverWait wait = new WebDriverWait(driver, 4, 50);
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element, attribute));
    }
    
    
    
    /**
     * 
     * @param wait
     * @param element
     * @param childrenSelector
     * @param currentSize 
     */
    private static void waitUntilSizeChange(WebDriverWait wait, WebElement element, String childrenSelector, int currentSize){
        wait.until((ExpectedCondition<Boolean>) (WebDriver d) -> {
            int size = element.findElements(By.xpath(childrenSelector)).size();
            return currentSize != size;
        });
    }
    
    
    private static void waitUntilHasValue(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until((ExpectedCondition<Boolean>) (WebDriver f) -> !"".equals(element.getAttribute(value)));
    }
    
}//class close

