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
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTPageConfigurePage;
import org.entando.selenium.pages.DTPageTreePage;
import org.entando.selenium.utils.PageTreeTestBase;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.pageParts.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
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
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTPageTreePage.getTableBody());
        
        Kebab kebab = dTPageTreePage.getTable().getKebabOnTable(pageName, headerTitles.get(0), headerTitles.get(3));
        //Assert the item has been found
        Assert.assertFalse(kebab == null);
        //Click on kebab menù
        kebab.getClickable().click();
        Utils.waitUntilIsVisible(driver, kebab.getAllActionsMenu());
        //Click on the action
        kebab.getAction(kebabAction).click();
        
        //Asserts the PAGE TITLE is the expected one
        Utils.waitUntilIsVisible(driver, dTPageConfigurePage.getPageTitle());
        Assert.assertEquals(pageName, dTPageConfigurePage.getPageTitle().getText());
        
        //Asserts the DEFAULT ICON is displayed
        Assert.assertTrue(dTPageConfigurePage.getDefaultWidgetIcon().isDisplayed());
        
        Utils.waitUntilIsVisible(driver, dTPageConfigurePage.getPageConfigGrid());
        
        
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
        
        WebElement from = driver.findElement(By.xpath("//a[text()='data_widget']"));
        Assert.assertNotNull(from);
        WebElement from2 = driver.findElement(By.xpath("//a[text()='English-widget']"));
        Assert.assertNotNull(from2);
        WebElement to = driver.findElement(By.xpath("//div[contains(@class, 'PageConfigGrid')]//*[text()= 'SeleniumCell']/../.."));
        Assert.assertNotNull(to);
        
        Assert.assertTrue(from.isDisplayed());
        Assert.assertTrue(to.isDisplayed());
        
        
        Actions testBuilder = new Actions(driver);
        Action seriesOfAction = testBuilder
                //.clickAndHold(from)
                .moveToElement(from)
                .clickAndHold()
                //.moveByOffset(-1, -1)
                //.moveToElement(to, to.getLocation().getX()+to.getSize().getWidth()/2,
                //        to.getLocation().getY()+to.getSize().getHeight()/2)
                //.moveToElement(search)
                //.release(search)
                .moveToElement(from2)
                .release()
                .build();
        seriesOfAction.perform();
        
        
        
        /*
        Actions testBuilder = new Actions(driver);
        Action seriesOfAction = testBuilder
                .moveToElement(title, 100, 13)
                .doubleClick()
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .moveToElement(to, 100, 100)
                .pause(Duration.ofSeconds(2))
                .release(to)
                .build();
        seriesOfAction.perform();*/
        
        
        Logger.getGlobal().info(to.getText());
        Logger.getGlobal().info(to.getTagName());
        Logger.getGlobal().info(to.getLocation().toString());
        Logger.getGlobal().info(to.getAttribute("style"));
        
        
        //sleep(1000);
        //Action dragAndDrop = builder.clickAndHold(from).build();
        //dragAndDrop.perform();
        
        //sleep(1000);
        //Action dragAndDrop2 = builder.moveToElement(to).build();
        //Action dragAndDrop2 = builder.moveByOffset(400, 400).build();
        //dragAndDrop2.perform(); 
       
        
        //WebElement dragHover = to.findElement(By.xpath("//div[contains(@class, 'drag-hover')]"));
        //Utils.waitUntilIsVisible(driver, dragHover);
        
        //sleep(1000);
        //Action dragAndDrop3 = builder.release(to).build();
        //dragAndDrop3.perform();
        
        
        /*
        sleep(1000);
        builder.clickAndHold(from).perform();
        
        builder.pause(Duration.ofSeconds(1)).perform();
        
        builder.moveToElement(to,10,10).perform();
        
        builder.pause(Duration.ofSeconds(1)).perform();
        
        builder.release().perform();
        
        builder.pause(Duration.ofSeconds(1)).perform();
        */
        
        
        /*
        builder.dragAndDropBy(from, 350, 350).perform();
        */
        
        /*
        Action dragAndDrop = builder.clickAndHold(from).moveToElement(to,20,20).release().build();        
        dragAndDrop.perform();*/
                
        Logger.getGlobal().info("azione eseguita!");
        
        /** Debug code **/
        Logger.getGlobal().info("TEST CONCLUSO");
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
    
}//end class
