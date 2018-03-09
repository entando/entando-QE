/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author simone
 */

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
            
        }	

	}
    
    
    public void SelectSecondOrderLink(String TabName, String Link) {   
        this.selectTab(TabName);
        WebElement secondOrderLink = driver.findElement(By.linkText(Link));
      secondOrderLink.click();
        
    }
    
    
    public void SelectKebabAction(String TabName, String Link, String Kebab, String MenuName, String Action){
        this.SelectSecondOrderLink(TabName, Link);
        WebElement kebabMenu = driver.findElement(By.className(Kebab));
      kebabMenu.click();
      
      //String selector = "li.WidgetListMenuAction__menu-item-delete > a > span";
      String selector = String.format("li.%s__menu-item-%s > a > span", MenuName, Action);
      System.out.printf(selector);
      
      
      
      //PAGE TREE KEBAB ACTIONS
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-add > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-edit > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-configure > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-details > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-clone > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-delete > a > span"));
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.PageTreeActionMenuButton__menu-item-unpublish > a > span"));
      
      
      //WIDGET LIST MENU ACTIONS
      //WebElement kebabAction = driver.findElement(By.cssSelector("li.WidgetListMenuAction__menu-item-delete > a > span"));
      WebElement kebabAction = driver.findElement(By.cssSelector(selector));
      kebabAction.click();
    }
    
    
    
    public void SelectKebabActionOnTable(String TabName, String Link, String cellText, int column,  String action){
        this.SelectSecondOrderLink(TabName, Link);
        
      
      WebElement mytable = driver.findElement(By.cssSelector("table > tbody"));
                	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
                        int rows_count = rows_table.size();
                System.out.println("number of rows: " + rows_count);
                
                
                for (int row = 0; row < rows_count; row++) {
    	    
    	    List < WebElement > cell = rows_table.get(row).findElements(By.tagName("td"));
    	    //To calculate no of columns (cells). In that specific row.
    	    int columns_count = cell.size();
            System.out.println("number of columns: " + columns_count);
    	   System.out.println(cell.get(1).getText());
    	        if(cell.get(column).getText().equalsIgnoreCase(cellText)){
                System.out.println("Trovato");
                int child = row + 1;
                String kebabSelector = String.format("table > tbody > tr:nth-child(%s) > td:nth-child(4) > div > i", child);
                System.out.println(kebabSelector);
                WebElement kebab = driver.findElement(By.cssSelector(kebabSelector));
                kebab.click();
                if(Link.equalsIgnoreCase("Widgets")){
                String actionSelector = String.format("table > tbody > tr:nth-child(%s) > td:nth-child(4) > div > ul > li.WidgetListMenuAction__menu-item-%s > a", child, action);
                System.out.println("action selector: " + actionSelector);
                driver.findElement(By.cssSelector(actionSelector)).click();
                }else if(Link.equalsIgnoreCase("Page Tree")){
                    String actionSelector = String.format("table > tbody > tr:nth-child(%s) > td:nth-child(4) > div > ul > li.PageTreeActionMenuButton__menu-item-%s > a", child, action);
                System.out.println("action selector: " + actionSelector);
                driver.findElement(By.cssSelector(actionSelector)).click();
                }
                
                }
                        }
      
    }
    
    public int NumberOfRowsInTable(){
        WebElement mytable = driver.findElement(By.cssSelector("table > tbody"));
                	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
                        int rows_count = rows_table.size();
                System.out.println("number of rows: " + rows_count);
                return rows_count;
    }
    
    
    public int NumberOfColumnsInTable(){
        int columns_count=0;
        WebElement mytable = driver.findElement(By.cssSelector("table > tbody"));
                	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
                        int rows_count = rows_table.size();
               
                for (int row = 0; row < rows_count; row++) {
    	    
    	    List < WebElement > cell = rows_table.get(row).findElements(By.tagName("td"));
    	    //To calculate no of columns (cells). In that specific row.
    	    columns_count = cell.size();
            
    }
                System.out.println("number of columns: " + columns_count);
                return columns_count;
                
    }
    
    
    
    
    public void isHeaderTable(){
        WebElement header = driver.findElement(By.cssSelector("table > thead > tr > th:nth-child(1)"));
        
    }
    
 public void checkPageTitle(String Title){
     String titlename = driver.findElement(By.cssSelector("h1 > span:nth-child(1)")).getText();
     Assert.assertEquals(Title, titlename);
 }
 
 public void checkHeaderTableColumnsNames(List expectedHeaderTitles){
     WebElement header = driver.findElement(By.cssSelector("table > thead"));
     List < WebElement > headerElements = header.findElements(By.tagName("th"));
     int rows_count = headerElements.size();
     
     
     List <String> fetchedHeaderTitles = new ArrayList<>();
     
     for(int i = 0; i < rows_count; i++){
     System.out.println("Header is : " + headerElements.get(i).getText());
     fetchedHeaderTitles.add(headerElements.get(i).getText());
 }
        Assert.assertEquals(fetchedHeaderTitles, expectedHeaderTitles);
     
     
 }
 
 public void checkButtonPresence(String buttonName){
     String buttonLocator = String.format("//button[contains(.,'%s')]", buttonName);
     WebElement button = driver.findElement(By.xpath(buttonLocator));
 }
    
    
    
    
    
    
  
    
    
    
    public DTDashboardPage(WebDriver driver) {
        super(driver);
    }
    
    
    /*public ReceiptDTLoginPage submit(){
		submitButton.click();
		return new ReceiptDTLoginPage(driver);
	}*/
    
    
    
}
