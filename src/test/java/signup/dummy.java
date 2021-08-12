package signup;

import org.testng.annotations.Test;
import signup.ReadSignUpExcelFile;
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

public class dummy extends ReadSignUpExcelFile {

	public dummy

	(String pathWithFileName) {
		super(pathWithFileName);
	}

	public WebDriver driver;

	@Test(dataProvider = "dp")
	public void f(String email, String pwd, String name, String number) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("src/test/resources/signup.property"));
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

		driver.findElement(By.xpath(prop.getProperty("createaccount"))).click();

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		driver.findElement(By.id(prop.getProperty("emailid"))).sendKeys(email); // Please enter first name using letters
																				// only.
		driver.findElement(By.id(prop.getProperty("passwordid"))).sendKeys(pwd);
		driver.findElement(By.id(prop.getProperty("name"))).sendKeys(name);
		driver.findElement(By.id(prop.getProperty("number")));

		driver.findElement(By.id(prop.getProperty("createaccountbuttonid"))).click();

		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		
		  if(driver.findElement(By.id("create_first_name_error")).isDisplayed()) {
		  System.out.println(driver.findElement(By.id("create_first_name_error")).
		  getText()); }
		  
		  else if(driver.findElement(By.id("create_last_name_error")).isDisplayed()) {
		  System.out.println(driver.findElement(By.id("create_last_name_error")).
		  getText()); }
		  
		  else if(driver.findElement(By.id("create_email_error")).isDisplayed()) {
		  System.out.println(driver.findElement(By.id("create_email_error")).getText())
		  ; }
		  
		  
		  else if(driver.findElement(By.
		  xpath("//h5[normalize-space()='Password must be at least 6 characters.']")).
		  isDisplayed()) { System.out.println(driver.findElement(By.
		  xpath("//h5[normalize-space()='Password must be at least 6 characters.']")).
		  getText()); }
		  
		  else if(driver.findElement(By.
		  xpath("//input[@id='passwordSignUp']='Passwords do not match.']")).isDisplayed()) {
		  System.out.println(driver.findElement(By.
		  xpath("//input[@id='passwordSignUp']='Passwords do not match.']")).getText()); }
		 
	}

	
	@DataProvider
	public Object[][] dp() {
		ReadSignUpExcelFile ex = new ReadSignUpExcelFile("C://Users//sony vaio//Desktop//Signup.xlsx");
		Object data[][] = new Object[4][4];

		for (int i = 1; i < 2; i++)

		{
			for (int j = 0; j < 4; j++) {
				System.out.println(ex.readData("Sheet1", i, j));
				data[i][j] = ex.readData("Sheet1", i, j);
			}
		}

		return data;
	}

	@BeforeMethod
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\sony vaio\\Downloads\\Selenium\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterMethod
	public void afterTest() {

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