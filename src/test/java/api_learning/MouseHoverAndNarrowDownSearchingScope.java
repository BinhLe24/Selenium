package api_learning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import driver.DriverFactory;
import url.Urls;

public class MouseHoverAndNarrowDownSearchingScope implements Urls {

    private final static By figureSel = By.className("figure");
    private final static By profileNameSel = By.cssSelector(".figcaption h5");
    private final static By profileLinkSel = By.cssSelector(".figcaption a");

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to target base
            driver.get(baseUrl.concat(hoverSlug));

            // Target parent elements
            List<WebElement> figuresElem = driver.findElements(figureSel);
            if (figuresElem.isEmpty())
                throw new RuntimeException("There is no profile image displayed");

            // Define action object
            Actions actions = new Actions(driver);

            for (WebElement figureElem : figuresElem) {
                WebElement profileNameElem = figureElem.findElement(profileNameSel);
                WebElement profileLinkElem = figureElem.findElement(profileLinkSel);

                // Before Mouse hover
                System.out.println(profileNameElem.getText() + ": " + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + ": " + profileLinkElem.isDisplayed());

                // Mouse hover
                actions.moveToElement(figureElem).perform();

                // After Mouse hover
                System.out.println(profileNameElem.getText() + ": " + profileNameElem.isDisplayed());
                System.out.println(profileLinkElem.getText() + ": " + profileLinkElem.isDisplayed());

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

}
