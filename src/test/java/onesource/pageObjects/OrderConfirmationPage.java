package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class OrderConfirmationPage extends BaseTest {
	
	protected OrderConfirmationPage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	
	@FindBy(xpath = "(.//button[@value='place-order'])[3]")
	protected WebElement paymentButton;
	
	@FindBy(css = ".order-thank-you-msg")
	protected WebElement thankYou;
	
	@FindBy(css = ".line-item-name span")
	protected WebElement productName;
	
	@FindBy(xpath = ".//span[contains(text(),'Size' )]/following-sibling::span")
	protected WebElement size;
	
	@FindBy(css = ".grand-total-sum")
	protected WebElement finalTotal;

}
