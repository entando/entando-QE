/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import java.util.Arrays;
import java.util.List;
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
import org.junit.Test;

/**
 *
 * @author leobel
 */
public class DTGroupDetailsTest extends FunctionalTest {
    
    @Test
    public void runTest(){
        DTLoginPage dtLoginPage = new DTLoginPage(driver);
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
        dtDashboardPage.SelectSecondOrderLink("Configuration", "Groups");
        
        DTGroupsPage dtGroupsPage = new DTGroupsPage(driver);
        
        String pageTitle = "Groups";
        Assert.assertEquals(pageTitle, dtGroupsPage.getPageTitle().getText());
        
        Utils util = new Utils();
        Kebab kebab = util.getKebabOnTable(dtGroupsPage.getGroupsTable(), 1, "button");
        kebab.getClickable().click();
        
        util.waitUntilVisible(driver, kebab.getActionList());
        
        util.clickKebabActionOnList(kebab.getActionList(), "Details");
        
        DTGroupDetailsPage dtGroupDetailsPage = new DTGroupDetailsPage(driver);
        
        String groupTitle = "Details";
        String detailsGroup = "Group";
        String detailsName = "Name";
        String[] tabs = new String[]{"Pages", "Users", "Widget Types", "Contents", "Resources"};
        String[] pagesHeaders = new String[]{"Pages", "Actions"};
        String[] usersHeaders = new String[]{"Username", "Last login", "Status", "Actions"};
        String[] widgetTypesHeaders = new String[]{"Title", "Code"};
        String[] contentsHeaders = new String[]{"Title", "Code", "Type", "Last edit"};
        String[] resourcesHeaders = new String[]{"Name", "Type"};

        String[] pageTabs = util.getText(dtGroupDetailsPage.getDetailsTabs());

        Assert.assertEquals(groupTitle, dtGroupDetailsPage.getPageTitle().getText());
        Assert.assertEquals(detailsGroup, dtGroupDetailsPage.getDetailsGroup().getText());
        Assert.assertEquals(detailsName, dtGroupDetailsPage.getDetailsName().getText());
        Assert.assertArrayEquals(tabs, pageTabs);
        
        dtGroupDetailsPage.getDetailsTabs().get(1).click();
        util.waitUntilVisible(driver, dtGroupDetailsPage.getUsersContent());
        String[] pageUsersHeaders = util.getText(dtGroupDetailsPage.getUsersHeaders());
        Assert.assertArrayEquals(usersHeaders, pageUsersHeaders);
        
        dtGroupDetailsPage.getDetailsTabs().get(2).click();
        util.waitUntilVisible(driver, dtGroupDetailsPage.getWidgetTypesContent());
        String[] pageWidgetTypesHeaders = util.getText(dtGroupDetailsPage.getWidgetTypesHeaders());        
        Assert.assertArrayEquals(widgetTypesHeaders, pageWidgetTypesHeaders);
        
        dtGroupDetailsPage.getDetailsTabs().get(3).click();
        util.waitUntilVisible(driver, dtGroupDetailsPage.getContentsContent());
        String[] pageContentsHeaders = util.getText(dtGroupDetailsPage.getContentsHeaders());        
        Assert.assertArrayEquals(contentsHeaders, pageContentsHeaders);
        
        dtGroupDetailsPage.getDetailsTabs().get(4).click();
        util.waitUntilVisible(driver, dtGroupDetailsPage.getResourcesContent());
        String[] pageResourcesHeaders = util.getText(dtGroupDetailsPage.getResourcesHeaders());        
        Assert.assertArrayEquals(resourcesHeaders, pageResourcesHeaders);
        
        dtGroupDetailsPage.getDetailsTabs().get(0).click();
        util.waitUntilVisible(driver, dtGroupDetailsPage.getPagesContent());
        String[] pagePagesHeaders = util.getText(dtGroupDetailsPage.getPagesHeaders());        
        Assert.assertArrayEquals(pagesHeaders, pagePagesHeaders);
        
    }
    
}
