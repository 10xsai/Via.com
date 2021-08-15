package base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver;

	//This method gets executed after every test case
	@BeforeTest
	public void beforeTest() {
		//to get properties from property file
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("src/test/resources/settings.property"));			
		}
		catch (Exception e) {
			System.out.println(e);
			driver.quit();
		}
		//sets system property for chrome driver
		System.setProperty("webdriver.chrome.driver", props.getProperty("cpath"));	
		ChromeDriver driver = new ChromeDriver();
		
		//takes the url from property file
		driver.get(props.getProperty("url"));
	}
	//This method gets executed after execution of all test methods
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
