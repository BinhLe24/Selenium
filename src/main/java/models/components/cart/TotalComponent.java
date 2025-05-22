package models.components.cart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = ".cart-footer .totals")
public class TotalComponent extends Component {

    private static final By priceTableRowSel = By.cssSelector(".cart-total tr");
    private static final By priceTypeSel = By.cssSelector(".cart-total-left");
    private static final By priceValueSel = By.cssSelector(".cart-total-right");
    private static final By tosSel = By.cssSelector("#termsofservice");
    private static final By checkoutBtnSel = By.cssSelector("#checkout");

    public TotalComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public Map<String, Double> priceCategories() {

        Map<String, Double> priceCategories = new HashMap<>();
        List<WebElement> priceTableRowsElem = component.findElements(priceTableRowSel);
        for (WebElement tableRowElem : priceTableRowsElem) {
            WebElement priceTypeELem = tableRowElem.findElement(priceTypeSel);
            WebElement priceValueElem = tableRowElem.findElement(priceValueSel);
            String priceType = priceTypeELem.getText().trim();
            double priceValue = Double.parseDouble(priceValueElem.getText().trim());

            priceCategories.put(priceType, priceValue);
        }

        return priceCategories;
    }

    public void agreeTOS() {
        component.findElement(tosSel).click();
    }

    public void clickOnCheckOutBtn() {
        component.findElement(checkoutBtnSel).click();
    }

}
