/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.pages;

import com.google.inject.Inject;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author leobel
 */
public class DTGroupDeletePage extends PageObject{
    
    @FindBy(xpath = "//*[contains(@role, 'dialog') and @id='DeleteGroupModal']")
    private WebElement deleteDialog;
    
    @FindBy(xpath = "//*[contains(@class, 'modal-title')]")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//button[contains(., 'Cancel')]")
    private WebElement cancel;
    
    @FindBy(xpath = "//button[contains(., 'Delete')]")
    private WebElement delete;
    
    @Inject
    public DTGroupDeletePage(WebDriver driver) {
        super(driver);
    }
    
    public WebElement getDeleteDialog() {
        return deleteDialog;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public WebElement getCancel() {
        return cancel;
    }

    public WebElement getDelete() {
        return delete;
    }
}
