package multicity;

import org.testng.annotations.Test;
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

public class RunnerMultiCity extends ExcelFramework {

	public RunnerMultiCity(String pathWithFileName) {
		super(pathWithFileName);
	}

	public WebDriver driver;

	@Test(dataProvider = "dp")
	public void f(
			String from, 
			String to, 
			String departureDate, 
			String adult, 
			String infant, 
			String children, 
			String title, 
			String firstname, 
			String lastname, 
			String mobile, 
			String email) {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/multicity.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();


		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("multicitybtn"))).click();
		
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath(prop.getProperty("from"))).sendKeys(email); // Please enter first name using letters
																				// only.
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(firstname);

		driver.findElement(By.xpath(prop.getProperty("loginbutton"))).click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}

	
	@DataProvider
	public Object[][] dp() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/multicity.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Multi city");
		Object data[][] = new Object[rowCount][11];

		for (int i = 1; i < rowCount -1; i++)

		{
			for (int j = 0; j < 11; j++) {
				System.out.println(ex.readData("Multi city", i, j));
				data[i-1][j] = ex.readData("Multi city", i, j);
			}
		}

		return data;
	}

	@BeforeMethod
	public void beforeTest() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/multicity.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", prop.getProperty("cpath"));
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterTest() throws Exception {
		Thread.sleep(2000);
		 driver.close();
	}

}
