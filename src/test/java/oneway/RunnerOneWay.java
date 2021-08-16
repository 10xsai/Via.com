package oneway;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.Base;
import base.ExcelFramework;
import base.calendarSelector;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
public class RunnerOneWay extends Base {

	private static String properyFilePath = "src/test/resources/properties/oneway.property";

	public RunnerOneWay() {
		super(properyFilePath);
	}
	int c=0;
	@Test(dataProvider = "dp")
	public void searchFlights(
			String from, 
			String to, 
			String departureDate, 
			String adults, 
			String children, 
			String infants,
			String title,
			String firstname,
			String lastname,
			String mobile,
			String email) throws Exception {
		ExtentReports ex = new ExtentReports(); // To Specify we are working with extent report 
		ex.attachReporter(new ExtentHtmlReporter("onewaypositive.html"));
		ExtentTest tc=ex.createTest("OneWaypositiveTest"); 
		//ExtentReports ex2 = new ExtentReports(); // To Specify we are working with extent report 
		//ex2.attachReporter(new ExtentHtmlReporter("onewaynegative.html"));
		//ExtentTest tc2=ex2.createTest("OneWayNegetiveTest");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();
		tc.pass("Closed POP-UP");
		driver.findElement(By.xpath(prop.getProperty("from"))).sendKeys(from);
		tc.pass("Entered From Location");
		driver.findElement(By.className("ui-menu-item")).click();
		driver.findElement(By.xpath(prop.getProperty("to"))).sendKeys(to); 
		tc.pass("Entered To Location");
		Thread.sleep(2000);
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		//try {
		try {
		calendarSelector cs = new calendarSelector(driver);
		cs.selectDate(c,prop);
		c++;
		//}
		//catch (Exception e)
		//{
			//ExtentTest tc=ex.createTest("OneWayFlightNegativeTest");
			//tc2.fail("Invalid Location---"+"From Location "+from+" To Location "+to);
			//ex2.flush();
			//Thread.sleep(3000);
			//driver.quit();
		//}
		Thread.sleep(2000);
		driver.findElement(By.xpath(prop.getProperty("searchflights"))).click();
			driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
			driver.findElement(By.xpath(prop.getProperty("book"))).click();
			WebElement E = driver.findElement(By.id(prop.getProperty("title")));
			Select Port = new Select (E); 
			Port.selectByValue("Miss");
			Assert.assertEquals(title, "Miss");
			tc.pass("Checked Title");
			driver.findElement(By.id(prop.getProperty("FirstName"))).sendKeys(firstname);
			Assert.assertEquals(firstname, "Abc");
			tc.pass("Checked First Name");
			driver.findElement(By.id(prop.getProperty("LastName"))).sendKeys(lastname);
			Assert.assertEquals(lastname, "Xyz");
			tc.pass("Checked Last Name");
			driver.findElement(By.id(prop.getProperty("Mobile"))).sendKeys(mobile);
			Assert.assertEquals(mobile, "9436452699");
			tc.pass("Checked Mobile");
			driver.findElement(By.xpath(prop.getProperty("Email"))).sendKeys(email);
			Assert.assertTrue(email.contains("@"), "Email id contains '@' ");
			tc.pass("Test Email");
			//driver.findElement(By.xpath(prop.getProperty("ssrcb"))).click();
			tc.pass("checkbox clicked");
			ex.flush();
		}
		catch (Exception e)
		{
			
		}
		
	}
	
	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		Object data[][] = new Object[3][11];
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j <11; j++) {
				System.out.println(ex.readData("One way trip", i+1, j));
				data[i][j] = ex.readData("One way trip", i+1, j);
			}
		}
		return data;
	}
	
}