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
public class DTUserManageAuthorityPage extends PageObject {
    
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//label/span[text()='User Group']")
    private WebElement groupLabel;
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//label/span[text()='User Role']")
    private WebElement roleLabel;

    public WebElement getRoleLabel() {
        return roleLabel;
    }

    public WebElement getGroupLabel() {
        return groupLabel;
    }
    
    @FindBy(xpath = "//*[@class='UserAuthorityTable']//select[@class='form-control']")
    private List<WebElement> authorizationControls;

    public List<WebElement> getAuthorizationControls() {
        return authorizationControls;
    }
    
    @FindBy(xpath = "//button[@type='button']/span[text()[contains(.,'Add')]]")
    private WebElement addButton;

    public WebElement getAddButton() {
        return addButton;
    }
     
    @FindBy(xpath = "//button[@type='submit']/span[text()[contains(.,'Save')]]")
    private WebElement saveButton;

    public WebElement getSaveButton() {
        return saveButton;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public DTUserManageAuthorityPage(WebDriver driver) {
        super(driver);
    }
    
}
