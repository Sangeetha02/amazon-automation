package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import abstractcomponent.AbstractComponent;

public class SelectProduct extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor to initialize the web elements.
	 */

	public SelectProduct(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@id='nav-hamburger-menu']")
	WebElement menu;

	@FindBy(xpath = "//div[contains(text(),'Shop by Category')]")
	WebElement scrollTo;

	@FindBy(xpath = "//a/div[contains(text(),'TV, Appliances, Electronics')]/following-sibling::i")
	WebElement menuSelect;

	@FindBy(xpath = "//div[contains(text(),'TV, Audio & Cameras')]")
	public WebElement title;

	@FindBy(xpath = "//div[@id='hmenu-content']//a[@href='/gp/browse.html?node=1389396031&ref_=nav_em_sbc_tvelec_television_0_2_9_2' and contains(text(),'Televisions')]")
	public WebElement categoryTV;

	String category = "//div[@id='hmenu-content']//div[9]/section//a[contains(text(),'";

	String subCategory = ("//span[contains(text(),'");

	String productBase = ("//span[contains(text(),'");

	String productEnd = ("')]/ancestor::div[1]/following-sibling::div//span/button");

	@FindBy(xpath = "//div[@id='nav-cart-count-container']")
	WebElement cart;

	@FindBy(xpath = "//div/h2[contains(text(),'Shopping Cart')]")
	WebElement cartHeader;

	String addedItemBase = ("//div[@class='sc-item-content-group']/ul/li/span/a/span//span/span[contains(text(),'");

	String addedItemEnd = ("')][1]");

	@FindBy(xpath = "//form[@id='activeCartViewForm']")
	WebElement cartItems;

	/*
	 * Method to navigate to electronic section
	 */

	public void selectMenu() throws InterruptedException {
		waitTillElementClickable(menu);
		menu.isEnabled();
		menu.click();
		waitTillElementClickable(menuSelect);
		menuSelect.isEnabled();
		menuSelect.click();
		waitForVisibilityOfElement(title);
	}

	/*
	 * Method to add product from 3 different category
	 */

	public void addProduct(String section, String subSection, String item) throws InterruptedException {
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		String productCategoryTemp = category + section + "')]";

		WebElement productCategory = driver.findElement(By.xpath(productCategoryTemp));

		js.executeScript("arguments[0].click();", productCategory);

		String productSubCategoryTemp = subCategory + subSection + "')]";

		WebElement productSubCategory = driver.findElement(By.xpath(productSubCategoryTemp));

		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", productSubCategory);

		String productTemp = productBase + item + productEnd;

		WebElement product = driver.findElement(By.xpath(productTemp));

		Thread.sleep(1000);
		waitTillElementClickable(product);
		Thread.sleep(3000);
		
		product.click();
		waitForVisibilityOfElement(menu);
		js.executeScript("window.scrollTo(0, 0);");

	}

	/*
	 * Method to navigate to cart
	 */

	public void goToCart() {
		waitTillElementClickable(cart);
		cart.click();
		waitForVisibilityOfElement(cartHeader);
	}

	/*
	 * Method to verify added items in the cart
	 */

	public void verifyCart(String item) {

		String itemInCartTemp = addedItemBase + item + addedItemEnd;
		String itemInCart = driver.findElement(By.xpath(itemInCartTemp)).getText();
		String addedItems = cartItems.getText();
		Assert.assertTrue(addedItems.contains(itemInCart), itemInCart + "Item did not get added");

	}

}
