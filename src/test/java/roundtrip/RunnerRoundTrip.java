package roundtrip;

import org.testng.annotations.Test;
import base.ExcelFramework;
import base.Base;
import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class RunnerRoundTrip extends Base{

	private static String properyFilePath = "src/test/resources/properties/roundtrip.property";
	
	public RunnerRoundTrip() {
		super(properyFilePath);
	}
	

	@Test(dataProvider = "dp")
	public void searchFlights(
			String from, 
			String to, 
			String departureDate, 
			String returnDate,
			String adults, 
			String children, 
			String infants, 
			String title, 
			String firstname, 
			String lastname, 
			String mobile, 
			String email) throws InterruptedException {
		
/*		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();

		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);		
		driver.findElement(By.xpath(prop.getProperty("clickrt"))).click(); 
		

		driver.findElement(By.xpath(prop.getProperty("from"))).sendKeys(from); // Please enter first name using letters
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.className("ui-menu-item")).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("to"))).sendKeys(to); // Please enter first name using letters
		Thread.sleep(2000);
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		Thread.sleep(2000);
		
		departureSelector ds = new departureSelector(driver);
		ds.selectDate(departureDate,prop);
		Thread.sleep(2000);
		
		*/
		
		
	//	driver.findElement(By.xpath("//*[@id=\"round-trip-panel\"]/div[5]/div[2]/div")).click();
	//	driver.findElement(By.xpath("//*[@id=\"return\"]")).click();
		
		
	//	returnSelector rs = new returnSelector(driver);
	//	rs.selectDate(returnDate,prop);
	//	Thread.sleep(2000);
	
		
	//	departureSelector cs2 = new departureSelector(driver);
	//	cs2.selectDate(returnDate, prop);
		
		//driver.findElement(By.className("ui-menu-item")).click();
		
//		driver.findElement(By.xpath(prop.getProperty("adults"))).click();
//		driver.findElement(By.xpath(prop.getProperty("children"))).click();
//		driver.findElement(By.xpath(prop.getProperty("infants"))).click();
		
//		driver.findElement(By.xpath(prop.getProperty("searchFlights"))).click();
//		Thread.sleep(2000);
		/*
		driver.findElement(By.xpath(prop.getProperty("depIcon"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("departureDate"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("retIcon"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("returnDate"))).click();
		Thread.sleep(2000);
		*/
	/*	driver.findElement(By.xpath(prop.getProperty("depIcon"))).click();
		Thread.sleep(4000); WebElement deptEle = driver.findElement(By.xpath(prop.getProperty("departureDate")));

		 WebDriverWait wt = new WebDriverWait(driver,30);
		wt.until(ExpectedConditions.visibilityOf(deptEle));
		wt.until(ExpectedConditions.elementToBeClickable(deptEle));
		deptEle.click();
		
		 driver.findElement(By.xpath(prop.getProperty("adults"))).click();
		 driver.findElement(By.xpath(prop.getProperty("children"))).click();
		 driver.findElement(By.xpath(prop.getProperty("infants"))).click();
			
		*/
	//	driver.findElement(By.xpath(prop.getProperty("searchFlights"))).click();
		
		//Selecting the Two-Way-Flight 
		
	//	driver.get(prop.getProperty("url"));
	//	Thread.sleep(4000);
			
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("flight1"))).click();
	//	Thread.sleep(2000);
	
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("flight2"))).click();
		//Thread.sleep(2000);
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("bookflight"))).click();
	//	Thread.sleep(16000);
	    
	
		//Filling Details of the Passenger
		driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		
		WebElement E = driver.findElement(By.xpath(prop.getProperty("title")));
		Select Port= new Select(E);
		Port.selectByValue(title);
		
		driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(firstname);
		
		
		driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(lastname);
		
		
		driver.findElement(By.xpath(prop.getProperty("mobile"))).sendKeys(mobile);
		
		
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email);
		
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("ssrcb"))).click();
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("mp"))).click();		
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("ptb"))).click();
		
		
		
	}

	
	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Round trip");
		Object data[][] = new Object[rowCount-1][12];
		System.out.println(rowCount);

		for (int i = 1; i < rowCount; i++)

		{
			for (int j = 0; j < 12; j++) {
				System.out.println(ex.readData("Round trip", i, j));
				data[i-1][j] = ex.readData("Round trip", i, j);
			}
		}

		return data;
	}
}
