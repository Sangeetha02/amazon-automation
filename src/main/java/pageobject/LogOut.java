package pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import abstractcomponent.AbstractComponent;

public class LogOut extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor to initialize the web elements.
	 */
	public LogOut(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//*[@id='nav-link-accountList']")
	WebElement accounts;

	@FindBy(xpath = "//span[text()='Sign Out']")
	WebElement singOut;

	/*
	 * Method to validate sign out
	 */

	public void signOut() {
		waitForVisibilityOfElement(accounts);
		Actions action = new Actions(driver);
		action.moveToElement(accounts, 0, 10).perform();
		singOut.click();
	}
}
