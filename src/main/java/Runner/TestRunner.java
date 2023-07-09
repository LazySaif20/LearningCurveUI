package Runner;

import org.aeonbits.owner.ConfigFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import DriverConfiguration.DriverConfiguration;
import Utils.ReportGenerate;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/Features", plugin = {
		"json:target/cucumber-reports/CucumberTestReport.json", "html:target/cucumber-reports/CucumberTestReport.html",
		"rerun:target/cucumber-reports/CucumberTestReport.txt", "pretty" }, tags = "@ValidateCarsGuideLogoSection")
public class TestRunner extends BaseRunner {
	
	private io.cucumber.testng.TestNGCucumberRunner testNGCucumberRunner; 
	
	@BeforeClass(alwaysRun = true)
	@Parameters({"browser", "browserVersion", "platform", "env", "headless"})
	public void setClass(String browser, String browserVersion, String platform, String env, boolean headless)
	{
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		Long threadId = Thread.currentThread().getId();
		DriverConfiguration dConfig = new DriverConfiguration(browser, browserVersion, platform, env, headless);
		BaseRunner.driversConfig.put(threadId, dConfig);
		ConfigFactory.setProperty("env", env);
	}
	
	@Test(description = "Runs Cucumber Feature", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper)
	{
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}
	
	@DataProvider(name = "scenarios")
	public Object [][] scenarios(){
		if(testNGCucumberRunner == null) {
			return new Object[0][0];
		}
		return testNGCucumberRunner.provideScenarios();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
		testNGCucumberRunner.finish();
		Long threadId = Thread.currentThread().getId();
		ReportGenerate.generateCucumberReport();
	}

}
