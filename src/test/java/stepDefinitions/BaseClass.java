package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.AddCustomerPage;
import pageObjects.LoginPage;
import pageObjects.SearchCustomerPage;

public class BaseClass {

	public WebDriver driver;
    public LoginPage lp;
    public AddCustomerPage acp;
    public SearchCustomerPage scp;
    public static Logger logger;
    public Properties prop;

    public static String randomString() {
        String generatedString = RandomStringUtils.randomAlphabetic(10);
        return generatedString;
    }
    
    public static String randomNumeric() {
        String generatedNumeric = RandomStringUtils.randomNumeric(10);
        return generatedNumeric;
    }
    
    public static String randomAlphaNumeric() {
    	String generatedString = RandomStringUtils.randomAlphabetic(5);
    	String generatedNumeric = RandomStringUtils.randomNumeric(5);
        return generatedString + generatedNumeric;
    }
}
