package roundtrip;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.ExcelFramework;
import base.Base;
import base.calendarSelector;
import base.newcalendarselector;

import org.testng.annotations.DataProvider;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
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
			String departure, 
			String returnDate,
			String adults, 
			String children, 
			String infants, 
			String title, 
			String firstname, 
			String lastname, 
			String mobile, 
			String email) throws InterruptedException {
		
		ExtentReports ex = new ExtentReports(); // To Specify we are working with extent report
		ex.attachReporter(new ExtentHtmlReporter("RoundTrip.html"));
		ExtentTest tc=ex.createTest("RoundTrip");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
			
		//driver.findElement(By.id("wzrk-cancel")).click(); 
		
		
    	driver.findElement(By.xpath(prop.getProperty("clickrt"))).click(); 
    	tc.pass("Round Trip Tab Clicked Successfully");
	    Thread.sleep(2000);
		
			
		WebElement From = driver.findElement(By.xpath(prop.getProperty("from")));
		From.sendKeys(from);   // Please enter first name using letters
		Assert.assertEquals(from,"Mumbai");
		tc.pass("Source Entered Successfully");
		
		driver.findElement(By.className("ui-menu-item")).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("to"))).sendKeys(to); // Please enter first name using letters
		Assert.assertEquals(to,"Chennai");
		tc.pass("Destination Entered Successfully");
		Thread.sleep(2000);
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		
		newcalendarselector cs = new newcalendarselector(driver);
		cs.selectDate(prop); 
		tc.pass("Departure and Return Date Selected Successfully");
		driver.findElement(By.xpath(prop.getProperty("searchFlights"))).click();
		tc.pass("Search Flight Button Clicked Sucessfully");
		Thread.sleep(2000);
					
//		driver.findElement(By.xpath(prop.getProperty("adults"))).click();
//		driver.findElement(By.xpath(prop.getProperty("children"))).click();
//		driver.findElement(By.xpath(prop.getProperty("infants"))).click();
		
		// Selecting Flights
		Thread.sleep(10000);
		
		
		WebElement searchFlightSuccessfull = driver.findElement(By.xpath(prop.getProperty("searchFlightSuccessfull")));
		Assert.assertTrue(searchFlightSuccessfull.isDisplayed());
		tc.pass("Loaded Flights Successfully");
		
		WebDriverWait wt=new WebDriverWait(driver, 20);
       // wt.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[@class='clear-icon']"))));
 
		
		//driver.manage().timeouts().implicitlyWait(80, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("flight1"))).click();
		tc.pass("Sucessfully Clicked");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("flight2"))).click();
		tc.pass("Sucessfully Clicked");
		
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("bookflight"))).click();
		tc.pass("Sucessfully Clicked");
		WebElement travellerDetails = driver.findElement(By.xpath(prop.getProperty("travellerDetails")));
		Assert.assertTrue(travellerDetails.isDisplayed());
		tc.pass("Navigated to Details Page");
		Thread.sleep(2000);
		
		WebElement E = driver.findElement(By.xpath(prop.getProperty("title")));
		Select Port= new Select(E);
		Port.selectByValue(title);
		Assert.assertEquals(title, "Mr");
		tc.pass("Successfully Selected");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("firstname"))).sendKeys(firstname);
		Assert.assertEquals(firstname, "Anirudh");
		tc.pass("Successfully Typed First Name");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("lastname"))).sendKeys(lastname);
		Assert.assertEquals(lastname, "P K");
		tc.pass("Successfully Typed Last Name");		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("mobile"))).sendKeys(mobile);
		Assert.assertEquals(mobile, "9436452699");
		tc.pass("Successfully Typed Mobile Number");		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath(prop.getProperty("email"))).sendKeys(email);
		Assert.assertEquals(email, "anirudhh@gmail.com");
		tc.pass("Successfully Typed Email");
		Thread.sleep(2000);
		
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);		
		driver.findElement(By.xpath(prop.getProperty("ssrcb"))).click();
		//Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("mp"))).click();
		//Thread.sleep(2000);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("ptb"))).click();
		WebElement cardDetails = driver.findElement(By.xpath(prop.getProperty("cardDetails")));
		Assert.assertTrue(cardDetails.isDisplayed());
		tc.pass("Payment Page Successfully Reached");
		Thread.sleep(2000);		
		
		ex.flush();
		
		
		
	}

	
	//private WebElement until(ExpectedCondition<WebElement> elementToBeClickable) {
		// TODO Auto-generated method stub
	//	return null;
	//}


	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Round trip");
		Object data[][] = new Object[rowCount][12];
		//System.out.println(rowCount);

		for (int i = 1; i <= rowCount; i++)

		{
			for (int j = 0; j < 12; j++) {
				System.out.println(ex.readData("Round trip", i, j));
				data[i-1][j] = ex.readData("Round trip", i, j);
			}
		}

		return data;
	}
}
