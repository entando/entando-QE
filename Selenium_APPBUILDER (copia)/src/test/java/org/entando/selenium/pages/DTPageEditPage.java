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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.entando.selenium.utils.PageObject;
import org.entando.selenium.utils.pageParts.SwitchButton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

/**
 * This class represent the Edit Page page
 * 
 * @version 1.01
 */
public class DTPageEditPage extends PageObject {
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(css = "[name='titles.en']")
    private WebElement enTitleField;
    
    @FindBy(xpath = "//label[contains(@for,'titles.en')]/*//*[contains(@class,'required-icon')]")
    private WebElement enTitleAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'titles.en')]")
    private WebElement enTitleFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'titles.it')]")
    private WebElement itTitleField;
    
    @FindBy(xpath = "//label[contains(@for,'titles.en')]/*//*[contains(@class,'required-icon')]")
    private WebElement itTitleAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'titles.it')]")
    private WebElement itTitleFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'titles.es')]")
    private WebElement esTitleField;
    
    @FindBy(xpath = "//label[contains(@for,'titles.es')]/*//*[contains(@class,'required-icon')]")
    private WebElement esTitleAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'titles.es')]")
    private WebElement esTitleFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'code')]")
    private WebElement codeField;
    
    @FindBy(xpath = "//label[contains(@for,'code')]/*//*[contains(@class,'required-icon')]")
    private WebElement codeAsterisk;
    
    @FindBy(xpath = "//label[contains(@*, 'code')]//button")
    private WebElement codeHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Insert page code']")
    private WebElement codeHelpPopOver;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//*[contains(@for,'code')]")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//select[contains(@name,'ownerGroup')]")
    private WebElement ownerGroup;
    
    @FindBy(xpath = "//span[text()='Owner Group']/../*/*[contains(@class,'required-icon')]")
    private WebElement ownerGroupAsterisk;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//select[contains(@name,'ownerGroup')]")
    private WebElement ownerGroupError;
    
    @FindBy(xpath = "//div[contains(@class,'form-group')]/*//*[text()='Join Group']/../../..//select")
    private WebElement joinGroup;
    
    @FindBy(xpath = "//span[text()='Join Group']/../*/*[contains(@class,'required-icon')]")
    private WebElement joinGroupAsterisk;
    
    @FindBy(xpath = "//select[contains(@name,'pageModel')]")
    private WebElement pageModel;
    
    @FindBy(xpath = "//span[text()='Page Model']/../*/*[contains(@class,'required-icon')]")
    private WebElement pageModelAsterisk;
    
    @FindBy(xpath = "//label[contains(@*, 'pageModel')]//button")
    private WebElement pageModelHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Select a page model']")
    private WebElement pageModelHelpPopOver;
    
    @FindBy(xpath = "//div[contains(@class,'has-error')]/*//select[contains(@name,'pageModel')]")
    private WebElement pageModelError;
    
    @FindBy(xpath = "//div[contains(@class, 'form-group')]/label[contains(@for, 'displayedInMenu')]/../div[1]//span[contains(@class, 'bootstrap-switch-label')]")
    private WebElement switchesSection;
        
    @FindBy(xpath = "//label[contains(@*, 'displayedInMenu')]//button")
    private WebElement displayedInMenuHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Show this page in menu']")
    private WebElement displayedInMenuHelpPopOver;
    
    @FindBy(xpath = "//span[text()='SEO']/..//button")
    private WebElement seoHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Activate SEO on page']")
    private WebElement seoHelpPopOver;
    
    @FindBy(xpath = "//select[contains(@name,'charset')]")
    private WebElement charsetSelect;
    
    @FindBy(xpath = "//label[contains(@*, 'charset')]//button")
    private WebElement charsetHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Interpret a sequence of byte as representation of characters.The Default is set for the machine in use']")
    private WebElement charsetHelpPopOver;
    
    @FindBy(xpath = "//select[contains(@name,'contentType')]")
    private WebElement mimeTypeSelect;
    
    @FindBy(xpath = "//span[text()='MimeType']/..//button")
    private WebElement mimeTypeHelpButton;
    
    @FindBy(xpath = "//div[contains(@id, 'popover')]/div[text()='Identify the type of information (imagine, text..) that Entando gives back to the asking browser']")
    private WebElement mimeTypeHelpPopOver;
    
    @FindBy(xpath = "//button/span[text()='Save']/..")
    private WebElement saveButton;
    
    @FindBy(xpath = "//button[contains(.,'Save and Configure')]")
    private WebElement saveAndConfigureButton;
    
    @FindBy(xpath = "//i[contains(@class, 'fa-plus')]/../..//button")
    private WebElement plusButton;
    
    @FindBy(xpath = "//div[contains(@class, 'MultiSelectRenderer')]")
    private WebElement multiSelectSection;
    
        
    public DTPageEditPage(WebDriver driver) {
       super(driver);
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getEnTitleField() {
        return enTitleField;
    }

    public WebElement getItTitleField() {
        return itTitleField;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getEnTitleFieldError() {
        return enTitleFieldError;
    }

    public WebElement getItTitleFieldError() {
        return itTitleFieldError;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public WebElement getEsTitleField() {
        return esTitleField;
    }

    public WebElement getEsTitleAsterisk() {
        return esTitleAsterisk;
    }

    public WebElement getEsTitleFieldError() {
        return esTitleFieldError;
    }
        
    public void setEnTitleField(String enTitle) {
        this.enTitleField.clear();
        this.enTitleField.sendKeys(enTitle);
    }

    public void setItTitleField(String itTitle) {
        this.itTitleField.clear();
        this.itTitleField.sendKeys(itTitle);
    }
    
    public void setEsTitleField(String esTitle) {
        this.esTitleField.clear();
        this.esTitleField.sendKeys(esTitle);
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public WebElement getEnTitleAsterisk() {
        return enTitleAsterisk;
    }

    public WebElement getItTitleAsterisk() {
        return itTitleAsterisk;
    }

    public WebElement getCodeAsterisk() {
        return codeAsterisk;
    }

    public WebElement getOwnerGroup() {
        return ownerGroup;
    }

    public WebElement getOwnerGroupAsterisk() {
        return ownerGroupAsterisk;
    }

    public WebElement getOwnerGroupError() {
        return ownerGroupError;
    }

    public Select getJoinGroup() {
        return new Select(joinGroup);
    }

    public WebElement getJoinGroupAsterisk() {
        return joinGroupAsterisk;
    }

    public Select getPageModel() {
        return new Select(pageModel);
    }

    public WebElement getPageModelAsterisk() {
        return pageModelAsterisk;
    }

    public WebElement getPageModelError() {
        return pageModelError;
    }
    
    public SwitchButton getDisplayedInMenuSwitch() {
        List<WebElement> switches = switchesSection.findElements(By.xpath("//div[contains(@class, 'bootstrap-switch wrapper')]"));
        if (switches.size() >= 0)
            return new SwitchButton(switches.get(0));
        return null;
    }
    
    public SwitchButton getSeoSwitch() {
        List<WebElement> switches = switchesSection.findElements(By.xpath("//div[contains(@class, 'bootstrap-switch wrapper')]"));
        if (switches.size() >= 0)
            return new SwitchButton(switches.get(1));
        return null;
    }

    public Select getCharset() {
        return new Select(charsetSelect);
    }

    public Select getMimeType() {
        return new Select(mimeTypeSelect);
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getSaveAndConfigureButton() {
        return saveAndConfigureButton;
    }

    public WebElement getCodeHelpButton() {
        return codeHelpButton;
    }

    public WebElement getCodeHelpPopOver() {
        return codeHelpPopOver;
    }

    public WebElement getPageModelHelpButton() {
        return pageModelHelpButton;
    }

    public WebElement getPageModelHelpPopOver() {
        return pageModelHelpPopOver;
    }

    public WebElement getDisplayedInMenuHelpButton() {
        return displayedInMenuHelpButton;
    }

    public WebElement getDisplayedInMenuHelpPopOver() {
        return displayedInMenuHelpPopOver;
    }

    public WebElement getSeoHelpButton() {
        return seoHelpButton;
    }

    public WebElement getSeoHelpPopOver() {
        return seoHelpPopOver;
    }

    public WebElement getCharsetHelpButton() {
        return charsetHelpButton;
    }

    public WebElement getCharsetHelpPopOver() {
        return charsetHelpPopOver;
    }

    public WebElement getMimeTypeHelpButton() {
        return mimeTypeHelpButton;
    }

    public WebElement getMimeTypeHelpPopOver() {
        return mimeTypeHelpPopOver;
    }

    public WebElement getPlusButton() {
        return plusButton;
    }
    
    
    public void addJoinGroup(String joinGroupName){
        Select joinGroupSelect = new Select(joinGroup);
        joinGroupSelect.selectByVisibleText(joinGroupName);
        plusButton.click();
    }
    
    
    public List<String> getJoinGroupAddedList(){
        List<WebElement> joinGroupAddedButtonsList = multiSelectSection.findElements(By.xpath("//span[contains(@class , 'MultiSelectRenderer__tag')]"));
        /*Debug code*/ Logger.getGlobal().log(Level.INFO, "Numero bottoni: {0}", String.valueOf(joinGroupAddedButtonsList.size()));
        List<String> joinGroupAddedList = new ArrayList<>();
        for (WebElement button : joinGroupAddedButtonsList)
        {
            /*Debug code*/ Logger.getGlobal().info(button.getText());
            joinGroupAddedList.add(button.getText());
        }
        return joinGroupAddedList;
    }
    
    
    public boolean deleteJoinGroup(String joinGroupName){
        String buttonLocator = String.format("//span[text()='%s']/button", joinGroupName);
        WebElement deleteButton = multiSelectSection.findElement(By.xpath(buttonLocator));
        if (deleteButton.isDisplayed())
        {
            deleteButton.click();
            return true;
        }
        return false;
    }
    

}