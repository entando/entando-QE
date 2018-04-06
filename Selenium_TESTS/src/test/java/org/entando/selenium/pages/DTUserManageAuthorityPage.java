/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.pages;

import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

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
    Select userGroup;
    Select userRole;

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
    
    public int getAuthorizations() {
        try{
            return getAuthorizationRows().size();
        }
        catch(Exception e){
            return 0;
        }
    }
    
    public void setGroupAndRole(int numGroup, int numRole){
        getUserGroup().getOptions().get(numGroup).click();        
        getUserRole().getOptions().get(numRole).click();
    }
    
    public DTUserManageAuthorityPage(WebDriver driver) {
        super(driver);
    }
    
    private Select getUserGroup(){
        if(userGroup == null){
            userGroup = new Select(authorizationControls.get(0));
        }
        return userGroup;
    }
    
    private Select getUserRole(){
        if(userRole == null){
            userRole = new Select(authorizationControls.get(1));
        }
        return userRole;
    }

    private List<WebElement> getAuthorizationRows() {
        return driver.findElements(By.xpath("//*[@class='UserAuthorityTable']//table/tbody/tr"));
    }
    
}
