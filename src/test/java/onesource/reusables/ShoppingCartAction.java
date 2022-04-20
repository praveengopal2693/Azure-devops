package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.ShoppingCartPage;

public class ShoppingCartAction extends ShoppingCartPage {
	
	public ShoppingCartAction(WebDriver e_driver) {
		super(e_driver);
	}
	
	public String name;
	public String sizeValue;
	public String total;
	
	public void navigateToCheckout() {
		logs("Clicked on the Checkout button from Payment Page");
		waitUntilFirstElement(checkout);
		name = productName.getText().trim();
		sizeValue = size.getText().trim();
		total = cartTotal.getText().trim();
		checkout.click();
	}
}
