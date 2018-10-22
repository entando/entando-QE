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
import org.entando.selenium.pages.DTPageModelsAddPage;
import org.entando.selenium.pages.DTPageModelsPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
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
    public final String jsonConfiguration = "{\n" +
                                            "  \"frames\": [\n" +
                                            "    {\n" +
                                            "      \"pos\": 0,\n" +
                                            "      \"descr\": \"SeleniumCell\",\n" +
                                            "      \"mainFrame\": false,\n" +
                                            "      \"defaultWidget\": null,\n" +
                                            "      \"sketch\": null\n" +
                                            "    }\n" +
                                            "  ]\n" +
                                            "}";
    public final String template = "<>";
    
    
    
    /**
     * 
     * @param dTPageModelsPage
     * @param dTPageModelsAddPage
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean addPageModel(DTPageModelsPage dTPageModelsPage, DTPageModelsAddPage dTPageModelsAddPage) throws InterruptedException{
        return this.addPageModel(dTPageModelsPage, dTPageModelsAddPage, this.code);
    }
    
    
    
    /**
     * 
     * @param dTPageModelsPage
     * @param dTPageModelsAddPage
     * @param fragmentCode
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean addPageModel(DTPageModelsPage dTPageModelsPage, 
            DTPageModelsAddPage dTPageModelsAddPage, String fragmentCode) throws InterruptedException{
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getAddButton());
        
        //Click on New Button
        dTPageModelsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTPageModelsAddPage.getSaveButton());
        
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        //Compilation of the fields
        dTPageModelsAddPage.setCodeField(code);
        dTPageModelsAddPage.setNameField(code);
        dTPageModelsAddPage.clearJsonConfigurationField();
        dTPageModelsAddPage.setJsonConfigurationField(jsonConfiguration);
        dTPageModelsAddPage.setTemplateField(template);
        
        //Save the data
        dTPageModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTPageModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTPageModelsPage.spinnerTag);
        
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getMessage());
        dTPageModelsPage.getCloseMessageButton().click();
                
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
    public boolean deletePageModel(DTPageModelsPage dTPageModelsPage, String pageModelCode) throws InterruptedException{
        Kebab kebab = dTPageModelsPage.getTable().getKebabOnTable(pageModelCode, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(2));
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
        
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTPageModelsPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", pageModelCode));
        Assert.assertTrue(dTPageModelsPage.getModalBody().getText().contains(pageModelCode));
        Utils.waitUntilIsClickable(driver, dTPageModelsPage.getDeleteModalButton());
        sleep(100);
        dTPageModelsPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTPageModelsPage.modalWindowTag);
        Utils.waitUntilIsVisible(driver, dTPageModelsPage.getMessage());
        dTPageModelsPage.getCloseMessageButton().click();
        
        /** Debug code **/ Logger.getGlobal().info("delete page return true");
        return true;
    }
    
}
