package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddGroupObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.GroupListObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.objectrepository.ViewGroupObject;
import com.nurseryadministrator.testdata.AddGroupData;
import com.nurseryadministrator.testdata.GroupListFilterData;
import com.nurseryadministrator.testdata.TestDataImport;

public class GroupListFilter extends SetUp {


	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddGroupObject addgroupObj;
	AddGroupData addgrpdataObj;
	GroupListObject grouplistObj;
	ViewGroupObject viewgroupObj;
	GroupListFilterData grplistfilterdataObj;
	String[] filterData;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addgrpdataObj = new AddGroupData();
			addgroupObj = new AddGroupObject(driver);
			grouplistObj = new GroupListObject(driver);
			viewgroupObj = new ViewGroupObject(driver);
			grplistfilterdataObj = new GroupListFilterData();
			
			tdImport.readSheet("ListGroup");
		
			
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
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");
			eTest = eReports.createTest("Filter By GroupName");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.status);
			grouplistObj.groupName.sendKeys(nam);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	//@Test(priority=1)
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered activateGroup test");	
			eTest = eReports.createTest("Filter By Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String stat = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.status);
			grouplistObj.status.sendKeys(stat);
			grouplistObj.filterButton.click();
			
			Thread.sleep(15000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	public void filterByGroupType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupType test");
			eTest = eReports.createTest("Filter By GroupType");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String typ = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupType);
			grouplistObj.groupType.sendKeys(typ);
			autoScrollandClick(grouplistObj.filterButton);
			//grouplistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+typ+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=4)
	public void filterByTeacher() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByTeacher test");
			eTest = eReports.createTest("Filter By Teacher");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String teacher = filterData[1];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.teacherName);
			grouplistObj.teacherName.sendKeys(teacher);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=5)
	public void filterByNameAndTeacher() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndTeacher test");
			eTest = eReports.createTest("Filter By Group name And Teacher");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String teacher = filterData[1];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.teacherName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.teacherName.sendKeys(teacher);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2=isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	
	@Test(priority=6)
	public void filterByNameAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndType test");
			eTest = eReports.createTest("Filter By Group name And Type");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.teacherName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean  value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=7)
	public void filterByNameAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndStatus test");
			eTest = eReports.createTest("Filter By Group name And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String stat = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.status.sendKeys(stat);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=8)
	public void filterByTeacherAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndStatus test");
			eTest = eReports.createTest("Filter By Teacher And Type");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String teacher = filterData[1];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.teacherName);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean  value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=9)
	public void filterByTypeAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByTypeAndStatus test");
			eTest = eReports.createTest("Filter By Type And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String type = filterData[2];
			String stat = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupType);
			grouplistObj.groupType.sendKeys(type);
			grouplistObj.status.sendKeys(stat);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean  value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=10)
	public void filterByTeacherAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByTeacherAndStatus test");
			eTest = eReports.createTest("Filter By Teacher And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String teacher = filterData[1];
			String stat = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.teacherName);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.status.sendKeys(stat);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean  value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	//@Test(priority=11)
	public void filterByNameTeacherAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameTeacherAndStatus test");
			eTest = eReports.createTest("Filter By Name,Teacher And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String teacher = filterData[1];
			String stat = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.status.sendKeys(stat);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=12)
	public void filterByNameTeacherAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameTeacherAndType test");
			eTest = eReports.createTest("Filter By Name,Teacher And Type");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String teacher = filterData[1];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=13)
	public void filterByNameTypeAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameTypeAndStatus test");
			eTest = eReports.createTest("Filter By Name,Type And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String stat = filterData[3];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.status.sendKeys(stat);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=14)
	public void filterByTeacherTypeAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByTeacherTypeAndStatus test");
			eTest = eReports.createTest("Filter By Teacher,Type And Status");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String teacher = filterData[1];
			String stat = filterData[3];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.status.sendKeys(stat);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
	
	@Test(priority=15)
	public void filterGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterGroup test");
			eTest = eReports.createTest("Filter By All fields");
			eTest.assignCategory("Groups - Group List");
			boolean condition=false;
			int c=0;
			filterData = grplistfilterdataObj.filterData();
			String nam = filterData[0];
			String teacher = filterData[1];
			String stat = filterData[3];
			String type = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.sendKeys(nam);
			grouplistObj.teacherName.sendKeys(teacher);
			grouplistObj.status.sendKeys(stat);
			grouplistObj.groupType.sendKeys(type);
			autoScrollandClick(grouplistObj.filterButton);
			
			Thread.sleep(3000);
			waitForElementToLoad(grouplistObj.totalRecords);
			System.out.println(grouplistObj.totalRecords.getText());
			String countstring=grouplistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(grouplistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+nam+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//a[contains(text(),'"+teacher+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value4 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(grouplistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(grouplistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(grouplistObj.resetButton);
			autoScrollandClick(grouplistObj.resetButton);
			if(isElementClickable(grouplistObj.goToFirstPage))
			{
				autoScrollandClick(grouplistObj.goToFirstPage);
				Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
