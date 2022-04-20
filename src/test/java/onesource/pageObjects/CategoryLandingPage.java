package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class CategoryLandingPage extends BaseTest {
	
	public CategoryLandingPage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	//jackets
	@FindBy(css = ".category-tile  a[href$='jackets']")
	protected WebElement tops;

	// SH+VA
	// login -> Sign-In
	// SH - Pass
	// VA - login/Sign-In - Fail
}
