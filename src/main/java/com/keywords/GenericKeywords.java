package com.keywords;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.reports.ExtentManager;

//import org.testng.Assert;

//import com.relevantcodes.extentreports.LogStatus;

public class GenericKeywords {

	public WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public ExtentTest test;
	public SoftAssert softassert;

	// @SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public void openBrowser(String browserlanuch) {
		// System.out.println(prop.getProperty("Browserlaunch"));
		if (envprop.getProperty(browserlanuch).equals("Chrome")) {
			// options = Options();
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			// ChromeOptions opts = new ChromeOptions();
			// opts.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			// For running in non gui mode from jenkins
			// *****************
			// opts.setBinary("/opt/google/chrome/chrome");
			//
			// opts.addArguments("--no-sandbox");
			// opts.addArguments("-disable-dev-shm-usage");
			// opts.addArguments("--headless");
			//
			// opts.addArguments("enable-automation");
			//
			// opts.addArguments("--window-size=1920,1080");
			// opts.setExperimentalOption("useAutomationExtension", false);
			// opts.addArguments("--disable-extensions");
			// opts.addArguments("--disable-gpu");
			// ***************************

			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

		} else if (browserlanuch.equals("Mozilla")) {
			System.setProperty("webdriver.gecko.driver", "geckodriver");
			FirefoxDriver driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		} else if (browserlanuch.equals("IE")) {
			System.setProperty("webriver.chrome.driver", "chromedriver");
			InternetExplorerDriver driver = new InternetExplorerDriver();
		}
		// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// driver.manage().window().maximize();
		// test.log(LogStatus.INFO, "Browser has oppened");

	}

	// ******************************Openning
	// URL***************************************
	public void navigateURL(String apURlkey) {
		// wait(500);
		// init();
		// System.out.println(apURlkey);
		// System.out.println(prop.getProperty(apURlkey));

		driver.navigate().to(envprop.getProperty(apURlkey));
		// driver.get(prop.getProperty(apURlkey));
		// driver.navigate().to(prop.getProperty(apURlkey));
		// driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
		// wait(500);
		// driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		// test.log(LogStatus.INFO, "URL has oppened");
		// wait(3000);
		// waitForPageToLoad();
		// takeScreenShot();
	}

	// ******************************Finding Elements by xpath,name and
	// id***************************************
	public WebElement getElement(String locatorkey)

	{

		WebElement e = null;
		try {
			if (locatorkey.endsWith("_xpath"))
				e = driver.findElement(By.xpath(prop.getProperty(locatorkey)));
			else if (locatorkey.endsWith("_id"))
				e = driver.findElement(By.id(prop.getProperty(locatorkey)));

			else if (locatorkey.endsWith("_name"))
				e = driver.findElement(By.name(prop.getProperty(locatorkey)));

			else {
				System.out.println("Locatorykey is not correct" + " "
						+ locatorkey);
				reportFailure("Locatorykey is not correct" + " " + locatorkey,
						true);

			}
			// return e;

		}

		catch (Exception ex) {
			// ex= null;
			reportFailure(ex.getMessage(), true);

			ex.printStackTrace();

		}
		return e;

	}

	// ******************************Type***************************************
	public void type(String locatorkey, String data) {
		isEnabled(locatorkey, "Text-box  is enabled",
				"Text-box is not enabled", true);
		new WebDriverWait(driver, 30).until(
				ExpectedConditions.visibilityOf(getElement(locatorkey)))
				.sendKeys((data));

		// driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	}

	// ******************************Click***************************************
	public void click(String locatorkey)

	{
		isEnabled(locatorkey, "Element is clickable",
				"Element is not clickable", true);

		try {

			new WebDriverWait(driver, 30).until(
					ExpectedConditions
							.elementToBeClickable(getElement(locatorkey)))
					.click();

		}

		catch (Exception ex) {
			reportFailure(ex.getMessage(), true);

			ex.printStackTrace();

		}
	}

	// ******************************Clear***************************************
	public void clear(String locatorKey) {
		log("Clearing text field " + locatorKey);
		getElement(locatorKey).clear();
	}

	// ******************************ClickEnterButton***************************************
	public void clickEnterButton(String locatorKey) {
		log("Clicking enter button");
		getElement(locatorKey).sendKeys(Keys.ENTER);
	}

	// ******************************jsClick***************************************
	public void jsclick(String locatorkey)

	{
		isEnabled(locatorkey, "Element is clickable","Element is not clickable", true);

		try {

			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(getElement(locatorkey))));

		}

		catch (Exception ex) {
			reportFailure(ex.getMessage(), true);

			ex.printStackTrace();
		}
	}

	// ******************************Verifying whether text box is enabled or
	// not***************************************
	// arug1=locatorkey for xpath ,arug2=Pass message has to be sent,arug3=Fail
	// message has to be sent,arg4=If you want to do soft you need to send
	// "softassert" value as argument or else you can send any value which will
	// do hardassert
	public void isEnabled(String locatorkey, String passm, String failm,
			boolean asserttype) {

		new WebDriverWait(driver, 30).until(ExpectedConditions
				.visibilityOf(getElement(locatorkey)));
		// String m="sdf";
		// String n="xcvcx";
		test.log(Status.INFO, "Verifying whether element is enabled/clickable");

		String actualValue = Boolean.toString(getElement(locatorkey)
				.isEnabled());
		String expectedValue = "true";

		if (actualValue.equals(expectedValue)) {
			// reportPass(passm);
		} else {
			reportFailure(failm, asserttype);
		}

	}

	public boolean isElementPresent(String locatorkey, String passm,String failm, boolean asserttype) {
		try {// new WebDriverWait(driver,
				// 50).until(ExpectedConditions.visibilityOf(getElement(locatorkey)));
			List<WebElement> webelementlist = null;

			if (locatorkey.endsWith("_xpath"))
				webelementlist = driver.findElements(By.xpath(prop
						.getProperty(locatorkey)));
			else if (locatorkey.endsWith("_id"))
				webelementlist = driver.findElements(By.id(prop
						.getProperty(locatorkey)));

			else if (locatorkey.endsWith("_name"))
				webelementlist = driver.findElements(By.name(prop
						.getProperty(locatorkey)));

			else {
				takeScreenShot();
				reportFailure("Locator not found" + locatorkey, asserttype);

			}
			// return e;
			if (webelementlist.size() == 0) {

				reportFailure(failm, asserttype);
				return false;
			}

			else {
				reportPass(passm);
				return true;
			}
		} catch (Exception ex) {
			reportFailure(ex.getMessage(), true);
			ex.printStackTrace();
			Assert.fail("Fail the test" + ex.getMessage());
			return false;
		}

	}

	// ******************************VerifyText(Based on locator get the text
	// and compare to the expected text which we
	// provide)***************************************
	// arug1=locatarkey for xpath ,arug2= expectedText has been sent ,
	// arug3=Pass message has to be sent,arug4=Fail message has to be
	// sent,arug5=If you want to do soft you need to send "softassert" value as
	// argument or else you can send any value which will do hardassert
	public boolean verifyText(String locatorkey, String text, String passm,
			String failm, boolean asserttype) {
		String ActualText = getElement(locatorkey).getText().trim();
		String ExpectedText = prop.getProperty(text);
		if (ActualText.equals(ExpectedText)) {
			reportPass(passm);
			return true;

		} else {

			reportFailure(failm, asserttype);

			return false;
		}

	}

	// ******************************GetText(Based on locator get the text and
	// compare to the expected text which we
	// provide)***************************************
	// arug1=locatarkey for xpath ,arug2= expectedText has been sent ,
	// arug3=Pass message has to be sent,arug4=Fail message has to be
	// sent,arug5=If you want to do soft you need to send "softassert" value as
	// argument or else you can send any value which will do hardassert

	// ******************************Verify Whether some text message is present
	// in the page based on page source***************************************
	// arug1=Send the text which you want to verify ,arug2=Pass message has to
	// be sent,arug3=Fail message has to be sent,arg4=If you want to do soft you
	// need to send "softassert" value as argument or else you can send any
	// value which will do hardassert
	public void verifyTextinPage(String text, String passm, String failm,
			boolean asserttype) {

		// String m="sdf";
		// String n="xcvcx";
		if (driver.getPageSource().contains(text)) {
			reportPass(passm);
			// reportPass("Unlock user option is not displaying for user who doesnt have create user role");
		} else {
			reportFailure(failm, asserttype);
		}

	}

	// **********************************************Waitforpagetoload**************************************
	public void waitForPageToLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 0;
		// ajax status
		while (i != 10) {
			String state = (String) js
					.executeScript("return document.readyState;");
			System.out.println(state);

			if (state.equals("complete"))
				break;
			else
				wait(2);

			i++;
		}
		// check for jquery status
		i = 0;
		while (i != 10) {

			Long d = (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if (d.longValue() == 0)
				break;
			else
				wait(2);
			i++;

		}

	}

	// **********************************************Wait**************************************
	public void pageLoaderwait(String locatorkey) {

		new WebDriverWait(driver, 20).until(ExpectedConditions
				.visibilityOf(getElement(locatorkey)));
		// wait(5000);
		new WebDriverWait(driver, 50).until(ExpectedConditions
				.invisibilityOf(getElement(locatorkey)));

	}

	public void pageLoaderwaiting(String locatorkey) {
		// WebDriverWait wait = new WebDriverWait(driver, 50);
		// wait(500);
		// WebElement w =
		// wait.until(ExpectedConditions.visibilityOf(getElement(locatorkey)));
		// WebDriverWait wait= new WebDriverWait(driver, 60);
		// WebElement w = (WebElement)
		// wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getElement(locatorkey)));
		// boolean element =
		// wait.until(ExpectedConditions.invisibilityOf(getElement(locatorkey)));
		// new WebDriverWait(driver,
		// 20).until(ExpectedConditions.visibilityOf(getElement(locatorkey)));
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.invisibilityOf(getElement(locatorkey)));
		// new WebDriverWait(driver,30).until(ExpectedConditions.)
	}

	// ******************************explictwait**************************************
	public void explictwait(String locatorkey) {
		// WebDriverWait wait = new WebDriverWait(driver, 50);
		// wait(500);
		// WebElement w =
		// wait.until(ExpectedConditions.visibilityOf(getElement(locatorkey)));
		// WebDriverWait wait= new WebDriverWait(driver, 60);
		// WebElement w = (WebElement)
		// wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(getElement(locatorkey)));
		// boolean element =
		// wait.until(ExpectedConditions.invisibilityOf(getElement(locatorkey)));
		// new WebDriverWait(driver,
		// 20).until(ExpectedConditions.visibilityOf(getElement(locatorkey)));
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.visibilityOf(getElement(locatorkey)));
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.elementToBeClickable(getElement(locatorkey)));
		// new WebDriverWait(driver,30).until(ExpectedConditions.)
	}

	public void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// ****************************Reporting
	// methods*******************************************
	public void log(String msg) {
		test.log(Status.INFO, msg);
	}

	public void reportFailure(String failuremsg, boolean stoponfailure) {
		test.log(Status.FAIL, failuremsg);// report failure in extent report
		takeScreenShot();// Put screen-shot in report
		softassert.fail(failuremsg);// report failure in testng
		if (stoponfailure == true) {
			Reporter.getCurrentTestResult().getTestContext()
					.setAttribute("CriticalFailure", "Y");
			AssertAll();
		}
	}

	public void AssertAll() {

		softassert.assertAll();
	}

	// ******************************Report
	// Pass***************************************
	public void reportPass(String msg) {
		test.log(Status.PASS, msg);
		takeScreenShot();

	}

	// ******************************Takingscreenshot***************************************
	public void takeScreenShot() {
		// fileName of the screenshot
		Date d = new Date();
		String screenshotFile = d.toString().replace(":", "_")
				.replace(" ", "_")
				+ ".png";
		// Take Screen-shot
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// get the dynamic folder name
			FileUtils.copyFile(scrFile, new File(
					ExtentManager.screenshotFolderPath + "/" + screenshotFile));
			// adding the screen-shot in extent reports
			test.log(
					Status.INFO,
					"Screenshot-> "
							+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath
									+ "/" + screenshotFile));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// put screenshot file in reports
		// String test1 = "./reports/"+folderName+"/screenshot/"+screenshotFile;
		// System.out.println("new=" + test1);

	}

	// ******************************AcceptAlert***************************************
	public void acceptAlert() {
		log("Switching to alert");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.alertIsPresent());
		try {
			driver.switchTo().alert().accept();
			driver.switchTo().defaultContent();
			log("Alert accepted successfully");
		} catch (Exception e) {
			reportFailure("Alert not found when mandatory", true);
		}

	}

	public void quit() {
		driver.quit();

	}
}