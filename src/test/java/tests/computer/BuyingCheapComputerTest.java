package tests.computer;

import org.testng.annotations.Test;

import models.components.order.CheapComputerComponent;
import test_flows.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingCheapComputer() {
        driver.get(demoPageUrl.concat("build-your-cheap-own-computer"));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver,
                CheapComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCard();

    }
}
