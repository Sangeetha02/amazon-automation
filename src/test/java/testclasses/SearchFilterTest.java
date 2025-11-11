package testclasses;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageobject.SearchFilter;
import testcomponents.BaseTest;

public class SearchFilterTest extends BaseTest {

	/**
	 * This class represents search and filter section of the application. It
	 * contains methods to interact with the search page elements.
	 */

	private SearchFilter searchFilter;

	@BeforeClass
	@Override
	public void initializeDriver() throws IOException {
		super.initializeDriver();
		searchFilter = new SearchFilter(driver);

	}

	/*
	 * Method to search a product and filter by brand and validate the filtered
	 * result
	 */

	@Test(priority=1)
	@Parameters({ "item", "filteredItem" })
	public void searchProduct(String item, String filteredItem) throws InterruptedException {
		searchFilter.filterProduct(item, filteredItem);

	}

}