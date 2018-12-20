### Selenium_APPBUILDER

This Maven project has been developed to perform automatic functional testing on the Entando AppBuilder application. 



__Prerequisites__

* Java, Maven, Selenium, Google Chrome and ChromeDriver installed on your machine

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

REMEMBER: If you use a major release of ChromeDriver (ex: x.40) you need a paired release of Google Chrome (ex: 68.x) to be able to run the tests.
For more information see this link: http://chromedriver.chromium.org/downloads


__How to set tests options__

• Set the landing page
1) Open the class DTLoginPage in the package `org.entando.selenium.pages`
2) Find the class constructor method
3) Change the parameter of driver.get("http://localhost:8080/myApp/") to whatever suits your needs

• Set the username and password
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) Find the USERNAME and PASSWORD parameters
3) Change the parameter values

• Set debug mode (to show some message about the execution of the test and to identify unexpected errors in the tests)
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) Find the method setUp()
3) Change the parameter of `Logger.getGlobal().setLevel(Level.OFF)`
You can use Level.OFF to disable debug mode or `Level.INFO` if enable it

• Set headless mode (a solution to run tests without opening the browser)
1) Open the class AppConfig in the package `org.entando.selenium.utils`
2) Find the local variable `HEADLESS`
3) Set it to true if you would like you enable "headless mode" or false to disable it


• Set browserstack mode (a solution to run tests on browserstack (TM))
1) Open the class AppConfig in the package `org.entando.selenium.utils`
2) Find the local variable `BROWSERSTACK`
3) Set it to true if you would like to enable  the "browserstack mode" or false to disable it
4) In order to be able to use Browserstack (TM) you need to have a valid account and to provide the correct browserstack (TM) credentials in the AppConfig class. 

Note: By default the BROWSERSTACK variable is set to true.



__Before run the tests__

It's essential to create the necessary environment for the tests to work properly.
This involves the creation of various elements within the application that will be used during the tests execution.
To automate this operation you can run the Environment Checker class as if it were a normal test.

How to run Environment Checker:
1) Open a terminal in the folder that contains the pom.xml file
2) Type the command: 
`mvn -Dtest=*EnvironmentChecker.java test`
Wait the end of this process.

Moreover it's essential to create a text file in your local file system like `seleniumTest-File.txt` and set file name and path in the FileBrowserTestBase.java (located in the package: org.entando.selenium.testHelpers): it's necessary to run DTFileBrowserUploadFileTest.java



__How to run the test__

1) Open a terminal in the folder that contains pom.xml file
2) Should you prefer to run ALL tests type the command:
`mvn test`
To run a specific test type the command:
`mvn -Dtest=SpecificTest.java test`


The tests can of course been run from your favorite IDE.
