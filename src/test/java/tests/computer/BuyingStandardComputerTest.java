package tests.computer;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import models.components.order.StandardComputerComponent;
import test_data.DataObjectBuilder;
import test_data.computer.ComputerData;
import test_flows.BaseTest;
import test_flows.computer.OrderComputerFlow;
import url.Urls;

public class BuyingStandardComputerTest extends BaseTest implements Urls {

    @Test(dataProvider = "computerData")
    public void testBuyingStandardComputer(ComputerData computerData) {
        driver.get(demoPageUrl.concat("build-your-own-computer"));
        OrderComputerFlow<StandardComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver,
                StandardComputerComponent.class, computerData);
        orderComputerFlow.buildCompSpecAndAddToCard();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\test\\java\\test_data\\computer\\StandardComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
