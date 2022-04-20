package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class ShoppingCartPage extends BaseTest {
	
	public ShoppingCartPage(WebDriver e_driver) {
		this.driver = e_driver;
	}

	@FindBy(css = "[href$='login']")
	protected WebElement checkout;
	
	@FindBy(css = ".line-item-name .text-truncate")
	public WebElement productName;
	
	@FindBy(xpath = ".//span[contains(text(),'Size' )]/following-sibling::span")
	public WebElement size;
	
	@FindBy(css = ".grand-total")
	public WebElement cartTotal;
}
