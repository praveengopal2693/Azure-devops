package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class ProductDetailsPage extends BaseTest {
	
	public ProductDetailsPage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	
	@FindBy(css = ".size-attribute")
	protected WebElement selectSize;
	
	@FindBy(css = ".add-to-cart")
	protected WebElement addToCart;
	
	@FindBy(css = ".minicart-total")
	protected WebElement miniCart;
	
	@FindBy(css = "[href$='cart']")
	protected WebElement viewCart;
	
}
