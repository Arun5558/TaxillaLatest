package com.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.testbase.BaseTest;

public class TenantBasedPartition extends BaseTest {

	@Test
	public void verifyAppDis()

	{
		test.log(Status.INFO, "opening browser");
		app.openBrowser("Browserlaunch");

		// openBrowser("Browserlaunch");
		// navigateURL("AppURl");
	}

	@Test
	public void urlNav()

	{
		test.log(Status.INFO, "Opening the browser");
		app.navigateURL("AppURl");

		// openBrowser("Browserlaunch");
		// navigateURL("AppURl");
	}
}
