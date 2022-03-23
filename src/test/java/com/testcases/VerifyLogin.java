package com.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

public class VerifyLogin {

	@Test(priority=1,dataProvider="InboundwithoutPartition")
	public void VerifyLogin(Hashtable<String, String> data)
	{

}
}
