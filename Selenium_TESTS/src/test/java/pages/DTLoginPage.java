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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTLoginPage extends PageObject {
    
    @FindBy(id="username")
	private WebElement userName;
    @FindBy(id="password")
	private WebElement passWord;
    
    @FindBy(className="LoginForm__loginButton")
	private WebElement submitButton;
    
    
    public void logIn(String userName, String passWord){
		this.userName.clear();
		this.userName.sendKeys(userName);

		this.passWord.clear();
		this.passWord.sendKeys(passWord);
	}
    
    public DTLoginPage(WebDriver driver) {
        
        super(driver);
        driver.get("http://designtime.serv.run");
    }
    
   
    
    
    public ReceiptDTLoginPage submit(){
		submitButton.click();
		return new ReceiptDTLoginPage(driver);
	}
    
    
    
}
