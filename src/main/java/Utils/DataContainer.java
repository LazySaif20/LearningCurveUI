package Utils;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import BasePage.PageObjectManager;
import DriverConfiguration.DriverConfiguration;
import io.cucumber.java.Scenario;

public class DataContainer {
	
	public HashMap<Long, DriverConfiguration> driverConfig;
	public WebDriver driver;
	public Scenario scenario;
	private PageObjectManager pageObjectManager;
	public ScenarioContext scenarioContext;
	
	public DataContainer() {
		this.scenarioContext = new ScenarioContext();
	}
	
	public void setPageObjectManager(PageObjectManager pageObjectManager) {
		this.pageObjectManager = pageObjectManager;
	}


	public HashMap<Long, DriverConfiguration> getDriverConfig() {
		return driverConfig;
	}
	
	public Scenario getScenario() {
		return scenario;
	}
	
	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
	
	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

	public ScenarioContext getScenarioContext() {
		return scenarioContext;
	}
	
}
