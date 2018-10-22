/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserGroupDetailsPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */

public class DTUserGroupDetailsTest extends FunctionalTestBase {
    
    @Autowired
    public DTLoginPage dTLoginPage;
    
    @Autowired
    public DTDashboardPage dTDashboardPage;
    
    @Autowired
    public DTUserGroupsPage dTGroupsPage;
    
    @Autowired
    public DTUserGroupDetailsPage dTGroupDetailsPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void runTest(){
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        dTDashboardPage.SelectSecondOrderLink("User Management", "Groups");
        
        String pageTitle = "Groups";
        Assert.assertEquals(pageTitle, dTGroupsPage.getPageTitle().getText());
        
    //    Kebab kebab = Utils.getKebabOnTable(dTGroupsPage.getGroupsTable(), 1, "button");
    //    kebab.getClickable().click();
        
    //    Utils.waitUntilIsVisible(driver, kebab.getActionList());
    //    Utils.clickKebabActionOnList(kebab.getActionList(), "Details");
        
        String groupTitle = "Details";
        String detailsGroup = "Group";
        String detailsName = "Name";
        String[] tabs = new String[]{"Pages", "Users", "Widget Types", "Contents", "Resources"};

    //    String[] pageTabs = Utils.getText(dTGroupDetailsPage.getDetailsTabs());

       /* Assert.assertEquals(groupTitle, dTGroupDetailsPage.getPageTitle().getText());
        Assert.assertEquals(detailsGroup, dTGroupDetailsPage.getDetailsGroup().getText());
        Assert.assertEquals(detailsName, dTGroupDetailsPage.getDetailsName().getText());
    //    Assert.assertArrayEquals(tabs, pageTabs);
        
        dTGroupDetailsPage.getDetailsTabs().get(1).click();
        Utils.waitUntilIsVisible(driver, dTGroupDetailsPage.getUsersContent());
        Assert.assertTrue(dTGroupDetailsPage.getUsersContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(2).click();
        Utils.waitUntilIsVisible(driver, dTGroupDetailsPage.getWidgetTypesContent());
        Assert.assertTrue(dTGroupDetailsPage.getWidgetTypesContent().isDisplayed());

        
        dTGroupDetailsPage.getDetailsTabs().get(3).click();
        Utils.waitUntilIsVisible(driver, dTGroupDetailsPage.getContentsContent());
        Assert.assertTrue(dTGroupDetailsPage.getContentsContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(4).click();
        Utils.waitUntilIsVisible(driver, dTGroupDetailsPage.getResourcesContent());
        Assert.assertTrue(dTGroupDetailsPage.getResourcesContent().isDisplayed());
        
        dTGroupDetailsPage.getDetailsTabs().get(0).click();
        Utils.waitUntilIsVisible(driver, dTGroupDetailsPage.getPagesContent());
        Assert.assertTrue(dTGroupDetailsPage.getPagesContent().isDisplayed());     */  
    }
    
}
