package DriverConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
	
	public WebDriver getDriver(DriverConfiguration driverConfig, Map<String, String> config) throws MalformedURLException {
		
		WebDriver driver;
		
		if(driverConfig.getEnv().equalsIgnoreCase("remote")) {
			String gridURL = config.get("remoteGridUrl");
			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability(CapabilityType.BROWSER_NAME, driverConfig.getBrowser());
			dc.setCapability(CapabilityType.BROWSER_VERSION, driverConfig.getBrowserVersion());
			dc.setCapability(CapabilityType.PLATFORM_NAME, driverConfig.getOs());
			dc.setCapability("network", true);
			dc.setCapability("video", true);
			dc.setCapability("console", true);
			dc.setCapability("visual", true);
			
			driver = new RemoteWebDriver(new URL(gridURL), dc);
		} else {
			switch(driverConfig.getBrowser().toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				if(driverConfig.isHeadless())
				{
					ChromeOptions chromeOptions = new ChromeOptions();
					chromeOptions.addArguments("window-size=1536x721");
					chromeOptions.addArguments("--disable-gpu");
					chromeOptions.addArguments("start-maximized");
					chromeOptions.setHeadless(true);
					DesiredCapabilities dc = new DesiredCapabilities();
					dc.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
					chromeOptions.merge(dc);
					driver = ThreadGuard.protect(new ChromeDriver(chromeOptions));			
				} else {
					driver = ThreadGuard.protect(new ChromeDriver());	
				}
				break;
				default:
					throw new WebDriverException("No Browser Specified");
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

}
