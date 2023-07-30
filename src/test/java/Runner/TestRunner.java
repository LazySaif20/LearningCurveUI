package Runner;

import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigCache;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import DriverConfiguration.DriverConfiguration;
import DriverConfiguration.EnvironmentConfig;
import Utils.ReportGenerate;
import io.cucumber.java.Scenario;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/Features", glue = { "StepDefinitions", "Hooks" }, plugin = {
		"json:target/cucumber-reports/CucumberUI_Report.json", "html:target/cucumber-reports/CucumberUI_Report.html",
		"rerun:target/cucumber-reports/CucumberUI_Report.txt", "pretty" }, tags = "@HomePageValidation")
public class TestRunner {

	private io.cucumber.testng.TestNGCucumberRunner testNGCucumberRunner;
	
	public static EnvironmentConfig envConfig;
	public static HashMap<Long, WebDriver> drivers;
	public static HashMap<Long, DriverConfiguration> driversConfig;
	public static Scenario scenario;
	
	@BeforeTest(alwaysRun = false)
	@Parameters({"env"})
	public void setUpSuite(String env) {
		driversConfig = new HashMap<Long, DriverConfiguration>();
		final Map<String, String> envMapping = new HashMap<String, String>(){{
			put("env", env);
		}};
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		envConfig = ConfigCache.getOrCreate(EnvironmentConfig.class, envMapping);
		drivers = new HashMap<Long, WebDriver>();
		
	}

	@BeforeClass(alwaysRun = true)
	@Parameters({ "browser", "browserVersion", "platform", "env", "headless" })
	public void setClass(String browser, String browserVersion, String platform, String env, boolean headless) {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
		Long threadId = Thread.currentThread().getId();
		DriverConfiguration dConfig = new DriverConfiguration(browser, browserVersion, platform, env, headless);
		TestRunner.driversConfig.put(threadId, dConfig);
		ConfigFactory.setProperty("env", env);
	}

	@Test(description = "Runs Cucumber Feature", dataProvider = "scenarios")
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
		testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
	}

	@DataProvider(name = "scenarios")
	public Object[][] scenarios() {
		if (testNGCucumberRunner == null) {
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
