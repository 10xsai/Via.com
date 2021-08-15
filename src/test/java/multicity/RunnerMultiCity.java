package multicity;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RunnerMultiCity extends Base {
	private static String properyFilePath = "src/test/resources/properties/multicity.property";
	private static String data[][];
	
	public RunnerMultiCity() {
		super(properyFilePath);
	}
	
	public void selectFlight(String[] passengerData) throws InterruptedException {
		String from=passengerData[0];
		String to=passengerData[1];
		String departureDate=passengerData[2];
		String adult=passengerData[3];
		String infant=passengerData[4];
		String children=passengerData[5];
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("from"))).sendKeys(from); // Please enter first name using letters
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.className("ui-menu-item")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("to"))).sendKeys(to); // Please enter first name using letters
		Thread.sleep(2000);
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("searchFlight"))).click();
	}
	
	public void enterPassengerDetails(String passengerData[]) throws Exception{
		String title=passengerData[6];
		String firstname=passengerData[7]; 
		String lastname=passengerData[8];
		String mobile=passengerData[9];
		String email=passengerData[10];
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("flight1"))).click();
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("flight2"))).click();
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("bookFlight"))).click();
		
//		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		WebElement E = driver.findElement(By.xpath(prop.getProperty("title")));
        Select Port= new Select(E);
        Port.selectByValue(title);
		
		driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(firstname);
		
		driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(lastname);
		
		driver.findElement(By.xpath(prop.getProperty("mobile"))).sendKeys(mobile);
		
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("checkbox"))).click();
		
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("proceed"))).click();
		}
		
		
	@Test()
	public void f() throws Exception{
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		driver.findElement(By.id("wzrk-cancel")).click();//close the notifications popup
//
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath(prop.getProperty("multicitybtn"))).click();//going to multicity section
//		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//		
//		driver.findElement(By.xpath(prop.getProperty("flight1btn"))).click();//clicking on flight1
//		
		
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Multi city");
		data = new String[rowCount-1][11];
		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < 11; j++) {
				data[i-1][j] = ex.readData("Multi city", i, j);
			}
		}
		
		String[] stringArray;
		for(int i=0;i<4;i++) {
			stringArray = data[i];
			selectFlight(stringArray);
			enterPassengerDetails(stringArray);
		}
	}
}
