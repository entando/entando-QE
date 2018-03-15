/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DTWidgetEditPage;
import utils.Utils;

/**
 *
 * @author simone
 */
public class TestingExtensionofPageList extends DTWidgetsListTest {
    
    @Test
	public void runTest(){
            
            super.runTest();
            driver.manage().window().maximize(); //added to resolve issue on non adaptivity of the application
                
                
                String xPathLinks = "//a[contains(@href,'/widget/edit')]";
                WebElement link = driver.findElement(By.xpath(xPathLinks));
                
                link.click();
                
                String pageTitle = "Edit Widget";
                    
		DTWidgetEditPage dtWidgetEditPage = new DTWidgetEditPage(driver);
                
                //Asserts that the page title is the expected one
                Assert.assertEquals(pageTitle, dtWidgetEditPage.getPageTitle().getText());
                
                dtWidgetEditPage.setEnTitle("English Title changed by Selenium");
                dtWidgetEditPage.setItTitle("Titolo Italiano modificato da Selenium");
                dtWidgetEditPage.setCustomUI("<p>Custom UI changed and set by Selenium</p>");
                
                Utils util = new Utils();
                
                util.selectSetByValue(dtWidgetEditPage.getGroup(), "Customers");
                
                
                dtWidgetEditPage.save();
            
        }
    
}
