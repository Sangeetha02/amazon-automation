package testclasses;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import pageobject.SelectProduct;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import testcomponents.BaseTest;

public class SelectProductTest extends BaseTest {

	/**
	 * This class represents products section of the application. It contains
	 * methods to interact with the products section elements.
	 */

	private SelectProduct selectProduct;

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		selectProduct = new SelectProduct(driver);

	}

	@DataProvider()
	public Object[][] StringitemList() {
		return new Object[][] {
				{ "Televisions", "Smart Televisions",
						"TCL 101 cms (40 inches) V5C Series Full HD Smart QLED TV Google TV 40V5C" },
				{ "Headphones", "In-Ear",
						"Oneplus Bullets Z2 Bluetooth Wireless in Ear Earphones with Mic, Bombastic Bass - 12.4 mm Drivers, 10 Mins Charge - 20 Hrs Music, 30 Hrs Battery Life, IP55 Dust and Water Resistant (Magico Black)" },
				{ "Speakers", "Smart Speakers",
						"Amazon Echo Dot (5th Gen) | Smart speaker with Big sound, Motion Detection, Temperature Sensor, Alexa and Bluetooth| Blue" },

		};
	}

	/*
	 * Method to navigate to electronic section and add product from 3 different
	 * category
	 */

	@Test(dataProvider = "StringitemList",priority=1)
	public void addToCart(String section, String subSection, String item) throws InterruptedException {
		selectProduct.selectMenu();
		selectProduct.addProduct(section, subSection, item);

	}

	/*
	 * Method to navigate to cart
	 */

	@Test(priority=2)
	public void goToCart() {
		selectProduct.goToCart();
	}

	@DataProvider
	public Object[][] StringItems() {
		return new Object[][] { { "TCL 101 cms (40 inches) V5C Series Full HD Smart QLED TV Google TV 40V5C" }, {
				"Oneplus Bullets Z2 Bluetooth Wireless in Ear Earphones with Mic, Bombastic Bass - 12.4 mm Drivers, 10 Mins Charge - 20 Hrs Music, 30 Hrs Battery Life, IP55 Dust and Water Resistant (Magico Black)" },
				{ "Amazon Echo Dot (5th Gen) | Smart speaker with Bigger sound, Motion Detection, Temperature Sensor, Alexa and Bluetooth| Blue" }, };
	}

	/*
	 * Method to verify added items in the cart
	 */

	@Test(dataProvider = "StringItems",priority=3)
	public void itemsAdded(String item) {
		selectProduct.verifyCart(item);
	}

}
