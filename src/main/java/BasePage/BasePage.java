package BasePage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import DriverConfiguration.EnvironmentConfig;
import Runner.TestRunner;

public class BasePage {
	
	protected WebDriver driver;
	protected Wait wait;
	protected Actions builder;
	EnvironmentConfig envConfig = TestRunner.envConfig;
	
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		builder = new Actions(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public BasePage launchUrl(String applicationurl) {
		driver.manage().deleteAllCookies();
		driver.get(applicationurl);
		return new BasePage(driver); 
	}
	
	
}
