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
import org.entando.selenium.utils.pageParts.SwitchButton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Edit User Roles page
 * 
 * @version 1.01
 */
public class DTUserRoleEditPage extends PageObject {
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(.,'Save')]")
    private WebElement saveButton;
    
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//input[contains(@name,'name')]")
    private WebElement nameField;
    
    @FindBy(xpath = "//input[contains(@name,'name')]/../span/span")
    private WebElement nameFieldError;
    
    @FindBy(xpath = "//input[contains(@name,'code')]")
    private WebElement codeField;
    
    @FindBy(xpath = "//input[contains(@name,'code')]/../span/span")
    private WebElement codeFieldError;
    
    @FindBy(xpath = "//label[text() = 'Content Editing']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement contentEditingSwitch;
    
    @FindBy(xpath = "//label[text() = 'Access to Administration Area']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement accessToAdminAreaSwitch;
    
    @FindBy(xpath = "//label[text() = 'Newsletter management']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement newsletterManagementSwitch;
    
    @FindBy(xpath = "//label[text() = 'Operations on Pages']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement operationsOnPagesSwitch;
    
    @FindBy(xpath = "//label[text() = 'View Users and Profiles']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement viewUsersAndProfilesSwitch;
    
    @FindBy(xpath = "//label[text() = 'User Profile Editing']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement userProfileEditingSwitch;
    
    @FindBy(xpath = "//label[text() = 'Comment Editing']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement commentEditingSwitch;
    
    @FindBy(xpath = "//label[text() = 'Rating Editing']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement ratingEditingSwitch;
    
    @FindBy(xpath = "//label[text() = 'Gestione Web Dynamic Forms']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement gestioneWebDynamicFormsSwitch;
    
    @FindBy(xpath = "//label[text() = 'Operations on Resources']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement operationsOnResourcesSwitch;
    
    @FindBy(xpath = "//label[text() = 'User Editing']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement userEditingSwitch;
    
    @FindBy(xpath = "//label[text() = 'Comment Moderate']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement commentModerateSwitch;
    
    @FindBy(xpath = "//label[text() = 'Newsletter Configuration']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement newsletterConfigurationSwitch;
    
    @FindBy(xpath = "//label[text() = 'Operations on Categories']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement operationsOnCategoriesSwitch;
    
    @FindBy(xpath = "//label[text() = 'All functions']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement allFunctionsSwitch;
    
    @FindBy(xpath = "//label[text() = 'Supervision of contents']/..//div[contains(@class,'bootstrap-switch wrapper')]")
    private WebElement supervisionOfContentsSwitch;
    
    @FindBy(xpath = "//div[contains(@class,'bootstrap-switch wrapper')]")
    private List<WebElement> allSwitches;
    

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getTooltip() {
        return tooltip;
    }

    public WebElement getHelp() {
        return help;
    }

    public WebElement getNameField() {
        return nameField;
    }

    public WebElement getNameFieldError() {
        return nameFieldError;
    }

    public WebElement getCodeField() {
        return codeField;
    }

    public WebElement getCodeFieldError() {
        return codeFieldError;
    }

    public void setNameField(String name) {
        this.nameField.clear();
        this.nameField.sendKeys(name);
    }

    public void setCodeField(String code) {
        this.codeField.clear();
        this.codeField.sendKeys(code);
    }

    public SwitchButton getContentEditingSwitch() {
        return new SwitchButton(contentEditingSwitch);
    }

    public SwitchButton getAccessToAdminAreaSwitch() {
        return new SwitchButton(accessToAdminAreaSwitch);
    }

    public SwitchButton getNewsletterManagementSwitch() {
        return new SwitchButton(newsletterManagementSwitch);
    }

    public SwitchButton getOperationsOnPagesSwitch() {
        return new SwitchButton( operationsOnPagesSwitch);
    }

    public SwitchButton getViewUsersAndProfilesSwitch() {
        return new SwitchButton( viewUsersAndProfilesSwitch);
    }

    public SwitchButton getUserProfileEditingSwitch() {
        return new SwitchButton( userProfileEditingSwitch);
    }

    public SwitchButton getCommentEditingSwitch() {
        return new SwitchButton( commentEditingSwitch);
    }

    public SwitchButton getRatingEditingSwitch() {
        return new SwitchButton( ratingEditingSwitch);
    }

    public SwitchButton getGestioneWebDynamicFormsSwitch() {
        return new SwitchButton( gestioneWebDynamicFormsSwitch);
    }

    public SwitchButton getOperationsOnResourcesSwitch() {
        return new SwitchButton( operationsOnResourcesSwitch);
    }

    public SwitchButton getUserEditingSwitch() {
        return new SwitchButton( userEditingSwitch);
    }
    
    public WebElement getUserEditingSwitchWebElement() {
        return userEditingSwitch;
    }

    public SwitchButton getCommentModerateSwitch() {
        return new SwitchButton( commentModerateSwitch);
    }

    public SwitchButton getNewsletterConfigurationSwitch() {
        return new SwitchButton( newsletterConfigurationSwitch);
    }

    public SwitchButton getOperationsOnCategoriesSwitch() {
        return new SwitchButton( operationsOnCategoriesSwitch);
    }

    public SwitchButton getAllFunctionsSwitch() {
        return new SwitchButton( allFunctionsSwitch);
    }

    public SwitchButton getSupervisionOfContentsSwitch() {
        return new SwitchButton( supervisionOfContentsSwitch);
    }

    public List<SwitchButton> getAllSwitches() {
        List<SwitchButton> switches = new ArrayList<>();
        this.allSwitches.forEach((switchButton) -> {
            switches.add(new SwitchButton(switchButton));
        });
        return switches;
    }
    
    public void setOnAllSwitches(){
        this.getAllSwitches().forEach((switchButton) -> {
            switchButton.setOn();
        });
    }
    
    public void setOffAllSwitches(){
        this.getAllSwitches().forEach((switchButton) -> {
            switchButton.setOff();
        });
    }
    
    public DTUserRoleEditPage(WebDriver driver) {
        super(driver);
    }
        
}
