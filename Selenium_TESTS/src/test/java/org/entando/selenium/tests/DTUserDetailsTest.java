/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTUserDetailsPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTUserDetailsTest extends FunctionalTest {
    
    @Autowired
    public DTUsersPage dTUsersPage;
    
    @Autowired
    public DTUserDetailsPage dTUserDetailsPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void runTest(){
        login();
        goTo("User Settings", "Users");
        
        String user = "admin";
        Utils.Kebab kebab = util.getKebabOnTable(dTUsersPage.getUsersTable(), "Username", user, "button");
        kebab.getClickable().click();
        
        util.waitUntilIsVisible(driver, kebab.getActionList());
        
        util.clickKebabActionOnList(kebab.getActionList(), "View profile of: " + user);
        
        
        String pageTitle = "Details";
        String[] headers = new String[]{"Username", "Full Name", "Email"}; 
        
        Assert.assertEquals(pageTitle, dTUserDetailsPage.getPageTitle().getText());
        Assert.assertArrayEquals(headers, dTUserDetailsPage.getDetailsTableHeaders());
        Assert.assertTrue(dTUserDetailsPage.getBackButton().isDisplayed());
    }
}
