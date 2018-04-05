/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.pages;

import com.google.inject.Inject;
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
public class DTPageEditPage extends PageObject {

    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;

    @FindBy(name = "titles.en")
    private WebElement enTitle;

    @FindBy(name = "titles.it")
    private WebElement itTitle;
    
    @FindBy(name = "code")
    private WebElement widgetCode;
    
    @FindBy(css = ".PageTreeSelector")
    private WebElement pagePlacementSelector;

    @FindBy(xpath = "//select[@name='pageModel']")
    private WebElement selectPageModel;
    Select pageModel = new Select(selectPageModel);

    @FindBy(css = "button[type='submit'].PageForm__save-btn")
    private WebElement saveButton;
    
    @Inject
    public DTPageEditPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getPageTitle() {
        return this.pageTitle;
    }

    public WebElement getCode() {
        return this.widgetCode;
    }

    public WebElement getEnTitle() {
        return this.enTitle;
    }
    
    public void setEnTitle(String enTitle) {
        this.enTitle.clear();
        this.enTitle.sendKeys(enTitle);
    }

    public WebElement getItTitle() {
        return this.itTitle;
    }
    
    public void setItTitle(String itTitle) {
        this.itTitle.clear();
        this.itTitle.sendKeys(itTitle);
    }
    
    public WebElement getPagePlacementSelector(){
        return pagePlacementSelector;
    }

    public WebElement getPageModel() {
        return this.selectPageModel;
    }
    
    public void setPageModel(int numOrder) {
        this.pageModel.getOptions().get(numOrder).click();
    }

    public WebElement getSaveButton() {
        return this.saveButton;
    }

    public void save() {
        this.saveButton.click();
    }

    public void choosePagePlacement(String page, List<WebElement> rows) {
        for(WebElement row: rows){
            WebElement selectArea = row.findElement(By.className("PageTreeSelector__select-area"));
            WebElement pageName = selectArea.findElement(By.className("PageTreeSelector__page-name"));
            if(pageName.getAttribute("innerText").equalsIgnoreCase(page)){
                selectArea.click();
                break;
            }
        }
    }

    

}