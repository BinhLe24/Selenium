package test_flows.computer;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import models.components.cart.CartItemRowComponent;
import models.components.cart.TotalComponent;
import models.components.checkout.BillingAddressComponent;
import models.components.checkout.ShippingMethodComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.CheckOutPage;
import models.pages.CheckoutOptionsPage;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_data.user.UserDataObject;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private double quantity;
    private double totalItemPrice;
    private UserDataObject defaultCheckoutUser;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = 1;
    }

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData,
            double quantity) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;
        this.quantity = quantity;
    }

    public void buildCompSpecAndAddToCard() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);

        // Unselect default options
        computerEssentialComp.unselectDefaultOptions();

        String processorFullStr = computerEssentialComp.getProcessorType(computerData.getProcessorType());
        String ramFullStr = computerEssentialComp.getRAMType(computerData.getRam());
        String hddFullStr = computerEssentialComp.getHddType(computerData.getHdd());
        String softwareFullStr = computerEssentialComp.getSoftwareType(computerData.getSoftware());

        double proccessorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);
        double softwareAdditionalPrice = extractAdditionalPrice(softwareFullStr);
        double osAdditionalPrice = 0;

        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.getOsType(computerData.getOs());
            osAdditionalPrice = extractAdditionalPrice(osFullStr);
        }

        // Calculate item's price
        double fullAdditionalPrices = proccessorAdditionalPrice + ramAdditionalPrice + hddAdditionalPrice
                + osAdditionalPrice + softwareAdditionalPrice;
        double basePrice = computerEssentialComp.getProductPrice();
        totalItemPrice = (fullAdditionalPrices + basePrice) * quantity;

        // Add to cart
        computerEssentialComp.clickOnAddToCartBtn();
        computerEssentialComp.waitUntilItemAddedToCart();

        // Navigate to shopping cart
        computerItemDetailsPage.headerComponent().clickShoppingCartLinkElem();

    }

    private double extractAdditionalPrice(String itemStr) {
        double price = 0;
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(itemStr);
        if (matcher.find()) {
            price = Double.parseDouble(matcher.group(1).replaceAll("[-+]", ""));
        }
        return price;
    }

    public void verifyShoppingCartPage() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<CartItemRowComponent> cartItemRowComps = shoppingCartPage.CartItemRowComponents();
        if (cartItemRowComps.isEmpty()) {
            Assert.fail("[ERR] There is no item displayed in the shopping cart!");
        }

        double currentSubTotal = 0;
        double currentTotalUnitPrice = 0;
        for (CartItemRowComponent cartItemRowComp : cartItemRowComps) {
            currentSubTotal = currentSubTotal + cartItemRowComp.subTotalPrice();
            currentTotalUnitPrice = currentTotalUnitPrice + (cartItemRowComp.unitPrice() * cartItemRowComp.quantity());
        }

        Assert.assertEquals(currentSubTotal, currentTotalUnitPrice, "[ERR] Shopping cart's sub-total is incorrect");

        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        double checkoutSubTotal = 0;
        double checkoutOtherFeesTotal = 0;
        double checkoutTotal = 0;

        for (String priceType : priceCategories.keySet()) {
            double priceValue = priceCategories.get(priceType);
            if (priceType.startsWith("Sub-Total")) {
                checkoutSubTotal = priceValue;
            } else if (priceType.startsWith("Total")) {
                checkoutTotal = priceValue;
            } else {
                checkoutOtherFeesTotal += priceValue;
            }
        }
        Assert.assertEquals(checkoutSubTotal, currentSubTotal, "[ERR] Shopping cart's checkout sub-total is incorrect");
        Assert.assertEquals(checkoutTotal, (checkoutSubTotal + checkoutOtherFeesTotal),
                "[ERR] Shopping cart's total is incorrect");
    }

    public void agreeTOSAndCheckOut() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        shoppingCartPage.totalComponent().agreeTOS();
        shoppingCartPage.totalComponent().clickOnCheckOutBtn();
        new CheckoutOptionsPage(driver).checkoutAsGuest();

        try {
            Thread.sleep(3000);
        } catch (Exception ignore) {
        }
    }

    public void inputBillingAddress() {
        String defaultCheckoutUserJSONLoc = "\\src\\test\\java\\test_data\\user\\DefaultCheckoutUser.json";
        defaultCheckoutUser = DataObjectBuilder.buildDataObjectFrom(defaultCheckoutUserJSONLoc, UserDataObject.class);
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        BillingAddressComponent billingAddressComp = checkOutPage.billingAddressComp();

        billingAddressComp.selectInputNewAddress();
        billingAddressComp.inputFirstname(defaultCheckoutUser.getFirstname());
        billingAddressComp.inputLastname(defaultCheckoutUser.getLastname());
        billingAddressComp.inputEmail(defaultCheckoutUser.getEmail());
        billingAddressComp.selectCountry(defaultCheckoutUser.getCountry());
        billingAddressComp.selectState(defaultCheckoutUser.getState());
        billingAddressComp.inputCity(defaultCheckoutUser.getCity());
        billingAddressComp.inputAddress1(defaultCheckoutUser.getAddress1());
        billingAddressComp.inputZipCode(defaultCheckoutUser.getZipCode());
        billingAddressComp.inputPhoneNumber(defaultCheckoutUser.getPhoneNumber());
        billingAddressComp.clickOnContinueBtn();
    }

    public void inputShippingAddress() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        checkOutPage.shippingAddressComp().clickOnContinueBtn();
    }

    public void selectShippingMethod() {
        CheckOutPage checkOutPage = new CheckOutPage(driver);
        ShippingMethodComponent shippingMethodComp = checkOutPage.shippingMethodComp();
        List<String> shippingMethods = Arrays.asList("Ground", "Next Day Air", "2nd Day Air");
        String randomShippingMethod = shippingMethods.get(new SecureRandom().nextInt(shippingMethods.size()));
        System.out.println("randomShippingMethod:" + randomShippingMethod);
        shippingMethodComp.selectShippingMethod(randomShippingMethod).clickOnContinueBtn();
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void selectPaymentMethod() {

    }

    public void inputPaymentInfo() {

    }

    public void confirmOrder() {

    }
}