package BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.failsafe.TimeoutExceededException;

public class CommonActions {

	final static Logger logger = LoggerFactory.getLogger(CommonActions.class);
	WebDriver driver;

	public CommonActions(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElemrnt(By by) {

		try {
			return driver.findElement(by);
		} catch (TimeoutExceededException se) {
			new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOf(driver.findElement(by)));
			return driver.findElement(by);
		} catch (Exception e) {
			logger.error("Element: %s is not available on the page", by);
			throw e;
		}
	}

	public boolean isElementDisplayed(By by) {
		if (getElemrnt(by).isDisplayed()) {
			return true;
		}
		return false;
	}
}
