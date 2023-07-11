package DriverConfiguration;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ThreadGuard;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver getDriver(DriverConfiguration driverConfig, Map<String, String> config)
			throws MalformedURLException {

		WebDriver driver;

		if (driverConfig.getEnv().equalsIgnoreCase("remote-Desktop")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "102.0");
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("platformName", "Windows");
			browserstackOptions.put("osVersion", "11");
			capabilities.setCapability("bstack:options", browserstackOptions);
			capabilities.setCapability("browserstack.ie.enablePopups", "true");
			capabilities.setCapability("browserstack.user", "");
			capabilities.setCapability("browserstack.key", "");
			driver = new RemoteWebDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);
		} else if (driverConfig.getEnv().equalsIgnoreCase("remote-Android")) {
			
			String username = "";
			String pwd = "";
			final String url = "https://"+username+":"+pwd+"@hub.browserstack.com/wd/hub";
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
			browserstackOptions.put("browserName", "iPhone");
			browserstackOptions.put("device", "iPhone 12");
			browserstackOptions.put("realMobile", "true");
			capabilities.setCapability("bstack:options", browserstackOptions);
			driver = new RemoteWebDriver(new URL(url), capabilities);
		} else if (driverConfig.getEnv().equalsIgnoreCase("remote-iOS")) {
			MutableCapabilities capabilities = new XCUITestOptions();
			HashMap<String, String> bstackOptions = new HashMap<>();
			// bstackOptions.putIfAbsent("source", "cucumber-java:sample-master:v1.2");
			capabilities.setCapability(CapabilityType.BROWSER_NAME, driverConfig.getBrowser());
			capabilities.setCapability(CapabilityType.BROWSER_VERSION, driverConfig.getBrowserVersion());
			capabilities.setCapability(CapabilityType.PLATFORM_NAME, driverConfig.getOs());
			capabilities.setCapability("browserstack.user", "saifansari_dilHIM");
			capabilities.setCapability("browserstack.key", "rpmpuMy1YsxWH4jcuBY4");
			driver = new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} else {
			switch (driverConfig.getBrowser().toLowerCase()) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				if (driverConfig.isHeadless()) {
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
