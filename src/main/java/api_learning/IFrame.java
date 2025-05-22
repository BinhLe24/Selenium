package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.DriverFactory;
import url.Urls;

public class IFrame implements Urls {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(iframeSlug));

            // Locate the iframe
            By iframeSel = By.cssSelector("[id$='ifr']");
            WebElement iframeElem = driver.findElement(iframeSel);

            // Switch to the Iframe
            driver.switchTo().frame(iframeElem);

            // Locate the element inside the iframe
            WebElement editInputElem = driver.findElement(By.id("tinymce"));
            editInputElem.click();
            editInputElem.clear();
            editInputElem.sendKeys("This is new text value");
            Thread.sleep(1000);

            // Switch to parent frame
            driver.switchTo().defaultContent();
            driver.findElement(By.linkText("Elemental Selenium")).click();
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
