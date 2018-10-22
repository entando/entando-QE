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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTCategoriesAddPage;
import org.entando.selenium.pages.DTCategoriesPage;
import org.entando.selenium.utils.pageParts.ExpandableTable;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the Categories tests (Helpers)
 * 
 * @version 1.01
 */
public class CategoriesTestBase extends ExpandableTableTestTypology{
    //Expected table header titles
    public final List<String> expectedHeaderTitles = 
            Arrays.asList("Categories tree", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);
    
    
    //Default category
    public final String defaultCategoryNameBase = "SeleniumTest_DontTouch";
    public final String defaultCategoryName = defaultCategoryNameBase + randomNumber;
    
    //Default branch name
    public final String defaultBranchName = "All";
    
    //Default category already present
    public final String defaultCategoryNameAlreadyPresent = "1_SLNM_TEST_DONT_TOUCH";
    
    
    
    /**
     * 
     * @param dTCategoriesPage
     * @param dTCategoriesAddPage
     * @param categoryName
     * @param branchName
     * @return 
     */
    public boolean addCategory(DTCategoriesPage dTCategoriesPage, DTCategoriesAddPage dTCategoriesAddPage,
            String categoryName, String branchName){
        dTCategoriesPage.getAddButton().click();
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getTableWebElement());
        Utils.waitUntilIsVisible(driver, dTCategoriesAddPage.getEnNameField());
        
        //Compiling the fields
        dTCategoriesAddPage.setEnNameField(categoryName);
        dTCategoriesAddPage.setItNameField(categoryName);
        dTCategoriesAddPage.setCodeField(categoryName);
        
        //Select the branch
        ExpandableTable table = dTCategoriesAddPage.getTable();
        WebElement row = table.findRowList(branchName, expectedHeaderTitles.get(0)).get(0);
        row.click();
        
        //Return back
        Assert.assertTrue("Save Button is disabled", dTCategoriesAddPage.getSaveButton().isEnabled());
        dTCategoriesAddPage.getSaveButton().click();
        
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getAddButton());
        Utils.waitUntilIsPresent(driver, dTCategoriesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTCategoriesPage.spinnerTag);
        
        List<WebElement> createdCategory = dTCategoriesPage.getTable()
                .findRowList(categoryName, expectedHeaderTitles.get(0));
        if(createdCategory.isEmpty())
            return false;
        else
            return true;
    }
    
    
    
    
    /**
     * 
     * @param dTCategoriesPage
     * @param dTCategoriesAddPage
     * @param categoryName
     * @return 
     */
    public boolean addCategory(DTCategoriesPage dTCategoriesPage, DTCategoriesAddPage dTCategoriesAddPage,
            String categoryName){
        return addCategory(dTCategoriesPage, dTCategoriesAddPage, categoryName, defaultBranchName);
        
    }
    
    
    
    
    /**
     * 
     * @param dTCategoriesPage
     * @param dTCategoriesAddPage
     * @return 
     */
    public boolean addCategory(DTCategoriesPage dTCategoriesPage, DTCategoriesAddPage dTCategoriesAddPage){
        return addCategory(dTCategoriesPage, dTCategoriesAddPage, defaultCategoryName,
                defaultBranchName);
        
    }
    
    
            
    /**
     * 
     * @param dTCategoriesPage
     * @param categoryNameToDelete
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteCategory(DTCategoriesPage dTCategoriesPage, String categoryNameToDelete) throws InterruptedException{
        //more pages can be found with the same name
        List<Kebab> foundedKebabs = dTCategoriesPage.getTable().getKebabsOnTable
            (categoryNameToDelete, expectedHeaderTitles.get(0), expectedHeaderTitles.get(1));
        if(foundedKebabs.isEmpty())
        {
            /** Debug code **/ Logger.getGlobal().info("delete category return false");
            return false;
        }
        //for each category found, click on the delete action
        for(Kebab kebab : foundedKebabs){
            //Click on kebab menù
            kebab.getClickable().click();
            /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
            Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
            //Click on the action
            kebab.getAction("Delete").click();
            /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
            Utils.waitUntilIsVisible(driver, dTCategoriesPage.getDeleteModalButton());
            /** Debug code **/ Logger.getGlobal().info(dTCategoriesPage.getModalBody().getText());
            /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", categoryNameToDelete));
            Assert.assertTrue("Delete confirm message not contains the category name",
                    dTCategoriesPage.getModalBody().getText().contains(categoryNameToDelete));
            Utils.waitUntilIsClickable(driver, dTCategoriesPage.getDeleteModalButton());
            sleep(100);
            dTCategoriesPage.getDeleteModalButton().click();
            Utils.waitUntilIsDisappears(driver, DTCategoriesPage.modalWindowTag);
            Utils.waitUntilIsDisappears(driver, kebab.getClickable());
        }
        
        Utils.waitUntilIsVisible(driver, dTCategoriesPage.getTableBody());
        /** Debug code **/ Logger.getGlobal().info("delete page return true");
        return true;
    }
    
}
