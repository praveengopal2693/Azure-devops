package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.PaymentPage;

public class PaymentAction extends PaymentPage {

	public PaymentAction(WebDriver e_driver) {
		super(e_driver);
	}

	public void navigateToConfirmation() {
		logs("Entered valid secutiry code");
		waitUntilFirstElement(securityCode);
		securityCode.sendKeys("123");
		logs("Clicked on the Payment button");
		if (!browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")) {
			waitUntilElementVisible(paymentButtonDesktop);
			paymentButtonDesktop.click();
		}
		if (browser.equalsIgnoreCase("android") | browser.equalsIgnoreCase("iphone")) {
			waitUntilFirstElement(paymentButtonMobile);
			paymentButtonMobile.click();
		}
	}
}
