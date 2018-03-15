package pages;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simone
 */


import utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DTFragmentPage extends PageObject {
    
    @FindBy(css="h1 > span:nth-child(1)")
	private WebElement pageTitle;
    
    @FindBy(css="table > thead")
	private WebElement tableHeader;
    
    @FindBy(css="table > tbody")
	private WebElement tableBody;
    
    
    
   
    public WebElement getPageTitle(){
        return this.pageTitle;
    }
    
    public WebElement getTableHeader(){
        return this.tableHeader;
    }
    
    public WebElement getTableBody(){
        return this.tableBody;
        
    }
    
    
    
    
    public DTFragmentPage(WebDriver driver) {
        super(driver);
    }
    
    
    

    
}
