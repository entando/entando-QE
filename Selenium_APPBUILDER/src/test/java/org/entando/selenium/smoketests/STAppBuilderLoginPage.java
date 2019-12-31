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

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

public class STAppBuilderLoginPage extends STKeycloakBasedLoginPage {

    protected void waitForReturnPage() {
        WaitUntil.urlContaining(driver, "/dashboard");
        WebElement table = driver.findElement(By.xpath("//div[@class='PagesList']/table"));
        WaitUntil.isVisible(driver, table);
        new FluentWait<>(table).withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofMillis(200)).until(
                webElement -> table.findElements(By.xpath("tbody/tr")).size() >= 2);
    }

    public STAppBuilderLoginPage(WebDriver driver, String appBuilderUrl) {
        super(driver, appBuilderUrl);
    }

}

