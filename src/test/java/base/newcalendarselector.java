package base;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class newcalendarselector {
	public WebDriver driver;
	
	public newcalendarselector(WebDriver driver) {
		this.driver = driver;
	}
	public void selectDate(Properties prop) throws InterruptedException {
	Thread.sleep(4000);
	driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form/div[3]/div/div[2]/div/div")).click();
	WebElement deptEle = driver.findElement(By.xpath("(//div[@class='vc-cell ' and @data-date='25'])[2]"));

	WebDriverWait wt = new WebDriverWait(driver,30);
	  wt.until(ExpectedConditions.visibilityOf(deptEle));
	  wt.until(ExpectedConditions.elementToBeClickable(deptEle));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", deptEle);
	  //Thread.sleep(2000);
	  //driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/form/div[3]/div/div[2]/div/div")).click();
		//WebElement deptEle2 = driver.findElement(By.xpath("(//div[@class='vc-cell ' and @data-date='28'])[1]"));
		//WebDriverWait wt2 = new WebDriverWait(driver,30);
		//wt2.until(ExpectedConditions.visibilityOf(deptEle2));
		//wt2.until(ExpectedConditions.elementToBeClickable(deptEle2));
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", deptEle2);
	}
}