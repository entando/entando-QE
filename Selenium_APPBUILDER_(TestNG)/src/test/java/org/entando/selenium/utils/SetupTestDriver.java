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

/**
 * 
 * 
 * @version 1.01
 */
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SetupTestDriver {
    private WebDriver driver = null;
    private String browser = null;
    private String baseUrl = null;
    private String os = null;
    private String node = null;

    public SetupTestDriver(String os, String browser, String baseUrl, String node) throws MalformedURLException {
        this.browser = browser;
        this.os = os;
        this.baseUrl = baseUrl;
        this.node = node;

        Platform platform = Platform.fromString(os.toUpperCase());
        if(browser.equalsIgnoreCase("chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), chromeOptions);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), firefoxOptions);
        } else {
            InternetExplorerOptions ieOption = new InternetExplorerOptions();
            ieOption.setCapability("platform", platform);
            this.driver = new RemoteWebDriver(new URL(node + "/wd/hub"), ieOption);
        }
                
        this.driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        this.driver.manage().window().maximize();
        this.driver.get(baseUrl);

    }

    public String getOs() {
        return this.os;
    }

    public String getBrowser() {
        return this.browser;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getNode() {
        return this.node;
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}