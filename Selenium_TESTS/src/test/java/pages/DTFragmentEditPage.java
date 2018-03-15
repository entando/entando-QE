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


import utils.ReceiptDTLoginPage;
import utils.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DTFragmentEditPage extends PageObject {
    
    @FindBy(css="h1 > span:nth-child(1)")
	private WebElement pageTitle;
    
    @FindBy(name="code")
	private WebElement fragmentCode;
    
    @FindBy(css="#basic-tabs-pane-1 > div > div > textarea")
    private WebElement GUICode;
   
    
    @FindBy(xpath="//button[contains(.,'Save')]")
	private WebElement saveButton;
    
    
    public WebElement getPageTitle(){
        return this.pageTitle;
    }
    
    
    public WebElement getCode(){
        return this.fragmentCode;
    }
    
   
    
    
    public WebElement getCustomUI(){
        return this.GUICode;
    }
    
    
    
    public WebElement getsaveButton(){
        return this.saveButton;
    }
    
    
    

    
    
    public void setGUICode(String customGUICode){
        this.GUICode.click();
        this.GUICode.clear();
        this.GUICode.sendKeys(customGUICode);
    }
    
    
  
    
    public void save(){
        this.saveButton.click();
    }
    
    
    
    
    
    public DTFragmentEditPage(WebDriver driver) {
        super(driver);
    }
    
    
    /*public ReceiptDTLoginPage submit(){
		submitButton.click();
		return new ReceiptDTLoginPage(driver);
	}*/
    
    
    
}
