package internationalHotel;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.Base;
import base.ExcelFramework;
import base.newcalendarselector;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunnerInternationalHotels extends Base {
	private static String properyFilePath = "src/test/resources/properties/InternationalHotels.property";
	
	public RunnerInternationalHotels() {
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
			String passportInfo,
			String passportNumber,
			String expiryDay,
			String expiryMonth,
			String expiryYear,
			String title2,
			String firstname2,
			String secondname2,
			String passportInfo2,
			String passportNumber2,
			String expiryDay2,
			String expiryMonth2,
			String expiryYear2,
			String mobile,
			String email
			) throws InterruptedException {
		
		ExtentReports ex1 = new ExtentReports(); // To Specify we are working with extent report
		ex1.attachReporter(new ExtentHtmlReporter("InternationalHotelPositive.html"));
		ExtentTest tc1 = ex1.createTest("InternationalHotelPositive");

		tc1.info("Entering hotel details");
		// Entering hotel details
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement fromElem = driver.findElement(By.id("destination"));
		fromElem.sendKeys(destination);
		driver.findElement(By.className("ui-menu-item")).click();
		tc1.pass("Entered destination " + destination);
		
		newcalendarselector cs = new newcalendarselector(driver);
		tc1.pass("Selected Date");
		
		cs.selectDate(prop); 
		driver.findElement(By.xpath(prop.getProperty("searchHotel"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@placeholder='Search By Hotel Name']"))));
		tc1.pass("Successfully entered hotel details");
		
		tc1.info("Selecting hotel Room");
		// Select Rooms
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("selectroomsbtn"))).click();
		tc1.pass("Selected Room");
		
//		boolean hotelSelected = driver.findElement(By.xpath(prop.getProperty("hotelSelected"))).isDisplayed();
//		Assert.assertEquals(hotelSelected,"Guest Details");
//		tc.pass("Sucessfully selected a hotel");
		
		// Book Room
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("bookroombtn"))).click();
		tc1.pass("Successfully selected hotel room");
//		tc.pass("Successfully selected hotel room");
//		
//		tc.info("Entering passenger details");
		// Enter Adult1 details
		tc1.info("Entering passenger details");
		Select sel1 = new Select(driver.findElement(By.xpath(prop.getProperty("title1"))));
		sel1.selectByVisibleText(title1);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		tc1.pass("Selected title 1");
		driver.findElement(By.xpath(prop.getProperty("firstname1"))).sendKeys(firstname1);
		tc1.pass("Entered firstname");
		driver.findElement(By.xpath(prop.getProperty("secondname1"))).sendKeys(secondname1);
		tc1.pass("Entered lastname");
		driver.findElement(By.xpath(prop.getProperty("pannumber"))).sendKeys(pannumber);
		tc1.pass("Entered pannumber");
	    driver.findElement(By.xpath(prop.getProperty("passportInfo1"))).sendKeys(passportInfo);
		driver.findElement(By.xpath(prop.getProperty("passportNumber1"))).sendKeys(passportNumber);
		WebDriverWait waits = new WebDriverWait(driver, 30);
		waits.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(prop.getProperty("expiryDay1")))));
		Thread.sleep(8000);
		Select expday1 = new Select(driver.findElement(By.id(prop.getProperty("expiryDay1"))));
		expday1.selectByValue("26");
		Select expmonth1 = new Select(driver.findElement(By.id(prop.getProperty("expiryMonth1"))));
		expmonth1.selectByValue("08");
		Select expyear1 = new Select(driver.findElement(By.id(prop.getProperty("expiryYear1"))));
		expyear1.selectByValue("2025");
		
		Boolean bool = false;
		try {
			bool = driver.findElement(By.xpath(prop.getProperty("title2"))).isDisplayed();			
			tc1.info("Entering adult 2 details");
			Select sel2 = new Select(driver.findElement(By.xpath(prop.getProperty("title2"))));
			sel2.selectByVisibleText(title2);
			tc1.pass("Selected title 2");
			System.out.println("Printing line");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			driver.findElement(By.xpath(prop.getProperty("firstname2"))).sendKeys(firstname2);
			tc1.pass("Entered firstname 2");
			driver.findElement(By.xpath(prop.getProperty("secondname2"))).sendKeys(secondname2);
			tc1.pass("Entered secondname 2");
			driver.findElement(By.xpath(prop.getProperty("passportInfo2"))).sendKeys(passportInfo2);
			Thread.sleep(8000);
			driver.findElement(By.xpath(prop.getProperty("passportNumber2"))).sendKeys(passportNumber2);
			Select expday2 = new Select(driver.findElement(By.xpath(prop.getProperty("expiryDay2"))));
			expday2.selectByValue("18");
			Select expmonth2 = new Select(driver.findElement(By.xpath(prop.getProperty("expiryMonth2"))));
			expmonth2.selectByValue("11");
			Select expyear2 = new Select(driver.findElement(By.xpath(prop.getProperty("expiryYear2"))));
			expyear2.selectByValue("2027");
		}
		catch (Exception e) {
//			Assert.assertTrue(bool, "Cannot enter second adult details");
		}
		finally {
			tc1.info("Enter other details");
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("mobile"))).sendKeys(mobile);
			tc1.pass("Entered mobile number");
			driver.findElement(By.id(prop.getProperty("email"))).sendKeys(email);
			tc1.pass("Entered Email");
			driver.findElement(By.id(prop.getProperty("readterms"))).click();
			Thread.sleep(2000);
			driver.findElement(By.id(prop.getProperty("proceed"))).click();
			tc1.pass("Clicked Proceed");
		}
		
		tc1.info("Proceed to Make payment");
		Thread.sleep(2000);		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.id(prop.getProperty("makePayment"))).click();
		Thread.sleep(2000);
		ex1.flush();
		Thread.sleep(2000);
	}

	@DataProvider
	public Object[][] dp() {
		  ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl")); 
		  int rowCount = ex.getLastRowNum("International Hotel Booking"); 
		  Object data[][] = new Object[rowCount-2][26];
		  
		  for (int i = 2; i < rowCount; i++) { 
			  for (int j = 0; j < 26; j++) {
				  data[i-2][j] = ex.readData("International Hotel Booking", i, j); 
				 } 
			  }
		  return data;
	}
}