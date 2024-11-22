package stepDefinitions;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class Steps extends BaseClass {
	
	@Before
	public void setup() throws IOException {
		
		logger = Logger.getLogger("nopCommerce"); // logger added
		PropertyConfigurator.configure("log4j.properties"); // logger added
		
		prop = new Properties();
		FileInputStream file = new FileInputStream("config.properties");
		prop.load(file);
		
		String br = prop.getProperty("browser");
		
		if(br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromepath"));
//			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + ".//Drivers/chromedriver.exe");
			
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
	        options.addArguments("--disable-blink-features=AutomationControlled");
	        options.addArguments("--start-maximized");
	        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
	        options.setExperimentalOption("useAutomationExtension", false);

	        driver = new ChromeDriver(options);
		} else if(br.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", prop.getProperty("firefoxpath"));
			driver = new FirefoxDriver();
		}
		

        
        logger.info("*** Launching browser ***");
	}
	
	@Given("user launches Chrome browser")
	public void user_launches_chrome_browser() {
		
        lp = new LoginPage(driver);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().deleteAllCookies();

	}

	@When("user opens URL {string}")
	public void user_opens_url(String url) {
		logger.info("*** Opening URL ***");
		driver.get(url);
		driver.manage().window().maximize(); 
	}

	@When("user enters Email as {string} and Password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		logger.info("*** Providing login details ***");
	    lp.setEmail(email);
	    lp.setPassword(password);
	}

	@Then("page title should be {string}")
	public void page_title_should_be(String title) {
		System.out.println(title);
        if(driver.getPageSource().contains("Login was unsuccessful.")) {
            driver.close();
            logger.info("*** Login passed ***");
            Assert.assertTrue(false);
        } else {
        	logger.info("*** Login failed ***");
            Assert.assertEquals(title, driver.getTitle());
        }
	    
	}

	@Then("clicks on Login button")
	public void clicks_on_login_button() {
		logger.info("*** Starting login ***");
		lp.clickLogin();
	}
	
	@When("user clicks on Logout link")
	public void user_clicks_on_logout_link() {
		logger.info("*** Click on Logout link ***");
		lp.clickLogout();
	}

	@Then("close browser")
	public void close_browser() {
		logger.info("*** Closing browser ***");
		driver.quit();
	}
	
	// Customers feature step definitions
	@Then("user can view Dashboard")
	public void user_can_view_dashboard() {
		acp = new AddCustomerPage(driver);
		String titleString = acp.getPageTitle();
		Assert.assertEquals("Dashboard / nopCommerce administration", titleString);
	}
	
	@When("user clicks on Customers menu")
	public void user_clicks_on_customers_menu() throws InterruptedException {
		Thread.sleep(2000);
	    acp.clickLinkCustomersMenu();
	    System.out.println("LinkCustomersMenu clicked.");
	}
	
	@When("clicks on Customers menu item")
	public void clicks_on_customers_menu_item() throws InterruptedException {
		Thread.sleep(2000);
	    acp.clickLinkCustomersMenuItem();
	    System.out.println("LinkCustomersMenuItem clicked.");
	}
	
	@When("clicks on Add new button")
	public void clicks_on_add_new_button() throws InterruptedException {
	    acp.clickButtonAddNew();
	    System.out.println("ButtonAddNew clicked.");
	    Thread.sleep(2000);
	}
	
	@Then("user can view Add new customer page")
	public void user_can_view_add_new_customer_page() {
		String titleString = acp.getPageTitle();
		Assert.assertEquals("Add a new customer / nopCommerce administration", titleString);
	}
	
	@When("user enters customer details")
	public void user_enters_customer_details() {
		logger.info("*** Adding new customer ***");
		logger.info("*** Providing customer details ***");
		String email = randomString() + "@example.com";
		String pass = randomAlphaNumeric();
		
		acp.setEmail(email);
		acp.setPassword(pass);
		acp.setFirstName("Sarah");
		acp.setLastName("Smith");
		acp.setGender("Male");
		acp.setDOB("1/01/2000");
		acp.setCompany("QA");
		acp.setCustomerAttr("QA1");
		acp.setTax();
		acp.setManagerOfVendor("Vendor 2");
		acp.setAdminComment("This is for testing purposes.");
	}
	
	@When("clicks on Save button")
	public void clicks_on_save_button() throws InterruptedException {
		logger.info("*** Saving customer data ***");
	    acp.clickButtonSave();
	    Thread.sleep(2000);
	}
	
	@Then("user can view confirmation message {string}")
	public void user_can_view_confirmation_message(String msg) {
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-success alert-dismissable']")).getText().contains("The new customer has been added successfully."));
	}
	
	// steps for searching a customer using email
	@When("enters customer email")
	public void enters_customer_email() {
		logger.info("*** Searching customer by email ***");
	    scp = new SearchCustomerPage(driver);
	    scp.setEmail("victoria_victoria@nopCommerce.com");
	}

	@When("clicks on search button")
	public void clicks_on_search_button() throws InterruptedException {
	    scp.clickButtonSearch();
	    Thread.sleep(3000);
	}

	@Then("user should find email in the search table")
	public void user_should_find_email_in_the_search_table() {
	    boolean status = scp.searchCustomerByEmail("victoria_victoria@nopCommerce.com");
	    Assert.assertEquals(true, status);
	}
	
	// steps for searching a customer using first name and last name
	@When("enters customer first name")
	public void enters_customer_first_name() {
		logger.info("*** Searching customer by name ***");
	    scp = new SearchCustomerPage(driver);
	    scp.setFirstName("Victoria");
	}

	@When("enters customer last name")
	public void enters_customer_last_name() {
		scp = new SearchCustomerPage(driver);
	    scp.setLastName("Terces");
	}

	@Then("user should find name in the search table")
	public void user_should_find_name_in_the_search_table() {
		boolean status = scp.searchCustomerByEmail("Victoria Terces");
	    Assert.assertEquals(true, status);
	}
}
