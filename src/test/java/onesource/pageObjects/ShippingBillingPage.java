package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class ShippingBillingPage extends BaseTest {
	
	protected ShippingBillingPage(WebDriver e_driver) {
		this.driver = e_driver;
	}
	
	@FindBy(id = "shippingFirstName")
	protected WebElement shippingFirstName;

	@FindBy(id = "shippingLastName")
	protected WebElement shippingLastName;
	
	@FindBy(id = "shippingAddressOne")
	protected WebElement shippingAddressOne;
	
	@FindBy(id = "shippingCountry")
	protected WebElement shippingCountry;
	
	@FindBy(id = "shippingState")
	protected WebElement shippingState;
	
	@FindBy(id = "shippingAddressCity")
	protected WebElement shippingAddressCity;
	
	@FindBy(id = "shippingZipCode")
	protected WebElement shippingZipCode;
	
	@FindBy(id = "shippingPhoneNumber")
	protected WebElement shippingPhoneNumber;
	
	@FindBy(xpath = "(.//*[contains(text(),'Go to Payment')])[1]")
	protected WebElement submitShippingDesktop;
	
	@FindBy(xpath = "(.//*[contains(text(),'Go to Payment')])[3]")
	protected WebElement submitShippingMobile;
}
