package tests.global.footer;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import test_flows.BaseTest;
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
        Assert.fail();
        FooterTestFlow footerTestFlow = new FooterTestFlow(driver);
        footerTestFlow.verifyProductCatFooterComponent();
    }
}
