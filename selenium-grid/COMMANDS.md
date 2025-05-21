## Command start hub
java -jar selenium-server-4.32.0.jar hub


## Command to start Node
This node will register:
- Chrome
- Firefox
- MicrosoftEdge
- Safari

Note: With Windows OS, please remove part related to safari and add for Edge

java -jar -Dwebdriver.<type>.<name> path/to/selenium/server.jar node --config /path/to/nodeConfig.json

java -jar -Dwebdriver.gecko.driver=geckodriver.exe -Dwebdriver.chrome.driver=chromedriver.exe -Dwebdriver.edge.driver=msedgedriver.exe selenium-server-4.32.0.jar node --config node_config.json