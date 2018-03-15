package tests;



import utils.ReceiptDTLoginPage;
import pages.DTLoginPage;
import utils.FunctionalTest;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DTLoginFormTest extends FunctionalTest {

	@Test
	public void LogIn(){
	
		
		DTLoginPage dtLoginPage = new DTLoginPage(driver);


		dtLoginPage.logIn("admin", "adminadmin");

		ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
		assertTrue(receiptDtPage.isInitialized());
                
                assertEquals(" Admin ", receiptDtPage.confirmationHeader());
	}	
}