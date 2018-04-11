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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTPageTreePage extends PageObject {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(css = "table > thead")
    private WebElement tableHeader;

    @FindBy(css = "table > tbody")
    private WebElement tableBody;

    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public WebElement getTableHeader() {
        return this.tableHeader;
    }

    public WebElement getTableBody() {
        return this.tableBody;

    }

    public DTPageTreePage(WebDriver driver) {
        super(driver);
    }

}
