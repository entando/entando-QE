/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.pages;

import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author leobel
 */
public class DTUserGroupDetailsPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@class, 'DetailGroupPage__header')]//span[text()='Group']")
    private WebElement detailsGroup;
    
    @FindBy(xpath = "//*[contains(@class, 'DetailGroupPage__header')]//span[text()='Name']")
    private WebElement detailsName;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabs__container')]//li/a")
    private List<WebElement> detailsTabs;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabPages')]")
    private WebElement pagesContent;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabUsers')]")
    private WebElement usersContent;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabWidgetTypes')]")
    private WebElement widgetTypesContent;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabContents')]")
    private WebElement contentsContent;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabResources')]")
    private WebElement resourcesContent;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabPages')]//thead//th")
    private List<WebElement> pagesHeaders;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabUsers')]//thead//th")
    private List<WebElement> usersHeaders;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabWidgetTypes')]//thead//th")
    private List<WebElement> widgetTypesHeaders;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabContents')]//thead//th")
    private List<WebElement> contentsHeaders;
    
    @FindBy(xpath = "//*[contains(@class, 'GroupDetailTabResources')]//thead//th")
    private List<WebElement> resourcesHeaders;

    public DTUserGroupDetailsPage(WebDriver driver) {
        super(driver);
    }
    
    
    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getDetailsGroup() {
        return detailsGroup;
    }

    public WebElement getDetailsName() {
        return detailsName;
    }

    public List<WebElement> getDetailsTabs() {
        return detailsTabs;
    }
    
    public WebElement getPagesContent() {
        return pagesContent;
    }

    public WebElement getUsersContent() {
        return usersContent;
    }

    public WebElement getWidgetTypesContent() {
        return widgetTypesContent;
    }

    public WebElement getContentsContent() {
        return contentsContent;
    }

    public WebElement getResourcesContent() {
        return resourcesContent;
    }
    
    public List<WebElement> getPagesHeaders() {
        return pagesHeaders;
    }

    public List<WebElement> getUsersHeaders() {
        return usersHeaders;
    }

    public List<WebElement> getWidgetTypesHeaders() {
        return widgetTypesHeaders;
    }

    public List<WebElement> getContentsHeaders() {
        return contentsHeaders;
    }

    public List<WebElement> getResourcesHeaders() {
        return resourcesHeaders;
    }
}
