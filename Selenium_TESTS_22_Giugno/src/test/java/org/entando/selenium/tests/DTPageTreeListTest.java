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

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import static java.lang.Thread.sleep;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTDashboardPage;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.entando.selenium.pages.DTPageTreePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Action;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test of the Page Tree page
 * 
 * @version 1.01
 */
public class DTPageTreeListTest extends PageTreeTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException, AWTException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        //Final page title
        String pageTitle = "Page Tree";
                
        //Buttons name
        String button1 = "Add";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
                
        //Asserts the PAGE TITLE is the expected one
        Assert.assertEquals(pageTitle, dTPageTreePage.getPageTitle().getText());
        
        //Asserts the presence of the HELP button
        dTPageTreePage.getHelp().click();
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTooltip());
        Assert.assertTrue(dTPageTreePage.getTooltip().isDisplayed());
        
        //Asserts the presence of the BUTTON with displayed name as argument
        //Debug code
        Logger.getGlobal().info(dTPageTreePage.getAddButton().getText());
        Assert.assertTrue(dTPageTreePage.getAddButton().getText().equals(button1));
        
        //Asserts table COLUMNS NAME are the expected ones
        Assert.assertEquals(super.headerTitles, dTPageTreePage.getTable().getHeaderTitlesList());
        
        //Verification of the opening tree functionality
        //expandTable(dTPageTreePage);
        
        //Verification of the drag and drop functionality
        /*
        WebElement from = dTPageTreePage.getTable().getDragButton("SeleniumTest_DontTouch2"); 
        WebElement to = dTPageTreePage.getTable().getCell("SeleniumTest_DontTouch", "Page tree", "Page tree");
        Actions builder = new Actions(driver); 
        
        builder.dragAndDrop(from, to).perform();
        
        from = dTPageTreePage.getTable().getDragButton("SeleniumTest_DontTouch2"); 
        to = dTPageTreePage.getTable().getCell("Home", "Page tree", "Page tree");
        
        builder.dragAndDrop(from, to).perform();
        */
        
        /*WebElement from = dTPageTreePage.getTable().getDragButton("SeleniumTest_DontTouch2");
        WebElement to = dTPageTreePage.getTable().getCell("Home", "Page tree", "Page tree");

        Actions builder = new Actions(driver);

        Action series = builder
                .moveToElement(from)
                .doubleClick()
                .clickAndHold()
                .moveByOffset(-1, -1)
                .moveToElement(to)
                .release()
                .click()
                .build();
        series.perform();*/
        
        
        WebElement from = driver.findElement(By.xpath("//*[text()= 'SeleniumTest_DontTouch2']"));
        WebElement to = dTPageTreePage.getSearchField();
        Point coordinates1 = from.getLocation();
        Point coordinates2 = to.getLocation();  
        Robot robot = new Robot();        
        Actions builder = new Actions(driver);
        Action series = builder
                .moveToElement(to)
                .doubleClick(from)
                .build();
        series.perform();
        Thread.sleep(2000);
        robot.mouseMove(coordinates1.getX()+5, coordinates1.getY()+5);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseMove(coordinates2.getX()+10, coordinates2.getY()+10);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
        Thread.sleep(2000);
        
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/

    }
}
