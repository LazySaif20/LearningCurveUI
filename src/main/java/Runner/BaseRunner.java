package Runner;

import java.util.HashMap;
import java.util.Map;

import org.aeonbits.owner.ConfigCache;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import DriverConfiguration.DriverConfiguration;
import DriverConfiguration.EnvironmentConfig;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(glue= {"StepDefinitions"})
public class BaseRunner extends AbstractTestNGCucumberTests{
	
	public BaseRunner() {}
	
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
		//envConfig = ConfigCache.getOrCreate(EnvironmentConfig.class, envMapping);
	}
	

}
