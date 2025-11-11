package testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import pageobject.AddAddress;
import org.testng.annotations.Parameters;
import java.io.IOException;
import testcomponents.BaseTest;

public class AddAddressTest extends BaseTest {

	/**
	 * This class represents adding address to the application. It contains methods
	 * to interact with the address page elements.
	 */
	private AddAddress addaddress;

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		addaddress = new AddAddress(driver);

	}

	/*
	 * Method to navigate to add address page
	 */

	@Test(priority=1)
	public void navigateToAddAddress() throws InterruptedException {
		addaddress.goToYourAddress();
	}

	/*
	 * Methods validates the regex of given inputs: validateNumber and
	 * validatePinCode
	 */

	@Test(priority=2)
	public void errorMsgValidation() {
		addaddress.validateMandatoryFields();
	}

	/*
	 * Method to validate adding address
	 */

	@Test(priority=3)
	@Parameters({ "Country", "Name", "PhoneNumber", "Zipcode", "Address1", "Address2", "Landmark", "MakeDefault" })
	public void addingAddress(String Country, String Name, String PhoneNumber, String Zipcode, String Address1,
			String Address2, String Landmark, String MakeDefault) throws InterruptedException {
		addaddress.addAddress(Country, Name, PhoneNumber, Zipcode, Address1, Address2, Landmark, MakeDefault);
	}

}
