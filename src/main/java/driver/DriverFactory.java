package driver;

import java.net.URL;
import java.time.Duration;

import org.apache.commons.exec.OS;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    private WebDriver driver;

    public static WebDriver getChromDriver() {
        String currentProjectLocation = System.getProperty("user.dir");
        String chromeDriverLocation = "";
        if (OS.isFamilyMac()) {
            chromeDriverLocation = currentProjectLocation + "/src/main/resources/drivers/chromedriver";
        }

        if (OS.isFamilyWindows()) {
            chromeDriverLocation = currentProjectLocation + "\\src\\main\\resources\\drivers\\chromedriver.exe";
        }

        if (chromeDriverLocation.isEmpty()) {
            throw new IllegalArgumentException("Can't detect OS type!");
        }

        System.setProperty("webdriver.chrome.driver", chromeDriverLocation);

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--incognito");

        WebDriver driver = new ChromeDriver(chromeOptions);

        // Interval time | 500 mili seconds = 0.5 seconds
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        return driver;

    }

    public WebDriver getDriver(String browserName) {
        if (driver == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setPlatform(Platform.ANY);
            BrowserType browserType;
            try {
                browserType = BrowserType.valueOf(browserName);
            } catch (Exception e) {
                throw new IllegalArgumentException(browserName + "is not supported");
            }

            switch (browserType) {
                case chrome:
                    desiredCapabilities.setBrowserName(BrowserType.chrome.getName());
                    break;
                case firefox:
                    desiredCapabilities.setBrowserName(BrowserType.firefox.getName());
                    break;
                case edge:
                    desiredCapabilities.setBrowserName(BrowserType.edge.getName());
                    break;
                case safari:
                    desiredCapabilities.setBrowserName(BrowserType.safari.getName());
                    break;
            }
            try {
                String hub = "http://localhost:4444/wd/hub";
                // String hub = "http://192.168.1.110:4444/wd/hub";
                driver = new RemoteWebDriver(new URL(hub), desiredCapabilities);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public void closeBrowserSession() {
        if (driver != null) {
            driver.quit();
        }
    }
}
