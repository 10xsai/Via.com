package oneway;
import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class RunnerOneWay2 extends Base {

	private static String properyFilePath = "src/test/resources/properties/oneway.property";

	public RunnerOneWay2() {
	super(properyFilePath);
	}


	@Test(dataProvider = "dp")
	public void bookFlights(
			String title,
			String firstname,
			String lastname,
			String mobile,
			String email) throws InterruptedException {
		
		
		WebDriverWait wt=new WebDriverWait(driver,400);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url1"));
		driver.findElement(By.xpath(prop.getProperty("book"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement E = driver.findElement(By.id(prop.getProperty("title")));
		Select Port = new Select (E); 
		Port.selectByValue("Miss");
		driver.findElement(By.id(prop.getProperty("FirstName"))).sendKeys(firstname);
		driver.findElement(By.id(prop.getProperty("LastName"))).sendKeys(lastname);
		driver.findElement(By.id(prop.getProperty("Mobile"))).sendKeys(mobile);
		driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(email);
		driver.findElement(By.id(prop.getProperty("ssrcb"))).click(); 
		wt.until(ExpectedConditions.elementToBeClickable(By.id(prop.getProperty("PTB")))); 
		driver.findElement(By.id(prop.getProperty("PTB"))).click();
		
		}
		

	
	@DataProvider
	public Object[][] dp() {
		
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		Object data[][] = new Object[1][5];
		
			for (int j = 6; j <11; j++) {
				System.out.println(ex.readData("One way trip", 1, j));
				data[0][j-6] = ex.readData("One way trip", 1, j);
			}
		
		return data;
	}

}
