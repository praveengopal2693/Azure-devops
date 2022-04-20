package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class LoginPage extends BaseTest {
	
	public LoginPage(WebDriver e_driver) {
		this.driver = e_driver;
	}

	@FindBy(id = "login-form-email")
	protected WebElement email;
	
	@FindBy(id = "login-form-password")
	protected WebElement password;
	
	@FindBy(css = ".btn-block.btn-primary")
	protected WebElement logInButton;
	
}
