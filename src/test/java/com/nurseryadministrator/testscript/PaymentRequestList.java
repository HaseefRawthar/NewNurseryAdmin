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
import com.nurseryadministrator.objectrepository.PaymentInfoObject;
import com.nurseryadministrator.objectrepository.PaymentRequestListObject;
import com.nurseryadministrator.objectrepository.RequestPaymentObject;
import com.nurseryadministrator.testdata.RequestPaymentData;
import com.nurseryadministrator.testdata.TestDataImport;

public class PaymentRequestList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	RequestPaymentObject reqpaymentObj;
	RequestPaymentData reqpaydataObj;
	PaymentRequestListObject payreqlistObj;
	PaymentInfoObject paymentinfoObj;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			reqpaymentObj = new RequestPaymentObject(driver);
			reqpaydataObj = new RequestPaymentData();
			payreqlistObj = new PaymentRequestListObject(driver);
			paymentinfoObj = new PaymentInfoObject(driver);
			
			tdImport.readSheet("PaymentList");
		
			
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
	public void filterByStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStatus test");	
			eTest = eReports.createTest("Filter By Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String stat = reqpaydataObj.filterData()[3];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentName test");	
			eTest = eReports.createTest("Filter By PaymentName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title =reqpaydataObj.filterData()[0];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByStudentName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentName test");	
			eTest = eReports.createTest("Filter By StudentName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String student = reqpaydataObj.filterData()[2];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterBySameDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterBySameDate test");	
			eTest = eReports.createTest("Filter By Same Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String date = reqpaydataObj.filterData()[4];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.dueDateFrom.sendKeys(date);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(date);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span[contains(text(),'"+date+"')]")));
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByDueDateFrom() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDueDateFrom test");	
			eTest = eReports.createTest("Filter By Due Date From");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String fromDate = reqpaydataObj.filterData()[4];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean value = checkDateIsAfter(date, fromDate);
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByDueDateTo() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDueDateTo test");	
			eTest = eReports.createTest("Filter By Due Date To");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String toDate = reqpaydataObj.filterData()[5];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean value = checkDateIsBefore(date, toDate);
						actualBoolArray.add(value);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByDueDateFromAndDueDateTo() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDueDateFromAndDueDateTo test");	
			eTest = eReports.createTest("Filter By Due Date From and Due Date To");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean value1 = checkDateIsBefore(date, toDate);
						boolean value2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						actualBoolArray.add(value1 | value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByGroupName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupName test");	
			eTest = eReports.createTest("filterByGroupName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String groupName = reqpaydataObj.filterData()[1];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(groupName);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						waitForElementToLoad(payreqlistObj.filterButton);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						//driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")).click();
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value = paymentinfoObj.groupName.getText().equals(groupName);
						actualBoolArray.add(value);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentNameAndStudentName() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameAndStudentName test");	
			eTest = eReports.createTest("Filter By Paymentname And StudentName");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title =reqpaydataObj.filterData()[0];
			String student = reqpaydataObj.filterData()[2];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentNameAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameAndGroup test");	
			eTest = eReports.createTest("Filter By Paymentname And Group");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						//driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")).click();
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value2 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentNameAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameAndStatus test");	
			eTest = eReports.createTest("Filter By Paymentname And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String status = reqpaydataObj.filterData()[3];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.status.sendKeys(status);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+status+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentNameAndFromDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameAndFromDate test");	
			eTest = eReports.createTest("Filter By Paymentname And From Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String fromDate = reqpaydataObj.filterData()[4];


			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean value2 = checkDateIsAfter(date, fromDate);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPaymentNameAndToDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameAndToDate test");	
			eTest = eReports.createTest("Filter By Paymentname And To Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String toDate = reqpaydataObj.filterData()[5];


			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean value2 = checkDateIsBefore(date, toDate);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByPymentNameDueDateFromAndDueDateTo() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPymentNameDueDateFromAndDueDateTo test");	
			eTest = eReports.createTest("Filter By PaymentName And Due Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value2 = (date1 | date2);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	public void filterByGroupAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupAndStatus test");	
			eTest = eReports.createTest("Filter By Group And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String group = reqpaydataObj.filterData()[1];
			String stat = reqpaydataObj.filterData()[3];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						waitForElementToLoad(payreqlistObj.filterButton);
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value2 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=16)
	public void filterByDueDateAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByDueDateAndGroup test");	
			eTest = eReports.createTest("Filter By Group And Due Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String group = reqpaydataObj.filterData()[1];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						//driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")).click();
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value1 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						boolean value2 = (date1 | date2);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=17)
	public void filterByStudentAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentAndStatus test");	
			eTest = eReports.createTest("Filter By Student And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String stat = reqpaydataObj.filterData()[3];
			String student = reqpaydataObj.filterData()[2];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=18)
	public void filterByStudentAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentAndDate test");	
			eTest = eReports.createTest("Filter By Student And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						waitForElementToLoad(payreqlistObj.filterButton);
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value2 = (date1 | date2);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=19)
	public void filterByStatusAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStatusAndDate test");	
			eTest = eReports.createTest("Filter By Status And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String stat = reqpaydataObj.filterData()[3];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value2 = (date1 | date2);
						actualBoolArray.add(value1 && value2);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=20)
	public void filterByPaymentNameGroupAndStudent() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameGroupAndStudent test");	
			eTest = eReports.createTest("Filter By Paymentname,Group And Student");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value3 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=21)
	public void filterByPaymentNameGroupAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameGroupAndStatus test");	
			eTest = eReports.createTest("Filter By Paymentname,Group And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];
			String stat = reqpaydataObj.filterData()[3];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value3 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=22)
	public void filterByPymentNameDueDateAndGroup() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPymentNameDueDateFromAndDueDateTo test");	
			eTest = eReports.createTest("Filter By PaymentName,Group And Due Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.dueDateFrom);
			payreqlistObj.title.sendKeys(title);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			payreqlistObj.filterButton.click();
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						//driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")).click();
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value2 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						boolean value3 = (date1 | date2);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=23)
	public void filterByPaymentNameStudentAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameStudentAndStatus test");	
			eTest = eReports.createTest("Filter By Paymentname,Student And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String stat = reqpaydataObj.filterData()[3];
			String student = reqpaydataObj.filterData()[2];

			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=24)
	public void filterByPaymentNameStudentAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameStudentAndDate test");	
			eTest = eReports.createTest("Filter By Paymentname,Student And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value3 = (date1 | date2);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=25)
	public void filterByStudentGroupAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentGroupAndStatus test");	
			eTest = eReports.createTest("Filter By Student,Group And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String stat = reqpaydataObj.filterData()[3];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value3 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=26)
	public void filterByStudentGroupAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentGroupAndDate test");	
			eTest = eReports.createTest("Filter By Student,Group And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						waitForElementToLoad(payreqlistObj.filterButton);
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value2 = (date1 | date2);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value3 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=27)
	public void filterByGroupStatusAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByGroupStatusAndDate test");	
			eTest = eReports.createTest("Filter By Group,Status And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String group = reqpaydataObj.filterData()[1];
			String stat = reqpaydataObj.filterData()[3];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value2 = (date1 | date2);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value3 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=28)
	public void filterByPaymentNameStudentGroupAndStatus() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameStudentGroupAndStatus test");	
			eTest = eReports.createTest("Filter By Payment name,Student,Group And Status");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String stat = reqpaydataObj.filterData()[3];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.status.sendKeys(stat);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value4 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=29)
	public void filterByPaymentNameStudentGroupAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameStudentGroupAndDate test");	
			eTest = eReports.createTest("Filter By Payment name,Student,Group And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value3 = (date1 | date2);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value4 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=30)
	public void filterByStudentGroupStatusAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByStudentGroupStatusAndDate test");	
			eTest = eReports.createTest("Filter By Student,Group,Status And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String stat = reqpaydataObj.filterData()[3];
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value3 = (date1 | date2);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value4 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3 && value4);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=31)
	public void filterByPaymentNameStudentGroupStatusAndDate() 
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered filterByPaymentNameStudentGroupStatusAndDate test");	
			eTest = eReports.createTest("Filter By Payment Name,Student,Group,Status And Date");
			eTest.assignCategory("Request Payment List");
			boolean condition=false;
			int c=0;
			String title = reqpaydataObj.filterData()[0];
			String stat = reqpaydataObj.filterData()[3];
			String group = reqpaydataObj.filterData()[1];
			String student = reqpaydataObj.filterData()[2];
			String fromDate = reqpaydataObj.filterData()[4];
			String toDate = reqpaydataObj.filterData()[5];
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			waitForElementToLoad(payreqlistObj.status);
			Thread.sleep(1000);
			payreqlistObj.groupName.sendKeys(group);
			payreqlistObj.title.sendKeys(title);
			payreqlistObj.status.sendKeys(stat);
			payreqlistObj.studentName.sendKeys(student);
			payreqlistObj.dueDateFrom.sendKeys(fromDate);
			Thread.sleep(200);
			payreqlistObj.dueDateTo.sendKeys(toDate);
			autoScrollandClick(payreqlistObj.filterButton);
			
			Thread.sleep(2000);
			waitForElementToLoad(payreqlistObj.totalRecords);
			System.out.println(payreqlistObj.totalRecords.getText());
			String countstring=payreqlistObj.totalRecords.getText().replaceAll("[^\\d]", "").trim();
			System.out.println(countstring);
			int count = Integer.parseInt(countstring);
			System.out.println(count);
			
			for(int i=0;i<count;i++)
			{
				expectedBoolArray.add(true);
			}
			while(condition==false)
			{
				condition = isElementPresent(payreqlistObj.lastpagecheck);
				for(int i =1;i<=10;i++)
				{
					if(c+i<=count)
					{
						boolean value1 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[contains(text(),'"+title+"')]")));
						boolean value2 =isElementPresent(driver.findElement(By.xpath("//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[contains(text(),'"+stat+"')]")));
						boolean value3 =isElementPresent(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]/a[contains(text(),'"+student+"')]")));
						String date = driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]/span")).getText();
						boolean date1 = checkDateIsBefore(date, toDate);
						boolean date2 = checkDateIsAfter(date, fromDate);
						System.out.println(date);
						boolean value4 = (date1 | date2);
						waitIfElementClickIsIntercepted(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper["+i+"]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")), "click", "");
						waitForElementToLoad(paymentinfoObj.groupName);
						boolean value5 = paymentinfoObj.groupName.getText().equals(group);
						paymentinfoObj.backButton.click();
						waitForElementToLoad(payreqlistObj.filterButton);
						actualBoolArray.add(value1 && value2 && value3 && value4 && value5);
					}
				}
				
				if(!isElementPresent(payreqlistObj.lastpagecheck))
				{
					waitIfElementClickIsIntercepted(payreqlistObj.goToNextPage, "click", "");
				}
				c=c+10;
			}
			waitForElementToLoad(payreqlistObj.resetButton);
			autoScrollandClick(payreqlistObj.resetButton);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=32)
	public void verifySortTitle()
	{
		try
		{
			
			log.info("Entered verifySortTitle test");	
			eTest = eReports.createTest("Verify Sort Title");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			expectedMsg = verifySortButton(payreqlistObj.titleSortButton, payreqlistObj.sortedTitle);
			actualMsg = payreqlistObj.sortedTitle.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=33)
	public void verifySortStudentName()
	{
		try
		{
			
			log.info("Entered verifySortStudentName test");	
			eTest = eReports.createTest("Verify Sort StudentName");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			expectedMsg = verifySortButton(payreqlistObj.studentSortButton, payreqlistObj.sortedStudent);
			actualMsg = payreqlistObj.sortedStudent.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=34)
	public void verifySortDueDate()
	{
		try
		{
			
			log.info("Entered verifySortDueDate test");	
			eTest = eReports.createTest("Verify Sort DueDate");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			
			scrollToTheElement(payreqlistObj.studentSortButton);
			expectedMsg = verifySortButton(payreqlistObj.studentSortButton, payreqlistObj.sortedStudent);
			actualMsg = payreqlistObj.sortedStudent.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
	
	@Test(priority=35)
	public void verifySortStatus()
	{
		try
		{
			
			log.info("Entered verifySortStatus test");	
			eTest = eReports.createTest("Verify Sort Status");
			eTest.assignCategory("Request Payment List");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			
			scrollToTheElement(payreqlistObj.statusSortButton);
			expectedMsg = verifySortButton(payreqlistObj.statusSortButton, payreqlistObj.sortedStatus);
			actualMsg = payreqlistObj.sortedStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
