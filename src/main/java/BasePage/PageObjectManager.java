package BasePage;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	private WebDriver driver;
	private BasePage basePage;
	private CarsHomePage carsHomePage;
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
		this.basePage = new BasePage(this.driver);
	}

	public BasePage getBasePage() {
	    if (basePage == null) { basePage = new BasePage(this.driver);}return basePage;
	}
	
	public CarsHomePage getcarsHomePage() {
		if (carsHomePage == null) { carsHomePage = new CarsHomePage(this.driver);}return carsHomePage;
	}
}
