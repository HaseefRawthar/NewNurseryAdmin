package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.EditProfileObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.testdata.EditProfileData;
import com.nurseryadministrator.testdata.TestDataImport;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class EditProfile extends SetUp{

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	EditProfileData editprofiledataObj;
	EditProfileObject editprofileObj;
	String[] testData;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			editprofileObj = new EditProfileObject(driver);
			editprofiledataObj = new EditProfileData();
			tdImport = new TestDataImport();
			
			tdImport.readSheet("EditProfile");
			
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
	
	@Test
	public void editPhoneNumberAndVerifyOTP()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered editProfileDetails test");
			eTest = eReports.createTest("Edit Profile Details");
			eTest.assignCategory("Edit Profile");
			
			expectedArraylist.add((language.equals("Arabic"))?"تم تحديث الحساب بنجاح":"Account updated successfully");
			testData = editprofiledataObj.editPhoneNumberData();
			waitIfElementClickIsIntercepted(dashboardObj.userAdmin, "click", "");
			waitForElementToLoad(dashboardObj.editProfile);
			waitIfElementClickIsIntercepted(dashboardObj.editProfile, "click", "");
			waitForElementToLoad(editprofileObj.countryCode);
			expectedArraylist.add(testData[1]);
			editprofileObj.countryCode.sendKeys(testData[0]);
			editprofileObj.phone.clear();
		
			editprofileObj.phone.sendKeys(testData[1]);
			autoScrollandClick(editprofileObj.saveButton);
			System.out.println(editprofileObj.phone.getAttribute("value"));
			
			Response response =  RestAssured.get("https://qa.sso-api.hellobounce.com/v1/sso/otp-list?offset=0&limit=10&startDate=&endDate=&status=&phoneCode=&phoneNumber="+testData[1]+"");
			response.getStatusCode();
			System.out.println(response.getBody().asString());
			JsonPath jsonPathEvaluator = response.jsonPath();
			String otp = jsonPathEvaluator.get("data[0].otp").toString();
			System.out.println(otp);
			
			waitForElementToLoad(editprofileObj.oneTimePassword);
			editprofileObj.oneTimePassword.sendKeys(otp);
			editprofileObj.verifyButton.click();
			waitForElementToLoad(editprofileObj.accountUpdateSuccessMsg);
			actualArraylist.add(editprofileObj.accountUpdateSuccessMsg.getText());
			actualArraylist.add(editprofileObj.phone.getAttribute("value"));
			
			
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
