package abstractcomponent;

import java.time.Duration;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractComponent {

	/**
	 * Constructor to initialize the web elements.
	 */
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
	}

	protected static final Logger logger = LoggerFactory.getLogger(AbstractComponent.class);

	/*
	 * Method to wait implicitly till defined time
	 */

	public void waitImplicitely() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	/*
	 * Method to wait till given element become visible
	 */

	public void waitForVisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(120));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/*
	 * Method to wait till given element become present in the UI
	 */

	public void waitTillPresenceOfElement(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.presenceOfElementLocated(findBy));
	}

	/*
	 * Method to wait till given element become clickable
	 */

	public void waitTillElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

}
