package test_flows.computer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import models.components.order.ComputerEssentialComponent;
import models.pages.ComputerItemDetailsPage;
import test_data.computer.ComputerData;

public class OrderComputerFlow<T extends ComputerEssentialComponent> {

    private final WebDriver driver;
    private final Class<T> computerEssentialComponent;
    private final ComputerData computerData;

    public OrderComputerFlow(WebDriver driver, Class<T> computerEssentialComponent, ComputerData computerData) {
        this.driver = driver;
        this.computerEssentialComponent = computerEssentialComponent;
        this.computerData = computerData;

    }

    public void buildCompSpecAndAddToCard() {
        ComputerItemDetailsPage computerItemDetailsPage = new ComputerItemDetailsPage(driver);
        T computerEssentialComp = computerItemDetailsPage.computerComp(computerEssentialComponent);
        String processorFullStr = computerEssentialComp.getProcessorType(computerData.getProcessorType());
        String ramFullStr = computerEssentialComp.getRAMType(computerData.getRam());
        String hddFullStr = computerEssentialComp.getHddType(computerData.getHdd());

        double proccessorAdditionalPrice = extractAdditionalPrice(processorFullStr);
        double ramAdditionalPrice = extractAdditionalPrice(ramFullStr);
        double hddAdditionalPrice = extractAdditionalPrice(hddFullStr);

        System.out.println("proccessorAdditionalPrice: " + proccessorAdditionalPrice);
        System.out.println("ramAdditionalPrice: " + ramAdditionalPrice);
        System.out.println("hddAdditionalPrice: " + hddAdditionalPrice);

        if (computerData.getOs() != null) {
            String osFullStr = computerEssentialComp.getOsType(computerData.getOs());
            double osAdditionalPrice = extractAdditionalPrice(osFullStr);
            System.out.println("osAdditionalPrice: " + osAdditionalPrice);
        }
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
}
