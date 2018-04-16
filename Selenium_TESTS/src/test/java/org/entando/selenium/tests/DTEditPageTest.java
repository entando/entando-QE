/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import java.util.List;
import org.entando.selenium.pages.DTPageEditPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */

public class DTEditPageTest extends FunctionalTest {
    
    @Autowired
    public DTPageEditPage dTPageEditPage;
    
    @Autowired
    public Utils util;
    
    @Test
    public void editPage(){
        login();
        goTo("Page Creator", "Page Tree");

        WebElement table = driver.findElement(By.className("PageTree"));
        Kebab kebab = util.getKebabOnTable(table, 1, "i");
        kebab.getClickable().click();
        
        util.waitUntilIsVisible(driver, kebab.getActionList());
        
        util.clickKebabActionOnList(kebab.getActionList(), "Edit");
        
         String pageTitle = "Edit";
        
         //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dTPageEditPage.getPageTitle().getText());
        
        dTPageEditPage.setEnTitle("English Title changed by Selenium");
        dTPageEditPage.setItTitle("Titolo Italiano modificato da Selenium");

        util.selectSetByValue(dTPageEditPage.getPageModel(), "Home Page");
        
        List<WebElement> rows = util.expandAllRowsOnTable(driver, 
                dTPageEditPage.getPagePlacementSelector());
        
        dTPageEditPage.choosePagePlacement("Log In", rows);
        
        Assert.assertTrue(dTPageEditPage.getSaveButton().isEnabled());
    }
}
