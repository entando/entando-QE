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

import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class represent the Settings Page page
 * 
 * @version 1.01
 */
public class DTPageSettingsPage extends PageObject{
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
        
    @FindBy(xpath = "//*[contains(@role, 'tooltip')]")
    private WebElement tooltip;
    
    @FindBy(css = "i.PageTitle__icon")
    private WebElement help;
    
    @FindBy(xpath = "//button/span[text()='Save']/..")
    private WebElement saveButton;
    
    @FindBy(xpath = "//select[contains(@name,'homePageCode')]")
    private WebElement homePageCode;
    
    @FindBy(xpath = "//select[contains(@name,'notFoundPageCode')]")
    private WebElement notFoundPageCode;
    
    @FindBy(xpath = "//select[contains(@name,'errorPageCode')]")
    private WebElement errorPageCode;
    
    @FindBy(xpath = "//select[contains(@name,'loginPageCode')]")
    private WebElement loginPageCode;
    
    @FindBy(xpath = "//div[contains(@class, 'btn-group')]//*[contains(@value, 'relative')]/../..")
    private WebElement baseUrlGroupButtons;
    
    @FindBy(xpath = "//div[contains(@class, 'btn-group')]//*[text()= '\n" +
                    "                                Load nodes on demand']/..")
    private WebElement chooseTheStyleGroupButtons;
    
    @FindBy(xpath = "//div[contains(@class, 'btn-group')]//*[contains(@value, 'breadcrumbs')]/../..")
    private WebElement urlStyleGroupButtons;
    
    @FindBy(xpath = "//div//div[text()='\n" +
                    "                        Append context name on BaseURL']/..//div[2]/div")
    private WebElement appendContextNameSwitch;
    
    @FindBy(xpath = "//div//div[text()='\n" +
                    "                        Append context name on BaseURL']/..//div[4]/div")
    private WebElement useJessessionidSwitch;
    
    @FindBy(xpath = "//div//div[text()='\n" +
                    "                        Home page gets its language from the browser']/..//div[1]/div")
    private WebElement homePageGetsSwitch;
    
    
    
    
    public DTPageSettingsPage(WebDriver driver) {
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

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getHomePageCode() {
        return homePageCode;
    }

    public WebElement getNotFoundPageCode() {
        return notFoundPageCode;
    }

    public WebElement getErrorPageCode() {
        return errorPageCode;
    }

    public WebElement getLoginPageCode() {
        return loginPageCode;
    }

    public WebElement getBaseUrlGroupButtons() {
        return baseUrlGroupButtons;
    }

    public WebElement getChooseTheStyleGroupButtons() {
        return chooseTheStyleGroupButtons;
    }

    public WebElement getUrlStyleGroupButtons() {
        return urlStyleGroupButtons;
    }

    public WebElement getAppendContextNameSwitch() {
        return appendContextNameSwitch;
    }

    public WebElement getUseJessessionidSwitch() {
        return useJessessionidSwitch;
    }

    public WebElement getHomePageGetsSwitch() {
        return homePageGetsSwitch;
    }
    
    
}
