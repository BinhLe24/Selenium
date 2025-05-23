package driver;

public enum BrowserType {

    chrome("chrome"),
    firefox("firefox"),
    safari("safari"),
    edge("MicrosoftEdge");

    private final String name;

    BrowserType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
