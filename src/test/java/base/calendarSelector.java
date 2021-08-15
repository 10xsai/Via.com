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
	
	// To declare the web driver
	public WebDriver driver;
	
	// Constructor declaration
	public calendarSelector(WebDriver driver) {
			this.driver = driver;
	}
	
	public void selectDate(String date, Properties prop) throws InterruptedException {
	
	// Departure date
	String[] depDt = date.split("-");
	
	// Departure Month
	String dMonth = depDt[1];
	
	String dDate = depDt[0];
		
	// To click the calender icon on the website
	driver.findElement(By.xpath(prop.getProperty("calendarIcon"))).click();	
	Thread.sleep(4000);

	// Declaring web element
	WebElement deptEle = driver.findElement(By.xpath(prop.getProperty("departure")));

	// Declaring explicit wait
	WebDriverWait wt = new WebDriverWait(driver,30);
	  wt.until(ExpectedConditions.visibilityOf(deptEle));
	  wt.until(ExpectedConditions.elementToBeClickable(deptEle));
	  deptEle.click();
	
	
}
}
