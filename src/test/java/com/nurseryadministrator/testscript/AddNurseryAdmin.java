package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddAdminObject;
import com.nurseryadministrator.objectrepository.AdminListObject;
import com.nurseryadministrator.objectrepository.AdminViewObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.testdata.AddNurseryAdminData;
import com.nurseryadministrator.testdata.TestDataImport;

public class AddNurseryAdmin extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddAdminObject addadminObj;
	AddNurseryAdminData admindataObj;
	AdminListObject adminlistObj;
	AdminViewObject adminviewObj;
	String[] adminData;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addadminObj = new AddAdminObject(driver);
			admindataObj = new AddNurseryAdminData();
			adminlistObj = new AdminListObject(driver);
			adminviewObj = new AdminViewObject(driver);
			
			tdImport.readSheet("AddAdmin");
			
		
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
	public void verifyMandatoryMsgVisible()
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered addNurseryAdmin test");	
			eTest = eReports.createTest("Verify Mandatory Msg Visible");
			eTest.assignCategory("Nursery Admin - Add Admin");
			
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.addAdmin.click();
			waitForElementToLoad(addadminObj.addButton);
			addadminObj.nationality.sendKeys((language.equals("Arabic"))?"اختر":"Select");;
			autoScrollandClick(addadminObj.addButton);
			for(int i=0;i<5;i++)
			{
				expectedBoolArray.add(true);
			}
			waitForElementToLoad(addadminObj.firstNameMandatoryMsg);
			actualBoolArray.add(isElementPresent(addadminObj.firstNameMandatoryMsg));
			actualBoolArray.add(isElementPresent(addadminObj.emailMandatoryMsg));
			actualBoolArray.add(isElementPresent(addadminObj.nationalityMandatoryMsg));
			actualBoolArray.add(isElementPresent(addadminObj.countryCodeMandatoryMsg));
			actualBoolArray.add(isElementPresent(addadminObj.phoneMandatoryMsg));
		
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}

	
	@Test(priority=2)
	public void addNurseryAdmin()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered addNurseryAdmin test");	
			eTest = eReports.createTest("Add Nursery Admin");
			eTest.assignCategory("Nursery Admin - Add Admin");
		
			admindataObj.generateFakeAdminData();
			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.addAdmin.click();
			addadminObj.addAdminDetails(adminData);
			expectedArraylist.add("Nursery Admin created successfully");
			expectedArraylist.add(adminData[0]+" "+adminData[1]+" "+adminData[2]);
			expectedArraylist.add(adminData[3]);
			expectedArraylist.add(adminData[5].replaceAll("[^\\d+]", "")+" "+adminData[6]);
			expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			expectedArraylist.add(adminData[7]);
			expectedArraylist.add(adminData[8]);
			waitForElementToLoad(addadminObj.adminSuccessMsg);
			actualArraylist.add(addadminObj.adminSuccessMsg.getText());
			System.out.println(addadminObj.adminSuccessMsg.getText());
			
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"مفعل":"Active", adminData[3]);
			waitIfElementClickIsIntercepted(adminlistObj.viewButton, "click", "");
			Thread.sleep(1500);
			waitForElementToLoad(adminviewObj.name);
			actualArraylist.add(adminviewObj.name.getText());
			actualArraylist.add(adminviewObj.email.getText());
			actualArraylist.add(adminviewObj.phoneNumber.getText());
			actualArraylist.add(adminviewObj.status.getText());
			actualArraylist.add(adminviewObj.titleEng.getText());
			actualArraylist.add(adminviewObj.titleArb.getText());
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedArraylist);
		System.out.println(actualArraylist);
		Assert.assertEquals(expectedArraylist, actualArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=3)
	public void viewAdminDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered viewAdminDetails test");	
			eTest = eReports.createTest("View Admin Details");
			eTest.assignCategory("Nursery Admin - List Admin");
		
			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			expectedArraylist.add(adminData[0]+" "+adminData[1]+" "+adminData[2]);
			expectedArraylist.add(adminData[3]);
			expectedArraylist.add(adminData[5].replaceAll("[^\\d+]", "")+" "+adminData[6]);
			expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			expectedArraylist.add(adminData[7]);
			expectedArraylist.add(adminData[8]);
			
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"مفعل":"Active", adminData[3]);
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(adminlistObj.viewButton, "click", "");
			waitForElementToLoad(adminviewObj.name);
			actualArraylist.add(adminviewObj.name.getText());
			actualArraylist.add(adminviewObj.email.getText());
			actualArraylist.add(adminviewObj.phoneNumber.getText());
			actualArraylist.add(adminviewObj.status.getText());
			actualArraylist.add(adminviewObj.titleEng.getText());
			actualArraylist.add(adminviewObj.titleArb.getText());
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedArraylist);
		System.out.println(actualArraylist);
		Assert.assertEquals(expectedArraylist, actualArraylist);
		log.info("Assertion Passed");
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
