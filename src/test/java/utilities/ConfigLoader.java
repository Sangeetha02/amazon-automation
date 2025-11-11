package utilities;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class ConfigLoader {
	private Properties properties;

	/*
	 * Method to get input from property file
	 */
	public String getProperty(String key) throws IOException {
		properties = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src/main//java//resourses//GlobalData.properties");
		properties.load(fis);
		return properties.getProperty(key);
	}
}
