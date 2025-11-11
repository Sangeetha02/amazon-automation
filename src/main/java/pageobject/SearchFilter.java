package pageobject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import abstractcomponent.AbstractComponent;

public class SearchFilter extends AbstractComponent {

	WebDriver driver;

	/**
	 * Constructor to initialize the web elements.
	 */

	public SearchFilter(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[@aria-label='Amazon.in']")
	WebElement amazonLogo;

	@FindBy(xpath = "//input[@id='twotabsearchtextbox']")
	WebElement searchInputBox;

	@FindBy(xpath = "//input[@id='nav-search-submit-button']")
	WebElement searchIcon;

	@FindBy(xpath = "//div[@id='brandsRefinements']//ul/span/li/span/div/a/i")
	WebElement seeMore;

	String filterCheckBoxBase = "//div[@id='brandsRefinements']/ul//a/span[contains(text(),'";

	@FindBy(xpath = "//div/h2[text()='Results']")
	WebElement headerResult;

	@FindBy(xpath = "//div/span[text()='Brands']")
	WebElement Brands;

	String filteredResult = "//span[contains(text(),'";

	/*
	 * Method to search a product and filter by brand and validate the filtered
	 * result
	 */
	public void filterProduct(String item, String filteredItem) throws InterruptedException {
		amazonLogo.click();
		waitForVisibilityOfElement(searchInputBox);
		searchInputBox.sendKeys(item);
		searchIcon.click();
		waitTillElementClickable(seeMore);
		seeMore.click();
		String productToSelect = (filterCheckBoxBase + filteredItem + "')]");
		waitTillElementClickable(driver.findElement(By.xpath(productToSelect)));
		driver.findElement((By.xpath(productToSelect))).click();
		waitTillElementClickable(headerResult);
		String listOfItems = (filteredResult + filteredItem + "')]");
		List<WebElement> elements = driver.findElements(By.xpath(listOfItems));
		for (WebElement brand : elements) {
			if (brand.getText().contains(filteredItem)) {

				Assert.assertTrue(true, "All the products are filtered with " + filteredItem);
			} else {
				Assert.assertFalse(false);
			}

		}

	}
}
