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
import org.entando.selenium.pages.DTDataTypesAddPage;
import org.entando.selenium.pages.DTDataTypesPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

/**
 * This class contains some utils methos for the Data Types tests (Helpers)
 * 
 * @version 1.01
 */
public class DataTypesTestBase extends BrowsableTableTestTypology{
    
    //Expected table header titles
    public final List<String> expectedHeaderTitles = 
            Arrays.asList("Name", "Code", "Status", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);
    
    //Data Type name
    public final String dataTypeName = "1SLNM_TEST_" + randomNumber;
    
    //Data Type Code
    public final String dataTypeCode = "ABA";
    public final String dataTypeCodeExistent = "SLN";
    
    
    
    /**
     * 
     * @param dTDataTypesPage
     * @param dTDataTypesAddPage
     * @return
     * @throws InterruptedException 
     */
    public boolean addDataType(DTDataTypesPage dTDataTypesPage,
            DTDataTypesAddPage dTDataTypesAddPage) throws InterruptedException{
        
        return this.addDataType(dTDataTypesPage, dTDataTypesAddPage, this.dataTypeName);
    }
    
    
    
    /**
     * 
     * @param dTDataTypesPage
     * @param dTDataTypesAddPage
     * @param dataTypeName
     * @return
     * @throws InterruptedException 
     */
    public boolean addDataType(DTDataTypesPage dTDataTypesPage,
            DTDataTypesAddPage dTDataTypesAddPage, String dataTypeName) throws InterruptedException{
        //Click on New Button
        dTDataTypesPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getSaveButton());
        dTDataTypesAddPage.setCode(this.dataTypeCode);
        dTDataTypesAddPage.setName(dataTypeName);
        
        //Save the data
        dTDataTypesAddPage.getSaveButton().click();
        Utils.waitUntilIsVisible(driver, dTDataTypesAddPage.getTypeSelect());
        sleep(500);
        dTDataTypesAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTDataTypesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTDataTypesPage.spinnerTag);
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getTableBody());
        
        List<WebElement> createdDataType = dTDataTypesPage.getTable()
                .findRowList(dataTypeCode, expectedHeaderTitles.get(1));
        
        return (!createdDataType.isEmpty());
    }
    
    
    
    /**
     * 
     * @param dTDataTypesPage
     * @param dataTypeCode
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteDataType(DTDataTypesPage dTDataTypesPage,
            String dataTypeCode) throws InterruptedException{
        Kebab kebab = dTDataTypesPage.getTable().getKebabOnTable(dataTypeCode,
                expectedHeaderTitles.get(1), expectedHeaderTitles.get(3));
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
        
        Utils.waitUntilIsVisible(driver, dTDataTypesPage.getDeleteModalButton());
        /** Debug code **/ Logger.getGlobal().info(dTDataTypesPage.getModalBody().getText());
        /** Debug code **/ Logger.getGlobal().info(MessageFormat.format("Expected: {0}", dataTypeCode));
        Assert.assertTrue(dTDataTypesPage.getModalBody().getText().contains(dataTypeCode));
        Utils.waitUntilIsClickable(driver, dTDataTypesPage.getDeleteModalButton());
        sleep(100);
        dTDataTypesPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTDataTypesPage.modalWindowTag);
        /** Debug code **/ Logger.getGlobal().info("delete Data Type return true");
        
        return true;
    }
    
}//end class
