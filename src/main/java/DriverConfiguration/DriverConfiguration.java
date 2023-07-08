package DriverConfiguration;

public class DriverConfiguration {

	private String browser;
	private String browserVersion;
	private String os;
	private String env;
	private boolean headless;

	public DriverConfiguration(String browser, String browserVersion, String os, String env, boolean headless) {
		super();
		this.browser = browser;
		this.browserVersion = browserVersion;
		this.os = os;
		this.env = env;
		this.headless = headless;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getEnv() {
		return env;
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public boolean isHeadless() {
		return headless;
	}

	public void setHeadless(boolean headless) {
		this.headless = headless;
	}

}
