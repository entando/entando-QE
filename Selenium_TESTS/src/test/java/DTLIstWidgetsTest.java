
//this is a generic test for accessing the different tabs from Entando Dashboard
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DTLIstWidgetsTest extends FunctionalTest {

	@Test
	public void accessTab(){
		driver.get("http://designtime.serv.run");
		
                DTLoginPage dtLoginPage = new DTLoginPage(driver);
                dtLoginPage.logIn("admin", "adminadmin");
                
                ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                List<String> expectedHeaderTitles = Arrays.asList("Name", "Code", "used", "Actions");
		DTDashboardPage dtDashboardPage = new DTDashboardPage(driver);
                dtDashboardPage.SelectSecondOrderLink("UX Pattern", "Widgets");
                dtDashboardPage.checkPageTitle("Widget");
                dtDashboardPage.isHeaderTable();
                dtDashboardPage.NumberOfRowsInTable();
                dtDashboardPage.NumberOfColumnsInTable();
                dtDashboardPage.checkHeaderTableColumnsNames(expectedHeaderTitles);
                dtDashboardPage.checkButtonPresence("New");

                //Assert.assertEquals(expectedNumColumns, fetchedNumColumns);
	}	
}