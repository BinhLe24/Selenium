package models.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;

public class ShoppingCartPage extends BasePage {

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public List<CartItemRowComponent> CartItemRowComponents() {
        return findComponents(CartItemRowComponent.class, driver);
    }

    public TotalComponent totalComponent() {
        return findComponent(TotalComponent.class, driver);
    }

}
