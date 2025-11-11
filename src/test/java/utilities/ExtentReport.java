package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	/*
	 * Method to design and generate extent report
	 */

	public static ExtentReports getReportObject() {
		String path = System.getProperty("user.dir") + "/Reports/Report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Amazon Test Report");
		reporter.config().setDocumentTitle("Test Result");

		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.createTest(path);
		return extent;
	}

}
