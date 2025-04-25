package tests.global.footer;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import driver.DriverFactory;
import models.components.global.footer.AccountColumnComponent;
import models.components.global.footer.CustomerServiceColumnComponent;
import models.components.global.footer.FollowUsColumnComponent;
import models.components.global.footer.FooterColumnComponent;
import models.components.global.footer.InformationColumnComponent;
import models.pages.HomePage;
import support.verification.Verifier;
import url.Urls;

public class FooterTest {

    @Test
    public void testDemo1() {
        String actualResult = "Ti";
        String expectedResult = "Teo";
        // Verifier.verifyResult(actualResult, expectedResult);

        // Hard assertion
        Assert.assertEquals(expectedResult, actualResult, "[ERR] Welcome message is incorrect!");
        Assert.assertTrue("[ERR] Welcome message is incorrect!", actualResult.equals(expectedResult));
        Assert.assertFalse("[ERR] Welcome message is incorrect!", !actualResult.equals(expectedResult));
        Assert.fail();
        Assert.fail("[ERR] Welcome message is incorrect!");
    }

    @Test
    public void testDemo2() {
        // Soft assertion
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(1, 2);
        softAssert.assertEquals(true, true);
        softAssert.assertEquals(2, 3);
        softAssert.assertAll();

        System.out.println("Hello");

    }

    WebDriver driver = DriverFactory.getChromDriver();

    @Test
    public void testFooterCategoryPage() {
        try {
            testFooter(driver, Urls.categoryPageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFooterRegisterPage() {
        try {
            testFooter(driver, Urls.registerPageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFooterLoginPage() {
        try {
            testFooter(driver, Urls.loginPageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @AfterSuite
    public void afterSuite() {
        driver.quit();
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
