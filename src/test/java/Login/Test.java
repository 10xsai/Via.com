package login;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Properties props = new Properties();
		props.load(new FileInputStream("src/test/resources/settings.property"));
		
		System.setProperty("webdriver.chrome.driver", props.getProperty("cpath"));
		ChromeDriver driver = new ChromeDriver();
		
		driver.get("https://www.selenium.dev");
		
		driver.findElement(By.id("gsc-i-id1")).sendKeys("LTI");
		driver.findElement(By.linkText("Downloads")).click();
		
		System.out.println();
		
		Thread.sleep(2000);
		driver.quit();
	}

}
