/*
Copyright 2015-Present Entando Inc. (http://www.entando.com) All rights reserved.
This library is free software; you can redistribute it and/or modify it under
the terms of the GNU Lesser General Public License as published by the Free
Software Foundation; either version 2.1 of the License, or (at your option)
any later version.
This library is distributed in the hope that it will be useful, but WITHOUT
ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
details.
 */

package org.entando.selenium.tests;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.*;
import org.entando.selenium.pages.DTLoginPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This class perform a test of the Login page
 * 
 * @version 1.03
 */
@RunWith(Parallelized.class)
public class DTLoginFormTest extends FunctionalTestBase {

    public DTLoginFormTest(String browserName, String platformName) {
        super(browserName, platformName);
    }
    
    @Test
    public void LogIn() throws InterruptedException {
        DTLoginPage dTLoginPage = new DTLoginPage(driver);
        dTLoginPage.logIn("admin", "adminadmin");

        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());

        assertEquals("admin", receiptDtPage.confirmationHeader());
        
        /** Debug code **/
        if(Logger.getGlobal().getLevel() == Level.INFO){
            sleep(SLEEPTIME);
        }
        /** End Debug code**/
    }
}
