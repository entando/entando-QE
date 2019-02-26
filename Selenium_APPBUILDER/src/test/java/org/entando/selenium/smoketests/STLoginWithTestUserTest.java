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
package org.entando.selenium.smoketests;

import org.entando.selenium.pages.DTDashboardPage;
import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.entando.selenium.utils.Utils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

import static java.lang.Thread.sleep;

/**
 *
 */
public class STLoginWithTestUserTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public DTDashboardPage dTDashboardPage;

    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public STAppBuilderLoginPage sTAppBuilderLoginPage;
    @Autowired
    public STEngineLoginPage stEngineLoginPage;

    @Test
    public void loginTestUserOnAppBuilder() throws InterruptedException {
        driver.manage().window().maximize();
        sTAppBuilderLoginPage.logIn(SmokeTestUser.SMOKE_TEST_USER);
        //Navigation to the page
        dTDashboardPage.SelectSecondOrderLinkWithSleep("User Management", "Users");
        Utils.waitUntilIsVisible(driver, dTUsersPage.getAddButton());
    }
    @Test
    public void loginTestUserOnEngine() throws InterruptedException {
        driver.manage().window().maximize();
        stEngineLoginPage.logIn(SmokeTestUser.SMOKE_TEST_USER);
        //Navigation to the page
        Utils.waitUntilIsVisible(driver, driver.findElement(new By.ByLinkText("Add User")));
    }
}//end class
