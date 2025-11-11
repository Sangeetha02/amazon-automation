package testcomponents;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	protected WebDriver driver;

	/*
	 * Method to get driver instance
	 */
	@BeforeTest
	public void initializeDriver() throws IOException {
		driver = WebDriverManager.getDriver();

	}

	/*
	 * Method to quit driver
	 */
	@AfterTest
	public void tearDown() {
		WebDriverManager.quitDriver();
	}

}
