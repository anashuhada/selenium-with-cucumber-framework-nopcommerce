package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilities.WaitHelper;

public class SearchCustomerPage {
	
	public WebDriver driver;
	WaitHelper waitHelper;
	
	public SearchCustomerPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
	}
	
	@FindBy(how = How.ID, using = "SearchEmail")
	@CacheLookup
	WebElement textEmail;

	@FindBy(how = How.ID, using = "SearchFirstName")
	@CacheLookup
	WebElement textFirstName;
	
	@FindBy(how = How.ID, using = "SearchLastName")
	@CacheLookup
	WebElement textLastName;
	
	@FindBy(how = How.ID, using = "search-customers")
	@CacheLookup
	WebElement buttonSearch;
	
	@FindBy(how = How.XPATH, using = "//div[@class='dataTables_scroll']")
	WebElement table;
	
	@FindBy(how = How.XPATH, using = "//div[@class='dataTables_scroll']//tbody//tr")
	List<WebElement> tableRows;
	
	@FindBy(how = How.XPATH, using = "//div[@class='dataTables_scroll']//tbody//tr//td")
	List<WebElement> tableColumns;
	
	public void setEmail(String email) {
		waitHelper.WaitForElement(textEmail, Duration.ofSeconds(10));
		textEmail.clear();
		textEmail.sendKeys(email);
	}
	
	public void setFirstName(String fName) {
		waitHelper.WaitForElement(textFirstName, Duration.ofSeconds(10));
		textFirstName.clear();
		textFirstName.sendKeys(fName);
	}
	
	public void setLastName(String lName) {
		waitHelper.WaitForElement(textLastName, Duration.ofSeconds(10));
		textLastName.clear();
		textLastName.sendKeys(lName);
	}
	
	public void clickButtonSearch() {
		waitHelper.WaitForElement(buttonSearch, Duration.ofSeconds(10));
		buttonSearch.click();
	}
	
	public int getNoOfRows() {
		return tableRows.size();
	}
	
	public int getNoOfColumns() {
		return tableColumns.size();
	}
	
	public boolean searchCustomerByEmail(String email) {
		boolean flag = false;
		
		for(int i = 1; i <= getNoOfRows(); i++) {
			String emailID = table.findElement(By.xpath("//div[@class='dataTables_scroll']//tbody//tr[" + i + "]//td[2]")).getText();
			System.out.println(emailID);
			if(emailID.equals(email)) {
				flag = true; // found
			}
		}
		
		return flag; // not found
	}
	
	public boolean searchCustomerByName(String name) {
		boolean flag = false;
		
		for(int i = 1; i <= getNoOfRows(); i++) {
			String customerName = table.findElement(By.xpath("//div[@class='dataTables_scroll']//tbody//tr[" + i + "]//td[3]")).getText();
			
			String names[] = customerName.split(" "); // when space found, the name will be split; separating first and last name
			
			if(names[0].equals("Victoria") && names[1].equals("Terces")) {
				flag = true; // found
			}
		}
		
		return flag; // not found
	}
}
