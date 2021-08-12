package login;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.excelFramework;
import base.Base;
public class LoginData extends Base {
	WebDriver driver;
	  @Test(dataProvider = "dp")
	  public void f(String un, String pass) {
//		  System.setProperty("webdriver.chrome.driver", "D:\\seleniumFiles\\chromedriver_win32\\chromedriver.exe");
//		  driver = new ChromeDriver();
//	      driver.get("https://opensource-demo.orangehrmlive.com/");
//	   
//	        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
////	        PageFactory.initElements(driver, LoginFactory.class).doLogin(un, pass);;
//			driver.findElement(By.name("txtUsername")).sendKeys(un);
//			driver.findElement(By.name("txtPassword")).sendKeys(pass);
//			driver.findElement(By.name("Submit")).click();
//			driver.findElement(By.id("welcome")).click();
//			driver.findElement(By.linkText("Logout")).click();
//			
	  }

	  @DataProvider
	  public Object[][] dp() {
		  Object data[][] = new Object[4][2];
//		  data[0][0] = "admin";
//		  data[0][1] = "admin123";
//		  data[1][0] = "hr";
//		  data[1][1] = "hr123";
//		  data[2][0] = "demo";
//	      data[2][1] = "demo123";
//		  data[3][0] = "system";
//		  data[3][1] ="system123";
			
		  excelFramework ex = new excelFramework(props.getProperty("excelUrl"));
		  System.out.println(props.getProperty("excelUrl"));
		  for(int i=0;i<4;i++) {
			  data[i][0] = ex.readData("Sheet1", i, 0);
			  data[i][1] = ex.readData("Sheet1", i, 1);
		  }
		  return data;
		  
	     }

}
