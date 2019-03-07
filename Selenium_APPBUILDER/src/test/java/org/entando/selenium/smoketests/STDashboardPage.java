/**
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

/**
 * This class prepresent the Dashboard Page
 * 
 * @version 1.01
 */
package org.entando.selenium.smoketests;

import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class STDashboardPage extends PageObject {
    public void selectTab(String tabName) {
        WaitUntil.isVisible(driver, By.linkText(tabName)).click();
    }

    public void selectSecondOrderLink(String tabName, String link){
        this.selectTab(tabName);
        WaitUntil.isVisible(driver, By.linkText(link)).click();
    }

    public STDashboardPage(WebDriver driver) {
        super(driver);
    }
}
