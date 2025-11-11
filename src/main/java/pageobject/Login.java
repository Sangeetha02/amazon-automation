package pageobject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import abstractcomponent.AbstractComponent;

public class Login extends AbstractComponent {

	/**
	 * Constructor to initialize the web elements.
	 */

	WebDriver driver;

	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div[@id='nav-link-accountList']")
	WebElement accounts;

	@FindBy(xpath = "//div[@id='nav-al-signin']/div/a")
	WebElement signIn;

	@FindBy(name = "email")
	WebElement txtEmail;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement btnContinue;

	@FindBy(name = "password")
	WebElement passWord;

	@FindBy(xpath = "//input[@id='signInSubmit']")
	WebElement btnSubmit;

	@FindBy(xpath = "//span[contains(text(),'Hello, sign in')]")
	public WebElement txtSignIn;

	@FindBy(xpath = "//div[@id='invalid-phone-alert']/div/div[contains(text(),'Invalid mobile number')]")
	public WebElement invalidMobileNumber;

	@FindBy(xpath = "//div[@id='invalid-email-alert']/div/div[contains(text(),'Invalid email address')]")
	public WebElement invalidEmail;

	@FindBy(xpath = "//div/a[contains(text(),'Forgot password?')]")
	public WebElement forgotPassword;

	@FindBy(xpath = "//div/a[contains(text(),'Change')]")
	public WebElement change;

	@FindBy(xpath = "//div[@id='auth-error-message-box']")
	WebElement incorrectPassword;

	@FindBy(xpath = "//div/span[contains(text(),'Hello')]")
	public WebElement message;

	/*
	 * Method to launch URL
	 */

	public void goTo(String URL) {
		driver.get(URL);

	}
	/*
	 * Methods validates regex of the provided username: validateNumber and
	 * validateEmailId
	 */

	public boolean validateNumber(String username) {

		String regex = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[6789]\\d{9}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}

	public boolean validateEmailId(String username) {
		String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(username);
		return matcher.matches();
	}

	/*
	 * Method to validate input username
	 */
	public void userNameValidation(String username) throws Exception {
		waitForVisibilityOfElement(accounts);
		Actions action = new Actions(driver);
		action.moveToElement(accounts, 0, 10).perform();
		waitTillElementClickable(signIn);
		signIn.click();
		waitForVisibilityOfElement(txtEmail);
		txtEmail.isEnabled();
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].value='" + username + "';", txtEmail);
		btnContinue.click();

		if (validateNumber(username) == false & validateEmailId(username) == false) {
			Thread.sleep(1000);
			boolean phoneNumberError = invalidMobileNumber.isDisplayed();
			Assert.assertFalse(phoneNumberError);

			boolean emailError = invalidEmail.isDisplayed();
			Assert.assertFalse(emailError);
		}

	}
	/*
	 * Method to validate input password
	 */

	public void passwordValidation(String password) throws Exception {
		waitForVisibilityOfElement(passWord);
		passWord.isEnabled();
		passWord.sendKeys(password);
		btnSubmit.click();
		Thread.sleep(2000);
		try {
			Assert.assertFalse(incorrectPassword.isDisplayed(), "Incorrect Password");
		} catch (Exception e) {
			logger.info("Login Successful! Resuming NoSuchElementException for incorrectPassword element.");
		}
	}

	/*
	 * Method to sign in to the application
	 */
	public void signUp(String username, String password) throws Exception {
		userNameValidation(username);
		passwordValidation(password);

	}
}
