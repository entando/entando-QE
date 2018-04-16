/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTGroupDeletePage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTGroupDeleteTest extends FunctionalTest{
    
    @Autowired
    DTGroupsPage dtGroupsPage;
    
    @Autowired
    DTGroupDeletePage dtGroupDeletePage;
    
    @Autowired
    Utils util;
     
    @Test
    public void test(){
        login();
        goTo("Configuration", "Groups");
        
        Utils.Kebab kebab = util.getKebabOnTable(dtGroupsPage.getGroupsTable(), 1, "button");
        kebab.getClickable().click();
        
        util.waitUntilIsVisible(driver, kebab.getActionList());
        util.clickKebabActionOnList(kebab.getActionList(), "Delete");
        
        String title = "Delete";
        String pageTitle = util.getText(dtGroupDeletePage.getPageTitle());
        
        Assert.assertTrue(dtGroupDeletePage.getDeleteDialog().isDisplayed());
        Assert.assertEquals(title, pageTitle);
        Assert.assertTrue(dtGroupDeletePage.getCancel().isDisplayed());
        Assert.assertTrue(dtGroupDeletePage.getDelete().isDisplayed());
    }
}
