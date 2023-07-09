package DriverConfiguration;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;


@Sources({"file:src\\main\\java\\DriverConfiguration\\EnvironmentConfig.properties"})
public interface EnvironmentConfig extends Config{
	
	String env();
	
	@Key("application.${env}.siteURL")
	String sitrUrl();
	
}
