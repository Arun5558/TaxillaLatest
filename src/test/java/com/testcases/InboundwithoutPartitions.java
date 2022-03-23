package com.testcases;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testbase.BaseTest;

public class InboundwithoutPartitions extends BaseTest {

	@Test
	public void testinbound(ITestContext context) {

		app.navigateURL("AppURl");
		System.out
				.println("InboundwithoutPartitions with test has been executed");

		final String id = "aasfsdfwdfa345dsfsdfsdfd";

		context.setAttribute("bookingid", id);

	}

	@Parameters({ "action" })
	@Test
	public void test1(String partitiontype) {
		if (partitiontype.equals("withpartition")) {
			System.out
					.println("InboundwithPartitions with executed with test1");
		} else {
			System.out
					.println("InboundwithoutPartitions with executed with test1");
		}
	}

	@Test
	public void test2() {
		System.out.println("URL has been navigated");
		System.out.println("InboundwithoutPartitions excuded with test 2");
	}
}
