package BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.CommonActions;

public class CarsHomePage extends BasePage{
	
	WebDriver driver;
	CommonActions commonActions;
	
	private static final By carsGuideLogo = By.xpath("//*[@id='u_H-white']/div/div[1]/div[1]/a[1]/img");
	
	public CarsHomePage(WebDriver driver) {
		super(driver);
	}
	
	public void validateIsLogoPresent() {
		commonActions.isElementDisplayed(carsGuideLogo);
	}

}
