

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ReceiptDTLoginPage extends PageObject {

	@FindBy(css = "#root > div > div.BrandMenu > div > div.collapse.navbar-collapse.navbar-collapse-11 > ul > li.UserDropdown")
	private WebElement header;
	
	public ReceiptDTLoginPage(WebDriver driver) {
		super(driver);
	}

	public boolean isInitialized() {
		return header.isDisplayed();
	}

	public String confirmationHeader(){
		return header.getText();
	}

}
