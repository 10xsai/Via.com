package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver;

	public Properties prop;
	
	public Base(String propertyFilePath) {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(propertyFilePath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// This method gets executed before every test case
	@BeforeMethod
	public void beforeTest() {
		// Sets system property for chrome driver
		System.setProperty("webdriver.chrome.driver", props.getProperty("cpath"));	
		ChromeDriver driver = new ChromeDriver();
		
		//Takes the url from property file
		driver.get(props.getProperty("url"));
	}
		
	// This method gets executed after execution of all test methods
	@AfterMethod
	public void afterTest() {
		driver.quit();
	}
}