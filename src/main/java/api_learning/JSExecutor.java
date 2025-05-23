package api_learning;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import url.Urls;

public class JSExecutor implements Urls {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(floatingMenuSlug));

            // Scroll to bottom
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // Debug purpose only
            Thread.sleep(2000);

            // Scroll to top
            javascriptExecutor.executeScript("window.scrollTo(document.body.scrollHeight, 0);");

            // Debug purpose only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
