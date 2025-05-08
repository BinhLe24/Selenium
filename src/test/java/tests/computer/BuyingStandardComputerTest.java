package tests.computer;

import org.testng.annotations.Test;

import models.components.order.StandardComputerComponent;
import test_flows.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test
    public void testBuyingStandardComputer() {
        driver.get(demoPageUrl.concat("build-your-own-computer"));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver,
                StandardComputerComponent.class);
        orderComputerFlow.buildCompSpecAndAddToCard();

    }
}
