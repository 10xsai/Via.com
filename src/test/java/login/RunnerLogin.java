package login;

import org.testng.annotations.Test;

import base.Base;
import base.ExcelFramework;
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("wzrk-cancel")).click();

		driver.findElement(By.xpath(prop.getProperty("signin"))).click();


		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.findElement(By.xpath(prop.getProperty("emailid"))).sendKeys(email); // Please enter first name using letters
																				// only.
		driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(pwd);

		driver.findElement(By.xpath(prop.getProperty("loginbutton"))).click();

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