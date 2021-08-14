package domesticHotel;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;

import java.beans.Visibility;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RunnerDomesticHotel extends Base {
	private static String properyFilePath = "src/test/resources/properties/DomesticHotel.property";
	
	public RunnerDomesticHotel() {
		super(properyFilePath);
	}
	
	
	@Test(dataProvider = "dp")
	public void hotels(String destination,
			String checkin, 
			String checkout, 
			String rooms,
			String guests, 
			String nationality,
			String residence,
			String title1,
			String firstname1,
			String secondname1,
			String pannumber,
			String title2,
			String firstname2,
			String secondname2,
			String mobile,
			String email
			) throws InterruptedException {
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("selectroomsbtn"))).click();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("bookroombtn"))).click();
		
		Select sel = new Select(driver.findElement(By.xpath(prop.getProperty("title1"))));
		sel.selectByVisibleText("Mr");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("firstname1"))).sendKeys(firstname1);
		driver.findElement(By.xpath(prop.getProperty("secondname1"))).sendKeys(secondname1);
		driver.findElement(By.xpath(prop.getProperty("pannumber"))).sendKeys(pannumber);
		driver.findElement(By.xpath(prop.getProperty("mobile"))).sendKeys(mobile);
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email);
		driver.findElement(By.xpath(prop.getProperty("readterms"))).click(); 
		driver.findElement(By.xpath(prop.getProperty("proceed"))).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id(prop.getProperty("makePayment"))).click();
		
	}

	@DataProvider
	public Object[][] dp() {
		  ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl")); 
		  int rowCount = ex.getLastRowNum("Domestic Hotel Booking"); 
		  Object data[][] = new Object[rowCount-2][16];
		  
		  for (int i = 2; i < rowCount; i++) { 
			  for (int j = 0; j < 16; j++) {
				  data[i-2][j] = ex.readData("Domestic Hotel Booking", i, j); 
				 } 
			  }
		  
		  return data;
		 
	}

}