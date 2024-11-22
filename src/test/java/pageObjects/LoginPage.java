package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	public WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='Email']")
	@CacheLookup
	WebElement textEmail;
	
	@FindBy(xpath = "//input[@id='Password']")
	@CacheLookup
	WebElement textPassword;
	
	@FindBy(xpath = "//button[normalize-space()='Log in']")
	@CacheLookup
	WebElement buttonLogin;
	
	@FindBy(xpath = "//li[@class='nav-item']//a[contains(text(), 'Logout')]")
	@CacheLookup
	WebElement buttonLogout;
	
	public void setEmail(String email) {
		textEmail.clear();
		textEmail.sendKeys(email);
    }

    public void setPassword(String pass) {
        textPassword.clear();
        textPassword.sendKeys(pass);
    }

    public void clickLogin() {
        buttonLogin.click();
    }
    
    public void clickLogout() {
        buttonLogout.click();
    }
}
