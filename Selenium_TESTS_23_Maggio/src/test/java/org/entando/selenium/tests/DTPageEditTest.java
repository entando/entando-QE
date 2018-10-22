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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.pages.DTPageEditPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Edit Page page
 * 
 * @version 1.01
 */

public class DTPageEditTest extends FunctionalTest {
    /*
        Pages used on this test
     */
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageEditPage dTPageEditPage;
    
    /*
        Test
     */
    @Test
    public void pageEdit() throws InterruptedException{
        /*
            Parameters
         */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Edit";
        
        //Menù kebab item
        String menuItem = "Edit";
        
        /*
            Actions and asserts
         */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        
        //Kebab menù button detection and click
        WebElement table = driver.findElement(By.className("PageTree"));
        Kebab kebab = Utils.getKebabOnTable(table, 5, "button");
        kebab.getClickable().click();
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
        
        //Click on the menù item
        Utils.waitUntilIsVisible(driver, kebab.getActionList());
        Utils.clickKebabActionOnList(kebab.getActionList(), menuItem);

        //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dTPageEditPage.getPageTitle().getText());
        
        //Filling out the form
        dTPageEditPage.setEnTitle("English Title changed by Selenium");
        dTPageEditPage.setItTitle("Titolo Italiano modificato da Selenium");
        Utils.selectSetByValue(dTPageEditPage.getPageModel(), "Home Page");
        List<WebElement> rows = Utils.expandAllRowsOnTable(driver, 
                dTPageEditPage.getPagePlacementSelector());
        dTPageEditPage.choosePagePlacement("Log In", rows);
        
        //Asserts that Save button is enabled
        Assert.assertTrue(dTPageEditPage.getSaveButton().isEnabled());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
