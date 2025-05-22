package test.global.footer;

import java.util.List;

import org.openqa.selenium.WebDriver;

import driver.DriverFactory;
import models.components.product.ProductItemComponent;
import models.pages.HomePage;
import url.Urls;

public class FeatureProductTest {
    public static void main(String[] args) {
        WebDriver driver = DriverFactory.getChromDriver();

        try {
            testFeatureProduct(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }

        driver.quit();
    }

    private static void testFeatureProduct(WebDriver driver) {
        driver.get(Urls.demoPageUrl);
        HomePage homePage = new HomePage(driver);
        List<ProductItemComponent> itemComponents = homePage.productGridComponent()
                .productItemCoponents();
        itemComponents.forEach(productItem -> System.out.println(productItem.productTitleElem().getText()));
    }
}
