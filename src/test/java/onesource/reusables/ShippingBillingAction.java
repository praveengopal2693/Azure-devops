package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.ShippingBillingPage;

public class ShippingBillingAction extends ShippingBillingPage {
	
	public ShippingBillingAction(WebDriver e_driver) {
		super(e_driver);
	}
	
	public void navigateToPayment() {
		logs("Clicked on the Submit button from Shipping & Billing page");
		if (!browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")) {
		waitUntilFirstElement(submitShippingDesktop);
		submitShippingDesktop.click();
		}
		if (browser.equalsIgnoreCase("android") | browser.equalsIgnoreCase("iphone")) {
		waitUntilFirstElement(submitShippingMobile);
		submitShippingMobile.click();
		}
	}
}
