package testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import pageobject.BestSeller;
import pageobject.SelectProduct;
import java.io.IOException;
import org.testng.annotations.Parameters;
import testcomponents.BaseTest;

public class BestSellerTest extends BaseTest {
	/**
	 * This class represents best seller section of the application. It contains
	 * methods to interact with the best seller page elements.
	 */

	private BestSeller bestseller;
	SelectProduct selectproduct;

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		bestseller = new BestSeller(driver);
		selectproduct = new SelectProduct(driver);

	}

	/**
	 * Method to add 8th rank item in the cart
	 */

	@Test(priority=1)
	public void addEightRankItem() {
		bestseller.addItemByRank();
	}

	/**
	 * Method to navigate to cart
	 */

	@Test(priority=2)
	public void goToCart() {
		selectproduct.goToCart();
	}

	/**
	 * Method to validate added item in the cart
	 */

	@Test(priority=3)
	@Parameters({ "item" })
	public void validateAddedItem(String item) {
		selectproduct.verifyCart(item);

	}

}