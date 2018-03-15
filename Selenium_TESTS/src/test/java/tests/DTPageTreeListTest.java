package tests;


//this is a generic test for accessing the different tabs from Entando Dashboard
import utils.Utils;
import utils.ReceiptDTLoginPage;
import pages.DTDataTypesPage;
import pages.DTLoginPage;
import utils.FunctionalTest;
import pages.DTDashboardPage;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pages.DTPageTreePage;

public class DTPageTreeListTest extends FunctionalTest {

	@Test
	public void runTest() throws InterruptedException{
		
                DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                List<String> expectedHeaderTitles = Arrays.asList("Page tree", "Status", "Displayed in menu", "Actions");
		DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("Page Creator", "Page Tree");
                
                DTPageTreePage dtpageTreePage = new DTPageTreePage(driver);
                
               
       
                Utils util = new Utils();
                
                List <String> fetchedHeaderTitles = util.fetchHeaderTitles(dtpageTreePage.getTableHeader());

                //Asserts that table column names are the expected ones.
                Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);
                
                
                String pageTitle = "Page Tree";
                //Asserts that the page title is the  expected one.
                Assert.assertEquals(pageTitle, dtpageTreePage.getPageTitle().getText());
                
                 //Asserts the presence of the button with displayed name as argument
                Assert.assertTrue(util.checkButtonPresenceByName(driver, "Add"));
                                
	}	
}