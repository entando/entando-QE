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

package org.entando.selenium.utils;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTLoginPage;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * <strong>FunctionalTest</strong> handles setup and teardown of WebDriver.
 *
 * @version 1.01
 */

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners({ SeleniumTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class })
public class FunctionalTestBase {
    /*
        Pages used on this class
    */
    @Autowired
    protected WebDriver driver;
    
    @Autowired
    public DTLoginPage dTLoginPage;
        
    /*
        Parameters
    */
    //Login credentials
    private final String USERNAME = "admin";
    private final String PASSWORD = "adminadmin";
    
    //Time in milliseconds used for the sleep when the Logger is turned on
    public final int SLEEPTIME = 2000;
    
    /*
        Driver lifecycle management code
    */
    @BeforeAll
    public void setUp() {
        /*
            ATTENTION!!!
            Logger is utilized to turn on the sleeps and debugging messages
            Remember to set 'Level.OFF' to turn off Loger
            Set 'Level.INFO' to turn on Logger
        */
        Logger.getGlobal().setLevel(Level.INFO);
        
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
    }

    @AfterEach
    public void cleanUp() {
        driver.manage().deleteAllCookies();
    }

    @AfterAll
    public void tearDown() {
        driver.close();
        driver.quit();
    }
    
    /**
     * This login test is common to almost all test Classes
     */
    protected void login(){
        driver.manage().window().maximize();
        dTLoginPage.logIn(USERNAME, PASSWORD);
        ReceiptDTLoginPage receiptDtPage = dTLoginPage.submit();
        assertTrue(receiptDtPage.isInitialized());
    }
    
    /**
     * Return the USERNAME
     * @return USERNAME
     */
    protected String getUsername(){
        return USERNAME;
    }
    
    /**
     * Return the PASSWORD
     * @return PASSWORD
     */
    protected String getPassword(){
        return PASSWORD;
    }
}
