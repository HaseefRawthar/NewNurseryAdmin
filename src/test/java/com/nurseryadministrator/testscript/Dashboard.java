package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;

public class Dashboard extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			
			loginObj.logIn(userEmail, userPassword);
			dashboardObj.selectNursery(nurseryName);
			
			waitForElementToBeClickable(dashboardObj.adminImage);
			dashboardObj.changeLanguage(language);
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	@Test(priority=1)
	public void verifyDashboard() //verify students,parents and employees are present
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered verifyDashboard test");
			eTest = eReports.createTest("Verify Dashboard");
			eTest.assignCategory("Dashboard");
			
			for(int i=0;i<3;i++)
			{
				expectedBoolArray.add(true);
			}
			waitForElementToLoad(dashboardObj.Students);
			actualBoolArray.add(isElementPresent(dashboardObj.Students));
			actualBoolArray.add(isElementPresent(dashboardObj.Parents));
			actualBoolArray.add(isElementPresent(dashboardObj.Employees));
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualBoolArray);
		System.out.println(expectedBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion Passed");
	}
	
	//@Test(priority=2)
	public void switchToOliverNursery()
	{
		try
		{
			log.info("Entered switchToOliverNursery test");
			eTest = eReports.createTest("switchToOliverNursery");
			eTest.assignCategory("Dashboard");
			
			expectedMsg = "Oliver, Nursery Manager";
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			autoScrollandClick(dashboardObj.oliverNursery);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	//@Test(priority=3)
	public void switchToNurseryEdgetestNurseryManager()
	{
		try
		{
			log.info("Entered switchToNurseryEdgetestNurseryManager test");
			eTest = eReports.createTest("switchToNurseryEdgetestNurseryManager");
			eTest.assignCategory("Dashboard");
			
			expectedMsg = "Nursery-Edge-test, Nursery Manager";
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			autoScrollandClick(dashboardObj.NurseryEdgetestManager);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	//@Test(priority=4)
	public void switchToHappyTrailsDaycareAdmin()
	{
		try
		{
			log.info("Entered switchToHappyTrailsDaycareAdmin test");
			eTest = eReports.createTest("switchToHappyTrailsDaycareAdmin");
			eTest.assignCategory("Dashboard");
			
			expectedMsg = "Happy Trails Daycare, Nursery Admin";
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			autoScrollandClick(dashboardObj.happyTrailsDaycareAdmin);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	//@Test(priority=5)
	public void switchToHappyTrailsDaycareManager()
	{
		try
		{
			log.info("Entered switchToHappyTrailsDaycareManager test");
			eTest = eReports.createTest("switchToHappyTrailsDaycareManager");
			eTest.assignCategory("Dashboard");
			
			expectedMsg = "Happy Trails Daycare, Nursery Manager";
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			autoScrollandClick(dashboardObj.happyTrailsDaycareManager);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	//@Test(priority=6)
	public void switchToExperionNursery()
	{
		try
		{
			log.info("Entered switchToExperionNursery test");
			eTest = eReports.createTest("switchToExperionNursery");
			eTest.assignCategory("Dashboard");
			
			expectedMsg = "Experion, Nursery Manager";
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			scrollDown(400);
			//waitForElementToLoad(dashboardObj.experionNursery);
			autoScrollandClick(dashboardObj.experionNursery);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	
	//@Test
	public void switchNursery()
	{
		try
		{
			log.info("Entered switchNursery test");
			eTest = eReports.createTest("switchNursery");
			eTest.assignCategory("Dashboard");
			
			String name = "Happy Trails Daycare, Nursery Manager";
			expectedMsg = name;
			waitForElementToLoad(dashboardObj.switchNurseryDrop);
			waitIfElementClickIsIntercepted(dashboardObj.switchNurseryDrop, "click", "");
			WebElement nursery = driver.findElement(By.xpath("//h5[contains(text(),'"+name+"')]"));
			autoScrollandClick(nursery);
			Thread.sleep(2000);
			driver.navigate().refresh();
			waitForElementToLoad(dashboardObj.currentRoleSelected);
			actualMsg = dashboardObj.currentRoleSelected.getText();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualMsg);
		Assert.assertEquals(actualMsg, expectedMsg);
	}
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
