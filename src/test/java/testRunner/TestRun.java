package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = ".//Features/Customers.feature",
		glue = "stepDefinitions",
		dryRun = true, // cross-check every step having corresponding methods implemented or not
		monochrome = true,
		// plugin = {"pretty", "html:target/HtmlReports/report.html"}
		plugin = {"pretty", "html:test-output/cucumber-report.html"},
		tags = "@sanity and @regression"
	)

public class TestRun {

} 
