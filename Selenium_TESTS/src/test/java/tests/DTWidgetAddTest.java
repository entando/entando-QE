package tests;



import static java.lang.Thread.sleep;
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
import org.openqa.selenium.interactions.Actions;
import pages.DTDataTypesPage;
import pages.DTWidgetPage;

public class DTWidgetAddTest extends FunctionalTest {

	@Test
	public void EditWidget() throws InterruptedException{
            
            DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                
                DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Widgets");
                //dtDashboardPage.checkPageTitle("Widget");
                
                /*String xPathLinks = "//a[contains(@href,'/widget/edit')]";
                WebElement link = driver.findElement(By.xpath(xPathLinks));*/
                
               
                
		
                DTWidgetPage dtWidget = new DTWidgetPage(driver);
                
                Utils util = new Utils();
                Assert.assertTrue(util.checkButtonPresenceByName(driver, "New"));
                
                driver.manage().window().maximize();
                
                dtWidget.getNewWidgetButton().click();
                
                sleep(300);
		DTWidgetEditPage dtWidgetEdit = new DTWidgetEditPage(driver);
                
                dtWidgetEdit.getCode().sendKeys("code_set_by_Selenium");
                dtWidgetEdit.setEnTitle("English Title set by Selenium");
                dtWidgetEdit.setItTitle("Titolo Italiano da Selenium");
                dtWidgetEdit.setCustomUI("<p>Custom UI set by Selenium</p>");
                
          
                 util.selectSetByValue(dtWidgetEdit.getGroup(), "Customers");
                 
                 
                 dtWidgetEdit.save();
             
                 


	
                
   
	}	
}