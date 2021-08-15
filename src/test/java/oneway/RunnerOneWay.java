package oneway;

import org.testng.annotations.Test;
import base.Base;
import base.ExcelFramework;
import base.calendarSelector;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
public class RunnerOneWay extends Base {

	private static String properyFilePath = "src/test/resources/properties/oneway.property";

	public RunnerOneWay() {
		super(properyFilePath);
	}
	
	
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
			String email) throws InterruptedException {

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("from"))).sendKeys(from); 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.className("ui-menu-item")).click();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.xpath(prop.getProperty("to"))).sendKeys(to); 
		Thread.sleep(2000);
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).perform();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		calendarSelector cs = new calendarSelector(driver);
		cs.selectDate(departureDate,prop); 
		
		driver.findElement(By.xpath(prop.getProperty("searchFlights"))).click();
		
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
