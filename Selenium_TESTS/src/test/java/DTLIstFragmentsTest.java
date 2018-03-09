
//this is a generic test for accessing the different tabs from Entando Dashboard
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DTLIstFragmentsTest extends FunctionalTest {

	@Test
	public void accessTab(){
		driver.get("http://designtime.serv.run");
		
                DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
		DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                List<String> expectedHeaderTitles = Arrays.asList("Name", "Widget Type", "Plugin", "Actions");
                
                dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Fragments");
                dtDashboardPage.checkPageTitle("Fragments");
                dtDashboardPage.isHeaderTable();
                dtDashboardPage.NumberOfRowsInTable();
                dtDashboardPage.NumberOfColumnsInTable();
                dtDashboardPage.checkHeaderTableColumnsNames(expectedHeaderTitles);
                dtDashboardPage.checkButtonPresence("New");
                

	}	
}