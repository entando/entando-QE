### Selenium_GRID_APPBUILDER

The project has been developed on AppBuilder application. 

You can install it on your local machine.


__Notes__

This Selenium Project contains the same test cases of Selenium_APPBUILDER project, but it also implements the possibility of parallel run of the tests.
You can run the same test in one or more machines (also virtual) with different environments (combinations of Operating Systems and browsers) simultaneous.

__Prerequisites__

* What you need on every machine (node): Java, Maven, Selenium, one or more browser and its corresponding driver

Below the versions we worked with:

### Java version

Oracle Corporation Java 1.8.0_181

### Maven version

Apache Maven 3.3.9

### Selenium version

Selenium Server Standalone 3.14.0

### ChromeDriver Version

ChromeDriver 2.40.565383

### Google Chrome Version

Google Chrome 68.0.3440.106

### Mozilla Firefox Version

Mozilla Firefox 62.0.3

### Geckodriver Version (for Firefox)

Geckodriver 0.23.0 



REMEMBER: If you use a pair major release of ChromeDriver (ex: x.40) you need a pair release of Google Chrome (ex: 68.x)
For more information see this link: http://chromedriver.chromium.org/downloads


__How to set the Grid__

A grid consists of a single hub, and one or more nodes, Hub and Node are the two main elements that you come across when using grid.
The Hub is the central point which will receive all the test requests along with information on which browser, platform and other informations.
Based on the request received, it will distribute them to the registered nodes.
Nodes are where our tests will run, each Node is machine (can be a physical machine / virtual machine) that we register with the Hub.

• Start a hub with default parameters
1) Open a bash terminal then positioned in the folder that contains Selenium Server Standalone jar file
2) Type the command:
`java -jar selenium-server-standalone-3.14.0.jar -role hub`
3) If the hub is started, you can view the Grid Console in your browser at the following address: http://localhost:4444/grid/console

• Start a node
1) Open a bash terminal then positioned in the folder that contains Selenium Server Standalone jar file
2) Type the command:
`java -jar selenium-server-standalone-3.14.0.jar -role node -hub http://localhost:4444/grid/register`
If we are not specifying any parameters, its default port is 5555.
If we need more nodes, each one needs a different port number.
In this case, type the following command with different ports:
`java -jar selenium-server-standalone-3.14.0.jar -role node -hub http:/ocalhost:4444/grid/register/ -port 5003`
If we need specify what browsers and how many instances of it, add the parameter `-browser` like the following example:
`-browser browserName=firefox,maxInstances=20 -browser browserName=chrome,maxInstances=20`
3) If the node is registered, you can view it in the Grid Console


__How to set tests options__

• Set Grid Mode
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) Find the `GRID_EXECUTION` parameter
3) Set it `true` if you enable "grid execution" (parallel execution) or `false` to disable it

• Set Environments to run the tests in Grid Mode
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) Find the getEnvironments method
3) Set here grid environments

• Set the landing page
1) Open the class DTLoginPage in the package `org.entando.selenium.pages`
2) Find the class constructor method
3) Change the parameter of `driver.get("http://localhost:8080/myApp/")` to whatever suits your needs

• Set the username and password
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) Find the `USERNAME` and `PASSWORD` parameters
3) Change the parameter values

• Set debug mode (to show some message about the execution of the test and to identify unexpected errors in the tests)
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) find the method setUp()
3) Change the parameter of `Logger.getGlobal().setLevel(Level.OFF)`
You can use `Level.OFF` to disable debug mode or `Level.INFO` if enable it

• Set headless mode (a solution to run tests without opening the browser)
1) Open the class AppConfig in the package `org.entando.selenium.utils`
2) find the local variable `HEADLESS`
3) Set it `true` if you enable "headless mode" or `false` to disable it



__Before run the tests__

It's essential to create the necessary environment for the tests to work properly.
This involves the creation of various elements within the application that will be used during the execution of the tests.
To automate this operation you can run the Environment Checker class as if it were a normal test.
How to run Environment Checker:
1) Set `false` GRID_EXECUTION parameter in the FunctionalTestBase class (package `org.entando.selenium.utils`)
2) Open a bash terminal then positioned in the folder that contains pom.xml
3) Type the command: 
`mvn -Dtest=*EnvironmentChecker.java test`
Wait the end of this process.

Moreover it's essential to create a text file in your local file system like `seleniumTest-File.txt` and set file name and path in the FileBrowserTestBase.java (located in the package: org.entando.selenium.testHelpers): it's necessary to run DTFileBrowserUploadFileTest.java



__How to run the tests__

1) Open a bash terminal then positioned in the folder that contains pom.xml
2) If you run ALL tests type the command:
`mvn test`
If you run a specific test type the command:
`mvn -Dtest=SpecificTest.java test`
If you run a series of tests type the command:
`mvn -Dtest=DTEx* test`

It's recommended to use output redirection to read tests results
For Example:
`mvn -Dtest=DTEx* test > Example-Results.txt`

If you prefer you can run the tests on your favorite IDE likes NetBeans or IntelliJ.
