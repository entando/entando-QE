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

import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author leobel
 */
public class DTUserDetailsPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(css = "table")
    private WebElement tableDetails;
    
    @FindBy(xpath = "//button[@type='button' and ./span/text()='Back']")
    private WebElement backButton;

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getTableDetails() {
        return tableDetails;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public DTUserDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String[] getDetailsTableHeaders() {
        List<WebElement> headers = tableDetails.findElements(By.xpath("//tr/th"));
        String[] result = new String[headers.size()];
        for(int i = 0; i < headers.size(); i++){
            result[i] = headers.get(i).getText();
        }
        return result;
    }
    
}
