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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Labels&Languages page
 * 
 * @version 1.01
 */
public class DTLabelsAndLanguagesTest extends FunctionalTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage;
    
    /*
        Test
    */  
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Configuration";
        String secondLevelLink = "Labels and Languages";
        
        //Final page title
        String pageTitle = "Labels and Languages";

        //Expected table header titles
        List<String> expectedHeaderTitles = 
            Arrays.asList("Code", "Name", "Actions");
        
        //Language to set
        String languageToSet = "el – Greek";
        String languageName = "Greek";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Loading page
        Utils.waitUntilIsPresent(driver, dTLabelsAndLanguagesPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.spinnerTag);
        
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals("Page Title isn't the expected one",
                pageTitle, dTLabelsAndLanguagesPage.getPageTitle().getText());        
        
        //Asserts the presence of the HELP button
        dTLabelsAndLanguagesPage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getTooltip());
        Assert.assertTrue("Tooltip not work",
                dTLabelsAndLanguagesPage.getTooltip().isDisplayed());
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals("Colums Name aren't the expected ones",
                expectedHeaderTitles,
                dTLabelsAndLanguagesPage.getSimpleTable().getHeaderTitlesList());
        
        //Add a language
        dTLabelsAndLanguagesPage.getLanguageSelect().selectByVisibleText(languageToSet);
        dTLabelsAndLanguagesPage.getAddButton().click();
        sleep(600);
        
        //Verify the presence of the language in the table
        WebElement button = dTLabelsAndLanguagesPage.getSimpleTable().getButtonOnTable(
                languageName,
                expectedHeaderTitles.get(1), expectedHeaderTitles.get(2));
        Assert.assertFalse("Language not found on the table after being added",
                button==null);
        
        //Delete language
        button.click();
        Utils.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getDeleteModalButton());
        Utils.waitUntilIsClickable(driver, dTLabelsAndLanguagesPage.getDeleteModalButton());
        sleep(100);
        dTLabelsAndLanguagesPage.getDeleteModalButton().click();
        Utils.waitUntilIsDisappears(driver, dTLabelsAndLanguagesPage.modalWindowTag);
        sleep(200);
        
        //Verify the removal of the language from the table
        button = dTLabelsAndLanguagesPage.getSimpleTable().getButtonOnTable(languageName,
                expectedHeaderTitles.get(1), expectedHeaderTitles.get(2));
        Assert.assertTrue("Language found on the table after after being deleted",
                button==null);
        
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}//end class
