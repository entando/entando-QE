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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver; //aggiunto
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 * <strong>FunctionalTest</strong> handles setup and teardown of WebDriver.
 *
 * @author Kim Schiller
 */

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
@TestExecutionListeners({ SeleniumTestExecutionListener.class,
    DependencyInjectionTestExecutionListener.class })
public class FunctionalTest {

    @Autowired
    protected WebDriver driver;
    
    @BeforeAll
    public void setUp() {
        //driver = new ChromeDriver();   //aggiunto
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
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
}
