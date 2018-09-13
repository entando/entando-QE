

### Selenium_APPBUILDER

The project has been developed on AppBuilder application 
You can find it on http://appbuilder.serv.run/
else you can install it on your local machine.


__Prerequisites__

* Java, Maven, Google Chrome and ChromeDriver installed on your machine

Below the versions we worked with:

### Java version

Oracle Corporation Java 1.8.0_181

### Maven version

Apache Maven 3.3.9

### ChromeDriver Version

ChromeDriver 2.40.565383

### Google Chrome Version

Google Chrome 68.0.3440.106

REMEMBER: If you use a pair major release of ChromeDriver (ex: x.40) you need a pair release of Google Chrome (ex: 68.x)
For more information see this link: http://chromedriver.chromium.org/downloads


__Before run the tests__

Set this environment in the AppBuilder.

Create each of the following elements:

• User Group ->
	Name: 1SeleniumTest_DontTouch
	Code: 1seleniumtest_dontto

• Profile Type -> 
	Name: 1SeleniumTest_DontTouch
	Code: SLN

• User Role -> 
	Name: 1SeleniumTest_DontTouch
	Code: 1seleniumtest_role
	
• Languages ->
	Only English and Italian
	
• Users ->
	Name: 1SLNM_DONT_TOUCH
	
• Page Model ->
	Name: 1SLNM_TEST_DONT_TOUCH
	Code: 1SLNM_TEST_DONT_TOUCH
	JSon Configuration: {
								  "frames": [
									 {
										"pos": 0,
										"descr": "Test frame",
										"mainFrame": false,
										"defaultWidget": null,
										"sketch": null
									 }
								  ]
								}
	Template: <>

• Data Type
	Name: SeleniumTest_DontTouch
	Code: SLN

• Data Type
	Name: SeleniumTest_DontTouch1
	Code: SLM
	
• Data Type
	Name: SeleniumTest_DontTouch2
	Code: SLL

• Widget
	Name: SeleniumTest_DontTouch

• Page (in Page Tree) ->
	Code: SeleniumTest_DontTouch
	Title: SeleniumTest_DontTouch
	Placement: Home
	Owner Group: 1SeleniumTest_DontTouch
	Page Model: 1SLNM_TEST_DONT_TOUCH
	
• Categories
	Name: SeleniumTest_DontTouch
	Code: seleniumtest_donttouch
	
	
Set this options:
	
• User Management > User Restrictions ->
	Password Always Active: OFF
	Enable Gravatar Integration (Avatar of users): OFF
	Number of months the password is valid for, after last access: 3
	Number of months the password is valid for: 6


__How to set tests options__

• Set the landing page
1) Open the class DTLoginPage in the package `org.entando.selenium.pages`
2) Find the class constructor method
3) Change the parameter of driver.get("http://appbuilder.landing.page/")
Now it set to "http://appbuilder.serv.run/" (the our online AppBuilder)
You can use your local AppBuilder and the parameter to be set will be similar to "http://localhost:8080/myApp/"

• Set headless mode (a solution to run tests without opening the browser)
1) Open the class AppConfig in the package `org.entando.selenium.utils`
2) find the local variable `HEADLESS`
3) Set it true if you enable "headless mode" or false to disable it

• Set debug mode (to show some message about the execution of the test and to identify unexpected errors in the tests)
1) Open the class FunctionalTestBase in the package `org.entando.selenium.utils`
2) find the method setUp()
3) Change the parameter of `Logger.getGlobal().setLevel(Level.OFF)`
You can use Level.OFF to disable debug mode or `Level.INFO` if enable it


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

If you prefer you can run the tests on your favorite IDE likes NetBeans, JUnit or IntelliJ.