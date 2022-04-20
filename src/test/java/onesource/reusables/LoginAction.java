package onesource.reusables;

import org.openqa.selenium.WebDriver;

import onesource.pageObjects.LoginPage;

public class LoginAction extends LoginPage {
	
	public LoginAction(WebDriver e_driver) {
		super(e_driver);
	}
	
	public void login() {
		logs("Entered valid user name");
		waitUntilFirstElement(email);
		if (browser.equalsIgnoreCase("safari") | browser.equalsIgnoreCase("localchrome"))
		email.sendKeys(prop.getProperty("email"));
		if (browser.equalsIgnoreCase("chrome"))
			email.sendKeys(prop.getProperty("email1"));
		if (browser.equalsIgnoreCase("edge")) 
			email.sendKeys(prop.getProperty("email2"));
		if (browser.equalsIgnoreCase("android")) 
			email.sendKeys(prop.getProperty("email3"));
		if (browser.equalsIgnoreCase("iphone")) 
			email.sendKeys(prop.getProperty("email4"));
		if (browser.equalsIgnoreCase("ipad")) 
			email.sendKeys(prop.getProperty("email5"));
		logs("Entered valid password");
		waitUntilElementVisible(password);
		password.sendKeys(prop.getProperty("password"));
		logs("Clicked on tje Login Button");
		waitUntilElementVisible(logInButton);
		logInButton.click();
	}
}
