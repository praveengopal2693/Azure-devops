package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class HomePage extends BaseTest {
	
	protected HomePage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	
	@FindBy(css = ".user-message")
	protected WebElement signIn;
	
	@FindBy(css = ".nav-item [href$='login']")
	protected WebElement signInMobile;
	
	@FindBy(id = "men")
	protected WebElement men;
	
	@FindBy(css = ".consent-tracking_close")
	protected WebElement cookieClose;
	
	@FindBy(css = ".navbar-toggler")
	protected WebElement mobileMenu;

}
