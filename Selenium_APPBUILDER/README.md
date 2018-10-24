### Selenium_APPBUILDER

The project has been developed on AppBuilder application. 
You can install it on your local machine.


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

REMEMBER: If you use a pair major release of ChromeDriver (ex: x.40) you need a pair release of Google Chrome (ex: 68.x)
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
2) find the method setUp()
3) Change the parameter of `Logger.getGlobal().setLevel(Level.OFF)`
You can use Level.OFF to disable debug mode or `Level.INFO` if enable it

• Set headless mode (a solution to run tests without opening the browser)
1) Open the class AppConfig in the package `org.entando.selenium.utils`
2) find the local variable `HEADLESS`
3) Set it true if you enable "headless mode" or false to disable it



__Before run the tests__

It's essential to create the necessary environment for the tests to work properly.
This involves the creation of various elements within the application that will be used during the execution of the tests.
To automate this operation you can run the Environment Checker class as if it were a normal test.
How to run Environment Checker:
1) Open a bash terminal then positioned in the folder that contains pom.xml
2) Type the command: 
`mvn -Dtest=*EnvironmentChecker.java test`
Wait the end of this process.

Moreover it's essential to create a text file in your local file system like `seleniumTest-File.txt` and set file name and path in the FileBrowserTestBase.java (located in the package: org.entando.selenium.testHelpers): it's necessary to run DTFileBrowserUploadFileTest.java



__How to run the test__

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
