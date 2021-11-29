package pagefactory;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import java.io.File;

public class Util {

    public static String takeSnapShot(WebDriver driver,String pageName)
            throws Exception {
        //Convert web driver object to TakeScreenshot
        TakesScreenshot screenShot = ((TakesScreenshot) driver);
        //Call getScreenshotAs method to create image file
        File sourceFile = screenShot.getScreenshotAs(OutputType.FILE);

        String screenShotPath = System.getProperty("user.dir")+"/screenshots" +
                "/"+pageName+System.currentTimeMillis()+".jpg";
        File destFile = new File(screenShotPath);
        FileUtils.copyFile(sourceFile, destFile);

        return screenShotPath;
    }
/*
    public static void takeFullScreenShot(WebDriver driver,String pageName)
            throws Exception {

        Screenshot screenshot = new AShot().shootingStrategy
                (ShootingStrategies.viewportPasting(5000)).
                takeScreenshot(driver);

        //to take full screenshot
        Screenshot screenshot1=new AShot().shootingStrategy(
                ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(),"PNG",new File("./screenshots/"));

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileWithPath = "./screenshots/";
        String filename=pageName+timestamp+".jpg";

        ImageIO.write(screenshot.getImage(), "jpg", new File(fileWithPath+filename));
    }*/

}
