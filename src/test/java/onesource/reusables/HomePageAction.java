package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.HomePage;

public class HomePageAction extends HomePage {

	public HomePageAction(WebDriver e_driver) {
		super(e_driver);
	}

	public void signIn() {
		//logs("Closed the cookie overlay");
		//waitUntilFirstElement(cookieClose);
		//cookieClose.click();
		if (!browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")) {
			logs("Clicked on the Sign In button");
		waitUntilElementVisible(signIn);
		signIn.click();
		}
	}

	public void chooseCategory() {
		logs("Clicked on the Menu category");
		waitUntilFirstElement(men);
		men.click();
	}

	public void mobileLogin() {
		if (browser.equalsIgnoreCase("android") | browser.equalsIgnoreCase("iphone")) {
			logs("Clicked on the hamburger icon");
			waitUntilFirstElement(mobileMenu);
			mobileMenu.click();
			logs("Clicked on the Sign In button");
			waitUntilElementVisible(signInMobile);
			signInMobile.click();
		}
	}
	
	public void clickMenu() {
		if (browser.equalsIgnoreCase("android") | browser.equalsIgnoreCase("iphone")) {
			waitUntilFirstElement(mobileMenu);
			mobileMenu.click();
		}
	}

}
