package login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;
	
	By e_un = By.name("txtUsername");
	By e_pwd = By.name("txtPassword");
	By e_login = By.id("btnLogin");
	By e_forget = By.linkText("Forgot your password?");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private void setUsername(String name) {
		driver.findElement(e_un).sendKeys(name);
	}
	
	private void setPwd(String pwd) {
		driver.findElement(e_pwd).sendKeys(pwd);
	}
	
	public void clicklogin() {
		driver.findElement(e_login).click();
	}
	
	public void clickForget() {
		driver.findElement(e_forget).click();
	}
	
	public void doLogin(String name, String pwd) {
		setUsername(name);
		setPwd(pwd);
		clicklogin();
	}
	
}
