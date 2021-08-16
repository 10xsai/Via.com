package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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
	
	public void checkBlank (WebElement ele, String elemName){
		if(ele.getText()==""){
			Assert.assertEquals(elemName + " is blank", elemName + " should not be blank");
		}
	}
	
	@BeforeMethod
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", prop.getProperty("cpath"));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}
	
	@AfterMethod
	public void afterTest() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}