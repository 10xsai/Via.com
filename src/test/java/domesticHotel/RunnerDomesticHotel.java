package domesticHotel;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

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
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("selectroomsbtn"))).click();
		
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
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
		Thread.sleep(4000);
		driver.findElement(By.xpath(prop.getProperty("proceed"))).click();
		Thread.sleep(4000);
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