package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import abstractcomponent.AbstractComponent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.support.ui.Select;


/**
 * Constructor to initialize the web elements.
 */

public class AddAddress extends AbstractComponent {
	WebDriver driver;

	public AddAddress(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id='nav-link-accountList']")
	WebElement accounts;

	@FindBy(xpath = "//span[text()='Your Account']")
	WebElement yourAccount;

	@FindBy(xpath = "//div[@class='a-box-inner']/div/div/h2[contains(text(),'Your Addresses')]")
	WebElement yourAddress;

	@FindBy(xpath = "//div[@class='a-box-inner a-padding-extra-large']")
	WebElement addAddress;

	@FindBy(xpath = "//select[@name='address-ui-widgets-countryCode']")
	WebElement country;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']")
	WebElement name;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']")
	WebElement phoneNumber;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']")
	WebElement pinCode;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']")
	WebElement addressLine1;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine2']")
	WebElement addressLine2;

	@FindBy(xpath = "//input[@id='address-ui-widgets-landmark']")
	WebElement landMark;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressCity']")
	WebElement town;

	@FindBy(xpath = "//Select[@name='address-ui-widgets-enterAddressStateOrRegion']")
	WebElement state;

	@FindBy(xpath = "//label[@for='address-ui-widgets-use-as-my-default']")
	WebElement defaultCheckBox;

	@FindBy(xpath = "//span[@class='a-button a-button-primary']")
	WebElement btnAddAddress;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressFullName']/following-sibling::div[2]/div/div/div/div[text()='Please enter a name.']")
	WebElement emptyNameErrorMsg;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPhoneNumber']/following-sibling::div[3]/div/div/div/div[contains(text(),'Please')]")
	WebElement emptyPhoneNumberErrorMsg;

	@FindBy(xpath = "//div[@class='a-box a-alert-inline a-alert-inline-warning']")
	WebElement invalidPhoneNumber;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressPostalCode']/following-sibling::div[2]/div/div/div/div[contains(text(),'ZIP')]")
	WebElement emptyPinCodeErrorMsg;

	@FindBy(xpath = "//div[@id='address-ui-widgets-postalcode-error']")
	WebElement invalidPinCode;

	@FindBy(xpath = "//input[@id='address-ui-widgets-enterAddressLine1']/following-sibling::div/div/div/div/div[contains(text(),'address')]")
	WebElement emptyAddressErrorMsg;

	@FindBy(xpath = "//label[@for='address-ui-widgets-enterAddressCity']/parent::div/parent::div/following-sibling::div/div[2]/div/div/div/div[contains(text(),'city')]")
	WebElement emptyCityErrorMsg;

	@FindBy(xpath = "//div[@id='yaab-alert-box']/div/h4[contains(text(),'Address saved')]")
	WebElement alertAddressSaved;

	@FindBy(xpath = "//div[@id='yaab-alert-box']/div/h4")
	WebElement alertAddressNotSaved;

	@FindBy(xpath = "//div[@class='a-box-inner a-alert-container']/h4")
	WebElement alertReviewAddress;

	@FindBy(xpath = "//div[@id='ya-myab-display-address-block-0']")
	WebElement addedAddreess;

	@FindBy(xpath = "//div[@id='ya-myab-display-address-block-0']/div//span[contains(text(),'Default')]")
	WebElement defaultAddress;

	/*
	 * Method to navigate to add address page
	 */

	public void goToYourAddress() throws InterruptedException {
		waitForVisibilityOfElement(accounts);
		Actions action = new Actions(driver);
		action.moveToElement(accounts, 0, 10).perform();
		yourAccount.click();
		waitTillElementClickable(yourAddress);
		yourAddress.click();
		waitTillElementClickable(addAddress);
		addAddress.click();
	}

	/*
	 * Methods validates the regex of given inputs: validateNumber and
	 * validatePinCode
	 */

	public boolean validateNumber(String number) {

		String regex = "^\\d{10}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(number);
		return matcher.matches();

	}

	public boolean validatePinCode(String zipcode) {
		String regex = "^\\d{6}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(zipcode);
		return matcher.matches();
	}

	/*
	 * Method to validate the Mandatory error messages in add address page
	 */

	public void validateMandatoryFields() {
		waitTillElementClickable(btnAddAddress);
		btnAddAddress.click();
		if (emptyNameErrorMsg.isDisplayed() && emptyPhoneNumberErrorMsg.isDisplayed()
				&& emptyPinCodeErrorMsg.isDisplayed() && emptyAddressErrorMsg.isDisplayed()
				&& emptyCityErrorMsg.isDisplayed()) {
			Assert.assertTrue(true);
		} else {
			Assert.assertFalse(false, "Validation error message is not displaying");
		}

	}

	/*
	 * Method to validate adding address
	 */

	public void addAddress(String Country, String Name, String PhoneNumber, String Zipcode, String Address1,
			String Address2, String Landmark, String MakeDefault) throws InterruptedException {
		waitForVisibilityOfElement(country);
		Select select = new Select(country);
		select.selectByVisibleText(Country);
		name.sendKeys(Name);
		phoneNumber.sendKeys(PhoneNumber);
		pinCode.sendKeys(Zipcode);
		addressLine1.sendKeys(Address1);
		addressLine2.sendKeys(Address2);
		landMark.sendKeys(Landmark);
		if (MakeDefault == "Yes") {
			waitForVisibilityOfElement(defaultCheckBox);
			defaultCheckBox.click();
		}
		btnAddAddress.click();
		if (validatePinCode(Zipcode) == false) {
			Assert.assertTrue(town.getText().isBlank() && state.getText().contains("Choose"),
					"City and State dropdowns are not blank");
			Assert.assertFalse(invalidPinCode.isDisplayed(), invalidPinCode.getText());
		}
		if (validateNumber(PhoneNumber) == false) {
			Assert.assertFalse(invalidPhoneNumber.isDisplayed(), invalidPhoneNumber.getText());
			Assert.assertFalse(alertReviewAddress.isDisplayed(), alertReviewAddress.getText());
		}

		try {
			if (alertAddressSaved.isDisplayed()) {
				waitForVisibilityOfElement(alertAddressSaved);
				Assert.assertTrue(alertAddressSaved.isDisplayed());
				waitForVisibilityOfElement(addedAddreess);
				Assert.assertTrue(addedAddreess.isDisplayed());
				if (MakeDefault == "Yes") {
					Assert.assertTrue(defaultAddress.isDisplayed());
				}
			}
		} catch (Exception e) {
			logger.error(alertAddressNotSaved.getText() + "--Address didn't get added");
			Assert.assertFalse(alertAddressNotSaved.isDisplayed());
		}

	}

}
