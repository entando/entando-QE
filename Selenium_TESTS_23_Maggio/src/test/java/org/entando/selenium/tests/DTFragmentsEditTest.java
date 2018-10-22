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
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTFragmentPage;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTFragmentEditPage;
import org.entando.selenium.pages.DTFragmentNewPage;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Fragment Edit page
 * 
 * @version 1.01
 */

public class DTFragmentsEditTest extends FunctionalTest {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTFragmentPage dTFragmentPage;
    
    @Autowired
    public DTFragmentEditPage dTFragmentEditPage;
    
    @Autowired
    public DTFragmentNewPage dTFragmentNewPage;

    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Fragments";
        
        //Final pages titles
        String pageTitle1 = "Fragments";
        String pageTitle2 = "Edit";
        
        //Expected table header titles
        List<String> expectedHeaderTitles = Arrays.asList("Code", "Widget Type", "Plugin", "Actions");
                
        //Code name
        String code = "_";
        
        //Column value
        String columnName = "Code";
        
        /*
            Actions and asserts
        */
        //Login
        login();

        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle1, dTFragmentPage.getPageTitle().getText());
        
        //Asserts table COLUMN NAMES are the expected ones.
        List<String> fetchedHeaderTitles = Utils.fetchHeaderTitles(dTFragmentPage.getTableHeader());
        Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);
        
        
        
        //Searching the corresponding kebab menù in the table
        Utils.Kebab kebab = Utils.getKebabOnTable(dTFragmentPage.getTable(), 0, "button");
        if (kebab==null){
            dTFragmentPage.getNewButton().click();
            dTFragmentNewPage.setCode(code);
            dTFragmentNewPage.setGUICode("<>");
            dTFragmentNewPage.save();
            kebab = Utils.getKebabOnTable(dTFragmentPage.getTable(), 0, "button");
        }
        
        //Asserts kebab is found
        Assert.assertFalse(kebab==null);
        
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getActionList());
        Utils.clickKebabActionOnList(kebab.getActionList(), "Edit " + code);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle2, dTFragmentEditPage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTFragmentEditPage.getHelp().click();
        Assert.assertTrue(dTFragmentEditPage.getTooltip().isDisplayed());
        
        //Compiling the FORM fields
        dTFragmentEditPage.setGUICode("Code inserted by Selenium");
        
        //Asserts code field is disabled
        dTFragmentEditPage.getCode().click();
        Assert.assertFalse(dTFragmentEditPage.getCode().isEnabled());
        
        //Save the changes
        dTFragmentEditPage.save();
        
        //Retrun back to prev. page
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle1, dTFragmentPage.getPageTitle().getText());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
