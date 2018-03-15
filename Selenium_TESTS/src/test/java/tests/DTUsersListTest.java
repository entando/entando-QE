/**/

package tests;


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
import pages.DTUsersPage;

public class DTUsersListTest extends FunctionalTest {

	@Test
	public void runTest(){
		
                DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                List<String> expectedHeaderTitles = Arrays.asList("Username", "Full name", "Email", "Status", "Actions");
		DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("User Settings", "Users");
               // dtDashboardPage.selectTab("User Settings");
                
                DTUsersPage dtusersPage = new DTUsersPage(driver);
                
                dtusersPage.getPageTitle().getText();
                
                
                Utils util = new Utils();
                
                List <String> fetchedHeaderTitles = util.fetchHeaderTitles(dtusersPage.getTableHeader());

                //Asserts that table column names are the expected ones.
                Assert.assertEquals(expectedHeaderTitles, fetchedHeaderTitles);
                
                
                String pageTitle = "Users";
                //Asserts that the page title is the expected one
                Assert.assertEquals(pageTitle, dtusersPage.getPageTitle().getText());
                
                //Asserts the presence of the button with displayed name as argument
                Assert.assertTrue(util.checkButtonPresenceByName(driver, "Add"));
                
	}	
}