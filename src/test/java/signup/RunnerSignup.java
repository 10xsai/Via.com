package signup;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class RunnerSignup extends Base {
private static String properyFilePath = "src/test/resources/properties/signup.property";
	
	public RunnerSignup() {
		super(properyFilePath);
	}
	public void checkBlank (WebElement ele){
		if(ele.getText()==""){
			Assert.assertEquals("Email is blank","Email should not be blank");
			}
	}
	

	@Test(dataProvider = "dp")
	public void signup(String email, String pwd, String name, String number) throws Exception{
		
		
		// To Specify we are working with extent report 
		ExtentReports ex = new ExtentReports(); 
		ex.attachReporter(new  ExtentHtmlReporter("Signup.html")); 
		ExtentTest tc=ex.createTest("SignupTest");
		tc.pass("Cancelling POP-UP");
		 
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();

		// To click on the signin button
		driver.findElement(By.xpath(prop.getProperty("signin"))).click();

		// To click on the signup button
		driver.findElement(By.xpath(prop.getProperty("signup"))).click();

		// Implicit wait declaration
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		// To insert the value of email id from excel sheet
		WebElement Email= driver.findElement(By.xpath(prop.getProperty("emailid")));
		Email.sendKeys(email);
		Assert.assertTrue(email.contains("@"), "Email id should contain @");
		checkBlank(Email);
			
		tc.pass("Checking if email is valid");
			
		// To insert the value of password from excel sheet
		WebElement Password=driver.findElement(By.xpath(prop.getProperty("password")));
		Password.sendKeys(pwd);
		checkBlank(Password);
		tc.pass("Checking if password is valid");
		
		// To insert the value of name from excel sheet
		WebElement Name=driver.findElement(By.xpath(prop.getProperty("name")));
		Name.sendKeys(name);
		checkBlank(Name);
		tc.pass("Checking if name is valid");
		
		// To insert the value of number from excel sheet
		WebElement Number=driver.findElement(By.xpath(prop.getProperty("number")));
		Number.sendKeys(number);
		checkBlank(Number);
		tc.pass("Checking if number is valid");

		// To click on create account button
		driver.findElement(By.xpath(prop.getProperty("createaccountbutton"))).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		System.out.println(driver.getTitle());

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(3000);
		
		ex.flush();
	}

	// To provide data from excel sheet
	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Sign up");
		Object data[][] = new Object[rowCount][4];

		for (int i = 1; i <= rowCount; i++)

		{
			for (int j = 0; j < 4; j++) {
				System.out.println(ex.readData("Sign up", i, j));
				data[i-1][j] = ex.readData("Sign up", i, j);
			}
		}

		return data;
	}

}