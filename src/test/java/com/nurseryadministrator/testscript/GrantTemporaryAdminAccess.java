package com.nurseryadministrator.testscript;


import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.GrantTemporaryAdminAccessObject;
import com.nurseryadministrator.objectrepository.LogInObject;

public class GrantTemporaryAdminAccess extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	GrantTemporaryAdminAccessObject grantaccessObj;
	String s;
	Date date;
	Format formatter;
	Calendar calendar = Calendar.getInstance();
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			grantaccessObj = new GrantTemporaryAdminAccessObject(driver);
			
			
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
	public void  grantAdminaccessfor1day()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered grantAdminaccessfor1day test");	
			eTest = eReports.createTest("Grant Admin access for 1day ");
			eTest.assignCategory("Grant Temporary Admin Access");
			
			expectedArraylist.add( (language.equals("Arabic"))?"تم اضافة بطاقة الدعم بنجاح":"Support ticket added successfully");
			 date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  expectedArraylist.add(formatter.format(date));
			  calendar.add(Calendar.DATE, 1);
			  date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  s=formatter.format(date);
			  expectedArraylist.add(s);
			  expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			waitIfElementClickIsIntercepted(dashboardObj.grantTemporaryAdminAccess, "click", "");
			Thread.sleep(2500);
			waitForElementToLoad(grantaccessObj.accessDuration);
			grantaccessObj.accessDuration.sendKeys((language.equals("Arabic"))?"يوم (exp. "+s+")":"1 day (EXPIRES_ON: "+s+")");
			grantaccessObj.submitButton.click();
			Thread.sleep(4500);
			
			waitForElementToLoad(grantaccessObj.grantedMsg);
			actualArraylist.add(grantaccessObj.grantedMsg.getText());
			actualArraylist.add(grantaccessObj.startDate.getText());
			actualArraylist.add(grantaccessObj.endDate.getText());
			actualArraylist.add(grantaccessObj.status.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test
	public void  grantAdminaccessfor3day()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered grantAdminaccessfor3day test");	
			eTest = eReports.createTest("Grant Admin access for 3dayS ");
			eTest.assignCategory("Grant Temporary Admin Access");
			
			expectedArraylist.add( (language.equals("Arabic"))?"تم اضافة بطاقة الدعم بنجاح":"Support ticket added successfully");
			 date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  expectedArraylist.add(formatter.format(date));
			  calendar.add(Calendar.DATE, 3);
			  date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  s=formatter.format(date);
			  expectedArraylist.add(s);
			  expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			waitIfElementClickIsIntercepted(dashboardObj.grantTemporaryAdminAccess, "click", "");
			Thread.sleep(2500);
			waitForElementToLoad(grantaccessObj.accessDuration);
			grantaccessObj.accessDuration.sendKeys((language.equals("Arabic"))?"ثلاثة أيام (exp. "+s+")":"3 day (EXPIRES_ON: "+s+")");
			grantaccessObj.submitButton.click();
			Thread.sleep(2500);
			
			
			waitForElementToLoad(grantaccessObj.grantedMsg);
			actualArraylist.add(grantaccessObj.grantedMsg.getText());
			actualArraylist.add(grantaccessObj.startDate.getText());
			actualArraylist.add(grantaccessObj.endDate.getText());
			actualArraylist.add(grantaccessObj.status.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test
	public void  grantAdminaccessfor1Week()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered grantAdminaccessfor1Week test");	
			eTest = eReports.createTest("Grant Admin access for 1Week ");
			eTest.assignCategory("Grant Temporary Admin Access");
			
			expectedArraylist.add( (language.equals("Arabic"))?"تم اضافة بطاقة الدعم بنجاح":"Support ticket added successfully");
			 date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  expectedArraylist.add(formatter.format(date));
			  calendar.add(Calendar.DATE, 7);
			  date = calendar.getTime();
			  formatter = new SimpleDateFormat("dd/MM/yyyy");
			  s=formatter.format(date);
			  expectedArraylist.add(s);
			  expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			waitIfElementClickIsIntercepted(dashboardObj.grantTemporaryAdminAccess, "click", "");
			Thread.sleep(2500);
			waitForElementToLoad(grantaccessObj.accessDuration);
			grantaccessObj.accessDuration.sendKeys((language.equals("Arabic"))?"اسبوع (exp. "+s+")":"1 week (EXPIRES_ON: "+s+")");
			grantaccessObj.submitButton.click();
			Thread.sleep(2500);
			
			
			waitForElementToLoad(grantaccessObj.grantedMsg);
			actualArraylist.add(grantaccessObj.grantedMsg.getText());
			actualArraylist.add(grantaccessObj.startDate.getText());
			actualArraylist.add(grantaccessObj.endDate.getText());
			actualArraylist.add(grantaccessObj.status.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	
	@Test
	public void  revokeAdminaccess()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered revokeAdminaccess test");	
			eTest = eReports.createTest("Revoke Admin access ");
			eTest.assignCategory("Grant Temporary Admin Access");
			
			expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			expectedArraylist.add( (language.equals("Arabic"))?"تم تحديث بطاقة الدعم بنجاح":"Support ticket updated successfully");
			expectedArraylist.add((language.equals("Arabic"))?"انتهاء":"Expired");
			
			waitIfElementClickIsIntercepted(dashboardObj.grantTemporaryAdminAccess, "click", "");
		    waitForElementToLoad(grantaccessObj.revoke);
			actualArraylist.add(grantaccessObj.status.getText());
		    autoScrollandClick(grantaccessObj.revoke);
		    waitForElementToBeClickable(grantaccessObj.yes);
		    autoScrollandClick(grantaccessObj.yes);
	        Thread.sleep(3500);;
			waitForElementToLoad(grantaccessObj.revokedMsg);
			actualArraylist.add(grantaccessObj.revokedMsg.getText());
			actualArraylist.add(grantaccessObj.status.getText());
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
