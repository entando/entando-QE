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

public class DTWidgetEditPage extends PageObject {
    
    @FindBy(css="h1 > span:nth-child(1)")
	private WebElement pageTitle;
    
    @FindBy(name="code")
	private WebElement widgetCode;
    
    @FindBy(name="titles.en")
	private WebElement enTitle;
    
    @FindBy(name="titles.it")
	private WebElement itTitle;
    
    @FindBy(xpath="//select")
	private WebElement selectGroup;
        Select group = new Select(selectGroup);
    
    @FindBy(css="#basic-tabs-pane-1 > textarea")
    private WebElement customUI;
   
    
    @FindBy(xpath="//button[contains(.,'Save')]")
	private WebElement saveButton;
    
    
    public WebElement getPageTitle(){
        return this.pageTitle;
    }
    
    
    public WebElement getCode(){
        return this.widgetCode;
    }
    
    public WebElement getEnTitle(){
        return this.enTitle;
    }
    
    public WebElement getItTitle(){
        return this.itTitle;
    }
    
    public WebElement getCustomUI(){
        return this.customUI;
    }
    
    public WebElement getGroup(){
        return this.selectGroup;
    }
    
    public WebElement getsaveButton(){
        return this.saveButton;
    }
    
    
    
    
    public void setEnTitle(String enTitle){
		this.enTitle.clear();
		this.enTitle.sendKeys(enTitle);

	}
    
    public void setItTitle(String itTitle){
		this.itTitle.clear();
		this.itTitle.sendKeys(itTitle);

	}
    
    public void setGroup(int numOrder){
        this.group.getOptions().get(numOrder).click();
    }
    
    
    public void setCustomUI(String customUIContent){
        this.customUI.click();
        this.customUI.clear();
        this.customUI.sendKeys(customUIContent);
    }
    
    public void save(){
        this.saveButton.click();
    }
    
    
    
    
    
    public DTWidgetEditPage(WebDriver driver) {
        super(driver);
    }
    
    
    /*public ReceiptDTLoginPage submit(){
		submitButton.click();
		return new ReceiptDTLoginPage(driver);
	}*/
    
    
    
}
