package Hooks;

import java.net.MalformedURLException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import DriverConfiguration.DriverConfiguration;
import DriverConfiguration.DriverFactory;
import DriverConfiguration.EnvironmentConfig;
import Pages.PageObjectManager;
import Runner.TestRunner;
import Utils.DataContainer;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	
	//final static Logger logger = (Logger) LoggerFactory.getLogger(GeneralStepDefinition.class);
	private Scenario scenario;
	DriverConfiguration driverConfig;
	DataContainer dataHolder;
	WebDriver driver;
	PageObjectManager pageObjectManager;
	
	EnvironmentConfig envConfig = TestRunner.envConfig;
	
	public Hooks(DataContainer dataHolder) {
		this.driverConfig = TestRunner.driversConfig.get(Thread.currentThread().getId());
		this.dataHolder = dataHolder;
	}
	
	@Before
	public void beforeScenario(Scenario scenario) throws MalformedURLException  {
		this.scenario = scenario;
		this.dataHolder.setScenario(scenario);
		this.driver = new DriverFactory().getDriver(driverConfig, null);
		this.dataHolder.setDriver(driver);
		this.pageObjectManager = new PageObjectManager(driver);
		this.dataHolder.setPageObjectManager(pageObjectManager);
	} 
	
	@After
	public void afterScenario() {
		if(scenario.isFailed()) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "FailureScreenShot");
			//logger.info("\n Scenario end. Status: {}", scenario.getStatus());
		}
		//driver.close();
		driver.quit();
	}
	
	public void logInReport(String item, String str) {
		this.dataHolder.scenario.log(item+": "+str);
	}
	
}
