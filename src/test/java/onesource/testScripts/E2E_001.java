package onesource.testScripts;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import onesource.utilities.BaseTest;

public class E2E_001 extends BaseTest {
	
	@Test(description = "Sales Force - Checkout as Signed In Useer")
	@Severity(SeverityLevel.MINOR)
	@Description("Test Description: Validate if registered user can checkout")
	public void testCheckoutFlow() {
		saucelabsJobName("Registered User Checkout");
		hp.mobileLogin();
		hp.signIn();
		logp.login();
		hp.clickMenu();
		hp.chooseCategory();
		clp.chooseSubCategory();
		plp.clickFilters();
		plp.applyFilter();
		plp.chooseProduct();
		pdp.addToCart();
		scp.navigateToCheckout();
		sbp.navigateToPayment();
		pp.navigateToConfirmation();
		ocp.placeOrder();
		customAssertion(ocp.name, scp.name);
		customAssertion(ocp.sizeValue, scp.sizeValue);
		customAssertion(ocp.total, scp.total);
	}
}
