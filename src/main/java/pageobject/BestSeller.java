package pageobject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import abstractcomponent.AbstractComponent;

public class BestSeller extends AbstractComponent {

	/**
	 * Constructor to initialize the web elements.
	 */

	WebDriver driver;

	public BestSeller(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//div/a[contains(text(),'Bestsellers')]")
	WebElement bestSeller;

	@FindBy(xpath = "//div[@class='a-row a-carousel-header-row a-size-large']/div/h2[contains(text(),'Bags')]")
	WebElement bagsWallets;

	@FindBy(xpath = "//div[@class='a-row a-carousel-header-row a-size-large']/div/h2[contains(text(),'Bags')]/parent::div/div")
	WebElement seeMore;

	@FindBy(xpath = "//div[@class='a-section zg-bdg-ctr']/div/span[contains(text(),'#8')]")
	WebElement scrollTo;

	@FindBy(xpath = "//div[@id='gridItemRoot'][8]//div//following-sibling::div//a/span/div")
	WebElement rankEight;

	@FindBy(xpath = "//div[@id='availability']/span[2]/span[contains(text(),'Currently unavailable')]")
	WebElement itemUnavailable;

	@FindBy(xpath = "//input[@id=\"add-to-cart-button\"]")
	WebElement addToCart;

	@FindBy(xpath = "//div[@id='nav-cart-count-container']")
	WebElement cart;

	@FindBy(xpath = "//div/h2[contains(text(),'Shopping Cart')]")
	WebElement cartHeader;

	@FindBy(xpath = "//div[@data-name='Active Items']")
	WebElement itemAdded;

	String eigthItem;
	String addedItem;

	/**
	 * Method to add 8th rank item in the cart
	 */

	public void addItemByRank() {
		waitForVisibilityOfElement(bestSeller);
		bestSeller.click();
		waitTillElementClickable(bagsWallets);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", bagsWallets);
		waitTillElementClickable(seeMore);
		seeMore.click();
		waitForVisibilityOfElement(rankEight);
		js.executeScript("arguments[0].scrollIntoView(true);", scrollTo);
		eigthItem = rankEight.getText();
		waitTillElementClickable(rankEight);
		rankEight.click();
		try {
			Assert.assertFalse(itemUnavailable.isDisplayed(), "Item is out of stock");
		} catch (Exception e) {

		}
		waitTillElementClickable(addToCart);
		addToCart.click();

	}

	/*
	 * Method to verify the added item in the cart
	 */

	public void verifyCartItem() {
		cart.click();
		waitForVisibilityOfElement(cartHeader);
		addedItem = itemAdded.getText();
		String itemInCart = eigthItem.replace("...", "");
		Assert.assertTrue(addedItem.contains(itemInCart), "Added item is wrong");
	}

}