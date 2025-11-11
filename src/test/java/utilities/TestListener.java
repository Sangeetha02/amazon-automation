package utilities;

import testclasses.LoginTest;
import testcomponents.WebDriverManager;
import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class TestListener implements ITestListener {
	private static final Logger logger = LoggerFactory.getLogger(LoginTest.class);

	/*
	 * Listener class to do operations on test status
	 */

	ExtentReports extent = ExtentReport.getReportObject();
	ExtentTest test;

	/*
	 * Method to perform defined actions on test failure
	 */

	@Override
	public void onTestFailure(ITestResult result) {

		test.log(Status.FAIL, "TEST FAILED");
		test.fail(result.getThrowable());
		Object testlistener = result.getInstance();
		WebDriver driver = null;
		logger.error("Test Failed: " + result.getName(), result.getThrowable());
		try {
			driver = WebDriverManager.getDriver();
		} catch (IOException e) {

		}
		String screenshotPath = ScreenShotUtil.takeScreenShotOnFailure(driver, result.getName());
		test.addScreenCaptureFromPath(screenshotPath, result.getMethod().getMethodName());
	}

	/*
	 * Method to perform defined actions on test success
	 */

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("Test Passed: " + result.getName());
		test.log(Status.PASS, "TEST PASSED");
	}

	/*
	 * Method to perform defined actions on test skipped
	 */

	@Override
	public void onTestSkipped(ITestResult result) {
		logger.warn("Test Skipped: " + result.getName());
		test.log(Status.SKIP, "TEST SKIPPED");
	}

	/*
	 * Method to perform defined actions on test start
	 */

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("Test Started: " + result.getName());
		test = extent.createTest(result.getMethod().getMethodName());
	}

	/*
	 * Method to perform defined actions on test finish
	 */

	@Override
	public void onFinish(ITestContext context) {
		logger.info("Test suite finished: " + context.getName());
		extent.flush();
	}

}
