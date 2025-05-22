package models.pages;

import org.openqa.selenium.WebDriver;

import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ConfirmOrderComponent;
import models.components.checkout.PaymentInformationComponent;
import models.components.checkout.PaymentMethodComponent;
import models.components.checkout.ShippingAddressComponent;
import models.components.checkout.ShippingMethodComponent;

public class CheckOutPage extends BasePage {

    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public BillingAddressComponent billingAddressComp() {
        return findComponent(BillingAddressComponent.class, driver);
    }

    public ShippingAddressComponent shippingAddressComp() {
        return findComponent(ShippingAddressComponent.class, driver);
    }

    public ShippingMethodComponent shippingMethodComp() {
        return findComponent(ShippingMethodComponent.class, driver);
    }

    public PaymentMethodComponent paymentMethodComp() {
        return findComponent(PaymentMethodComponent.class, driver);
    }

    public PaymentInformationComponent paymentInformationComp() {
        return findComponent(PaymentInformationComponent.class, driver);
    }

    public ConfirmOrderComponent confirmOrderComp() {
        return findComponent(ConfirmOrderComponent.class, driver);
    }
}
