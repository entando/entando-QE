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
import org.entando.selenium.pages.DTWidgetAddPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.testHelpers.WidgetsTestBase;
import org.entando.selenium.utils.Parallelized;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;

/**
 * This class perform a test of the Delete Widget
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTWidgetDeleteTest extends WidgetsTestBase {
    
    //Constructor
    public DTWidgetDeleteTest(String browserName, String platformName) {
        super(browserName, platformName);
    }
    
    /*
        Test
    */
    @Test
    public void runTest() throws InterruptedException {
        /*
            Pages used on this test
        */

        DTDashboardPage dTDashboardPage = new DTDashboardPage(driver);
        DTWidgetPage dTWidgetPage = new DTWidgetPage(driver);
        DTWidgetAddPage dTWidgetAddPage = new DTWidgetAddPage(driver);
    
        /*
                Parameters
        */
        //Link menù buttons
        String firstLevelLink = "UX Patterns";
        String secondLevelLink = "Widgets";
        
        
        /*
            Actions and asserts
        */
        //Login
        login();
        
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLink(firstLevelLink, secondLevelLink);
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getNewButton());
        
        //Add a Standard Widget to delete
        Assert.assertTrue("Unable to add a widget",
                addWidget(dTWidgetPage, dTWidgetAddPage));
        
        //Delete the Widget
        Assert.assertTrue("Unable to delete the widget",
                deleteWidget(dTWidgetPage, code));
        
        //Assert the element is not present in the table
        /*Debug code*/ Logger.getGlobal().log(Level.INFO, "Widget Code: {0}", code);
        SimpleTable table = new SimpleTable(dTWidgetPage.getTables().get("user"));
        List<WebElement> foundedWidget = table.findRowList(code, expectedHeaderTitles.get(1));
        Assert.assertTrue(foundedWidget.isEmpty());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
