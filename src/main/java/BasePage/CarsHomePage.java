package BasePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarsHomePage extends BasePage{
	
	public CarsHomePage(WebDriver driver) {
		super(driver);
	}
	
	private static final By carsGuideLogo = By.xpath("//*[@id='u_H-white']/div/div[1]/div[1]/a[1]/img");
	
	public void validateIsLogoPresent() {
		commonActions.isElementDisplayed(carsGuideLogo);
	}

}
