

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DTLoginFormTest extends FunctionalTest {

	@Test
	public void LogIn(){
		driver.get("http://designtime.serv.run");
		
		DTLoginPage dtLoginPage = new DTLoginPage(driver);


		dtLoginPage.logIn("admin", "adminadmin");

		ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                assertEquals(" Admin ", receiptDtPage.confirmationHeader());
	}	
}