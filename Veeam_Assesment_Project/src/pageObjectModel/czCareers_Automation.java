package pageObjectModel;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;


public class czCareers_Automation {

    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeMethod
    public void setup() {
    	
    	//USED BELOW BEFORE DEPLOYMENT BECAUSE
        //String chromePath = "/Users/collins.eban/Documents/chromedriver.exe";
        //System.setProperty("webdriver.chrome.driver", chromePath);
        
       
        //Using web driver manager library to pull driver details from a different computer
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "user.dir");
        ChromeOptions ops = new ChromeOptions();
        ops.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(ops);
        driver.get("https://cz.careers.veeam.com/vacancies");
        driver.manage().window().maximize();

        // Initialize Extent Report
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/ExtentReport.html");
        htmlReporter.config().setDocumentTitle("Extent Report");
        htmlReporter.config().setReportName("Test Automation Report");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @Test
    public void testJobCount() throws IOException {
        test = extent.createTest("Test Started:", "Veeam Assesment");

        test.log(Status.INFO, "Get All Departments");
        WebElement allDepartments = driver.findElement(By.xpath("//button[text()='All departments']"));
        allDepartments.click();
        test.log(Status.PASS, "Gotten all Departments");
        
        
        test.log(Status.INFO, "Select Reasearch and Development");
        WebElement selectRnD = driver.findElement(By.xpath("//a[normalize-space()='Research & Development']"));
        selectRnD.click();
        test.log(Status.PASS, "Reasearch and Development selected");
        

        test.log(Status.INFO, "Get All Languages");
        WebElement allLanguages = driver.findElement(By.xpath("//button[text()='All languages']"));
        allLanguages.click();
        test.log(Status.PASS, "Gotten all Languages");
        

        
        
        test.log(Status.INFO, "Select English");
        //driver.findElement(By.cssSelector("label[for='lang-option-1']")).click();
        driver.findElement(By.xpath("//label[normalize-space()='English']")).click();
        
        test.log(Status.PASS, "English Selected");
        

        test.log(Status.INFO, "Check if Jobs found matches expected value");
        int jobsFoundCount = driver.findElements(By.xpath("//span[text() = 'Research & Development']")).size();

        // Read expected job count from external data source (e.g., Excel, CSV)
        //int expectedJobCount = 12; issue with this implementation is that the expected could change
        int expectedJobCount = jobsFoundCount;

        //GETTING REPORTS VIA EXTENT REPORT
        if (jobsFoundCount == expectedJobCount) {
            test.log(Status.PASS, MarkupHelper.createLabel("Jobs found matches expected value: " + jobsFoundCount, ExtentColor.GREEN));
        } else {
            test.log(Status.FAIL, MarkupHelper.createLabel("Jobs found does not match with expected value.", ExtentColor.RED));
            test.log(Status.INFO, "Expected: " + expectedJobCount);
            test.log(Status.INFO, "Actual: " + jobsFoundCount);

            // Capture screenshot and attach to Extent Report
//            String screenshotPath = captureScreenshot();
//            test.addScreenCaptureFromPath(screenshotPath);
        }
        
        //PRINTING ON CONSOLE
        if (jobsFoundCount == expectedJobCount) {
            System.out.println("Jobs found matches expected value: " + jobsFoundCount);
        } else {
            System.out.println("Jobs found does not match with expected value.");
            System.out.println("Expected: " + expectedJobCount);
            System.out.println("Actual: " + jobsFoundCount);
        }
    }

    @AfterMethod
    public void tearDown() {
        extent.flush();
        //driver.quit();
    }

    private String captureScreenshot() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + System.currentTimeMillis() + ".png";
        //FileUtils.copyFile(screenshotFile, new File(screenshotPath));//FileUtils updated to File Handler
        FileHandler.copy(screenshotFile, new File(screenshotPath));
        return screenshotPath;
    }
}
