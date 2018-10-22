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

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    public static List fetchHeaderTitles(WebElement tableHeader) {

        List< WebElement> headerElements = tableHeader.findElements(By.tagName("th"));
        int rows_count = headerElements.size();

        List<String> fetchedHeaderTitles = new ArrayList<>();

        for (int i = 0; i < rows_count; i++) {
            System.out.println("Header is : " + headerElements.get(i).getText());
            fetchedHeaderTitles.add(headerElements.get(i).getText());
        }

        return fetchedHeaderTitles;
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
    
    
    /**
     * This method find the value corresponding line, click on the kebab menù and select the action
     * @param tableHeader The table header
     * @param tableBody The table body
     * @param ColumnTitle The column title in which to search for the value
     * @param SearchValue The value to search in the column
     * @param Action The action to do on the kebab menù
     * @return columns_count
     */
    public static int selectKebabActionOnTable(WebElement tableHeader, WebElement tableBody, String ColumnTitle, String SearchValue, String Action) {
        int columns_count = 0;

        //Lista di tutte le celle del header
        List<WebElement> tableHeaderCells = tableHeader.findElements(By.tagName("th"));
        int numOfColumns = tableHeaderCells.size();

        List<String> tableHeadersTitles = new ArrayList<>();

        for (int i = 0; i < numOfColumns; i++) {
            tableHeadersTitles.add(tableHeaderCells.get(i).getText());
        }

        //indice della colonna che contiene il valore assegnato
        int indexOfColumn = tableHeadersTitles.indexOf(ColumnTitle);
        int indexOfActionsColumn = tableHeadersTitles.indexOf("Actions");

        List< WebElement> rows_table = tableBody.findElements(By.tagName("tr"));

        int rows_count = rows_table.size();

        for (int row = 0; row < rows_count; row++) {

            List< WebElement> cell = rows_table.get(row).findElements(By.tagName("td"));

            columns_count = cell.size();
            //restituisce le celle della colonna indicata in indexOfColumn
            if (cell.get(indexOfColumn).getText().trim().equalsIgnoreCase(SearchValue)) {

                //clicca il corrispondente kebab
                cell.get(indexOfActionsColumn).click();

                System.out.println(" the kebab has been found");

                WebElement ul = cell.get(indexOfActionsColumn).findElement(By.tagName("ul"));
                List<WebElement> li = ul.findElements(By.tagName("li"));

                List<String> rawavailableActionsStrings = new ArrayList<>();
                List<String> cleanavailableActionsStrings = new ArrayList<>();

                for (WebElement actionEl : li) {
                    String cleanActionString = new String();
                    System.out.println("azione: " + actionEl.getText());
                    rawavailableActionsStrings.add(actionEl.getText());

                    if (actionEl.getText().contains(" ")) {
                        cleanActionString = actionEl.getText().substring(0, actionEl.getText().indexOf(" ")).trim();
                        System.out.println("stringa ripulita: " + cleanActionString);
                    } else {
                        cleanActionString = actionEl.getText().trim();
                        System.out.println("stringa originale: " + cleanActionString);
                    }
                    cleanavailableActionsStrings.add(cleanActionString);

                }

                //Get the index of the available action corresponding to the given parameter
                int indexOfActionToTrigger = cleanavailableActionsStrings.indexOf(Action);

                System.out.println("Index is: " + indexOfActionToTrigger);

                if (indexOfActionToTrigger != -1) {
                    WebElement action = li.get(indexOfActionToTrigger);
                    action.click();
                    break;
                } else {
                    assert false : "The action " + Action + " has not been found in the list of actions";
                }

            } else {

                assert false : "The value " + SearchValue + " has not been found in the column " + ColumnTitle;
            }

            //System.out.println("I valori sono: " + cell.get(indexOfColumn).getText());
        }
        //System.out.println("number of columns: " + columns_count);
        return columns_count;

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
     * This metod find in a static html table the kebab menù by index row
     * @param table a static html table WebElement
     * @param index the index of the row
     * @param clickableTag the tag that identifies the kebab
     * @return the kebab found on the table by index
     */
    public static Kebab getKebabOnTable(WebElement table, int index, String clickableTag){
        WebElement row = table.findElement(By.xpath(String.format("//tbody/tr[%d]", index)));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        WebElement kebab = cells.get(cells.size() - 1);
        Kebab result = new Kebab(kebab.findElement(By.tagName(clickableTag)), kebab.findElement(By.tagName("ul")));
        return result;
    }
    
    
    /**
     * This method find in a static html table the kebab menù by column value in a specific column name
     * @param table a static html table WebElement
     * @param columnName the column name to look for the value
     * @param columnValue the value to look for
     * @param clickableTag the tag that identifies the kebab
     * @return the kebab found on the table by column value
     */
    public static Kebab getKebabOnTable(WebElement table, String columnName, String columnValue, String clickableTag){
        /*
            Find the column index
        */
        List<WebElement> headers = table.findElements(By.xpath("//thead/tr[1]/th"));
        int columnIndex = -1;
        /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Header Size: {0}", headers.size());
        for(int i = 0; i < headers.size(); i++){
            WebElement th = headers.get(i);
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column: {0} {1}", th.getAttribute(innerText));
            if(th.getAttribute(innerText).equalsIgnoreCase(columnName)){
                columnIndex = i;
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Column found! {0}", columnIndex);
                break;
            }
        }
        /*
            Find the row
        */
        if(columnIndex >= 0){
            List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
            int rowIndex = -1;
            /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Rows Size: {0}", rows.size());
            for(int j = 0; j < rows.size(); j++){
                WebElement cell = rows.get(j).findElement(By.xpath(String.format("./td[%d]", columnIndex + 1)));
                /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Cell {0}: {1}", new Object[]{j, cell.getAttribute(innerText)});
                if(cell.getAttribute(innerText).equalsIgnoreCase(columnValue)){
                    rowIndex = j;
                    /** Debug code **/ Logger.getGlobal().log(Level.INFO, "Row found! {0}", rowIndex);
                    break;
                }
            }
            if(rowIndex >= 0){
                return getKebabOnTable(table, rowIndex + 1, clickableTag);
            }
            else{
                return null;
            }
        }
        else{
            assert false : "The column " + columnName + " has not been found in the table.";
        }
        return null;
    }
    
    
    /**
     * This method find in browsable table the kebab menù by column value in a specific column name
     * The test ignores possible changes in content hypothetically due to changes by other users during execution
     * @param browsableTablePage a page implements BrowsableTablePage Interface
     * @param columnName the column name to look for the value
     * @param columnValue the value to look for
     * @return the kebab found on the table by column value 
     */
    public static Kebab getKebabOnBrowsableTable(BrowsableTablePage browsableTablePage, String columnName, String columnValue){
        //Searching the corresponding kebab menù in the table
        Kebab kebab = Utils.getKebabOnTable(browsableTablePage.getTable(), columnName, columnValue, "button");
        //The number of table pages displayed in the first page
        int numberOfTablePages = Integer.parseInt(browsableTablePage.getNumberOfTablePages().getText());
        /* Debug code */  Logger.getGlobal().log(Level.INFO, "Number Of Table Pages displayed is: {0}", numberOfTablePages);
        //Page counter 
        int countPageNumber = 1;
        
        //Scrolling the table pages if the item not found on the first table page
        while ((kebab == null)&&(countPageNumber < numberOfTablePages)){
            //Click on next button           
            browsableTablePage.getNextPageButton().click();
            countPageNumber++;
            numberOfTablePages = Integer.parseInt(browsableTablePage.getNumberOfTablePages().getText());
            //Searching the corresponding kebab menù in the table
            kebab = Utils.getKebabOnTable(browsableTablePage.getTable(), columnName, columnValue, "button");
        }
        return kebab;
    }
    
    
    /**
     * This method check the correct functionality of a Browsable Table Page
     * in particular:
     * verify each numeric tag display a correct number (page number, items number, pages number, etc.);
     * verify browsable functionality
     * verify pagination functionality
     * The test ignores possible changes in content hypothetically due to changes by other users during execution
     * @param browsableTablePage a page implements a BrowsableTablePage interface
     * @param driver the driver (to use the drop down kebab)
     * @return true, if the Browsable Table is fully functional
     * @throws InterruptedException 
     */
    public static boolean checkBrowsableTable(BrowsableTablePage browsableTablePage, WebDriver driver) throws InterruptedException{
        int countPageNumber = 1;
        if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
            assert false: "Test failed!!!";
            return false;
        }
        //Change the pagination (conventionally let's use the penultimate possibility so we have other pages and we can test the next button)
        WebElement clickable = browsableTablePage.getPaginationButton().findElement(By.tagName("button"));
        WebElement actionList = browsableTablePage.getPaginationButton().findElement(By.tagName("ul"));
        Utils.Kebab dropDownKebab = new Kebab(clickable, actionList);
        dropDownKebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, dropDownKebab.getActionList());
        //The list of item in the dropdown menù
        List<WebElement> menuItemList = dropDownKebab.getActionList().findElements(By.tagName("li"));
        //The last but one of the greatest number
        WebElement secondLastItem = menuItemList.get(menuItemList.size()-2);
        int secondLastItemValue = Integer.parseInt(menuItemList.get(menuItemList.size()-2).getText());
        /* Debug code */  Logger.getGlobal().log(Level.INFO, "Second Last Item is: {0}", secondLastItemValue);
        //Click
        secondLastItem.click();
        if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
                assert false: "Test failed!!!";
                return false;
        }
        //The number of table pages displayed
        int numberOfTablePages = Integer.parseInt(browsableTablePage.getNumberOfTablePages().getText());
        
        //Scrolling the table pages
        while (countPageNumber < numberOfTablePages){
            //Click on next button           
            browsableTablePage.getNextPageButton().click();
            countPageNumber++;
            //The number of table pages displayed
            numberOfTablePages = Integer.parseInt(browsableTablePage.getNumberOfTablePages().getText());
            if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
                assert false: "Test failed!!!";
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * This is a method used by checkBrowsableTable method 
     * to verify each numeric tag display a correct number
     * (page number, items number, pages number, etc.)
     * @param browsableTablePage a page implements a BrowsableTablePage interface
     * @param countPageNumber the page counter
     * @return true, if the numeric tag are correct, false otherwise
     */
    private static boolean checkNumericTagsOnBrowsableTable(BrowsableTablePage browsableTablePage, int countPageNumber){
        //The number of table pages displayed in the first page
        int numberOfTablePages = Integer.parseInt(browsableTablePage.getNumberOfTablePages().getText());
        /* Debug code */  Logger.getGlobal().log(Level.INFO, "Number Of Table Pages displayed is: {0}", numberOfTablePages);
        //The actual page number displayed in the page number field
        int actualPageNumber = Integer.parseInt(browsableTablePage.getActualPageNumber().getAttribute("value"));
        //The number of total items in the entire table
        int numberOfTotalItems = Integer.parseInt(browsableTablePage.getNumberOfTotalItems().getText());
        //Items per page
        int itemPerPage = Integer.parseInt(browsableTablePage.getPaginationButton().getText());
        //Assert the page number displayed is correct
        if (actualPageNumber != countPageNumber){
            assert false: "The page number displayed is incorrect!!!";
            return false;
         }
        //Assert the pagination is correct
        int expectedNumberOfTablePages = (numberOfTotalItems / itemPerPage) + 1;
        if (expectedNumberOfTablePages != numberOfTablePages){
            assert false: "The pagination is incorrect!!!";
            return false;
         }
        //Test the effective items number
        WebElement table = browsableTablePage.getTable();
        List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));
        if ((rows.size() != (numberOfTotalItems % itemPerPage))&&(rows.size() != itemPerPage)){
            assert false: "The effective items number is incorrect!!!";
            return false;
         }
        return true;
    }
    
    
    public static void clickKebabActionOnList(WebElement ul, String action){
        WebElement link = ul.findElement(By.linkText(action));
        link.click();
    }
    
    
    public static List<WebElement> expandAllRowsOnTable(WebDriver driver, WebElement table) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
        boolean allExpanded = false;
        int size;
        while(!allExpanded){
            size = rows.size();
            for(WebElement row: rows){
                WebElement cell = row.findElement(By.tagName("td"));
                WebElement icon = cell.findElement(By.className("PageExpandedIcon"));
                if(!hasClass(cell, "PageTreeSelector__column-td--empty") && hasClass(icon, "fa-angle-right")){
                    WebElement button = cell.findElement(By.className("PageTreeSelector__expand-area"));
                    button.click();
                    waitUntilSizeChange(wait, button, "//tbody/tr", size);
                }
            }
            rows = table.findElements(By.xpath("//tbody/tr"));
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
    
    
    public static void waitUntilIsVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    
    
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
    
    
    
    /**
     * This class represents the drop-down menu present in every item (row) of a table
     */
    public static class Kebab {
        private WebElement actionList;
        private WebElement clickable;
        
        public Kebab(){
        }
        
        public Kebab(WebElement clickable, WebElement actionList){
            this.clickable = clickable;
            this.actionList = actionList;
        }

        public WebElement getClickable() {
            return clickable;
        }

        public void setClickable(WebElement clickable) {
            this.clickable = clickable;
        }

        public WebElement getActionList() {
            return actionList;
        }

        public void setActionList(WebElement actionList) {
            this.actionList = actionList;
        }
    }

}//class close

