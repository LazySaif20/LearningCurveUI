package Runner;

import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/resources/Features",
		plugin = {
				"json:target/cucumber-reports/CucumberTestReport.json",
				"html:target/cucumber-reports/CucumberTestReport.html",
				"rerun:target/cucumber-reports/CucumberTestReport.txt",
				"pretty"
		},
		tags = "@ValidateCarsGuideLogoSection"
		)
public class TestRunner extends BaseRunner {

}
