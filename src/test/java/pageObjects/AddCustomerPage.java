package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import utilities.WaitHelper;

public class AddCustomerPage {
	
	public WebDriver driver;
	WaitHelper waitHelper;
	public JavascriptExecutor js;
	
	public AddCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js = (JavascriptExecutor) driver;
	}
	
	By linkCustomersMenu = By.xpath("//a[@href='#']//p[contains(text(),'Customers')]");
	By linkCustomersMenuItem = By.xpath("//a[@href='/Admin/Customer/List']//p[contains(text(),'Customers')]");
	By buttonAddNew = By.xpath("//a[normalize-space()='Add new']");
	
//	By textCustomerRoleBy = By.xpath("//span[@class='select2 select2-container select2-container--default select2-container--focus']//span[@class='selection']");
//	By listRegistered = By.xpath("//li[@title='Registered']");
//	By listAdministrators = By.xpath("//li[@title='Administrators']");
//	By listGuests = By.xpath("//li[@title='Guests']");
//	By listVendors = By.xpath("//li[@title='Vendors']");
	
	By textEmail = By.xpath("//input[@id='Email']");	
	By textPassword = By.xpath("//input[@id='Password']");
	By textFirstName = By.xpath("//input[@id='FirstName']");
	By textLastName = By.xpath("//input[@id='LastName']");
	By radioGenderMale = By.xpath("//input[@id='Gender_Male']");
	By radioGenderFemale = By.xpath("//input[@id='Gender_Female']");
	By textDOB = By.xpath("//input[@id='DateOfBirth']");
	By textCompany = By.xpath("//input[@id='Company']");
	By textCustomerAttr = By.xpath("//input[@id='customer_attribute_1']");
	By checkTax = By.xpath("//input[@id='IsTaxExempt']");
	By dropdownVendor = By.xpath("//select[@id='VendorId']");
	By textAdminComment = By.xpath("//textarea[@id='AdminComment']");
	By buttonSave = By.xpath("//button[@name='save']");
	
	public String getPageTitle() {
		return driver.getTitle();
	}

	public void clickLinkCustomersMenu() {
		js.executeScript("arguments[0].click()", driver.findElement(linkCustomersMenu));
	}
	
	public void clickLinkCustomersMenuItem() {
		js.executeScript("arguments[0].click()", driver.findElement(linkCustomersMenuItem));
	}
	
	public void clickButtonAddNew() {
		js.executeScript("arguments[0].click()", driver.findElement(buttonAddNew));
	}
	
	public void setEmail(String email) {
		driver.findElement(textEmail).sendKeys(email);
	}
	
	public void setPassword(String password) {
		driver.findElement(textPassword).sendKeys(password);
	}
	
	public void setManagerOfVendor(String value) {
		Select sel = new Select(driver.findElement(dropdownVendor));
		sel.selectByVisibleText(value);
	}
	
	public void setGender(String gender) {
		if(gender.equals("Male")) {
			driver.findElement(radioGenderMale).click();
		} else if(gender.equals("Female")) {
			driver.findElement(radioGenderFemale).click();
		} else {
			driver.findElement(radioGenderMale).click();
		}
	}
	
	public void setFirstName(String fname) {
		driver.findElement(textFirstName).sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		driver.findElement(textLastName).sendKeys(lname);
	}
	
	public void setDOB(String dob) {
		driver.findElement(textDOB).sendKeys(dob);
	}
	
	public void setCompany(String conName) {
		driver.findElement(textCompany).sendKeys(conName);
	}
	
	public void setCustomerAttr(String custAttr) {
		driver.findElement(textCustomerAttr).sendKeys(custAttr);
	}
	
	public void setTax() {
		driver.findElement(checkTax).click();
	}
	
	public void setAdminComment(String comment) {
		driver.findElement(textAdminComment).sendKeys(comment);
	}
	
	public void clickButtonSave() {
		driver.findElement(buttonSave).click();
	}
	
}
