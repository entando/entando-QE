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

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUserGroupsPage;
import org.entando.selenium.pages.DTLoginPage;
import org.entando.selenium.utils.FunctionalTestBase;
import org.entando.selenium.utils.ReceiptDTLoginPage;
import org.entando.selenium.utils.Utils;
import org.junit.Assert;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author leobel
 */
public class DTUserGroupDeleteTest extends FunctionalTestBase{
    
    @Autowired
    DTLoginPage dtLoginPage;
    
    @Autowired
    DTDashboardPage dTDashboardPage;
    
    @Autowired
    DTUserGroupsPage dtGroupsPage;
    
    @Autowired
    Utils util;
     
    @Test
    public void test(){
        dtLoginPage.logIn("admin", "adminadmin");
        
        ReceiptDTLoginPage receiptDtPage = dtLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
        
    //    dTDashboardPage.SelectSecondOrderLink("User Management", "Groups");
        
   //     Utils.Kebab kebab = Utils.getKebabOnTable(dtGroupsPage.getGroupsTable(), 1, "button");
    //    kebab.getClickable().click();
        
     //   Utils.waitUntilIsVisible(driver, kebab.getActionList());
   //     Utils.clickKebabActionOnList(kebab.getActionList(), "Delete");
        
        String title = "Delete";
       
    }
}
