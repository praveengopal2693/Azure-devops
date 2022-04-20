package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.CategoryLandingPage;

public class CategoryLandingAction extends CategoryLandingPage {

	public CategoryLandingAction(WebDriver e_driver) {
		super(e_driver);
	}

	public void chooseSubCategory() {
		logs("Clicked on the Jackets");
		waitUntilFirstElement(tops);
		tops.click();
	}
}
