## Command start hub
java -jar selenium-server-4.32.0.jar hub


## Command to start Node
This node will register:
- Chrome
- Firefox
- Safari

Note: With Windows OS, please remove part related to safari and add for Edge

java -jar -Dwebdriver.<type>.<name>s path/to/selenium/server.jar node --config /path/to/nodeConfig.json

java -jar -Dwebdriver.gecko.driver=geckodriver -Dwebdriver.chrome.driver=chromedriver selenium-server-4.32.0.jar node --config node_config.json