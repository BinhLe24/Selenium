package pom_tests;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import models.pages.LoginPageMod02;

public class LoginTestMod02 {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod02 loginPage = new LoginPageMod02(driver);

            loginPage.inputUsername("tomsmith");
            loginPage.inputPassword("SuperSecretPassword!");
            loginPage.loginBtn();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the browser session
        driver.quit();

    }
}
