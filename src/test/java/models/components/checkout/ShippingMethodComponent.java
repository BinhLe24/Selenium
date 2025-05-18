package models.components.checkout;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import models.components.Component;
import models.components.ComponentCssSelector;

@ComponentCssSelector(value = "#opc-shipping_method")
public class ShippingMethodComponent extends Component {

    private static final By continueBtnSel = By.cssSelector(".shipping-method-next-step-button");

    public ShippingMethodComponent(WebDriver driver, WebElement component) {
        super(driver, component);
    }

    public ShippingMethodComponent selectShippingMethod(String method) {
        try {

            component.findElement(By.xpath("//label[contains(text(),'" + method + "')]")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public void clickOnContinueBtn() {
        WebElement continueBtn = component.findElement(continueBtnSel);
        continueBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(continueBtn));
    }
}