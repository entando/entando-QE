import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginButton {

  @Test
  public void loginbutton() {
    WebDriver driver  = new ChromeDriver();
      driver.get("http://localhost:8080/progetto1");
      WebElement acceptAnswerLink = driver.findElement(By.className("btn-login"));
      acceptAnswerLink.click();  
    driver.quit();
  }
}