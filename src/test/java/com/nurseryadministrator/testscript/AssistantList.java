package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddEmployeeObject;
import com.nurseryadministrator.objectrepository.AssistantListObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.EmployeeDeatailsObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.testdata.AddEmployeeData;
import com.nurseryadministrator.testdata.TestDataImport;

public class AssistantList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddEmployeeObject addemployeeObj;
	AddEmployeeData addemployeedataObj;
	AssistantListObject assistantlistObj;
	EmployeeDeatailsObject employeedetailObj;
	String[] employeeData;
	String[] roleData;
	String[] editData;
	String[] filterData;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addemployeeObj = new AddEmployeeObject(driver);
			addemployeedataObj =new AddEmployeeData();
			assistantlistObj = new AssistantListObject(driver);
			employeedetailObj = new EmployeeDeatailsObject(driver);
			
		    tdImport.readSheet("AddEmployee");
			
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
	public void viewAssistantDetails()
	{
		try
		{
			log.info("Entered viewAssistantDetails test");		
			eTest = eReports.createTest("View Assistant Details");
			eTest.assignCategory("Employee - List Assistant");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			
			employeeData = addemployeedataObj.getEmployeeDetails();
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = "ASSISTANT";
			String fullname = employeeData[0]+" "+employeeData[1]+" "+employeeData[2];
			expectedArraylist.add(fullname);
			expectedArraylist.add(employeeData[3]);
			expectedArraylist.add(employeeData[4]);
			expectedArraylist.add(employeeData[5].replaceAll("[^\\d+]", "")+" "+employeeData[6]);
			for(int i=0;i<3;i++)
			{
				expectedArraylist.add(roleData[i]);
			}
			roleData[3] = (language.equals("Arabic"))?"غير معين":"Unassigned";;
			expectedArraylist.add(roleData[3]);
			assistantlistObj.filterAssistant(fullname,"","");
			Thread.sleep(2000);
			autoScrollandClick(assistantlistObj.viewButton);
			//waitIfElementClickIsIntercepted(nurselistObj.viewButton, "click", "");
			
			waitForElementToLoad(employeedetailObj.email);
			actualArraylist.add(employeedetailObj.name.getText());
			actualArraylist.add(employeedetailObj.email.getText());
			actualArraylist.add(employeedetailObj.nationality.getText());
			actualArraylist.add(employeedetailObj.phone.getText());
			actualArraylist.add(employeedetailObj.role.getText());
			actualArraylist.add(employeedetailObj.titleEng.getText());
			actualArraylist.add(employeedetailObj.titleArab.getText());
			actualArraylist.add(employeedetailObj.group.getText());
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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

	@Test(priority=2)
	public void deactivateAssistant()
	{
		try
		{
			log.info("Entered deactivateAssistant test");		
			eTest = eReports.createTest("Deactivate Assistant");
			eTest.assignCategory("Employee - List Assistant");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تفعيل");
				expectedArraylist.add("غير مفعل");
			}
			else
			{
				expectedArraylist.add("Employee status updated Successfully");
				expectedArraylist.add("ACTIVATE");
				expectedArraylist.add("Inactive");
			}
			
			employeeData = addemployeedataObj.getEmployeeDetails();
			String fullname = employeeData[0]+" "+employeeData[1]+" "+employeeData[2];
			assistantlistObj.filterAssistant(fullname, "", "");
			Thread.sleep(2000);
			autoScrollandClick(assistantlistObj.viewButton);
			waitForElementToLoad(employeedetailObj.deactivateButton);
			employeedetailObj.deactivateButton.click();
			waitForElementToLoad(employeedetailObj.yes);
			employeedetailObj.yes.click();
			waitForElementToLoad(employeedetailObj.statUpdateSucessMsg);
			actualArraylist.add(employeedetailObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(employeedetailObj.activateButton);
			actualArraylist.add(employeedetailObj.activateButton.getText());
			actualArraylist.add(employeedetailObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	public void activateAssistant()
	{
		try
		{
			log.info("Entered activateAssistant test");		
			eTest = eReports.createTest("Activate Assistant");
			eTest.assignCategory("Employee - List Assistant");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تعطيل");
				expectedArraylist.add("مفعل");
			}
			else
			{
				expectedArraylist.add("Employee status updated Successfully");
				expectedArraylist.add("DEACTIVATE");
				expectedArraylist.add("Active");
			}
			
			employeeData = addemployeedataObj.getEmployeeDetails();
			String fullname = employeeData[0]+" "+employeeData[1]+" "+employeeData[2];
			assistantlistObj.filterAssistant(fullname, "", "");
			Thread.sleep(2000);
			autoScrollandClick(assistantlistObj.viewButton);
			waitForElementToLoad(employeedetailObj.activateButton);
			employeedetailObj.activateButton.click();
			waitForElementToLoad(employeedetailObj.yes);
			employeedetailObj.yes.click();
			waitForElementToLoad(employeedetailObj.statUpdateSucessMsg);
			actualArraylist.add(employeedetailObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(employeedetailObj.deactivateButton);
			actualArraylist.add(employeedetailObj.deactivateButton.getText());
			actualArraylist.add(employeedetailObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	
	@Test(priority=5)
	public void editAndViewAssistantDetails()
	{
		try
		{
			log.info("Entered editAndViewAssistantDetails test");		
			eTest = eReports.createTest("Edit And View Assistant Details");
			eTest.assignCategory("Employee - List Assistant");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			
			expectedArraylist.add((language.equals("Arabic"))?"تم تحديث الموظف بنجاح":"Employee Updated Successfully");
			employeeData = addemployeedataObj.getEmployeeDetails();
			String fullname = employeeData[0]+" "+employeeData[1]+" "+employeeData[2];
			assistantlistObj.filterAssistant(fullname, "", "");
			Thread.sleep(2000);
			autoScrollandClick(assistantlistObj.editButton);
			editData = addemployeedataObj.editData();
			fullname = editData[0]+" "+editData[1]+" "+editData[2];
			expectedArraylist.add(fullname);
			expectedArraylist.add(editData[3]);
			expectedArraylist.add(editData[4]);
			waitForElementToLoad(addemployeeObj.firstName);
			if(language.equals("Arabic"))
			{
				addemployeeObj.firstName.sendKeys("__تم تحريره");
				addemployeeObj.middleName.sendKeys("__تم تحريره");
				addemployeeObj.lastName.sendKeys("__تم تحريره");
				addemployeeObj.titleEng.sendKeys("__تم تحريره");
				addemployeeObj.titleArab.sendKeys("__تم تحريره");
			}
			else
			{
				addemployeeObj.firstName.sendKeys("__edited");
				addemployeeObj.middleName.sendKeys("__edited");
				addemployeeObj.lastName.sendKeys("__edited");
				addemployeeObj.titleEng.sendKeys("__edited");
				addemployeeObj.titleArab.sendKeys("__edited");
			}
			
			autoScrollandClick(assistantlistObj.saveButton);
			waitForElementToLoad(assistantlistObj.updateSucessMsg);
			actualArraylist.add(assistantlistObj.updateSucessMsg.getText());
			waitForElementToLoad(assistantlistObj.resetButton);
			assistantlistObj.resetButton.click();
			Thread.sleep(1500);
			assistantlistObj.filterAssistant(fullname, "", "");
			Thread.sleep(2000);
			//autoScrollandClick(assistantlistObj.viewButton);
			waitIfElementClickIsIntercepted(assistantlistObj.viewButton, "click", "");
			
			waitForElementToLoad(employeedetailObj.email);
			actualArraylist.add(employeedetailObj.name.getText());
			actualArraylist.add(employeedetailObj.titleEng.getText());
			actualArraylist.add(employeedetailObj.titleArab.getText());
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	
	@Test(priority=6)
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");
			eTest = eReports.createTest("Filter By GroupName");
			eTest.assignCategory("Employee - List Assistant");
			boolean condition=false;
			int c=0;

			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			String grupname = addemployeedataObj.assistantFilterData()[2];
			waitForElementToLoad(assistantlistObj.group);
			assistantlistObj.group.sendKeys(grupname);
			assistantlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(assistantlistObj.totalRecords);
			System.out.println(assistantlistObj.totalRecords.getText());
			String countstring=assistantlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(assistantlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[1]//a[contains(text(),'"+grupname+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(assistantlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(assistantlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(assistantlistObj.resetButton);
			autoScrollandClick(assistantlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	public void filterByName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByName test");
			eTest = eReports.createTest("Filter By Name");
			eTest.assignCategory("Employee - List Assistant");
			boolean condition=false;
			int c=0;
			

			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			employeeData = addemployeedataObj.getEmployeeDetails();
			String fullname = addemployeedataObj.assistantFilterData()[0];
			waitForElementToLoad(assistantlistObj.group);
			assistantlistObj.filterAssistant(fullname, "", "");
			Thread.sleep(2000);

			waitForElementToLoad(assistantlistObj.totalRecords);
			System.out.println(assistantlistObj.totalRecords.getText());
			String countstring=assistantlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(assistantlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]//div[contains(text(),'"+fullname+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(assistantlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(assistantlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(assistantlistObj.resetButton);
			autoScrollandClick(assistantlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	public void filterByNameAndGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByNameAndGroupName test");
			eTest = eReports.createTest("Filter By Assistant name and GroupName");
			eTest.assignCategory("Employee - List Assistant");
			boolean condition=false;
			int c=0;

			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			String grupname = addemployeedataObj.assistantFilterData()[2];
			String fullname = addemployeedataObj.assistantFilterData()[0];
			waitForElementToLoad(assistantlistObj.group);
			assistantlistObj.name.sendKeys(fullname);
			assistantlistObj.group.sendKeys(grupname);
			assistantlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(assistantlistObj.totalRecords);
			System.out.println(assistantlistObj.totalRecords.getText());
			String countstring=assistantlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(assistantlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]//div[contains(text(),'"+fullname+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[1]//a[contains(text(),'"+grupname+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(assistantlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(assistantlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(assistantlistObj.resetButton);
			autoScrollandClick(assistantlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
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
	public void verifySorForName()
	{
		try
		{
			
			log.info("Entered verifySorForName test");
			eTest = eReports.createTest("Verify Sor For Name");
			eTest.assignCategory("Employee - List Assistant");
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			expectedMsg = verifySortButton(assistantlistObj.nameSortButton, assistantlistObj.sortedName);
			actualMsg = assistantlistObj.sortedName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	
	
	@Test(priority=8)
	public void verifySorForPhone()
	{
		try
		{
			
			log.info("Entered verifySorForPhone test");
			eTest = eReports.createTest("Verify Sort For Phone");
			eTest.assignCategory("Employee - List Assistant");
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			expectedMsg = verifySortButton(assistantlistObj.phoneSortButton, assistantlistObj.sortedPhone);
			actualMsg = assistantlistObj.sortedPhone.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	@Test(priority=9)
	public void verifySorForStatus()
	{
		try
		{
			
			log.info("Entered verifySorForStatus test");
			eTest = eReports.createTest("Verify Sort For Status");
			eTest.assignCategory("Employee - List Assistant");
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.assistantList.click();
			expectedMsg = verifySortButton(assistantlistObj.statusSortButton, assistantlistObj.sortedStatus);
			actualMsg = assistantlistObj.sortedStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion passed");
	}
	
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
