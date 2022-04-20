package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class ProductListPage extends BaseTest {
	
	public ProductListPage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	
	@FindBy(css = "[for='City']")
	protected WebElement activityFilter;
	
	@FindBy(css = ".product .pdp-link a")
	protected WebElement firstProduct;
	
	@FindBy(css = ".filter-results")
	protected WebElement filterButton;
	
	@FindBy(css = ".filter")
	protected WebElement filterButtonInside;
}
