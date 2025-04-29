package tests.global.footer;

import org.junit.Test;
import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import test_flows.global.FooterTestFlow;

public class FooterTest1 {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = DriverFactory.getChromDriver();
        driver.get(url.Urls.demoPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        try {
            footerTestFlow.verifyProductCatFooterComponent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

    }

    // @Test
    // public void testFooterRegistorPage() {

    // }

    // @Test
    // public void testFooterLoginPage() {

    // }

    // @Test
    // public void testFooterHomePage() {

    // }

    // @Test
    // public void testMain() {
    // WebDriver driver = DriverFactory.getChromDriver();
    // driver.get(url.Urls.demoPageUrl);
    // FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
    // try {
    // footerTestFlow.verifyProductCatFooterComponent();
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    // driver.quit();

    // }

}
