package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.ProductListPage;

public class ProductListAction extends ProductListPage {

	public ProductListAction(WebDriver e_driver) {
		super(e_driver);
	}

	public void applyFilter() {
		if (!browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")) {
			logs("Clicked on the City fillter");
			waitUntilFirstElement(activityFilter);
			activityFilter.click();
		}
	}

	public void clickFilters() {
		if (browser.equalsIgnoreCase("android") | browser.equalsIgnoreCase("iphone")) {
			logs("Clicked on the City fillter");
			waitUntilFirstElement(filterButton);
			filterButton.click();
			waitUntilFirstElement(activityFilter);
			activityFilter.click();
			waitUntilElementVisible(filterButtonInside);
			filterButtonInside.click();
		}
	}

	public void chooseProduct() {
		logs("Clicked on the first product from the list after filter");
		captureScreenShot();
		waitUntilElementVisible(firstProduct);
		firstProduct.click();
		
	}

}
