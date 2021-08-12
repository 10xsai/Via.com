/*
 * package signup; import org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.support.ui.ExpectedConditions; import
 * org.openqa.selenium.support.ui.Select; import org.openqa.selenium.Alert;
 * import org.openqa.selenium.By; import org.openqa.selenium.WebElement; import
 * org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.support.ui.WebDriverWait; import
 * org.testng.annotations.Test;
 * 
 * import base.Base;
 * 
 * import java.util.List; import java.util.concurrent.*;
 * 
 * 
 * public class signUp extends Base {
 * 
 * WebDriver driver;
 * 
 * @Test(dataProvider="dp")
 * 
 * 
 * 
 * 
 * 
 * 
 * driver.findElement(By.className("elementPad menuLabel  secNavIcon")).click();
 * 
 * 
 * 
 * 
 * } }
 * 
 * 
 * By e_email= By.name("emailIdSignUp"); By e_pwd = By.name("passwordSignUp");
 * By e_name = By.name("nameSignUp"); By e_number = By.name("mobileNoSignUp");
 * 
 * public signUp(WebDriver driver) { this.driver = driver; }
 * 
 * private void setEmail(String email) {
 * driver.findElement(By.id("loginIdText").sendKeys("email"); }
 * 
 * private void setPwd(String pwd) { driver.findElement(e_pwd).sendKeys(pwd); }
 * 
 * public void clicklogin() { driver.findElement(e_login).click(); }
 * 
 * public void clickForget() { driver.findElement(e_forget).click(); }
 * 
 * public void doLogin(String name, String pwd) { setUsername(name);
 * setPwd(pwd); clicklogin(); }
 * 
 * }
 */