package testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import pageobject.Login;
import org.testng.Assert;
import java.io.IOException;
import org.testng.annotations.Parameters;
import testcomponents.BaseTest;
import utilities.ConfigLoader;

public class LoginTest extends BaseTest {
	/**
	 * This class represents the Login Page of the application. It contains methods
	 * to interact with the login page elements.
	 */

	private Login login;
	ConfigLoader configloader = new ConfigLoader();
	private String URL;

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		login = new Login(driver);
	}

	/*
	 * Method to launch URL
	 */

	@Test

	public void launchURL() throws Exception {
		login.goTo(configloader.getProperty("URL"));
	}

	/*
	 * Method to validate input login
	 */

	@Test
	@Parameters({ "username", "password" })
	public void loginApp(String username, String password) throws Exception {
		if (login.txtSignIn.isDisplayed()) {
			login.signUp(username, password);
		}
	}

	/*
	 * Method to sign in to the application
	 */

	@Test
	public void validateLogin() {
		String message = login.message.getText();
		Assert.assertFalse(message.contains("sign in"), "Login failed");
		

	}
}
