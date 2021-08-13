package signup;

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

public class RunnerSignup extends ExcelFramework {

	public RunnerSignup

	(String pathWithFileName) {
		super(pathWithFileName);
	}

	public WebDriver driver;

	@Test(dataProvider = "dp")
	public void f(String email, String pwd, String name, String number) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/signup.property"));
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

		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		driver.findElement(By.xpath(prop.getProperty("signup"))).click();

//		driver.findElement(By.xpath(prop.getProperty("createaccount"))).click();

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.findElement(By.xpath(prop.getProperty("emailid"))).sendKeys(email); // Please enter first name using letters
																				// only.
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(pwd);
		driver.findElement(By.xpath(prop.getProperty("name"))).sendKeys(name);
		driver.findElement(By.xpath(prop.getProperty("number"))).sendKeys(number);

		driver.findElement(By.xpath(prop.getProperty("createaccountbutton"))).click();

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	
	@DataProvider
	public Object[][] dp() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/signup.property"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		Object data[][] = new Object[6][4];

		for (int i = 1; i < ex.getLastRowNum("Sign up")-1; i++)

		{
			for (int j = 0; j < 4; j++) {
				System.out.println(ex.readData("Sign up", i, j));
				data[i-1][j] = ex.readData("Sign up", i, j);
			}
		}

		return data;
	}

	@BeforeMethod
	public void beforeTest() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/properties/signup.property"));
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

/*
 * Error ID's
 * 
 * create_first_name_error create_last_name_error create_email_error
 * 
 * //h5[normalize-space()='Password must be at least 6 characters.'] //Relative
 * Xpath for error in password
 * 
 * //h5[normalize-space()='Passwords do not match.'] //Confirm password doesnt
 * match
 * 
 */