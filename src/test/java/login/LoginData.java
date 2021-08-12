package login;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	  }

	  @DataProvider
	  public Object[][] dp() throws FileNotFoundException, IOException {
		  Object data[][] = new Object[4][2];
		  Properties props = new Properties();
		  props.load(new FileInputStream("src/test/resources/settings.property"));
		  System.out.println(props.getProperty("excelUrl"));
		  excelFramework ex = new excelFramework(props.getProperty("excelUrl"));
		  for(int i=0;i<4;i++) {
			  data[i][0] = ex.readData("Login", i, 0);
			  data[i][1] = ex.readData("Login", i, 1);
		  }
		  return data;
		  
	     }

}
