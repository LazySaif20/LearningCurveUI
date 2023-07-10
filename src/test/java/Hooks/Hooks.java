package Hooks;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

	final static Logger logger = LoggerFactory.getLogger(Hooks.class);
	WebDriver driver;

	public Hooks(WebDriver driver) {
		this.driver = driver;
	}
}
