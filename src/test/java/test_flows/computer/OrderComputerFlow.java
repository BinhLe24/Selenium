package test_flows.computer;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import models.components.cart.TotalComponent;
import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import models.pages.ShoppingCartPage;
import test_data.computer.ComputerData;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;
    private double quantity;
    private double totalItemPrice;

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
        TotalComponent totalComponent = shoppingCartPage.totalComponent();
        Map<String, Double> priceCategories = totalComponent.priceCategories();
        for (String priceType : priceCategories.keySet()) {
            System.out.println(priceType + priceCategories.get(priceType));
        }
    }
}
