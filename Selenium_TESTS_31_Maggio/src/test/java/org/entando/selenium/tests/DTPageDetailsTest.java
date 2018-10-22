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
package org.entando.selenium.tests;

import static java.lang.Thread.sleep;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageAddPage;
import org.entando.selenium.pages.DTPageDetailsPage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ufficio
 */
public class DTPageDetailsTest extends PageTreeTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    @Autowired
    public DTPageAddPage dTPageAddPage;
    
    @Autowired
    public DTPageDetailsPage dTPageDetailsPage;
    
    /**
    * This class perform a test the details page in the page tree
    * 
    * @version 1.01
     * @throws java.lang.InterruptedException
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Page Details";
        
        //pageName to delete
        Random generator = new Random();
        int randomNumber = generator.nextInt(9999);
        String pageName = "SeleniumTest" + randomNumber;
        
        //Where create a page for the test
        String branchName = "Home";
        
        //Kebab menù action
        String kebabAction = "Details";
        
        //Table entries
        List<String> tableEntries = Arrays.asList("Code", "Title", "Owner Group",
                "Page Model", "Displayed in menu", "SEO: When available, use extra titles");
        
        //Errors text list
        List<String> errorTexts = Arrays.asList("This page does not publish any content.",
                "There is no content with a link pointing to this page.");
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        
        //Create a page to clone
        Assert.assertTrue(addPage(dTPageTreePage, dTPageAddPage, pageName));
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, headerTitles.get(0), headerTitles.get(3));
        //Assert the item has been found
        Assert.assertFalse(kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        Utils.waitUntilIsVisible(driver, dTPageDetailsPage.getInfoButton());
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageDetailsPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageAddPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTPageDetailsPage.getTooltip());
        Assert.assertTrue(dTPageAddPage.getTooltip().isDisplayed());
        
        
        //Click on Info button
        dTPageDetailsPage.getInfoButton().click();
        Utils.waitUntilIsVisible(driver, dTPageDetailsPage.getTableBody());
        
        
        //Assert correct entries in the table
        //Code
        SimpleTable table = dTPageDetailsPage.getTable();
        List<WebElement> rowsList = table.getRowsList();
        int rowIndex = table.findColumnIndex(tableEntries.get(0));
        String cellContent = rowsList.get(rowIndex).getText();
        Assert.assertTrue(cellContent.contains(pageName));
        
        //Title
        rowIndex = table.findColumnIndex(tableEntries.get(1));
        cellContent = rowsList.get(rowIndex).getText();
        Assert.assertTrue(cellContent.contains(pageName));
        
        //Owner Group
        rowIndex = table.findColumnIndex(tableEntries.get(2));
        cellContent = rowsList.get(rowIndex).getText();
        Assert.assertTrue(cellContent.contains(super.ownerGroupName.toLowerCase()));
        
        //Page Model
        rowIndex = table.findColumnIndex(tableEntries.get(3));
        cellContent = rowsList.get(rowIndex).getText();
        Assert.assertTrue(cellContent.contains(super.pageModelName.toLowerCase()));
        
        //Displayed in menu
        rowIndex = table.findColumnIndex(tableEntries.get(4));
        WebElement cell = rowsList.get(rowIndex);
        String entry = cell.findElement(By.xpath(".//i")).getAttribute("class");
        Assert.assertTrue(entry.contains("fa-check-square-o"));
        
        //SEO: When available, use extra titles
        rowIndex = table.findColumnIndex(tableEntries.get(5));
        cell = rowsList.get(rowIndex);
        entry = cell.findElement(By.xpath(".//i")).getAttribute("class");
        Assert.assertTrue(entry.contains("fa-square-o"));
        
        
        //Assert the presence of the errors
        List<WebElement> errors = dTPageDetailsPage.getErrors();
        Assert.assertTrue(errors.size() == 2);
        Assert.assertTrue(errors.get(0).getText().equals(errorTexts.get(0)));
        Assert.assertTrue(errors.get(1).getText().equals(errorTexts.get(1)));
        
        //Come back in the Page Tree page
        driver.navigate().back();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        //Delete the created page
        deletePage(dTPageTreePage, pageName);
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
