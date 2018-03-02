import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class TestDTPageCreatorMenu {
    static WebDriver driver;

  @Test
  
  
  public void main() {
      String username="admin";
      String password = "adminadmin";
    driver  = new ChromeDriver();
      driver.get("http://designtime.serv.run");
      
      WebElement UserName= driver.findElement(By.id("username"));
      WebElement PassWord= driver.findElement(By.id("password"));
      
      Actions builder = new Actions(driver);
      
      Actions fillUsername = builder.moveToElement(UserName).click().sendKeys(username);
      fillUsername.perform();
      Actions fillPassword = builder.moveToElement(PassWord).click().sendKeys(password);
      fillPassword.perform();
      
      
WebElement acceptAnswerLink = driver.findElement(By.className("LoginForm__loginButton"));      
acceptAnswerLink.click();
      
      pageCreatorMenuOpen();
  
  
  
  
}
  
private static void pageCreatorMenuOpen() {
    //driver  = new ChromeDriver();
      //driver.get("http://designtime.serv.run");
      WebElement pageCreatorLink = driver.findElement(By.linkText("Page Creator"));
      

     // WebElement acceptAnswerLink = driver.findElements(By.cssSelector("li:nth-child(n)"));
      pageCreatorLink.click();  
      //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

      

      WebElement pageTreeLink = driver.findElement(By.linkText("Page Tree"));
      pageTreeLink.click();
      
      
      driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
      WebElement kebabMenu = driver.findElement(By.className("PageTreeActionMenuButton"));
      kebabMenu.click();
      
      //this for the add action
      WebElement kebabAdd = driver.findElement(By.xpath("//span[text()='Add']"));
      kebabAdd.click();

      
  
      driver.close();
      
    driver.quit();
  }
}