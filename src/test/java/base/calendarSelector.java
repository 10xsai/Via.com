package base;


import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;


public class calendarSelector {
	public WebDriver driver;
	
	public calendarSelector(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectDate(String date, Properties prop) throws InterruptedException {

	String[] depDt = date.split("-");
	String dMonth = depDt[1];
	
	String dDate = depDt[0];
	driver.findElement(By.xpath(prop.getProperty("calendarIcon"))).click();	
	Thread.sleep(4000);

	WebElement deptEle = driver.findElement(By.xpath(prop.getProperty("departure")));

	WebDriverWait wt = new WebDriverWait(driver,30);
	  wt.until(ExpectedConditions.visibilityOf(deptEle));
	  wt.until(ExpectedConditions.elementToBeClickable(deptEle));
	  deptEle.click();
	
	
}
}
