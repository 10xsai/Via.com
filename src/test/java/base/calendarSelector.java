package base;


import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class calendarSelector {
	public WebDriver driver;
	
	public calendarSelector(WebDriver driver) {
		this.driver = driver;
	}
	public void selectDate(int count, Properties prop) throws InterruptedException {
	Thread.sleep(4000);
	
		
	WebElement deptEle = driver.findElement(By.xpath(prop.getProperty("departure")));
	WebDriverWait wt = new WebDriverWait(driver,30);
	  wt.until(ExpectedConditions.visibilityOf(deptEle));
	  wt.until(ExpectedConditions.elementToBeClickable(deptEle));
	  ((JavascriptExecutor)driver).executeScript("arguments[0].click();", deptEle);
	}
}
