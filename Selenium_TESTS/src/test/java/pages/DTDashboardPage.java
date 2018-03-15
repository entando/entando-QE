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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DTDashboardPage extends PageObject {
    
    @FindBy(linkText="Dashboard")
	private WebElement dashBoardTab;
    @FindBy(linkText="Page Creator")
	private WebElement pageCreatorTab;
    @FindBy(linkText="UX Pattern")
	private WebElement uxPatternTab;
    @FindBy(linkText="Integration")
	private WebElement integrationTab;
    @FindBy(linkText="Data")
	private WebElement dataTab;
    @FindBy(linkText="Configuration")
	private WebElement configurationTab;
    @FindBy(linkText="User Settings")
	private WebElement userSettingsTab;
    
    
    
    public void selectTab(String tabName){
   
        switch (tabName){
            
            case "Dashboard": {
                dashBoardTab.click();
            break;}
            case "Page Creator": {
                pageCreatorTab.click();
            break;
            }
            case "UX Pattern": {
                uxPatternTab.click();
            break;
            
            }
            case "Integration": {
                integrationTab.click();
            break;
            
            }
            
            case "Data": {
                dataTab.click();
            break;
            
            }
            
            case "Configuration": {
                configurationTab.click();
            break;
            
            }
            
            case "User Settings": {
                userSettingsTab.click();
            break;
            
            }
            
        }	

	}
    
    
    public void SelectSecondOrderLink(String TabName, String Link) {   
        this.selectTab(TabName);
        WebElement secondOrderLink = driver.findElement(By.linkText(Link));
      secondOrderLink.click();
        
    }
    
    
    
  
    
    public DTDashboardPage(WebDriver driver) {
        super(driver);
    }
    
    
    /*public ReceiptDTLoginPage submit(){
		submitButton.click();
		return new ReceiptDTLoginPage(driver);
	}*/
    
    
    
}
