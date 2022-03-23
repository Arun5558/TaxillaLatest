package com.keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords {

	public ApplicationKeywords() {
		if (prop == null) {
			prop = new Properties();
			envprop = new Properties();
			try {
				FileInputStream fs = new FileInputStream(
						System.getProperty("user.dir")
								+ "/src/test/rescources/env.properties");
				// FileInputStream fs = new FileInputStream
				// ("/home/arnandigam/workspace/TaxillaProjectFeb/src/project/resources/Projectconf.properties");
				prop.load(fs);
				String env = prop.getProperty("env") + ".properties";
				fs = new FileInputStream(System.getProperty("user.dir")
						+ "/src/test/rescources/" + env);
				envprop.load(fs);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		softassert = new SoftAssert();
	}

	public void setReport(ExtentTest test) {
		this.test = test;
	}

	//Default Login
	public boolean defaultLogin(String username,String password,String securityanswer1,String securityanswer2,String passm,String failm,boolean asserttype)
	{
		log( "Trying to login"+":" +username+ "," +password);
		type("UserId_xpath", username);
		type("Password_xpath", password);
		click("SignInButton_xpath");

		boolean Firstloginstatus=isElementPresent("SignInSuccesscheck_xpath",passm,failm,asserttype);
		wait(1000);
		takeScreenShot();

		//return Firstloginstatus;

		boolean b1 = true;
		boolean b2 = Firstloginstatus;

		int b3 = Boolean.compare(b1,b2);
		if (b3==0)
		{
			entSecAnswers(securityanswer1,securityanswer2,passm,failm,asserttype);
		}
		return Firstloginstatus;
	}
	//Default
	public void entSecAnswers(String answ1,String answ2,String passm,String failm,boolean asserttype)
	{
		log("Trying to enter security answers"+":" +answ1+ "," +answ2 );
		//wait(2000);
		//waitForPageToLoad();
		//takeScreenShot();
		type("SecurityAnswer1_xpath", answ1);
		type("SecurityAnswer2_xpath", answ2);
		click("Submitbutton_xpath");
		//pageLoaderwait("PageLoader_xpath");
		//wait(500);
		//driver.manage().
		//driver.wait(5);


		isElementPresent("SignInhomepageSuccesscheck_xpath",passm,failm,asserttype);
	}
}
