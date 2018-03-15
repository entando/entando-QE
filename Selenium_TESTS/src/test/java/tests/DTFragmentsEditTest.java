/*DTListFragmentsTest: tests the Entando Fragments List Page*/

/**
 *
 * @author simone sanna
 */
package tests;


import utils.Utils;
import utils.ReceiptDTLoginPage;
import pages.DTLoginPage;
import pages.DTFragmentPage;
import utils.FunctionalTest;
import pages.DTDashboardPage;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import pages.DTFragmentEditPage;

public class DTFragmentsEditTest extends FunctionalTest {

	@Test
	public void runTest(){
		
                DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
		DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Fragments");
                
                List<String> expectedHeaderTitles = Arrays.asList("Name", "Widget Type", "Plugin", "Actions");
                
                DTFragmentPage dtfragmentPage = new DTFragmentPage(driver);
                
                Utils util = new Utils();
                               
                List <String> fetchedHeaderTitles = util.fetchHeaderTitles(dtfragmentPage.getTableHeader());
                
                //Asserts that table column names are the expected ones.
                Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);
                
                String pageTitle = "Fragments";
                //Asserts that the page title is the expected one
                Assert.assertEquals(pageTitle, dtfragmentPage.getPageTitle().getText());
                
                Assert.assertTrue(util.checkButtonPresenceByName(driver, "New"));
                
                util.SelectKebabActionOnTable(dtfragmentPage.getTableHeader(), dtfragmentPage.getTableBody(), "Name", "myCode", "Edit");
                
                DTFragmentEditPage dtfragmentEditPage = new DTFragmentEditPage(driver);
                dtfragmentEditPage.setGUICode("Code inserted by Selenium");
                dtfragmentEditPage.getCode().click();
               // dtfragmentEditPage.getCode().sendKeys("insert text");
               Assert.assertFalse(dtfragmentEditPage.getCode().isEnabled());
               
               
                
                dtfragmentEditPage.save();
                
    
                

	}	
}