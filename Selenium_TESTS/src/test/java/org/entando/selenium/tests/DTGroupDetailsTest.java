/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTGroupDetailsPage;
import org.entando.selenium.pages.DTGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */

public class DTGroupDetailsTest extends FunctionalTest {
    
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTGroupsPage dTGroupsPage;
    
    @Autowired
    public DTGroupDetailsPage dTGroupDetailsPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void runTest(){
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("Configuration", "Groups");
        
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
        String[] pagesHeaders = new String[]{"Pages", "Actions"};
        String[] usersHeaders = new String[]{"Username", "Last login", "Status", "Actions"};
        String[] widgetTypesHeaders = new String[]{"Title", "Code"};
        String[] contentsHeaders = new String[]{"Title", "Code", "Type", "Last edit"};
        String[] resourcesHeaders = new String[]{"Name", "Type"};

        String[] pageTabs = util.getText(dTGroupDetailsPage.getDetailsTabs());

        Assert.assertEquals(groupTitle, dTGroupDetailsPage.getPageTitle().getText());
        Assert.assertEquals(detailsGroup, dTGroupDetailsPage.getDetailsGroup().getText());
        Assert.assertEquals(detailsName, dTGroupDetailsPage.getDetailsName().getText());
        Assert.assertArrayEquals(tabs, pageTabs);
        
        dTGroupDetailsPage.getDetailsTabs().get(1).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getUsersContent());
        String[] pageUsersHeaders = util.getText(dTGroupDetailsPage.getUsersHeaders());
        Assert.assertArrayEquals(usersHeaders, pageUsersHeaders);
        
        dTGroupDetailsPage.getDetailsTabs().get(2).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getWidgetTypesContent());
        String[] pageWidgetTypesHeaders = util.getText(dTGroupDetailsPage.getWidgetTypesHeaders());        
        Assert.assertArrayEquals(widgetTypesHeaders, pageWidgetTypesHeaders);
        
        dTGroupDetailsPage.getDetailsTabs().get(3).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getContentsContent());
        String[] pageContentsHeaders = util.getText(dTGroupDetailsPage.getContentsHeaders());        
        Assert.assertArrayEquals(contentsHeaders, pageContentsHeaders);
        
        dTGroupDetailsPage.getDetailsTabs().get(4).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getResourcesContent());
        String[] pageResourcesHeaders = util.getText(dTGroupDetailsPage.getResourcesHeaders());        
        Assert.assertArrayEquals(resourcesHeaders, pageResourcesHeaders);
        
        dTGroupDetailsPage.getDetailsTabs().get(0).click();
        util.waitUntilIsVisible(driver, dTGroupDetailsPage.getPagesContent());
        String[] pagePagesHeaders = util.getText(dTGroupDetailsPage.getPagesHeaders());        
        Assert.assertArrayEquals(pagesHeaders, pagePagesHeaders);
        
    }
    
}
