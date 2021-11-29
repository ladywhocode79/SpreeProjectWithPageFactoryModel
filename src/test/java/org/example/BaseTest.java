package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import static pagefactory.Util.takeSnapShot;

public class BaseTest {
    //declare webdriver object
    static WebDriver driver;
    //for reports
    //builds a new report using the html template
    ExtentHtmlReporter htmlReporter;

    ExtentReports extentReports;
    //helps to generate the logs in test report.
    ExtentTest extentTest;

    @BeforeClass
    public void startReport() {
        // initialize the HtmlReporter
        htmlReporter = new ExtentHtmlReporter("./TestReports/extentReport.html");

        //initialize ExtentReports and attach the HtmlReporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);


        //configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Extent Report Demo");
        htmlReporter.config().setReportName("Test Report for Login to Checkout Scenario");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");

        extentTest =extentReports.createTest("Order Completion");
        extentTest.log(Status.INFO, "Execution started.");
    }

    @BeforeMethod
    public void setupDriverAndOpenBrowser(){
        //set system property
        System.setProperty("webdriver.chrome.driver","./Driver/chromedriver");
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
            //to write or update test information to reporter
            extentReports.flush();
        }
        else if(result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" " +
                    "PASSED ", ExtentColor.GREEN));
            filePath =takeSnapShot(driver,"Order_placed_page_passed");
            System.out.println(filePath);
            //add screenshot to the test report
            extentTest.addScreenCaptureFromPath(filePath);
            //to write or update test information to reporter
            extentReports.flush();
        }
        else {
            extentTest.log(Status.SKIP, MarkupHelper.createLabel(
                    result.getName()+" SKIPPED ", ExtentColor.ORANGE));
            extentTest.skip(result.getThrowable());
        }
        driver.close();
    }

}
