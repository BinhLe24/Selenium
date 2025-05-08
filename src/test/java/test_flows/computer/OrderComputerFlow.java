package test_flows.computer;

import org.openqa.selenium.WebDriver;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
    }

    public void buildCompSpecAndAddToCard() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
        computerEssentialComp.getProcessorType("2.2GHz");
        computerEssentialComp.getRAMType("4GB");
    }
}
