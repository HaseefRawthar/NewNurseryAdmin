package com.nurseryadministrator.testscript;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.testdata.LogInData;
import com.nurseryadministrator.testdata.TestDataImport;

public class LogIn extends SetUp{

	LogInObject loginObj;
	TestDataImport tdImport;
	LogInData logindataObj;
	DashboardObject dashboardObj;
	String[] loginData;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			tdImport = new TestDataImport();
			logindataObj = new LogInData();
			dashboardObj = new DashboardObject(driver);
			
			tdImport.readSheet("Login");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	@Test(priority = 1)
	public void invalidEmail() //login with invalid email id
	{
		try
		{
		log.info("Entered invalidEmail test");	
		eTest = eReports.createTest("Invalid Email");
		eTest.assignCategory("LogIn");
		
		expectedMsg = "email must be a valid email";
		loginData = logindataObj.getInValidEmailData();
		
		loginObj.logIn(loginData[0], loginData[1]);
		waitForElementToLoad(loginObj.invalidEmailMsg);
		actualMsg = loginObj.invalidEmailMsg.getText();
		
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority= 2)
	public void invalidCredentials() //login with wrong credentials
	{
		try {
			log.info("Entered invalidCredentials test");
			eTest = eReports.createTest("Invalid Credentials");
			eTest.assignCategory("LogIn");
			driver.navigate().refresh();
			
			expectedMsg = "Provided email and/or password are incorrect";
			loginData = logindataObj.getInValidLoginData();
			loginObj.logIn(loginData[0], loginData[1]);
			waitForElementToLoad(loginObj.invalidMsg);
			actualMsg = loginObj.invalidMsg.getText();
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority = 3)
	public void noCredentials() //login without any credentials
	{
		try
		{
		log.info("Entered noCredentials test");
		eTest = eReports.createTest("No Credentials");
		eTest.assignCategory("LogIn");	
		driver.navigate().refresh();
		
		actualArray = new String[2];
		expectedArray = new String[2];
		expectedArray[0] = "Email is required";
		expectedArray[1] = "Password is required";
		
		loginObj.logIn("", "");
		waitForElementToLoad(loginObj.noEmailMsg);
		actualArray[0] = loginObj.noEmailMsg.getText();
		actualArray[1] = loginObj.noPasswordMsg.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		for(int i =0;i<actualArray.length;i++)
		{
			System.out.println(actualArray[i]);
		}
		Assert.assertEquals(actualArray, expectedArray);
		log.info("Assertion passed");
	}
	
	@Test(priority=4)
	public void validCredentials() //login with valid credentials
	{
		try
		{
			log.info("Entered validCredentials test");
			eTest = eReports.createTest("Valid Credentials");
			eTest.assignCategory("LogIn");	
			driver.navigate().refresh();
			
			expectedMsg = "Nursery-Edge-test, Nursery Admin";
			loginData = logindataObj.getValidLoginData();
			loginObj.logIn(loginData[0], loginData[1]);
			waitForElementToLoad(dashboardObj.selectButton);
			dashboardObj.selectNurseryEdgetestNurseryAdmin.click();
			dashboardObj.selectButton.click();
			
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
			dashboardObj.logOut();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
		log.info("Assertion Passed");
	}
}
