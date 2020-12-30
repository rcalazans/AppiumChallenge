package Challenge.runner;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import Challenge.utils.Report;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)

@CucumberOptions(
		features= {"src/test/java/Challenge/features"},
		glue = {"Challenge.steps"},
		monochrome = true,
		tags = {},
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json", "com.cucumber.listener.ExtentCucumberFormatter:output/report.html"}
		)
public class MainRunner extends AbstractTestNGCucumberTests {
	@AfterClass
	public static void writeExtentReport() throws IOException {
		Report.copyLatestExtentReport();
	}

	

}
