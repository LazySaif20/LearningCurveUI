package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(features = "src/test/resources/Features", glue = { "StepDefinitions" }, monochrome = true, plugin = {
		"json:target/cucumber-reports/CucumberAPI_Report.json", "html:target/cucumber-reports/CucumberAPI_Report.html",
		"rerun:target/cucumber-reports/CucumberAPI_Report.txt", "pretty" }, tags = "@SampleTest")
public class TestAPI_Runner extends AbstractTestNGCucumberTests {

}
