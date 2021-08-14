package login;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RunnerLogin extends Base {
	private static String properyFilePath = "src/test/resources/properties/login.property";
	
	public RunnerLogin() {
		super(properyFilePath);
	}

	@Test(dataProvider = "dp")
	public void f(String email, String pwd) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();

		driver.findElement(By.xpath(prop.getProperty("signin"))).click();


		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.findElement(By.xpath(prop.getProperty("emailid"))).sendKeys(email); // Please enter first name using letters
																				// only.
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(pwd);

		driver.findElement(By.xpath(prop.getProperty("loginbutton"))).click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	
	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Login");
		Object data[][] = new Object[rowCount][2];

		for (int i = 1; i < rowCount -1; i++)

		{
			for (int j = 0; j < 2; j++) {
				System.out.println(ex.readData("Login", i, j));
				data[i-1][j] = ex.readData("Login", i, j);
			}
		}

		return data;
	}

}
