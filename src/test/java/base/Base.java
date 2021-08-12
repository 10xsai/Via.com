package base;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Base {
	public WebDriver driver;
	public Properties props = new Properties();

	
	@BeforeTest
	public void beforeTest() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream("src/test/resources/settings.property"));			
		}
		catch (Exception e) {
			System.out.println(e);
			driver.quit();
		}
		
		System.setProperty("webdriver.chrome.driver", props.getProperty("cpath"));
		ChromeDriver driver = new ChromeDriver();
		
	}
	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}
}
