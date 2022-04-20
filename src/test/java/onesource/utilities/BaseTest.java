package onesource.utilities;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

import javax.swing.JFrame;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import onesource.reusables.CategoryLandingAction;
import onesource.reusables.HomePageAction;
import onesource.reusables.LoginAction;
import onesource.reusables.OrderConfirmationAction;
import onesource.reusables.PaymentAction;
import onesource.reusables.ProductDetailsAction;
import onesource.reusables.ProductListAction;
import onesource.reusables.ShippingBillingAction;
import onesource.reusables.ShoppingCartAction;

public class BaseTest {
	private Logger Log = Logger.getLogger(BaseTest.class.getName());
	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Random rand = new Random();
	public static Properties prop = new Properties();
	DesiredCapabilities capabilities = new DesiredCapabilities();
	MutableCapabilities sauceOptions = new MutableCapabilities();
	public JFrame f = new JFrame();
	public String browser;
	public String suiteName;
	public CategoryLandingAction clp;
	public HomePageAction hp;
	public LoginAction logp;
	public OrderConfirmationAction ocp;
	public PaymentAction pp;
	public ProductDetailsAction pdp;
	public ProductListAction plp;
	public ShippingBillingAction sbp;
	public ShoppingCartAction scp;

	@BeforeMethod
	@Parameters(value = { "browser", "appiumJobName" })
	public void beforeMethod(ITestContext context, String browser, @Optional String appiumJobName)
			throws IOException, InterruptedException {
		String suiteName = context.getCurrentXmlTest().getSuite().getName();
		congfigFileDetails();
		String[] iPhoneVersion = prop.getProperty("iPhone_version").split(",");
		String[] iPadVersion = prop.getProperty("iPad_version").split(",");
		String[] androidVersion = prop.getProperty("android_version").split(",");
		Random random = new Random();
		this.browser = browser;
		this.suiteName = suiteName;
		switch (browser.toLowerCase()) {
		case "android":
			// capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceOrientation", prop.getProperty("android_deviceOrientation"));
			capabilities.setCapability("deviceName", "Huawei_P30_Lite_real_us");
			capabilities.setCapability("browserName", prop.getProperty("android_browser"));
			capabilities.setCapability("platformVersion", androidVersion[random.nextInt(androidVersion.length)]);
			capabilities.setCapability("platformName", prop.getProperty("android_platformname"));
			capabilities.setCapability("phoneOnly", prop.getProperty("android_phoneOnly"));
			capabilities.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			capabilities.setCapability("name", appiumJobName + "_android");
			capabilities.setCapability("acceptInsecureCerts", true);
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), capabilities);
			break;

		case "iphone":
			// capabilities = DesiredCapabilities.iphone();
			capabilities.setCapability("deviceOrientation", prop.getProperty("iPhone_deviceOrientation"));
			capabilities.setCapability("platformName", prop.getProperty("iPhone_platformname"));
			capabilities.setCapability("platformVersion", iPhoneVersion[random.nextInt(iPhoneVersion.length)]);
			capabilities.setCapability("phoneOnly", prop.getProperty("iPhone_phoneOnly"));
			capabilities.setCapability("browserName", prop.getProperty("iPhone_browser"));
			capabilities.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			capabilities.setCapability("name", appiumJobName + "_iPhone");
			capabilities.setCapability("autoAcceptAlerts", "true");
			capabilities.setCapability("autoDismissAlerts", "true");
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), capabilities);
			Thread.sleep(3000);
			break;

		case "ipad":
			// capabilities = DesiredCapabilities.iphone();
			capabilities.setCapability("deviceOrientation", prop.getProperty("iPad_deviceOrientation"));
			capabilities.setCapability("platformName", prop.getProperty("iPad_platformname"));
			capabilities.setCapability("platformVersion", iPadVersion[random.nextInt(iPadVersion.length)]);
			capabilities.setCapability("tabletOnly", prop.getProperty("iPad_tabletOnly"));
			capabilities.setCapability("browserName", prop.getProperty("iPad_browser"));
			capabilities.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			capabilities.setCapability("name", appiumJobName + "_ipad");
			capabilities.setCapability("autoAcceptAlerts", "true");
			capabilities.setCapability("autoDismissAlerts", "true");
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), capabilities);
			Thread.sleep(3000);
			break;

		case "edge":
			sauceOptions.setCapability("screenResolution", "2560x1600");
			sauceOptions.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			EdgeOptions edgeBrowserOptions = new EdgeOptions();
			edgeBrowserOptions.setCapability("platformName", "Windows 10");
			edgeBrowserOptions.setCapability("browserVersion", "latest");
			edgeBrowserOptions.setCapability("sauce:options", sauceOptions);
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), edgeBrowserOptions);
			break;

		case "chrome":
			sauceOptions.setCapability("screenResolution", prop.getProperty("chrome_screenResolution"));
			sauceOptions.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			ChromeOptions chromeBrowserOptions = new ChromeOptions();
			chromeBrowserOptions.setExperimentalOption("w3c", true);
			chromeBrowserOptions.setCapability("platformName", "macOS 10.15");
			chromeBrowserOptions.setCapability("browserVersion", "latest");
			chromeBrowserOptions.setCapability("sauce:options", sauceOptions);
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), chromeBrowserOptions);
			break;

		case "safari":
			sauceOptions.setCapability("screenResolution", prop.getProperty("safari_screenResolution"));
			sauceOptions.setCapability("idleTimeout", prop.getProperty("idleTimeout"));
			SafariOptions safariBrowserOptions = new SafariOptions();
			sauceOptions.setCapability("screenResolution", prop.getProperty("safari_screenResolution"));
			safariBrowserOptions.setCapability("platformName", prop.getProperty("safari_platform"));
			safariBrowserOptions.setCapability("browserVersion", prop.getProperty("safari_version"));
			safariBrowserOptions.setCapability("browserName", prop.getProperty("safari_browserName").toLowerCase());
			safariBrowserOptions.setCapability("sauce:options", sauceOptions);
			driver = new RemoteWebDriver(new java.net.URL(prop.getProperty("SaucelabIP")), safariBrowserOptions);
			Thread.sleep(3000);
			break;

		case "localchrome":
			ChromeDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		case "localsafari":
			driver = new SafariDriver();
			driver.manage().window().maximize();
			break;

		default:
			Log.error("Browser Not Found. Please Provide a Valid Browser");
			throw new NotFoundException("Browser Not Found. Please Provide a Valid Browser");
		}

		logs("Invoking the driver " + browser);

		// Set build name for sauce labs
		if (!browser.equalsIgnoreCase("localchrome") && !browser.equalsIgnoreCase("localsafari")
				&& !browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")
				&& !browser.equalsIgnoreCase("ipad")) {
			((JavascriptExecutor) driver).executeScript("sauce:job-build=" + suiteName);
		}

		// POM Modal - Page Object Initialization
		clp = PageFactory.initElements(driver, CategoryLandingAction.class);
		hp = PageFactory.initElements(driver, HomePageAction.class);
		logp = PageFactory.initElements(driver, LoginAction.class);
		ocp = PageFactory.initElements(driver, OrderConfirmationAction.class);
		pp = PageFactory.initElements(driver, PaymentAction.class);
		pdp = PageFactory.initElements(driver, ProductDetailsAction.class);
		plp = PageFactory.initElements(driver, ProductListAction.class);
		sbp = PageFactory.initElements(driver, ShippingBillingAction.class);
		scp = PageFactory.initElements(driver, ShoppingCartAction.class);

		clp.setBrowser(this.browser);
		hp.setBrowser(this.browser);
		logp.setBrowser(this.browser);
		ocp.setBrowser(this.browser);
		pp.setBrowser(this.browser);
		pdp.setBrowser(this.browser);
		plp.setBrowser(this.browser);
		sbp.setBrowser(this.browser);
		scp.setBrowser(this.browser);
		// getURL
		driver.get(prop.getProperty("url"));
		waitForLoad();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) throws IOException {
		if (!browser.equalsIgnoreCase("localchrome") && !browser.equalsIgnoreCase("localsafari")) {
			if (result.getStatus() == ITestResult.FAILURE) {
				((JavascriptExecutor) driver).executeScript("sauce:job-result=failed");
				if (driver != null) {
					driver.quit();
				} else {
					logs("Driver terminated sucessfully");
				}
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				((JavascriptExecutor) driver).executeScript("sauce:job-result=passed");
				if (driver != null) {
					if (driver != null) {
						driver.quit();
					} else {
						logs("Driver terminated sucessfully");
					}

				} else {
					logs("Driver terminated sucessfully");
				}
			} else {
				if (driver != null) {
					driver.quit();
				} else {
					logs("Driver terminated sucessfully");
				}
			}
		} else {
			driver.quit();
		}
	}

	public void customAssertion(String expected, String actual) {
		String result = null;
		try {
			result = "PASS";
			if (actual.contains(expected)) {
				logs("Assertion Result:->" + result + " Expected:->" + expected + ", Actual->" + actual);
			} else {
				throw new MyException(result = "FAIL");
			}
		} catch (MyException exp) {
			logs("Assertion Result:->" + exp.getMessage() + " Expected:->" + expected + ", Actual->" + actual);
		}
	}

	public void congfigFileDetails() throws IOException {
		prop.load(new FileInputStream("./dataimporter/config.properties"));
		prop.load(new FileInputStream("./dataimporter/testdata.properties"));
	}

	public void saucelabsJobName(String jobName) {
		if (!browser.equalsIgnoreCase("localchrome") && !browser.equalsIgnoreCase("android")
				&& !browser.equalsIgnoreCase("iphone") && !browser.equalsIgnoreCase("ipad")) {
			((JavascriptExecutor) driver).executeScript("sauce:job-name=" + jobName + "_" + browser);
		}
	}

	public void logs(String message) {
		if (!browser.equalsIgnoreCase("localchrome") && !browser.equalsIgnoreCase("localsafari")
				&& !browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")
				&& !browser.equalsIgnoreCase("ipad")) {
			((JavascriptExecutor) driver).executeScript("sauce:context=" + message);
		}
		Allure.step(message);
	}

	public void logs(String actual, String Expected) {
		if (!browser.equalsIgnoreCase("localchrome") && !browser.equalsIgnoreCase("localsafari")
				&& !browser.equalsIgnoreCase("android") && !browser.equalsIgnoreCase("iphone")
				&& !browser.equalsIgnoreCase("ipad")) {
			((JavascriptExecutor) driver).executeScript("sauce:context=" + actual);
			((JavascriptExecutor) driver).executeScript("sauce:context=" + Expected);
		}
		Allure.step(actual);
		Allure.step(Expected);
	}

	public void waitUntilFirstElement(WebElement element) {
		waitForLoad();
		await().atMost(30, SECONDS).pollDelay(3, SECONDS).pollInterval(2, SECONDS).ignoreExceptions()
				.until(() -> element.isDisplayed());
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitUntilElementVisible(WebElement element) {
		await().atMost(30, SECONDS) // max wait
				.pollInterval(2, SECONDS).ignoreExceptions().until(() -> element.isDisplayed());
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForLoad() {
		if (browser.equalsIgnoreCase("localchrome") | browser.equalsIgnoreCase("edge")
				| browser.equalsIgnoreCase("chrome") | browser.equalsIgnoreCase("safari")
				| browser.equalsIgnoreCase("androidemulator") | browser.equalsIgnoreCase("iphoneemulator")
				| browser.equalsIgnoreCase("ipademulator") | browser.equalsIgnoreCase("android")
				| browser.equalsIgnoreCase("localsafari")) {
			ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
				}
			};
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
			wait.until(pageLoadCondition);
		} else {
			await().atMost(30, SECONDS);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	@Attachment
	public void captureScreenShot() {
		byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Allure.getLifecycle().addAttachment(
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yy_hh:mm:ss")), "image/png", "png",
				screenShot);
	}
}
