/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTGroupDetailsPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */

public class DTGroupDetailsTest extends FunctionalTest {
    
    @Autowired
    public DTGroupsPage dTGroupsPage;
    
    @Autowired
    public DTGroupDetailsPage dTGroupDetailsPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void runTest(){
        login();
        goTo("Configuration", "Groups");
        
        String pageTitle = "Groups";
        Assert.assertEquals(pageTitle, dTGroupsPage.getPageTitle().getText());
        
        Kebab kebab = util.getKebabOnTable(dTGroupsPage.getGroupsTable(), 1, "button");
        kebab.getClickable().click();
        
        util.waitUntilIsVisible(driver, kebab.getActionList());
        util.clickKebabActionOnList(kebab.getActionList(), "Details");
        
        String groupTitle = "Details";
        String detailsGroup = "Group";
        String detailsName = "Name";
        String[] tabs = new String[]{"Pages", "Users", "Widget Types", "Contents", "Resources"};

        String[] pageTabs = util.getText(dTGroupDetailsPage.getDetailsTabs());

        Assert.assertEquals(groupTitle, dTGroupDetailsPage.getPageTitle().getText());
        Assert.assertEquals(detailsGroup, dTGroupDetailsPage.getDetailsGroup().getText());
        Assert.assertEquals(detailsName, dTGroupDetailsPage.getDetailsName().getText());
        Assert.assertArrayEquals(tabs, pageTabs);
        
        dTGroupDetailsPage.getDetailsTabs().get(1).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getUsersContent());
        Assert.assertTrue(dTGroupDetailsPage.getUsersContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(2).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getWidgetTypesContent());
        Assert.assertTrue(dTGroupDetailsPage.getWidgetTypesContent().isDisplayed());

        
        dTGroupDetailsPage.getDetailsTabs().get(3).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getContentsContent());
        Assert.assertTrue(dTGroupDetailsPage.getContentsContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(4).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getResourcesContent());
        Assert.assertTrue(dTGroupDetailsPage.getResourcesContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(0).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getPagesContent());
        Assert.assertTrue(dTGroupDetailsPage.getPagesContent().isDisplayed());       
    }
    
}
