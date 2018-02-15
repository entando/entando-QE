import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Login {

  @Test
  public void loginaction() {
      String username="admin";
      String password = "adminadmino";
    WebDriver driver  = new ChromeDriver();
      driver.get("http://localhost:8080/progetto1/do/login");
      
      WebElement UserName= driver.findElement(By.id("username"));
      WebElement PassWord= driver.findElement(By.id("password"));
      
      Actions builder = new Actions(driver);
      
      Actions fillUsername = builder.moveToElement(UserName).click().sendKeys(username);
      fillUsername.perform();
      Actions fillPassword = builder.moveToElement(PassWord).click().sendKeys(password);
      fillPassword.perform();
      
      WebElement acceptAnswerLink = driver.findElement(By.className("btn-login"));
      acceptAnswerLink.click();
      
      
    
    //driver.quit();
  }
}