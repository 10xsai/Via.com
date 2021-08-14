package oneway;
import org.testng.annotations.Test;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class RunnerOneWay2 extends ExcelFramework {

	public RunnerOneWay2(String pathWithFileName) {
		super(pathWithFileName);
	}

	public WebDriver driver;

	@Test(dataProvider = "dp")
	public void bookFlights(
			String title,
			String firstname,
			String lastname,
			String mobile,
			String email) throws InterruptedException {
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/oneway.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/oneway.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		Object data[][] = new Object[1][5];
		
			for (int j = 6; j <11; j++) {
				System.out.println(ex.readData("One way trip", 1, j));
				data[0][j-6] = ex.readData("One way trip", 1, j);
			}
		
		return data;
	}

	@BeforeMethod
	public void beforeTest() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/oneway.property"));
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
		 //driver.close();
	}

}

