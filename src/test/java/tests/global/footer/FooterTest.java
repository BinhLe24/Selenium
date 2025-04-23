package tests.global.footer;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import models.components.global.footer.AccountColumnComponent;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FollowUsColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import url.Urls;

public class FooterTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            testFooterHomePage(driver);
            testFooterCategoryPage(driver);
            testFooterRegisterPage(driver);
            testFooterLoginPage(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();

    }

    private static void testFooterHomePage(WebDriver driver) {
        testFooter(driver, Urls.demoPageUrl);
    }

    private static void testFooterCategoryPage(WebDriver driver) {
        testFooter(driver, Urls.categoryPageUrl);
    }

    private static void testFooterRegisterPage(WebDriver driver) {
        testFooter(driver, Urls.registerPageUrl);
    }

    private static void testFooterLoginPage(WebDriver driver) {
        testFooter(driver, Urls.loginPageUrl);

    }

    public static void testFooterColumn(FooterColumnComponent footerColumnComponent) {
        System.out.println(footerColumnComponent.headerElem().getText());
        footerColumnComponent.linksElem().forEach(link -> {
            System.out.println(link.getText());
            System.out.println(link.getDomAttribute("href"));
        });
    }

    public static void testFooter(WebDriver driver, String url) {
        driver.get(url);
        HomePage homePage = new HomePage(driver);
        InformationColumnComponent informationColumnComponent = homePage.footerComponent().informationColumnComponent();
        CustomerServiceColumnComponent customerServiceColumnComponent = homePage.footerComponent()
                .customerServiceColumnComponent();
        AccountColumnComponent accountColumnComponent = homePage.footerComponent().accountColumnComponent();
        FollowUsColumnComponent followUsColumnComponent = homePage.footerComponent().followUsColumnComponent();
        testFooterColumn(informationColumnComponent);
        testFooterColumn(customerServiceColumnComponent);
        testFooterColumn(accountColumnComponent);
        testFooterColumn(followUsColumnComponent);
    }

}
