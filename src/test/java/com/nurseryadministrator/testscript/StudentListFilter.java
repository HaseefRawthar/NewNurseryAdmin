package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddStudentObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.objectrepository.StudentDetailsObject;
import com.nurseryadministrator.objectrepository.StudentListObject;
import com.nurseryadministrator.testdata.AddStudentData;
import com.nurseryadministrator.testdata.TestDataImport;

public class StudentListFilter extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddStudentObject addstudentObj;
	AddStudentData addstudentdataObj;
	StudentListObject studentlistObj;
	StudentDetailsObject studentdetailObj;
	String[] filterData;
	String[] editDetails;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
		    addstudentObj = new AddStudentObject(driver);
		    addstudentdataObj = new AddStudentData();
		    studentlistObj = new StudentListObject(driver);
		    studentdetailObj = new StudentDetailsObject(driver);
			
		    tdImport.readSheet("StudentList");
		    
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
	public void filterByFirstName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstName test");
			eTest = eReports.createTest("Filter By FirstName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByLastName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByLastName test");
			eTest = eReports.createTest("Filter By LastName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String lastName = filterData[1];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByParent1Name() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByParent1Name test");
			eTest = eReports.createTest("Filter By ParentName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent2;
			boolean parent1;
			filterData = addstudentdataObj.filterData();
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						actualBoolArray.add(parent1 | parent2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStatus test");
			eTest = eReports.createTest("Filter By Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String stat = filterData[5];
		
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.status);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");
			eTest = eReports.createTest("Filter By GroupName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.status);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByGroupType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupType test");
			eTest = eReports.createTest("Filter By GroupType");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String type = filterData[4];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.status);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameAndLastName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameAndLastName test");
			eTest = eReports.createTest("Filter By FirstName and LastName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameAndParent() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameAndParent test");
			eTest = eReports.createTest("Filter By FirstName and ParentName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value2 = parent1 | parent2;
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameAndGroup test");
			eTest = eReports.createTest("Filter By FirstName and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameAndGroupType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameAndGroup test");
			eTest = eReports.createTest("Filter By FirstName and GroupType");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String type = filterData[4];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameAndStatus test");
			eTest = eReports.createTest("Filter By FirstName and Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByParentAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByParentAndGroup test");
			eTest = eReports.createTest("Filter By ParentName and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String group = filterData[3];
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value2 = parent1 | parent2;
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByParentAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByParentAndGroup test");
			eTest = eReports.createTest("Filter By ParentName and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String type = filterData[4];
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value2 = parent1 | parent2;
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByParentAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByParentAndStatus test");
			eTest = eReports.createTest("Filter By ParentName and Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String stat = filterData[5];
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value2 = parent1 | parent2;
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByGroupAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupAndType test");
			eTest = eReports.createTest("Filter By type and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String type = filterData[4];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByGroupAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupAndStatus test");
			eTest = eReports.createTest("Filter By status and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String stat = filterData[5];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByTypeAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByTypeAndGroup test");
			eTest = eReports.createTest("Filter By Group and Type");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String type = filterData[4];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameLastNameAndParent() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameLastNameAndParent test");
			eTest = eReports.createTest("Filter By FirstName,LastName and ParentName");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String parentName = filterData[2];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value3 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameLastNameAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameLastNameAndGroup test");
			eTest = eReports.createTest("Filter By FirstName,LastName and Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterByFirstNameLastNameAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByFirstNameLastNameAndStatus test");
			eTest = eReports.createTest("Filter By FirstName,LastName and Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameParentAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudentByFirstNameParentAndStatus test");
			eTest = eReports.createTest("Filter By FirstName,Parent And Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value3 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameParentAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By All Fields");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];
			String group = filterData[3];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value3 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameParentGroupAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudentByFirstNameParentGroupAndType test");
			eTest = eReports.createTest("Filter By FirstName,Parent,Group And Type");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];
			String group = filterData[3];
			String type = filterData[4];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value4 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameLastNameParentAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By FirstName,LastName,Parent And Group");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String parentName = filterData[2];
			String group = filterData[3];
			String type = filterData[4];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						boolean value4 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean value5 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value6 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4 && value5 && value6);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameLastNameParentAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By FirstName,LastName,Parent And Type");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String parentName = filterData[2];
			String type = filterData[4];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value4 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameLastNameParentAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By FirstName,LastName,Parent And Status");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];
			String group = filterData[3];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value4 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudentByFirstNameParentGroupStatusAndType() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By FirstName,Parent,Group,Status And Type");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String parentName = filterData[2];
			String group = filterData[3];
			String type = filterData[4];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean value4 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value5 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4 && value5);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
	public void filterStudent() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterStudent test");
			eTest = eReports.createTest("Filter By All Fields");
			eTest.assignCategory("Student - Student List");
			boolean condition=false;
			int c=0;
			boolean parent1;
			boolean parent2;
			filterData = addstudentdataObj.filterData();
			String firstName = filterData[0];
			String lastName = filterData[1];
			String parentName = filterData[2];
			String group = filterData[3];
			String type = filterData[4];
			String stat = filterData[5];

			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			waitForElementToLoad(studentlistObj.firstName);
			studentlistObj.firstName.sendKeys(firstName);
			studentlistObj.lastName.sendKeys(lastName);
			studentlistObj.parentName.sendKeys(parentName);
			studentlistObj.groupName.sendKeys(group);
			studentlistObj.groupType.sendKeys(type);
			studentlistObj.status.sendKeys(stat);
			studentlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(studentlistObj.totalRecords);
			System.out.println(studentlistObj.totalRecords.getText());
			String countstring=studentlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(studentlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[contains(text(),'"+firstName+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[contains(text(),'"+lastName+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/a[contains(text(),'"+group+"')]")));
						boolean value4 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]/span[contains(text(),'"+type+"')]")));
						boolean value5 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]/span[contains(text(),'"+stat+"')]")));
						try {
							parent1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent1 = false;
						}
						try {
							parent2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[contains(text(),'"+parentName+"')]")));
						}
						catch(NoSuchElementException e) {
							parent2 = false;
						}
						boolean value6 = parent1 | parent2;
						actualBoolArray.add(value1 && value2 && value3 && value4 && value5 && value6);
					}
				}
				
				if(!isElementPresent(studentlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(studentlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(studentlistObj.resetButton);
			autoScrollandClick(studentlistObj.resetButton);
			autoScrollandClick(dashboardObj.students);
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
