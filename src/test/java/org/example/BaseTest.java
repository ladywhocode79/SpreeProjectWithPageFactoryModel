package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;

import static pagefactory.Util.takeSnapShot;

public class BaseTest {
    //declare webdriver object
    static WebDriver driver;
    //for reports
    //builds a new report using the html template
    //ExtentHtmlReporter htmlReporter;

    ExtentReports extentReports;
    //helps to generate the logs in test report.
    ExtentTest extentTest;
    final File CONF = new File("./TestReports/spark-config.xml");

    @BeforeClass
    public void startReport() throws IOException {

        //initialize ExtentReports
        extentReports = new ExtentReports();

        ExtentSparkReporter spark = new ExtentSparkReporter("./TestReports/Spark.html");
        extentReports.attachReporter(spark);
        spark.loadXMLConfig(CONF);

        extentTest =extentReports.createTest("Order Completion");
        extentTest.log(Status.INFO, "Execution started.");
    }

    @BeforeMethod
    public void setupDriverAndOpenBrowser() {
        //set system property
        System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver");
        //assign chrome driver to driver object
        driver = new ChromeDriver();

        //home page link
        driver.get("https://spree-vapasi-prod.herokuapp.com/");
        driver.manage().window().fullscreen();

    }

    @AfterMethod
    public void takeTestcaseScreenshots(ITestResult result) throws Exception {
        String filePath;
        if(result.getStatus() == ITestResult.FAILURE) {

            filePath =takeSnapShot(driver,"Order_placed_page_failed");
            System.out.println(filePath);
            extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+" FAILED ", ExtentColor.RED));
            extentTest.fail(result.getThrowable());
            //add screenshot to the test report
            extentTest.addScreenCaptureFromPath(filePath);
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" " +
                    "PASSED ", ExtentColor.GREEN));
            filePath =takeSnapShot(driver,"Order_placed_page_passed");
            System.out.println(filePath);
            //add screenshot to the test report
            extentTest.addScreenCaptureFromPath(filePath);
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(
                    result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
        extentReports.flush();
        driver.close();
    }

}
