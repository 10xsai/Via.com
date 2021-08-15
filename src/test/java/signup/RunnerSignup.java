package signup;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
import org.testng.annotations.DataProvider;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;


public class RunnerSignup extends Base {
private static String properyFilePath = "src/test/resources/properties/signup.property";
	
	public RunnerSignup() {
		super(properyFilePath);
	}

	
	@Test(dataProvider = "dp")
	public void f(String email, String pwd, String name, String number) {

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