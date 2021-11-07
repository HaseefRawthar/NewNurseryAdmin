package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
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

public class AdminList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddAdminObject addadminObj;
	AddNurseryAdminData admindataObj;
	AdminListObject adminlistObj;
	AdminViewObject adminviewObj;
	String[] adminData;
	String[] filterData;
	
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
			
			loginObj.logIn("teste1exp@gmail.com", "0pR6Gm");
		
			//loginObj.logIn(userEmail, userPassword);
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
	public void deactivateAdmin()
	{
		try
		{
			log.info("Entered deactivateAdmin test");		
			eTest = eReports.createTest("Deactivate Admin");
			eTest.assignCategory("Nursery Admin - List Admin");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			adminData = admindataObj.getAdminData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث حالة حساب الاداري بنجاح");
				expectedArraylist.add("تفعيل");
				expectedArraylist.add("غير مفعل");
			}
			else
			{
				expectedArraylist.add("Admin status updated Successfully");
				expectedArraylist.add("ACTIVATE");
				expectedArraylist.add("Inactive");
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"مفعل":"Active", adminData[3]);
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(adminlistObj.viewButton, "click", "");
			waitForElementToLoad(adminviewObj.deactivateButton);
			adminviewObj.deactivateButton.click();
			waitForElementToLoad(adminviewObj.yes);
			adminviewObj.yes.click();
			waitForElementToLoad(adminviewObj.statusUpdateMsg);
			actualArraylist.add(adminviewObj.statusUpdateMsg.getText());
			waitForElementToLoad(adminviewObj.activateButton);
			actualArraylist.add(adminviewObj.activateButton.getText());
			actualArraylist.add(adminviewObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedArraylist);
		System.out.println(actualArraylist);
		Assert.assertEquals(expectedArraylist, actualArraylist);
		log.info("Assertion passed");
	}
	
	@Test(priority=2)
	public void activateAdmin()
	{
		try
		{
			log.info("Entered deactivateAdmin test");		
			eTest = eReports.createTest("Activate Admin");
			eTest.assignCategory("Nursery Admin - List Admin");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			adminData = admindataObj.getAdminData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث حالة حساب الاداري بنجاح");
				expectedArraylist.add("تعطيل");
				expectedArraylist.add("مفعل");
			}
			else
			{
				expectedArraylist.add("Admin status updated Successfully");
				expectedArraylist.add("DEACTIVATE");
				expectedArraylist.add("Active");
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"غير مفعل":"Inactive", adminData[3]);
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(adminlistObj.viewButton, "click", "");
			waitForElementToLoad(adminviewObj.activateButton);
			adminviewObj.activateButton.click();
			waitForElementToLoad(adminviewObj.yes);
			adminviewObj.yes.click();
			waitForElementToLoad(adminviewObj.statusUpdateMsg);
			actualArraylist.add(adminviewObj.statusUpdateMsg.getText());
			waitForElementToLoad(adminviewObj.deactivateButton);
			actualArraylist.add(adminviewObj.deactivateButton.getText());
			actualArraylist.add(adminviewObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedArraylist);
		System.out.println(actualArraylist);
		Assert.assertEquals(expectedArraylist, actualArraylist);
		log.info("Assertion passed");
	}
	
	@Test(priority=4)
	public void editNurseryAdmin()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered editNurseryAdmin test");	
			eTest = eReports.createTest("Edit Nursery Admin");
			eTest.assignCategory("Nursery Admin - List Admin");
			
			expectedArraylist.add((language.equals("Arabic"))?"تم تحديث حالة حساب اداري الحضانة بنجاح":"Nursery Admin updated successfully");
			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"مفعل":"Active", adminData[3]);
			waitIfElementClickIsIntercepted(adminlistObj.editButton, "click", "");
			waitForElementToLoad(addadminObj.firstName);
			if(language.equals("Arabic"))
			{
				addadminObj.firstName.sendKeys("__تم تحريره");
				addadminObj.titleEng.sendKeys("__تم تحريره");
				addadminObj.titleArb.sendKeys("__تم تحريره");
			}
			else
			{
				addadminObj.firstName.sendKeys("__edited");
				addadminObj.titleEng.sendKeys("__edited");
				addadminObj.titleArb.sendKeys("__edited");
			}
			
			autoScrollandClick(addadminObj.saveButton);
			waitForElementToLoad(addadminObj.adminUpdateSuccessMsg);
			actualArraylist.add(addadminObj.adminUpdateSuccessMsg.getText());
			adminData = admindataObj.getEditAdminData();
			waitForElementToBeClickable(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			Thread.sleep(2000);
			adminlistObj.filterAdmin(adminData[0], (language.equals("Arabic"))?"مفعل":"Active", adminData[3]);
			waitIfElementClickIsIntercepted(adminlistObj.viewButton, "click", "");
			expectedArraylist.add(adminData[0]+" "+adminData[1]+" "+adminData[2]);
			expectedArraylist.add(adminData[3]);
			expectedArraylist.add(adminData[5].replaceAll("[^\\d+]", "")+" "+adminData[6]);
			expectedArraylist.add((language.equals("Arabic"))?"مفعل":"Active");
			expectedArraylist.add(adminData[7]);
			expectedArraylist.add(adminData[8]);
			
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
	public void filterByName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByName test");
			eTest = eReports.createTest("Filter By Name");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			String nam = filterData[0];
			adminlistObj.filterAdmin(nam,"" ,"" );
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]/span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	@Test(priority=5)
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStatus test");
			eTest = eReports.createTest("Filter By Status");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.status);
			String stat = filterData[1];
			adminlistObj.status.sendKeys(stat);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	@Test(priority=6)
	public void filterByEmail() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByEmail test");
			eTest = eReports.createTest("Filter By Email");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();
			String email = filterData[2];

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.email);
			adminlistObj.email.sendKeys(email);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+email+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	
	@Test(priority=7)
	public void filterByNameAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndStatus test");
			eTest = eReports.createTest("Filter By Name and Status");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();
			String nam = filterData[0];
			String stat = filterData[1];

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.email);
			adminlistObj.name.sendKeys(nam);
			adminlistObj.status.sendKeys(stat);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]/span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	@Test(priority=8)
	public void filterByEmailAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByEmailAndStatus test");
			eTest = eReports.createTest("Filter By Email and Status");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();
			String email = filterData[2];
			String stat = filterData[1];

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.email);
			adminlistObj.email.sendKeys(email);
			adminlistObj.status.sendKeys(stat);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+email+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	@Test(priority=9)
	public void filterByNameAndEmail() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByEmail test");
			eTest = eReports.createTest("Filter By Name and Email");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();
			String nam = filterData[0];
			String email = filterData[2];

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.email);
			adminlistObj.name.sendKeys(nam);
			adminlistObj.email.sendKeys(email);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+email+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]/span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	@Test(priority=10)
	public void filterByNameEmailAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByEmailAndStatus test");
			eTest = eReports.createTest("Filter By Email and Status");
			eTest.assignCategory("Nursery Admin - List Admin");
			boolean condition=false;
			int c=0;
			filterData = admindataObj.filterData();
			String nam = filterData[0];
			String email = filterData[2];
			String stat = filterData[1];

			adminData = admindataObj.getAdminData();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			waitForElementToLoad(adminlistObj.email);
			adminlistObj.name.sendKeys(nam);
			adminlistObj.email.sendKeys(email);
			adminlistObj.status.sendKeys(stat);
			adminlistObj.filterButton.click();
			Thread.sleep(2000);

			waitForElementToLoad(adminlistObj.totalRecords);
			System.out.println(adminlistObj.totalRecords.getText());
			String countstring=adminlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(adminlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[contains(text(),'"+email+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]/span[contains(text(),'"+nam+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(adminlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(adminlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(adminlistObj.resetButton);
			autoScrollandClick(adminlistObj.resetButton);
			if(isElementClickable(adminlistObj.goToFirstPage))
			{
				autoScrollandClick(adminlistObj.goToFirstPage);
				Thread.sleep(1500);
			}
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
	
	
	@Test(priority=11)
	public void verifySortForName()
	{
		try
		{
			log.info("Entered verifySortForName test");	
			eTest = eReports.createTest("Verify Sort For Name");
			eTest.assignCategory("Nursery Admin - List Admin");
			 
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			
			expectedMsg = verifySortButton(adminlistObj.nameSortButton, adminlistObj.sortedName);
			actualMsg = adminlistObj.sortedName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	
	
	@Test(priority=12)
	public void verifySortForPhone()
	{
		try
		{
			log.info("Entered verifySortForPhone test");	
			eTest = eReports.createTest("Verify Sort For Phone");
			eTest.assignCategory("Nursery Admin - List Admin");
			 
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			
			expectedMsg = verifySortButton(adminlistObj.phoneSortButton, adminlistObj.sortedPhone);
			actualMsg = adminlistObj.sortedPhone.getText();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=13)
	public void verifySortForEmail()
	{
		try
		{
			log.info("Entered verifySortForEmail test");	
			eTest = eReports.createTest("Verify Sort For Email");
			eTest.assignCategory("Nursery Admin - List Admin");
			 
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			
			expectedMsg = verifySortButton(adminlistObj.emailSortButton, adminlistObj.sortedEmail);
			actualMsg = adminlistObj.sortedEmail.getText();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=14)
	public void verifySortForStatus()
	{
		try
		{
			log.info("Entered verifySortForStatus test");	
			eTest = eReports.createTest("Verify Sort For Status");
			eTest.assignCategory("Nursery Admin - List Admin");
			 
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
			dashboardObj.adminList.click();
			
			expectedMsg = verifySortButton(adminlistObj.statusSortButton, adminlistObj.sortedStatus);
			actualMsg = adminlistObj.sortedStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.nurseryAdmin, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
	
}
