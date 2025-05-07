package test_flows;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import driver.DriverFactory;
import io.qameta.allure.Allure;

public class BaseTest {

    protected static WebDriver driver = null;

    @BeforeTest
    public void initBrowserSession() {
        driver = DriverFactory.getChromDriver();
    }

    @AfterTest(alwaysRun = true)
    public void quitBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterMethod
    public void captureScreenShot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get method name
            String methodName = result.getName();

            // 2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH);
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.YEAR);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String fileName = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec + ".png";
            ;

            // 3. Take screenshot
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                // 4. Save
                String fileLocation = System.getProperty("user.dir") + "/screenShots/" + fileName;
                FileUtils.copyFile(screenShot, new File(fileLocation));

                // 5. Attach to report
                Path content = Paths.get(fileLocation);
                try (InputStream inputStream = Files.newInputStream(content)) {
                    Allure.addAttachment(fileName, inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
