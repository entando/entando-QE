/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.entando.selenium.pages;

import java.util.ArrayList;
import java.util.List;
import org.entando.selenium.utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author leobel
 */
public class DTUserDetailsPage extends PageObject {
    
    @FindBy(css = "h1.PageTitle__title > span:nth-child(1)")
    private WebElement pageTitle;
    
    @FindBy(css = "table")
    private WebElement tableDetails;
    
    @FindBy(xpath = "//button[@type='button' and ./span/text()='Back']")
    private WebElement backButton;

    public WebElement getBackButton() {
        return backButton;
    }

    public WebElement getTableDetails() {
        return tableDetails;
    }

    public WebElement getPageTitle() {
        return pageTitle;
    }
    
    public DTUserDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String[] getDetailsTableHeaders() {
        List<WebElement> headers = tableDetails.findElements(By.xpath("//tr/th"));
        String[] result = new String[headers.size()];
        for(int i = 0; i < headers.size(); i++){
            result[i] = headers.get(i).getText();
        }
        return result;
    }
    
}
