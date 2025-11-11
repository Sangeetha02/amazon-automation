package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotUtil {

	/*
	 * Method to take screenshot and store it in defined location
	 */
	public static String takeScreenShotOnFailure(WebDriver driver, String ScreenshotName) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String filePath = System.getProperty("user.dir") + "/screenshots/" + ScreenshotName + "_" + timeStamp + ".png";
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File(filePath);
			FileUtils.copyFile(srcFile, destFile);
		}

		catch (IOException e) {

			System.out.print(e.getMessage());
		}
		return filePath;

	}

}
