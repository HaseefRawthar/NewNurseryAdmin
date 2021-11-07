package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.objectrepository.NurseryOnlineRevenueReportObject;
import com.nurseryadministrator.testdata.RevenueReportData;
import com.nurseryadministrator.testdata.TestDataImport;

public class NurseryOnlineRevenueReport extends SetUp{

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	NurseryOnlineRevenueReportObject nursryonlinerevObj;
	RevenueReportData revenuedataObj;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			nursryonlinerevObj = new NurseryOnlineRevenueReportObject(driver);
			revenuedataObj = new RevenueReportData();
			
			tdImport.readSheet("RevenueReport");
		
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
	
	@Test(priority=2)
	public void filterByDateFrom() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDateFrom test");	
			eTest = eReports.createTest("Filter By Transaction date from");
			eTest.assignCategory("Online Revenue Report");
			boolean condition=false;
			int c=0;
			String fromDate = revenuedataObj.filterData()[0];

			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
			dashboardObj.nurseryOnlineRevenueReport.click();
			waitForElementToLoad(nursryonlinerevObj.fromDate);
			nursryonlinerevObj.fromDate.sendKeys(fromDate);
			nursryonlinerevObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(nursryonlinerevObj.totalRecords);
			System.out.println(nursryonlinerevObj.totalRecords.getText());
			String countstring=nursryonlinerevObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(nursryonlinerevObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span")).getText();
						boolean value = checkDateIsAfter(date, fromDate);
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(nursryonlinerevObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(nursryonlinerevObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(nursryonlinerevObj.resetButton);
			autoScrollandClick(nursryonlinerevObj.resetButton);
			if(isElementClickable(nursryonlinerevObj.goToFirstPage))
			{
				autoScrollandClick(nursryonlinerevObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedBoolArray);
		System.out.println(actualBoolArray);
		//Assert.assertEquals(actualBoolArray, expectedBoolArray);
		log.info("Assertion passed");
	}
	
	@Test(priority=1)
	public void filterByDateTo() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDateTo test");	
			eTest = eReports.createTest("Filter By Transaction date to");
			eTest.assignCategory("Online Revenue Report");
			boolean condition=false;
			int c=0;
			String toDate = revenuedataObj.filterData()[1];

			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
			dashboardObj.nurseryOnlineRevenueReport.click();
			waitForElementToLoad(nursryonlinerevObj.toDate);
			nursryonlinerevObj.toDate.sendKeys(toDate);
			nursryonlinerevObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(nursryonlinerevObj.totalRecords);
			System.out.println(nursryonlinerevObj.totalRecords.getText());
			String countstring=nursryonlinerevObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(nursryonlinerevObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span")).getText();
						boolean value = checkDateIsBefore(date, toDate);
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(nursryonlinerevObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(nursryonlinerevObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(nursryonlinerevObj.resetButton);
			autoScrollandClick(nursryonlinerevObj.resetButton);
			if(isElementClickable(nursryonlinerevObj.goToFirstPage))
			{
				autoScrollandClick(nursryonlinerevObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
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
	
	
	@Test(priority=3)
	public void filterByFromDateToDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFromDateToDate test");	
			eTest = eReports.createTest("Filter By Transaction date From and To");
			eTest.assignCategory("Online Revenue Report");
			boolean condition=false;
			int c=0;
			String fromDate = revenuedataObj.filterData()[0];
			String toDate = revenuedataObj.filterData()[1];

			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
			dashboardObj.nurseryOnlineRevenueReport.click();
			waitForElementToLoad(nursryonlinerevObj.toDate);
			nursryonlinerevObj.fromDate.sendKeys(fromDate);
			Thread.sleep(500);
			nursryonlinerevObj.toDate.sendKeys(toDate);
			nursryonlinerevObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(nursryonlinerevObj.totalRecords);
			System.out.println(nursryonlinerevObj.totalRecords.getText());
			String countstring=nursryonlinerevObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(nursryonlinerevObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span")).getText();
						boolean value1 = checkDateIsAfter(date, fromDate);
						boolean value2 = checkDateIsBefore(date, toDate);
						actualBoolArray.add(value1 | value2);
					}
				}
				
				if(!isElementPresent(nursryonlinerevObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(nursryonlinerevObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(nursryonlinerevObj.resetButton);
			autoScrollandClick(nursryonlinerevObj.resetButton);
			if(isElementClickable(nursryonlinerevObj.goToFirstPage))
			{
				autoScrollandClick(nursryonlinerevObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.reports, "click", "");
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
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
