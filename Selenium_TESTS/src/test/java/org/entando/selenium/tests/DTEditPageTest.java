/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.tests;

import java.util.List;
import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.pages.DTPageEditPage;
import org.entando.selenium.utils.FunctionalTest;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.entando.selenium.utils.Utils.Kebab;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 *
 * @author leobel
 */
public class DTEditPageTest extends FunctionalTest {
    
    @Test
    public void editPage(){
        DTLoginPage dtLoginPage = new DTLoginPage(driver);
        dtLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
        DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
        dtDashboardPage.SelectSecondOrderLink("Page Creator", "Page Tree");

        Utils util = new Utils();
        
        WebElement table = driver.findElement(By.className("PageTree"));
        Kebab kebab = util.getKebabOnTable(table, 1, "i");
        kebab.getClickable().click();
        
        util.waitUntilVisible(driver, kebab.getActionList());
        
        util.clickKebabActionOnList(kebab.getActionList(), "Edit");
        
         String pageTitle = "Edit";
        
        DTPageEditPage dtPageEditPage = new DTPageEditPage(driver);
        
         //Asserts that the page title is the expected one
        Assert.assertEquals(pageTitle, dtPageEditPage.getPageTitle().getText());
        
        dtPageEditPage.setEnTitle("English Title changed by Selenium");
        dtPageEditPage.setItTitle("Titolo Italiano modificato da Selenium");

        util.selectSetByValue(dtPageEditPage.getPageModel(), "Home Page");
        
        List<WebElement> rows = util.expandAllRowsOnTable(driver, 
                dtPageEditPage.getPagePlacementSelector());
        
        dtPageEditPage.choosePagePlacement("Log In", rows);
        
        Assert.assertTrue(dtPageEditPage.getSaveButton().isEnabled());
    }
}
