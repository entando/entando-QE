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
import org.entando.selenium.pages.DTSystemLabelsPage;
import org.entando.selenium.pages.DTSystemLabelsAddPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;

/**
 * This class contains some utils methos for the System Labels tests (Helpers)
 * 
 * @version 1.01
 */
public class SystemLabelsTestBase extends BrowsableTableTestTypology{
    //Expected table header titles
    public final List<String> expectedHeaderTitles = Arrays.asList("Code", "English", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);

    //Code name
    public final String code = "1SLNM_TEST_" + randomNumber;

    
    
    /**
     * 
     * @param dTSystemLabelsPage
     * @param dTSystemLabelsAddPage
     * @return 
     */
    public boolean addLabel(DTSystemLabelsPage dTSystemLabelsPage,
            DTSystemLabelsAddPage dTSystemLabelsAddPage){
        return this.addLabel(dTSystemLabelsPage, dTSystemLabelsAddPage, this.code);
    }
    
    
    
    /**
     * 
     * @param dTSystemLabelsPage
     * @param dTSystemLabelsAddPage
     * @param labelCode
     * @return 
     */
    public boolean addLabel(DTSystemLabelsPage dTSystemLabelsPage, 
            DTSystemLabelsAddPage dTSystemLabelsAddPage, String labelCode){
        //Click on Add Button
        dTSystemLabelsPage.getAddButton().click();
        
        Utils.waitUntilIsVisible(driver, dTSystemLabelsAddPage.getSaveButton());
        //Compilation of the fields
        dTSystemLabelsAddPage.setCodeField(labelCode);
        dTSystemLabelsAddPage.setEnNameField(labelCode);
        dTSystemLabelsAddPage.setItNameField(labelCode);
        
        //Save the data
        dTSystemLabelsAddPage.getSaveButton().click();
        
        //Wait loading page
        Utils.waitUntilIsPresent(driver, dTSystemLabelsPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTSystemLabelsPage.spinnerTag);
        
        return true;
    }
    
    
    
    /**
     * 
     * @param dTSystemLabelsPage
     * @param labelCode
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteLabel(DTSystemLabelsPage dTSystemLabelsPage, String labelCode) throws InterruptedException{
        Kebab kebab = dTSystemLabelsPage.getTable().getKebabOnTable(labelCode, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(2));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("Label not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        Utils.waitUntilIsVisible(driver, dTSystemLabelsPage.getDeleteModalButton());
        
        Assert.assertTrue(dTSystemLabelsPage.getModalBody().getText().contains(labelCode));
        Utils.waitUntilIsClickable(driver, dTSystemLabelsPage.getDeleteModalButton());
        sleep(100);
        dTSystemLabelsPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, DTSystemLabelsPage.modalWindowTag);
        /** Debug code **/ Logger.getGlobal().info("delete Label return true");
        sleep(500);
        return true;
    }
    
}//end class
