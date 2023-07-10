package StepDefinitions;

import org.openqa.selenium.WebDriver;

import BasePage.BasePage;
import BasePage.CarsHomePage;
import BasePage.PageObjectManager;
import DriverConfiguration.EnvironmentConfig;
import Runner.TestRunner;
import Utils.DataContainer;
import io.cucumber.java.en.*;

public class CarsGuideHomePageStepDefinitions {
	
	WebDriver driver;
	BasePage basePage;
	EnvironmentConfig envConfig = TestRunner.envConfig;
	DataContainer dataHolder;
	PageObjectManager pageObjectManager;
	
	public CarsGuideHomePageStepDefinitions(DataContainer dataHolder) {
		this.dataHolder = dataHolder;
		this.driver = dataHolder.driver;
		this.pageObjectManager = new PageObjectManager(driver);
	}
	
	
	@Given("when user is on the CarsGuide HomePage")
	public void when_user_is_on_the_cars_guide_home_page() {
	    // Write code here that turns the phrase above into concrete actions
		pageObjectManager.getBasePage().launchUrl(envConfig.sitrUrl());
		
	}

	@Then("user is able to see the CarsGuide Logo")
	public void user_is_able_to_see_the_cars_guide_logo() {
	    // Write code here that turns the phrase above into concrete actions
		CarsHomePage carsHomePage = pageObjectManager.getcarsHomePage();
		carsHomePage.validateIsLogoPresent();
	}
}
