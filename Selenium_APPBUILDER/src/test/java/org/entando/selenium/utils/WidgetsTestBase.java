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
import org.entando.selenium.pages.DTWidgetAddPage;
import org.entando.selenium.pages.DTWidgetPage;
import org.entando.selenium.utils.pageParts.Kebab;
import org.entando.selenium.utils.pageParts.SimpleTable;
import org.junit.Assert;

 /**
 * This class contains some utils methos for the Widgets tests (Helpers)
 * 
 * @version 1.01
 */
public class WidgetsTestBase extends FunctionalTestBase {
    
    public final List<String> expectedHeaderTitles = Arrays.asList("Name", "Code", "used", "Actions");
    
    public final String codeAlreadyPresent = "SeleniumTest_DontTouch";
    
    Random generator = new Random();
        int randomNumber = generator.nextInt(999);
        
    public final String code = "SeleniumTest_DontTouch" + randomNumber;
    public final String enTitle = code;
    public final String itTitle = code;
    public final String group = "1SeleniumTest_DontTouch";
    public final String customUI = "<>";
    
    public final String addSuccessMessage = "The widget "+ code.toLowerCase() +" has been created";
    public final String deleteSuccessMessage = "The widget "+ code.toLowerCase() +" has been deleted";
    public final String editSuccessMessage = "The widget "+ code.toLowerCase() +" has been updated";
    
    /**
     * 
     * @param dTWidgetPage
     * @param dTWidgetAddPage
     * @return 
     */
    public boolean addWidget(DTWidgetPage dTWidgetPage, DTWidgetAddPage dTWidgetAddPage){
        //Click on New Button
        dTWidgetPage.getNewButton().click();
        
        Utils.waitUntilIsVisible(driver, dTWidgetAddPage.getPageTitle());
        //Compilation of the fields
        dTWidgetAddPage.setCodeField(code);
        dTWidgetAddPage.setEnTitleField(enTitle);
        dTWidgetAddPage.setItTitleField(itTitle);
        dTWidgetAddPage.getGroupSelect().selectByVisibleText(group);
        dTWidgetAddPage.setCustomUI(customUI);
        
        //Save the data
        dTWidgetAddPage.getSaveButton().click();
                
        //Wait loading page
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getPageTitle());
        Utils.waitUntilIsPresent(driver, dTWidgetPage.spinnerTag);
        Utils.waitUntilIsDisappears(driver, dTWidgetPage.spinnerTag);
        
        //Verify the success message
        Assert.assertEquals("Success message content not valid",
                addSuccessMessage, dTWidgetPage.getAlertMessageContent());
        dTWidgetPage.getCloseAlertMessageButton().click();
        
        return true;
    }
    
    
    /**
     * 
     * @param dTWidgetPage
     * @param code
     * @return
     */
    public boolean deleteWidget(DTWidgetPage dTWidgetPage, String code){
        SimpleTable table = new SimpleTable(dTWidgetPage.getTables().get("user"));
        Kebab kebab = table.getKebabOnTable(code, expectedHeaderTitles.get(1), expectedHeaderTitles.get(3));
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
        /** Debug code **/ Logger.getGlobal().info("Kebab delete clicked");
        Utils.waitUntilIsVisible(driver, dTWidgetPage.getSuccessMessage());
        Assert.assertEquals("Delete success message content not valid ", 
                deleteSuccessMessage, dTWidgetPage.getAlertMessageContent());
        dTWidgetPage.getCloseAlertMessageButton().click();
        return true;
    }
    
        
}
