package test.global.footer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import test.BaseTest;
import test_flows.global.FooterTestFlow;

public class FooterTest1 extends BaseTest {

    @Test
    public void testFooterCategoryPage() {
        WebDriver driver = getDriver();
        driver.get(url.Urls.categoryPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }

    @Test
    public void testFooterRegistorPage() {
        WebDriver driver = getDriver();
        driver.get(url.Urls.registerPageUrl);
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }
}
