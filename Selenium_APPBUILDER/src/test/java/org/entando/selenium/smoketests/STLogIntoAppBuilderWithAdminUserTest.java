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

import org.entando.selenium.pages.DTUsersPage;
import org.entando.selenium.testHelpers.UsersTestBase;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 */
public class STLogIntoAppBuilderWithAdminUserTest extends UsersTestBase {
    /*
        Pages used on this test
    */
    @Autowired
    public STDashboardPage dTDashboardPage;

    @Autowired
    public DTUsersPage dTUsersPage;

    @Autowired
    public STAppBuilderLoginPage sTAppBuilderLoginPage;
    @Autowired
    public STEngineLoginPage stEngineLoginPage;

    @Test
    @Tag("login-tests")
    public void loginOnAppBuilder() throws InterruptedException {
        driver.manage().window().maximize();
        sTAppBuilderLoginPage.logIn(SmokeTestUser.ADMIN);
        //Navigation to the page
        dTDashboardPage.selectSecondOrderLink("User Management", "Users");
        WaitUntil.isVisible(driver, dTUsersPage.getAddButton());
    }
}//end class
