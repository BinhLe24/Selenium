package test;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import driver.DriverFactory;
import io.qameta.allure.Allure;

public class BaseTest {

    private final static List<DriverFactory> webdriverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String browser;

    protected WebDriver getDriver() {
        return driverThread.get().getDriver(browser);
    }

    @BeforeTest(description = "Init browser session")
    @Parameters({ "browser" })
    public void initBrowserSession(String browser) {
        this.browser = browser;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory webdriverThread = new DriverFactory();
            webdriverThreadPool.add(webdriverThread);
            return webdriverThread;
        });
    }

    @AfterTest(alwaysRun = true)
    public void quitBrowserSession() {
        driverThread.get().closeBrowserSession();
    }

    @AfterMethod
    public void captureScreenShot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            // 1. Get method name
            String methodName = result.getName();

            // 2. Get taken time
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int hr = calendar.get(Calendar.HOUR);
            int min = calendar.get(Calendar.MINUTE);
            int sec = calendar.get(Calendar.SECOND);
            String fileName = methodName + "-" + y + "-" + m + "-" + d + "-" + hr + "-" + min + "-" + sec + ".png";

            // 3. Take screenshot
            WebDriver driver = driverThread.get().getDriver(browser);
            File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            try {
                // 4. Save
                String fileLocation = System.getProperty("user.dir") + "/screenShots/" + fileName;
                FileUtils.copyFile(screenShot, new File(fileLocation));

                // 5. Attach to report
                Path content = Paths.get(fileLocation);
                try (InputStream inputStream = Files.newInputStream(content)) {
                    Allure.addAttachment(methodName, inputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
