package BasePage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverConfiguration.EnvironmentConfig;
import Runner.TestRunner;

public class BasePage {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected Actions builder;
	protected CommonActions commonActions;
	EnvironmentConfig envConfig = TestRunner.envConfig;
	
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
		commonActions = new CommonActions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public BasePage launchUrl(String applicationurl) {
		driver.manage().deleteAllCookies();
		driver.get(applicationurl);
		return new BasePage(driver); 
	}
	
}
