package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utilities.ExcelReader;

public class TestBase {
	
	/*Initialise
	 * WebDriver
	 * Properties
	 * Log - log4j jar,log4j properties file,
	 * Extent report`
	 * DB
	 * Mail
	 */
	
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	
	public static ExcelReader excel = new ExcelReader("C:\\Users\\sam\\eclipse-workspace\\testAutomation\\src\\test\\resources\\excel\\testData.xlsx");
	public static final Logger log=Logger.getLogger(TestBase.class.getName());
	
	
	
	
	@BeforeSuite
	public void setUp() {
		
		
		if(driver==null) {
			try {
				fis = new FileInputStream("C:\\Users\\sam\\eclipse-workspace\\testAutomation\\src\\test\\resources\\properties\\Config.properties");
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				config.load(fis);
				log.debug("Config file loaded>>>!!!");
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			try {
				fis = new FileInputStream("C:\\Users\\sam\\eclipse-workspace\\testAutomation\\src\\test\\resources\\properties\\OR.properties");
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			
			try {
				OR.load(fis);
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			if(config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
				log.debug("Firefox Lunch..!!");
			}
			else if(config.getProperty("browser").equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\sam\\eclipse-workspace\\testAutomation\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				
			} else if(config.getProperty("browser").equals("IE")) {
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\sam\\eclipse-workspace\\testAutomation\\src\\test\\resources\\executables\\MicrosoftWebDriver.exe");
				driver = new InternetExplorerDriver();
				
				}
			
			driver.get(config.getProperty("testSuiteUrl"));
//			driver.get("http://beta.hypernesto.com");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
			
			}
		
	}
	
	@AfterSuite
	public void tearDown() {
		if(driver!=null) {
		driver.quit();
		}
	}

}
