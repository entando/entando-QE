/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import org.entando.selenium.pages.DTLabelsAndLanguagesPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTLabelsAndLanguagesTest extends FunctionalTest {
    
    @Autowired
    DTLabelsAndLanguagesPage dTLabelsAndLanguagesPage;
    
    @Autowired
    Utils util;
    
    @Test
    public void test(){
        login();
        goTo("Configuration", "Labels and Languages");
        
        String pageTitle = "Labels and Languages";
        String[] tabs = new String[]{"Languages", "System labels"};
        
        Assert.assertEquals(pageTitle, dTLabelsAndLanguagesPage.getPageTitle().getText());
        
        String[] pageTabs = util.getText(dTLabelsAndLanguagesPage.getTabs());
        Assert.assertArrayEquals(tabs, pageTabs);
        Assert.assertTrue(dTLabelsAndLanguagesPage.getLanguageTable().isDisplayed());
        
        dTLabelsAndLanguagesPage.getLabelsTab().click();
        
        util.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getLabelsTable());
        Assert.assertTrue(dTLabelsAndLanguagesPage.getSearchForm().isDisplayed());
        dTLabelsAndLanguagesPage.search("all");
        util.waitUntilIsVisible(driver, dTLabelsAndLanguagesPage.getSearchSpinner());
        Assert.assertTrue(dTLabelsAndLanguagesPage.getLabelsTable().isDisplayed());
    }
    
}
