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
package org.entando.selenium.pages;

import org.entando.selenium.utils.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTDashboardPage extends PageObject {

    @FindBy(linkText = "Dashboard")
    private WebElement dashBoardTab;
    @FindBy(linkText = "Page Creator")
    private WebElement pageCreatorTab;
    @FindBy(linkText = "UX Pattern")
    private WebElement uxPatternTab;
    @FindBy(linkText = "Integration")
    private WebElement integrationTab;
    @FindBy(linkText = "Data")
    private WebElement dataTab;
    @FindBy(linkText = "Configuration")
    private WebElement configurationTab;
    @FindBy(linkText = "User Settings")
    private WebElement userSettingsTab;

    public void selectTab(String tabName) {

        switch (tabName) {

            case "Dashboard": {
                dashBoardTab.click();
                break;
            }
            case "Page Creator": {
                pageCreatorTab.click();
                break;
            }
            case "UX Pattern": {
                uxPatternTab.click();
                break;

            }
            case "Integration": {
                integrationTab.click();
                break;

            }

            case "Data": {
                dataTab.click();
                break;

            }

            case "Configuration": {
                configurationTab.click();
                break;

            }

            case "User Settings": {
                userSettingsTab.click();
                break;

            }

        }

    }

    public void SelectSecondOrderLink(String TabName, String Link) {
        this.selectTab(TabName);
        WebElement secondOrderLink = driver.findElement(By.linkText(Link));
        secondOrderLink.click();

    }

    public DTDashboardPage(WebDriver driver) {
        super(driver);
    }

}
