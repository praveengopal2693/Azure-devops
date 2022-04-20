package onesource.reusables;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import onesource.pageObjects.ProductDetailsPage;

public class ProductDetailsAction extends ProductDetailsPage {

	public ProductDetailsAction(WebDriver e_driver) {
		super(e_driver);
	}

	public void addToCart() {
		logs("Chosen size from product detail page");
		waitUntilFirstElement(selectSize);
		((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(250,350)");

		logs("Clicked on the Add to cart button");
		if (!browser.equalsIgnoreCase("safari") || browser.equalsIgnoreCase("lcoalchrome")) {
			selectSize.click();
			waitUntilElementVisible(addToCart);
			addToCart.click();
		}
		if (browser.equalsIgnoreCase("safari")) {
			((JavascriptExecutor) driver).executeScript("document.querySelector('.size-attribute').click();");
			((JavascriptExecutor) driver).executeScript("document.querySelector('.add-to-cart').click();");
		}
		logs("Clicked on the View cart button");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		((JavascriptExecutor) driver).executeScript("document.querySelector('[href$=cart]').click();");
	}
}
