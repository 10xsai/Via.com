package login;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import base.Base;
import base.ExcelFramework;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;

public class RunnerLogin extends Base {
	private static String properyFilePath = "src/test/resources/properties/login.property";
	
	public RunnerLogin() {
		super(properyFilePath);
	}

	@Test(dataProvider = "dp")
	public void f(String email, String pwd) {
		
		ExtentReports ex = new ExtentReports(); // To Specify we are working with extent report
		ex.attachReporter(new ExtentHtmlReporter("Login.html"));
		ExtentTest tc=ex.createTest("LoginTest");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();
		driver.findElement(By.xpath(prop.getProperty("signin"))).click();
		tc.pass("Cancelling popup");

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath(prop.getProperty("emailid"))).sendKeys(email); 
		Assert.assertTrue(email.contains("@"), "Email id should contain '@'");
		Assert.assertEquals(email,"mirtaimur7@gmail.com");
		tc.pass("Checking Email field");

		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(pwd);
		Assert.assertEquals(pwd, "taimur123");
		tc.pass("Checking Password Field");

		driver.findElement(By.xpath(prop.getProperty("loginbutton"))).click();
		String hi = driver.findElement(By.xpath(prop.getProperty("loginSuccess"))).getText();
		Assert.assertEquals(hi,"Hi Mir");
		tc.pass("Checking Login button");
		ex.flush();
	}

	
	@DataProvider
	public Object[][] dp() {
		ExcelFramework ex = new ExcelFramework(prop.getProperty("excelUrl"));
		int rowCount = ex.getLastRowNum("Login");
		Object data[][] = new Object[rowCount][2];

		for (int i = 1; i <= rowCount; i++)

		{
			for (int j = 0; j < 2; j++) {
				System.out.println(ex.readData("Login", i, j));
				data[i-1][j] = ex.readData("Login", i, j);
			}
		}

		return data;
	}

}