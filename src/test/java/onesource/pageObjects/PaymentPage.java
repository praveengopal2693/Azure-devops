package onesource.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import onesource.utilities.BaseTest;

public class PaymentPage extends BaseTest {
	
	public PaymentPage(WebDriver e_driver) {
		this.driver = e_driver;
	} 

	@FindBy(id = "cardNumber")
	protected WebElement cardNumber;
	
	@FindBy(id = "expirationMonth")
	protected WebElement expirationMonth;
	
	@FindBy(id = "expirationYear")
	protected WebElement expirationYear;
	
	@FindBy(id = "saved-payment-security-code")
	protected WebElement securityCode;
	
	@FindBy(xpath = "(.//button[@value='submit-payment'])[2]")
	protected WebElement paymentButtonDesktop;
	
	@FindBy(xpath = "(.//button[@value='submit-payment'])[3]")
	protected WebElement paymentButtonMobile;
}
