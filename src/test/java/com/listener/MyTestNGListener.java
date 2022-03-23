package com.listener;




import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;



public abstract  class MyTestNGListener implements ITestListener {


	@Override
	public void onTestStart(ITestResult result) {

		ExtentTest test =(ExtentTest) result.getTestContext().getAttribute("test");
		test.log(Status.INFO, "Test has Started");
	}


	@Override
	public void onTestSuccess(ITestResult result) {

		ExtentTest test =(ExtentTest) result.getTestContext().getAttribute("test");
		test.log(Status.PASS, result.getTestName()+ " - Test Passed");

	}


	@Override
	public void onTestFailure(ITestResult result) {

		ExtentTest test =(ExtentTest) result.getTestContext().getAttribute("test");
		test.log(Status.FAIL, result.getThrowable().getMessage());

	}


	@Override
	public void onTestSkipped(ITestResult result) {

		ExtentTest test =(ExtentTest) result.getTestContext().getAttribute("test");
		test.log(Status.SKIP, result.getTestName()+" - Test Skipped");

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}




}
