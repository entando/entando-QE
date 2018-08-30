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
import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.openqa.selenium.WebElement;

/**
 *  This class contains some utils methos for the pages contains a browsable table
 * 
 *  @version 1.01
 */
public class BrowsableTableTestTypology extends FunctionalTestBase{
    
    
    /**
     * This method check the correct functionality of a Browsable Table Page
     * in particular:
     * verify each numeric tag display a correct number (page number, items number, pages number, etc.);
     * verify browsable functionality
     * verify pagination functionality
     * The test ignores possible changes in content hypothetically due to changes by other users during execution
     * @param browsableTablePage a page implements a BrowsableTablePage interface
     * @return true, if the Browsable Table is fully functional
     * @throws InterruptedException 
     */
    public boolean checkBrowsableTable(BrowsableTablePageInterface browsableTablePage) throws InterruptedException{
        int countPageNumber = 1;
        if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
            /* Debug code */  Logger.getGlobal().info("Test failed!!!");
            return false;
        }
        //Change the pagination (conventionally let's use the penultimate possibility 
        //so we have other pages to browse and we can test the next button)
        Kebab dropDownKebab = browsableTablePage.getPaginationKebab();
        dropDownKebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, dropDownKebab.getAllActionsMenu());
        //The list of item in the dropdown menù
        List<WebElement> menuItemList = dropDownKebab.getActionsList();
        //The last but one of the greatest number
        WebElement secondLastItem = menuItemList.get(menuItemList.size()-2);
        /* Debug code */  Logger.getGlobal().log(Level.INFO, "Second Last Item is: {0}", secondLastItem.getText());
        //Click
        secondLastItem.click();
        if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
                /* Debug code */  Logger.getGlobal().info("Test failed!!!");
                return false;
        }
        
        sleep(500);
        //The number of table pages displayed
        int numberOfTablePages = browsableTablePage.getNumberOfTablePages();
        
        //Scrolling the table pages
        while (countPageNumber < numberOfTablePages){
            //Click on next button           
            browsableTablePage.getNextPageButton().click();
            countPageNumber++;
            //The number of table pages displayed
            numberOfTablePages = browsableTablePage.getNumberOfTablePages();
            if (!checkNumericTagsOnBrowsableTable(browsableTablePage, countPageNumber)){
                /* Debug code */  Logger.getGlobal().info("Test failed!!!");
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
    private static boolean checkNumericTagsOnBrowsableTable(BrowsableTablePageInterface browsableTablePage, int countPageNumber){
        //The number of table pages displayed in the first page
        int numberOfTablePages = browsableTablePage.getNumberOfTablePages();
        /* Debug code */  Logger.getGlobal().log(Level.INFO, "Number Of Table Pages displayed is: {0}", numberOfTablePages);
        //The actual page number displayed in the page number field
        int actualPageNumber = browsableTablePage.getActualPageNumber();
        //The number of total items in the entire table
        int numberOfTotalItems = browsableTablePage.getNumberOfTotalItems();
        //Items per page
        int itemPerPage = browsableTablePage.getItemPerPage();
        
        Logger.getGlobal().info(MessageFormat.format(
                    "actualPageNumber: {0} countPageNumber: {1}",
                    actualPageNumber, countPageNumber));
        
        //Assert the page number displayed is correct
        if (actualPageNumber != countPageNumber){
            /* Debug code */  Logger.getGlobal().info(MessageFormat.format(
                    "The page number displayed is incorrect! actualPageNumber: {0} countPageNumber: {1}",
                    actualPageNumber, countPageNumber));
            return false;
         }
        //Assert the pagination is correct
        int expectedNumberOfTablePages = numberOfTotalItems / itemPerPage;
        Logger.getGlobal().info(MessageFormat.format(
                    "number of total items: {0} itemPerPage: {1} expectedNumberOfTablePages: {2}",
                    numberOfTotalItems, itemPerPage, expectedNumberOfTablePages));
        if ((numberOfTotalItems % itemPerPage) > 0){
            expectedNumberOfTablePages += 1;
        }
        if (expectedNumberOfTablePages != numberOfTablePages){
            /* Debug code */  Logger.getGlobal().info(MessageFormat.format(
                    "The page number displayed is incorrect! expectedNumberOfTablePages: {0} numberOfTablePages: {1}",
                    expectedNumberOfTablePages, numberOfTablePages));
            return false;
         }
        //Test the effective items number
        SimpleTable table = browsableTablePage.getTable();
        int tableSize = table.tableSize();
        if ((tableSize != (numberOfTotalItems % itemPerPage))&&(tableSize != itemPerPage)){
            /* Debug code */  Logger.getGlobal().info("The effective items number is incorrect!!!");
            return false;
         }
        return true;
    }
    
}
