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

import java.util.ArrayList;
import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.Kebab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Page Configure page
 * 
 * @version 1.01
 */
public class DTPageConfigurePage extends PageObject{
    
    @FindBy(css = "h1.PageConfigPage__title")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__info-btn')]")
    private WebElement infoButton;
    
    @FindBy(xpath = "//button[contains(@class, 'btn-warning')]")
    private WebElement restoreButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__unpublish-btn')]")
    private WebElement unpublishButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__publish-btn')]")
    private WebElement publishButton;
    
    @FindBy(xpath = "//button[contains(@class, 'dropdown-on-the-fly')]")
    private WebElement onTheFlyButton;
    
    @FindBy(xpath = "//a[contains(@title, 'Preview')]")
    private WebElement previewButton;
    
    @FindBy(xpath = "//button[contains(@class, 'PageConfigPage__apply-default-btn')]")
    private WebElement defaultWidgetButton;
    
    @FindBy(xpath = "//i[contains(@*, 'PageConfigPage__default-applied-icon')]")
    private WebElement defaultWidgetIcon;
    
    @FindBy(xpath = "//div[contains(@class, 'ContentWidgetList--list')]")
    private WebElement widgetList;
    
    @FindBy(xpath = "//div[contains(@class, 'PageConfigGrid')]")
    private WebElement pageConfigGrid;
    
    

    public DTPageConfigurePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getInfoButton() {
        return infoButton;
    }

    public WebElement getRestoreButton() {
        return restoreButton;
    }

    public WebElement getUnpublishButton() {
        return unpublishButton;
    }

    public WebElement getPublishButton() {
        return publishButton;
    }

    public WebElement getOnTheFlyButton() {
        return onTheFlyButton;
    }

    public WebElement getPreviewButton() {
        return previewButton;
    }

    public WebElement getDefaultWidgetButton() {
        return defaultWidgetButton;
    }

    public WebElement getDefaultWidgetIcon() {
        return defaultWidgetIcon;
    }

    public WebElement getWidgetList() {
        return widgetList;
    }

    public WebElement getPageConfigGrid() {
        return pageConfigGrid;
    }
    
    public WebElement getWidget(String widgetName){
        By widgetTag = By.xpath(String.format("//*[text()= '%s']/../../../../..", widgetName));
        WebElement widget = this.widgetList.findElement(widgetTag);
        return widget;
    }
    
    public List<WebElement> getCells(String cellName){
        By cellTag = By.xpath(String.format("//*[text()= '%s']/..", cellName));
        List<WebElement> cellsElement = this.pageConfigGrid.findElements(cellTag);
        return cellsElement;
    }
    
    public List<WebElement> getWidgetsOnGrid(String widgetName, String cellName){
        By widgetOnGridTag = By.xpath(String.format("//*[text()= '%s']/../..//*[text()= '%s']/..", cellName, widgetName));
        List<WebElement> widgetsElement = this.pageConfigGrid.findElements(widgetOnGridTag);
        return widgetsElement;
    }
    
    public List<Kebab> getKebabsWidgetOnGrid(String widgetName, String cellName){
        List<WebElement> widgetsElement = this.getWidgetsOnGrid(widgetName, cellName);
        List<Kebab> kebabs = new ArrayList<>();
        widgetsElement.forEach((widget) -> {
            kebabs.add(new Kebab(widget, widget));
        });
        return kebabs;
    }
}
