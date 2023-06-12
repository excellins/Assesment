package classes;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	@SuppressWarnings("deprecation")
	ExtentHtmlReporter htmlReporter;
	ExtentReports extent;
	
	@BeforeSuite
	public void setUp()
	{
		htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@Test
	public void test1() throws Exception
	{
		ExtentTest test = extent.createTest("CareearTest", "Any Sample description");
		test.log(Status.INFO, "usage log step");
		test.info("shows details");
		//test.fail("details", MediaEntityBuilder.createScreenCaptureFromPath(""));
		test.addScreenCaptureFromPath("screenshot.png");
		
	}
		
	
	@AfterSuite
	public void tearDown()
	{
		extent.flush();
	}
//	public static ExtentReports getReportObject()
//	{
//		
//		String path =System.getProperty("user.dir")+"\\reports\\index.html";
//		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
//		reporter.config().setReportName("Dot-Group Web Automation Results");
//		reporter.config().setDocumentTitle("Test Results");		
//		extent =new ExtentReports();
//		extent.attachReporter(reporter);
//		extent.setSystemInfo("Tester", "Collins");
//		return extent;
//		
//	}
}
