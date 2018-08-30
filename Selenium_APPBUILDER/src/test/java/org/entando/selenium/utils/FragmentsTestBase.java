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
import org.entando.selenium.pages.DTFragmentNewPage;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.utils.pageParts.Kebab;

/**
 * This class contains some utils methos for the Fragments tests (Helpers)
 * 
 * @version 1.01
 */
public class FragmentsTestBase extends BrowsableTableTestTypology{
    
    //Expected table header titles
    public final List<String> expectedHeaderTitles = Arrays.asList("Code", "Widget Type", "Plugin", "Actions");
    
    Random generator = new Random();
    int randomNumber = generator.nextInt(9999);

    //Code name
    public final String code = "1SLNM_TEST_" + randomNumber;
    
        
    //The code to insert in the GUI code field
    public final String guiCode = "<>";
    
    
    
    /**
     * 
     * @param dTFragmentPage
     * @param dTFragmentNewPage
     * @return 
     */
    public boolean addFragment(DTFragmentPage dTFragmentPage, DTFragmentNewPage dTFragmentNewPage){
        return this.addFragment(dTFragmentPage, dTFragmentNewPage, this.code);
    }
    
    
    
    /**
     * 
     * @param dTFragmentPage
     * @param dTFragmentNewPage
     * @param fragmentCode
     * @return 
     */
    public boolean addFragment(DTFragmentPage dTFragmentPage, 
            DTFragmentNewPage dTFragmentNewPage, String fragmentCode){
        //Click on New Button
        dTFragmentPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTFragmentNewPage.getSaveButton());
        //Compilation of the fields
        dTFragmentNewPage.setCode(fragmentCode);
        dTFragmentNewPage.setGUICode(guiCode);
        
        //Save the data
        dTFragmentNewPage.save();
        
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTFragmentPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTFragmentPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTFragmentPage.spinnerTag);
        
        return true;
    }
    
    
    
    /**
     * 
     * @param dTFragmentPage
     * @param fragmentCode
     * @return 
     * @throws java.lang.InterruptedException 
     */
    public boolean deleteFragment(DTFragmentPage dTFragmentPage, String fragmentCode) throws InterruptedException{
        Kebab kebab = dTFragmentPage.getTable().getKebabOnTable(fragmentCode, 
                expectedHeaderTitles.get(0), expectedHeaderTitles.get(3));
        if(kebab == null)
        {
            /** Debug code **/ Logger.getGlobal().info("Fragment not found!");
            return false;
        }
        //Click on kebab menù
        kebab.getClickable().click();
        /** Debug code **/ Logger.getGlobal().info("Kebab clicked");
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Delete").click();
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        
        sleep(500);
        return true;
    }
    
}
