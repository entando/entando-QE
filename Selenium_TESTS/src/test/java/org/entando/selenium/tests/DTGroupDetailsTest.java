/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTDashboardPage;
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
        dtDashboardPage.SelectSecondOrderLink("Configurtaion", "Groups");
        
        DTGroupsPage dtGroupsPage = new DTGroupsPage(driver);
        
        String pageTitle = "Groups";
        Assert.assertEquals(pageTitle, dtGroupsPage.getPageTitle().getText());
        
        Utils util = new Utils();
        Kebab kebab = util.getKebabOnTable(dtGroupsPage.getGroupsTable(), 0, "button");
        kebab.getClickable().click();
        
        util.waitUntilVisible(driver, kebab.getActionList());
        
        util.clickKebabActionOnList(kebab.getActionList(), "Details");
        
        
    }
    
}
