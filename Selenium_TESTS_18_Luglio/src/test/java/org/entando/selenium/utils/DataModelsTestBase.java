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
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDataModelsAddPage;
import org.entando.selenium.pages.DTDataModelsPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the Data Models tests (Helpers)
 * 
 * @version 1.01
 */
public class DataModelsTestBase extends BrowsableTableTestTypology{
    //Expected table header titles
    public final List<String> expectedHeaderTitles = 
            Arrays.asList("Name", "Type", "Id", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);
    
    //Data Model Type
    public final String dataModelType = "SeleniumTest_DontTouch";
    
    //Data Model name
    public final String dataModelName = "1SLNM_TEST_" + randomNumber;
    
    //Data Model Code
    public final String dataModelCode = "1";
    
    //Default values
    public final String defaultType = "SeleniumTest_DontTouch";
    public final String defaultModel = "SeleniumTest_DontTouch";
    public final String defaultStyleSheet = "SeleniumTest_DontTouch";
    
    
    
    /**
     * 
     * @param dTDataModelsPage
     * @param dTDataModelsAddPage
     * @return 
     */
    public boolean addDataModel(DTDataModelsPage dTDataModelsPage,
            DTDataModelsAddPage dTDataModelsAddPage){
        return this.addDataModel(dTDataModelsPage, dTDataModelsAddPage, 
                this.dataModelCode, this.dataModelName, this.defaultType);
    }
    
    
    
    /**
     * 
     * @param dTDataModelsPage
     * @param dTDataModelsAddPage
     * @param dataModelCode
     * @param dataModelName
     * @param dataModelType
     * @return 
     */
    public boolean addDataModel(DTDataModelsPage dTDataModelsPage,
            DTDataModelsAddPage dTDataModelsAddPage, String dataModelCode,
            String dataModelName, String dataModelType){
        //Click on New Button
        dTDataModelsPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataModelsAddPage.getSaveButton());
        
        //Compilation of the fields
        dTDataModelsAddPage.setCode(dataModelCode);
        dTDataModelsAddPage.setName(dataModelName);
        dTDataModelsAddPage.setTypeSelect(dataModelType);
        dTDataModelsAddPage.setModel(defaultModel);
        dTDataModelsAddPage.setStyleSheet(defaultStyleSheet);
        
        //Save the data
        dTDataModelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataModelsPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getTableBody());
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getAlertMessage());
        
        dTDataModelsPage.getCloseMessageButton().click();
        
                
        List<WebElement> createdDataModel = dTDataModelsPage.getTable()
                .findRowList(dataModelCode, expectedHeaderTitles.get(2));
        
        return(!createdDataModel.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTDataModelsPage
     * @param dataModelCode
     * @return
     * @throws InterruptedException 
     */
    public boolean deleteDataModel(DTDataModelsPage dTDataModelsPage,
            String dataModelCode) throws InterruptedException{
        Kebab kebab = dTDataModelsPage.getTable().getKebabOnTable(dataModelCode,
                expectedHeaderTitles.get(2), expectedHeaderTitles.get(3));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("Widget not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab action clicked");
        
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getDeleteModalButton());
        
        Assert.assertTrue(dTDataModelsPage.getModalBody().getText().contains(dataModelCode));
        Utils.waitUntilIsClickable(driver, dTDataModelsPage.getDeleteModalButton());
        sleep(100);
        dTDataModelsPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTDataModelsPage.modalWindowTag);
        Utils.waitUntilIsVisible(driver, dTDataModelsPage.getAlertMessage());
        Assert.assertTrue("Alert Message has not displayed",
                dTDataModelsPage.getAlertMessage().isDisplayed());
        Assert.assertTrue("Invalid Alert Message content",
                dTDataModelsPage.getAlertMessageContent().contains("successfully deleted"));
        dTDataModelsPage.getCloseMessageButton().click();
        //alert toast-pf alert-success alert-dismissable
        /** Debug code **/ Logger.getGlobal().info("delete Data Model return true");
        return true;
    }
    
}//end class
