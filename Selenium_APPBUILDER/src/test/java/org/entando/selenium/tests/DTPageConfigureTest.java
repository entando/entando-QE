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
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageConfigurePage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.testHelpers.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This class perform a test to configure a page in the page tree
 * 
 * @version 1.01
 */
public class DTPageConfigureTest extends PageTreeTestBase{
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTPageTreePage dTPageTreePage;
        
    @Autowired
    public DTPageConfigurePage dTPageConfigurePage;
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Parameters
        */
        //Link menù buttons
        String firstLevelLink = "Page Designer";
        String secondLevelLink = "Page Tree";
        
        String pageName = "SeleniumTest_DontTouch";
        
        //Kebab menù action
        String kebabAction = "Configure";
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        
        
        System.setProperty("java.awt.headless", "false");
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, headerTitles.get(0), headerTitles.get(3));
        //Assert the item has been found
        Assert.assertFalse("Can't find element \"SeleniumTest_DontTouch\" in the table",
                kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        //Asserts the PAGE TITLE is the expected one
        Utils.waitUntilIsVisible(driver, dTPageConfigurePage.getPageTitle());
        Assert.assertEquals(pageName, dTPageConfigurePage.getPageTitle().getText());
        
        //Asserts the DEFAULT ICON is displayed
        //Assert.assertTrue(dTPageConfigurePage.getDefaultWidgetIcon().isDisplayed());
        
        //Utils.waitUntilIsVisible(driver, dTPageConfigurePage.getPageConfigGrid());
        
        
        /* TESTING THE KEBAB ACTION ON GRID
        List<Kebab> kebabs = dTPageConfigurePage.getKebabsWidgetOnGrid("title", "Left");
        kebab = kebabs.get(0);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction("Details").click();
        */
        
        /* TESTING WIDGET DRAG AND DROP */
        sleep(2000);
        WebElement search = driver.findElement(By.xpath("//input[contains(@placeholder, 'Search')]"));
        WebElement title = dTPageConfigurePage.getPageTitle();
        WebElement widgetText = driver.findElement(By.xpath("//span[text()='Widgets']"));
        WebElement infoButton = dTPageConfigurePage.getInfoButton();
        
        
        JavascriptExecutor js = (JavascriptExecutor)driver;
        WebElement userWidget = dTPageConfigurePage.getWidget("SeleniumTest_DontTouch");
        
        //js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        
        //boolean scrollBarPresent = (boolean) ((JavascriptExecutor)driver).executeScript("return document.documentElement.scrollHeight>document.documentElement.clientHeight;");
        
        //Assert.assertTrue(scrollBarPresent);
        
        
        sleep(3000);
        //Assert.assertTrue(dTPageConfigurePage.isVisibleInViewport(userWidget));
        
 
      //dTPageConfigurePage.moveWidgetToFrame("SeleniumTest_DontTouch", "Test frame");
       
       dTPageConfigurePage.JavascriptDragAndDrop("SeleniumTest_DontTouch", "Test frame");
       //sleep(1000);
       //dTPageConfigurePage.moveWidgetToFrame("SeleniumTest_DontTouch", "SeleniumCell1");
       
       dTPageConfigurePage.getPublishButton().click();
            dTPageConfigurePage.getUnpublishButton().click();
                
        Logger.getGlobal().info("azione eseguita!");
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
