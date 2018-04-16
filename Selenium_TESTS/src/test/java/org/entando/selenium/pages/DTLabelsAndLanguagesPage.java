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
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author leobel
 */
public class DTLabelsAndLanguagesPage extends PageObject {
    
    @FindBy(css = "h1 > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(xpath = "//ul[contains(@class, 'LabelsAndLanguagesPage__header-container')]/li")
    private List<WebElement> tabs;
    
    @FindBy(xpath = "//select[contains(@class, 'LanguageForm__language-field')]")
    private WebElement selectLanguage;
    
    @FindBy(xpath = "//button[@type='submit']/span[text()='Add']/parent::button")
    private WebElement addLanguageButton;
    
    @FindBy(xpath = "//button[@type='button']/span[text()='Add']/parent::button")
    private WebElement addLabelButton;
    
    @FindBy(xpath = "//table[contains(@class, 'ActiveLangTable__table')]")
    private WebElement languageTable;
    
    @FindBy(xpath = "//form[contains(@class, 'LabelSearchForm')]")
    private WebElement searchForm;
    
    @FindBy(xpath = "//input[contains(@class, 'LabelSearchForm__text-field')]")
    private WebElement searchInput;
    
    @FindBy(xpath = "//button[@type='submit']/span[text()='Search']/parent::button")
    private WebElement searchButton;
    
    @FindBy (xpath = "//table[contains(@class, 'LabelsTable')]")
    private WebElement labelsTable;

    @FindBy(xpath = "//*[contains(@class, 'spinner')]")
    private WebElement searchSpinner;
    
     public WebElement getLabelsTable() {
        return labelsTable;
    }

    public WebElement getSearchSpinner() {
        return searchSpinner;
    }

    public WebElement getSearchForm() {
        return searchForm;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getSearchButton() {
        return searchButton;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }

    public List<WebElement> getTabs() {
        return tabs;
    }

    public WebElement getSelectLanguage() {
        return selectLanguage;
    }
    
    public WebElement getAddLanguageButton() {
        return addLanguageButton;
    }

    public WebElement getAddLabelButton() {
        return addLabelButton;
    }

    public WebElement getAddBUtton() {
        return addLanguageButton;
    }

    public WebElement getLanguageTable() {
        return languageTable;
    }
    
    public WebElement getLabelsTab(){
        return tabs.get(1);
    }
    
    
    public DTLabelsAndLanguagesPage(WebDriver driver) {
        super(driver);
    }

    public void setLanguage(String code) {
        Select language = getLanguageSelect();
        language.selectByValue(code);
    }

    private Select getLanguageSelect() {
        return new Select(selectLanguage);
    }

    public void search(String text) {
        getSearchInput().sendKeys(text);
        getSearchButton().click();
    }
    
}
