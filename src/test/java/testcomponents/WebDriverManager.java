package testcomponents;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.ConfigLoader;

public class WebDriverManager {
	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	static ConfigLoader configloader = new ConfigLoader();

	private WebDriverManager() {
	}

	/*
	 * Method initialize webdriver instance
	 */

	public static WebDriver getDriver() throws IOException {
		if (driver.get() == null) {
			synchronized (WebDriverManager.class) {
				if (driver.get() == null) {
					String browserName = configloader.getProperty("browser");
					if (browserName.equalsIgnoreCase("chrome")) {
						driver.set(new ChromeDriver());
					} else if (browserName.equalsIgnoreCase("firefox")) {
						driver.set(new FirefoxDriver());

					} else if (browserName.equalsIgnoreCase("safari")) {
						driver.set(new SafariDriver());
					}

				}

			}
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get().manage().window().maximize();
		}
		return driver.get();
	}

	/*
	 * Method to quit driver
	 */

	public static void quitDriver() {
		if (driver.get() != null) {
			driver.get().quit();
			driver.remove();
		}
	}
}
