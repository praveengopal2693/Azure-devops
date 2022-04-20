package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.OrderConfirmationPage;

public class OrderConfirmationAction extends OrderConfirmationPage {

	public OrderConfirmationAction(WebDriver e_driver) {
		super(e_driver);
	}

	public String name;
	public String sizeValue;
	public String total;

	public void placeOrder() {
		logs("Clicked on the Payment Button");
		waitUntilFirstElement(paymentButton);
		paymentButton.click();
		logs("Assert the Thank You Message");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		waitUntilFirstElement(thankYou);
		name = productName.getText().trim();
		sizeValue = size.getText().trim();
		total = finalTotal.getText().trim();
		captureScreenShot();
	}
}
