package testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import pageobject.LogOut;
import java.io.IOException;
import testcomponents.BaseTest;
import utilities.ConfigLoader;

public class LogOutTest extends BaseTest {

	/**
	 * This class represents logout of the application. It contains methods to
	 * interact with the login page elements.
	 */

	private LogOut logout;
	ConfigLoader configloader = new ConfigLoader();

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		logout = new LogOut(driver);
	}

	/*
	 * Method to validate sign out
	 */

	@Test(priority=1)
	public void validateLogOut() {
		logout.signOut();
	}

}