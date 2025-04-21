package api_learning;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import driver.DriverFactory;
import support.ui.SelectEx;
import url.Urls;

public class Dropdown implements Urls {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(dropdownSlug));

            // Dropdown locator and elements
            By dropdownSel = By.id("dropdown");
            WebElement dropdownElem = driver.findElement(dropdownSel);

            // Select dropdown
            SelectEx select = new SelectEx(dropdownElem);

            // Select by visible text | Option 1
            select.selectOption1();
            // DEBUG PURPOSE ONLY
            Thread.sleep(1000);

            // Select by index | Option 2
            select.selectByIndex(2);
            Thread.sleep(1000);

            // Select by value | Option 1
            select.selectByValue("1");
            Thread.sleep(1000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
