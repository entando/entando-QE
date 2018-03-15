package tests;



import org.junit.Assert;
import utils.Utils;
import utils.ReceiptDTLoginPage;
import pages.DTWidgetEditPage;
import pages.DTLoginPage;
import utils.FunctionalTest;
import pages.DTDashboardPage;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.DTDataTypesPage;

public class DTWidgetEditTest extends FunctionalTest {

	@Test
	public void EditWidget(){
            
            DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                
                DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Widgets");
                //dtDashboardPage.checkPageTitle("Widget");
                
                
                
                Utils util = new Utils();
                
                Assert.assertTrue(util.checkButtonPresenceByName(driver, "New"));
                
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
                
                util.selectSetByValue(dtWidgetEditPage.getGroup(), "Customers");
                
                
                dtWidgetEditPage.save();

	
        
       
                
   
	}	
}