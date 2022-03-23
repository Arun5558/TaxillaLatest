package com.testcases;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.testbase.BaseTest;

public class PartnerManagment extends BaseTest {

	@Test
	public void testP() {
		test.log(Status.INFO, "Opening the browser");
		app.navigateURL("AppURl");

	}

}
