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

import edu.emory.mathcs.backport.java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.entando.selenium.utils.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Widgets List page
 * 
 * @version 1.01
 */
public class DTWidgetPage extends PageObject {

    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(css = "table > thead")
    private List<WebElement> tableHeader;

    @FindBy(xpath = "//div[@class = 'WidgetListTable']")
    private List<WebElement> tables;
    
    @FindBy(xpath = "//button[contains(.,'New')]")
    private WebElement newButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//div[contains(@class, 'alert')]")
    private WebElement alertMessage;
    
    
    private static final By headerTag = By.xpath("//th");

    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public List<String> getTableHeader() {
        List<String> tableColumns = new ArrayList<>();
        String[] header = tableHeader.get(0).getText().split(" ");
        return Arrays.asList(header);
    }

    public WebElement getNewButton() {
        return newButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }
    
    public Map<String, WebElement> getTables(){
        Map<String, WebElement> tablesMap = new HashMap<>();
        String key;
        WebElement value;
        for(WebElement table : tables){
            key = table.findElement(By.xpath(".//*[@class = 'WidgetSectionTitle__type']")).getText();
            value = table.findElement(By.xpath(".//table"));
            tablesMap.put(key, value);
        }
        return tablesMap;
    }

    public DTWidgetPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getSuccessMessage() {
        return alertMessage;
    }
    
    public WebElement getCloseAlertMessageButton(){
        return alertMessage.findElement(By.xpath(".//button"));
    }
    
    public String getAlertMessageContent(){
        return alertMessage.getText();
    }

}
