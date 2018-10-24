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

import java.net.URL;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.pages.DTLoginPage;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
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
        Settings
    */
    private static final boolean GRID_EXECUTION = true;
    protected String node = "http://localhost:4444";
    private static final boolean HEADLESS = false;
    
    
    //Declare configuration variables
    protected WebDriver driver;
    protected String browserName;
    protected String platformName;
    
    
    //Hold all Configuration values in a LinkedList
    //Extra Usage Information: https://www.swtestacademy.com/junit-parametrized-tests/
    @Parameterized.Parameters
    public static LinkedList<String[]> getEnvironments() throws Exception {
        LinkedList<String[]> env = new LinkedList<>();
        if (GRID_EXECUTION){
            //SET HERE GRID ENVIRONMENTS
            env.add(new String[]{"chrome", Platform.LINUX.toString()});
            env.add(new String[]{"firefox", Platform.LINUX.toString()});
            //env.add(new String[]{"chrome", Platform.WINDOWS.toString()});
            //add more browsers here
            return env;
        }
        //Else (Classic local WebDriver execution)
        env.add(new String[]{"chrome", Platform.ANY.toString()});
        return env;
    }
    
    
    //Constructor
    public FunctionalTestBase(String browserName, String platformName){
        this.browserName = browserName;
        this.platformName = platformName;
    }
    
    
    /*
        Others Parameters
    */
    //Login credentials
    private final String USERNAME = "admin";
    private final String PASSWORD = "adminadmin";
    
    //Time in milliseconds used for the sleep when the Logger is turned on
    public final int SLEEPTIME = 2000;
    
    
    /*
        Driver lifecycle management code
    */    
    @Before
    public void setUp() throws Exception {
        if (GRID_EXECUTION)
        {
            /*
                Grid parallel WebDriver execution
            */
            
            //Set Driver
            if ("firefox".equals(browserName)) {
                FirefoxOptions options = new FirefoxOptions();
                options.setCapability("platform", platformName);
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                driver = new RemoteWebDriver(new URL(node + "/wd/hub"), options);
            }
            if ("chrome".equals(browserName)) {
                ChromeOptions options = new ChromeOptions();
                options.setCapability("platform", platformName);
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                options.addArguments("window-size=1200x600");
                driver = new RemoteWebDriver(new URL(node + "/wd/hub"), options);
            }
            if ("edge".equals(browserName)) {
                EdgeOptions options = new EdgeOptions();
                options.setCapability("platform", platformName);
                /*
                //Edge doesn't support the following options, find another solution for this webdriver
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                */
                driver = new RemoteWebDriver(new URL(node + "/wd/hub"), options);
            }
        }
        else
        {
            /*
                Classic local WebDriver execution
            */
            
            //Set Driver
            if ("firefox".equals(browserName)) {
                FirefoxOptions options = new FirefoxOptions();
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                driver = new FirefoxDriver(options);
            }
            if ("chrome".equals(browserName)) {
                ChromeOptions options = new ChromeOptions();
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                options.addArguments("window-size=1200x600");
                driver = new ChromeDriver(options);
            }
            if ("edge".equals(browserName)) {
                EdgeOptions options = new EdgeOptions();
                options.setCapability("platform", platformName);
                /*
                //Edge doesn't support the following options, find another solution for this webdriver
                if (HEADLESS) 
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                */
                driver = new EdgeDriver(options);
            }
        }
        
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
    
    
    
    
    @After
    public void tearDown() {
        //Delete all the cookies for the current domain.
        driver.manage().deleteAllCookies();
        //Quits the driver, closing every associated window.
        driver.quit(); 
    }
       
    
    /**
     * This login test is common to almost all test Classes
     */
    protected void login(){
        DTLoginPage dTLoginPage = new DTLoginPage(driver);
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
