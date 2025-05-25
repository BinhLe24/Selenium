package test.computer;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import models.components.order.CheapComputerComponent;
import test_data.CreditCardType;
import test_data.DataObjectBuilder;
import test_data.PaymentMethod;
import test_data.computer.ComputerData;
import test_flows.computer.OrderComputerFlow;
import test.BaseTest;
import url.Urls;

public class BuyingCheapComputerTest extends BaseTest implements Urls {

   @Test(dataProvider = "computerData")
      public void testBuyingCheapComputer(ComputerData computerData) {
        WebDriver driver = getDriver();
        driver.get(demoPageUrl.concat(buyCheapComputerSlug));
        OrderComputerFlow<CheapComputerComponent> orderComputerFlow = new OrderComputerFlow<>(driver,
                CheapComputerComponent.class, computerData);

        orderComputerFlow.buildCompSpecAndAddToCard();
        orderComputerFlow.verifyShoppingCartPage();
        orderComputerFlow.agreeTOSAndCheckOut();
        orderComputerFlow.inputBillingAddress();
        orderComputerFlow.inputShippingAddress();
        orderComputerFlow.selectShippingMethod();
        orderComputerFlow.selectPaymentMethod(PaymentMethod.CREDIT_CARD);
        orderComputerFlow.inputPaymentInfo(CreditCardType.DISCOVER);
        orderComputerFlow.confirmOrder();
    }

    @DataProvider
    public ComputerData[] computerData() {
        String fileLocation = "\\src\\main\\java\\test_data\\computer\\CheapComputerDataList.json";
        return DataObjectBuilder.buildDataObjectFrom(fileLocation, ComputerData[].class);
    }
}
