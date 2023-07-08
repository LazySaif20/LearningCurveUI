package DriverConfiguration;

import org.aeonbits.owner.Config.*;
import org.openqa.selenium.grid.config.Config;

@Sources({"file:src/main/java/DriverConfiguration/EnvironmentConfig.properties"})
public interface EnvironmentConfig extends Config{
	
	String env();
	
	@Key("application.${env}.siteURL")
	String sitrUrl();
	
}
