package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CarsHomePage extends BasePage{
	
	public CarsHomePage(WebDriver driver) {
		super(driver);
	}
	
	private static final By carsGuideLogo = By.xpath("//*[@id='u_H-white']/div/div[1]/div[1]/a[1]/img");
	private static final By searchBox = By.xpath("//*[@id='cgsearch']");
	
	
	public void validateIsLogoPresent() {
		commonActions.isElementDisplayed(carsGuideLogo);
	}
	
	public void searchPreferredCar(String carsName)
	{
		commonActions.writeInto(searchBox, carsName);
		commonActions.pressEnter(searchBox);
	}

}
