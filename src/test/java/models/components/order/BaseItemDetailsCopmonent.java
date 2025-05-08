package models.components.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;

public class BaseItemDetailsCopmonent extends Component {

    private final static By productPriceSel = By.cssSelector(".product-price");

    public BaseItemDetailsCopmonent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public double getProductPrice() {
        String productPriceText = component.findElement(productPriceSel).getText().trim();
        return Double.parseDouble(productPriceText);
    }

}
