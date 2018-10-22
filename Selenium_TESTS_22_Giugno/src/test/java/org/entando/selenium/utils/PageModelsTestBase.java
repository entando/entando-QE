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

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTPageModelsAddPage;
import org.entando.selenium.pages.DTPageModelsPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the Page Models tests (Helpers)
 * 
 * @version 1.01
 */
public class PageModelsTestBase extends BrowsableTableTestTypology{
    
    //Expected table header titles
    public final List<String> expectedHeaderTitles = 
            Arrays.asList("Code", "Name", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);

    //Code name
    public final String code = "1SLNM_TEST_" + randomNumber;
    
        
    //The code to insert in the fields
    public final String jsonConfiguration = "{\n" + "  \"frames\": []\n" + "}";
    public final String template = "<>";
    
    
    
    /**
     * 
     * @param dTPageModelsPage
     * @param dTPageModelsAddPage
     * @return 
     */
    public boolean addPageModel(DTPageModelsPage dTPageModelsPage, DTPageModelsAddPage dTPageModelsAddPage){
        return this.addPageModel(dTPageModelsPage, dTPageModelsAddPage, this.code);
    }
    
    
    
    /**
     * 
     * @param dTPageModelsPage
     * @param dTPageModelsAddPage
     * @param fragmentCode
     * @return 
     */
    public boolean addPageModel(DTPageModelsPage dTPageModelsPage, 
            DTPageModelsAddPage dTPageModelsAddPage, String fragmentCode){
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Click on New Button
        dTPageModelsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageModelsAddPage.getSaveButton());
        
        //Compilation of the fields
        dTPageModelsAddPage.setCodeField(code);
        dTPageModelsAddPage.setNameField(code);
        dTPageModelsAddPage.setJsonConfigurationField(jsonConfiguration);
        dTPageModelsAddPage.setTemplateField(template);
        
        //Save the data
        dTPageModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
                
        //Reload the page
        driver.get(driver.getCurrentUrl());
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        
        //Assert the element is present in the table
        List<WebElement> createdPageModels = dTPageModelsPage.getTable()
                .findRowList(code, expectedHeaderTitles.get(0));
        
        return (!createdPageModels.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTPageModelsPage
     * @param pageModelCode
     * @return 
     */
    public boolean deletePageModel(DTPageModelsPage dTPageModelsPage, String pageModelCode){
        Kebab kebab = dTPageModelsPage.getTable().getKebabOnTable(pageModelCode, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("PageModel not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        return true;
    }
    
}
