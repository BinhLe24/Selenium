package pom_tests;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import models.components.LoginFormComponent;
import models.pages.LoginPageMod03;

public class LoginTestMod03 {

    public static void main(String[] args) {
        // Get a chrome session
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            // Navigate to the target page
            driver.get("https://the-internet.herokuapp.com/login");

            LoginPageMod03 loginPage = new LoginPageMod03(driver);
            LoginFormComponent loginFormComp = loginPage.loginFormComponent();

            loginFormComp.inputUsername("tomsmith");
            loginFormComp.inputPassword("SuperSecretPassword!");
            loginFormComp.loginBtn();

            // DEBUG PURPOSE ONLY
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Quit the browser session
        driver.quit();

    }
}
