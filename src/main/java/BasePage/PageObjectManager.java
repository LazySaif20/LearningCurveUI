package BasePage;

import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	
	private WebDriver driver;
	private BasePage basePage;
	private HomePage homePage;
	
	
	public PageObjectManager(WebDriver driver) {
		this.driver = driver;
		this.basePage = new BasePage(this.driver);
	}

	public BasePage getBasePage() {
	    if (basePage == null) {
	        basePage = new BasePage(this.driver);
	    }
	    return basePage;
	}
	
	public HomePage gethomePage() {
	    if (homePage == null) {
	    	homePage = new HomePage(this.driver);
	    }
	    return homePage;
	}

	
}
