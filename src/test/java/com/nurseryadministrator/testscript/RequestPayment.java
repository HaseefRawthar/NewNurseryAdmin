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

public class RequestPayment extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	RequestPaymentObject reqpaymentObj;
	RequestPaymentData reqpaydataObj;
	PaymentRequestListObject payreqlistObj;
	PaymentInfoObject paymentinfoObj;
	String[] paymentData;
	String[] groupData;
	String[] attachments;
	String[] studentName;
	String[] lastnName;
	
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
			
			tdImport.readSheet("AddGroup");
		
			
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
	public void verifyMandatoryMsgVisibile()
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered createPaymentRequest test");	
			eTest = eReports.createTest("Verify Mandatory Message");
			eTest.assignCategory("Request Payment");
			
			for(int i=0;i<4;i++)
			{
				expectedBoolArray.add(true);
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.requestPayment.click();
			waitForElementToLoad(reqpaymentObj.addButton);
			autoScrollandClick(reqpaymentObj.addButton);
			waitForElementToLoad(reqpaymentObj.titleMandatoryMsg);
			actualBoolArray.add(isElementPresent(reqpaymentObj.titleMandatoryMsg));
			actualBoolArray.add(isElementPresent(reqpaymentObj.amountMandatoryMsg));
			actualBoolArray.add(isElementPresent(reqpaymentObj.descriptionMandatoryMsg));
			waitForElementToLoad(reqpaymentObj.recipientMandatoryMsg);
			actualBoolArray.add(isElementPresent(reqpaymentObj.recipientMandatoryMsg));
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
	public void createPaymentRequest()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered createPaymentRequest test");	
			eTest = eReports.createTest("Create Payment Request");
			eTest.assignCategory("Request Payment");
			
			expectedArraylist.add((language.equals("Arabic"))?"تم انشاء طلب الدفع بنجاح":"Payment request created successfully");
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.requestPayment.click();
			reqpaydataObj.generateFakeData();
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			lastnName = reqpaydataObj.getStudentlastName();
			attachments = reqpaydataObj.attachments();
			for(int i=0;i<studentName.length;i++)
			{
				expectedArraylist.add(studentName[i]+" "+studentName[i]+" "+studentName[i]+" "+lastnName[i]);
			}
			reqpaymentObj.addRequestPayment(paymentData);
			reqpaymentObj.group.click();
			waitForElementToLoad(reqpaymentObj.addGroup);
			autoScrollandClick(reqpaymentObj.addGroup);
			reqpaymentObj.filterGroup(groupData[0], groupData[1]);
			autoScrollandClick(reqpaymentObj.selectButton);
			reqpaymentObj.addAttachment(attachments, 1);
			autoScrollandClick(reqpaymentObj.addButton);
			waitForElementToLoad(reqpaymentObj.SucessMsg);
			actualArraylist.add(reqpaymentObj.SucessMsg.getText());
			
			for(int i=0;i<studentName.length;i++)
			{
				 payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[i], "", "", "");
				 Thread.sleep(1500);
				 actualArraylist.add(driver.findElement(By.xpath("//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]")).getText());
				 payreqlistObj.resetButton.click();
				 Thread.sleep(1000);
			}
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		   
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test (priority=3)
	public void viewPaymentDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered viewPaymentDetails test");	
			eTest = eReports.createTest("View Payment Details");
			eTest.assignCategory("Request Payment");
			
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			lastnName = reqpaydataObj.getStudentlastName();
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			for(int i=0;i<studentName.length;i++)
			{
				expectedArraylist.add(paymentData[0]);
				expectedArraylist.add(paymentData[1]);
				expectedArraylist.add(paymentData[2]);
				expectedArraylist.add(getCurrentDate());
				expectedArraylist.add(groupData[0]);
				expectedArraylist.add("Pending");
				expectedArraylist.add("Yes");
				expectedArraylist.add(getCurrentDate());
				expectedArraylist.add(studentName[i]+" "+studentName[i]+" "+studentName[i]+" "+lastnName[i]);
				payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[i], "Pending", "", "");
				Thread.sleep(1500);
				//waitIfElementClickIsIntercepted(payreqlistObj.viewButton, "click", "");
				waitForElementToBeClickable(payreqlistObj.viewButton);
				autoScrollandClick(payreqlistObj.viewButton);
				waitForElementToLoad(paymentinfoObj.title);
				actualArraylist.add(paymentinfoObj.title.getText());
				actualArraylist.add(paymentinfoObj.amount.getText().replaceAll("[^\\d.]", "").trim());
				actualArraylist.add(paymentinfoObj.description.getText());
				actualArraylist.add(paymentinfoObj.dueDate.getText());
				actualArraylist.add(paymentinfoObj.groupName.getText());
				actualArraylist.add(paymentinfoObj.status.getText());
				actualArraylist.add(paymentinfoObj.acceptDuePayments.getText());
				actualArraylist.add(paymentinfoObj.createDate.getText());
				actualArraylist.add(paymentinfoObj.studentName.getText());
				dashboardObj.paymentRequestList.click();
				
			}
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test(priority=4)
	public void acceptPayment()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered acceptPayment test");	
			eTest = eReports.createTest("Accept Payment");
			eTest.assignCategory("Request Payment");
			
			String payMethod = (language.equals("Arabic"))?"السيولة النقدية":"Cash";
			String comments = (language.equals("Arabic"))?"دفع":"paid";
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			expectedArraylist.add((language.equals("Arabic"))?"بالإنتظار":"Pending");
			expectedArraylist.add((language.equals("Arabic"))?"تم قبول طلب الدفع بنجاح":"Payment request accepted successfully");
			expectedArraylist.add((language.equals("Arabic"))?"تم الدفع":"Paid");
			expectedArraylist.add(paymentData[0]);
			expectedArraylist.add(studentName[0]+" "+studentName[0]+" "+studentName[0]+" "+lastnName[0]);
			expectedArraylist.add((language.equals("Arabic"))?"تم الدفع":"Paid");
			expectedArraylist.add((language.equals("Arabic"))?"السيولة النقدية":"Cash");
			expectedArraylist.add((language.equals("Arabic"))?"غير متصل":"Offline");
			expectedArraylist.add(payMethod);
			expectedArraylist.add((language.equals("Arabic"))? "عملية الدفع تمت بنجاح":"Payment Successful");
			expectedArraylist.add(getCurrentDate());
			expectedArraylist.add(comments);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[0], (language.equals("Arabic"))?"بالإنتظار":"Pending", "", "");
			Thread.sleep(2500);
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			waitIfElementClickIsIntercepted(payreqlistObj.acceptPayment, "click", "");
			waitForElementToLoad(payreqlistObj.paymentMethod);
			payreqlistObj.paymentMethod.sendKeys(payMethod);
			payreqlistObj.comments.sendKeys(comments);
			autoScrollandClick(payreqlistObj.submitPayment);
			waitForElementToLoad(payreqlistObj.requestAcceptSuccessMsg);
			actualArraylist.add(payreqlistObj.requestAcceptSuccessMsg.getText());
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			
			//waitIfElementClickIsIntercepted(payreqlistObj.viewButton, "click", "");
			waitForElementToBeClickable(payreqlistObj.viewButton);
			autoScrollandClick(payreqlistObj.viewButton);
			waitForElementToLoad(paymentinfoObj.title);
			actualArraylist.add(paymentinfoObj.title.getText());
			actualArraylist.add(paymentinfoObj.studentName.getText());
			actualArraylist.add(paymentinfoObj.status.getText());
			actualArraylist.add(paymentinfoObj.paymentGateway1.getText());
			actualArraylist.add(paymentinfoObj.paymentMode1.getText());
			actualArraylist.add(paymentinfoObj.paymentMethod1.getText());
			actualArraylist.add(paymentinfoObj.status1.getText());
			actualArraylist.add(paymentinfoObj.transactionDate1.getText());
			actualArraylist.add(paymentinfoObj.comments1.getText());
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test(priority=5)
	public void refundPayment()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered refundPayment test");	
			eTest = eReports.createTest("Refund Payment ");
			eTest.assignCategory("Request Payment");
			
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			String comments = (language.equals("Arabic"))?"إعادة مال":"refund";
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			expectedArraylist.add((language.equals("Arabic"))?"تم الدفع":"Paid");
			expectedArraylist.add((language.equals("Arabic"))?"تم استرجاع المبلغ بنجاح":"Payment request refunded successfully");
			expectedArraylist.add((language.equals("Arabic"))?"اعادة":"Refunded");
			expectedArraylist.add(paymentData[0]);
			expectedArraylist.add(studentName[0]+" "+studentName[0]+" "+studentName[0]+" "+lastnName[0]);
			expectedArraylist.add((language.equals("Arabic"))?"اعادة":"Refunded");
			expectedArraylist.add((language.equals("Arabic"))?"غير متصل":"Offline");
			expectedArraylist.add((language.equals("Arabic"))?"تم إسترداد الدفعة":"Payment Refunded");
			expectedArraylist.add(getCurrentDate());
			expectedArraylist.add(comments);
			
			payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[0], (language.equals("Arabic"))?"تم الدفع":"Paid", "", "");
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			waitIfElementClickIsIntercepted(payreqlistObj.refundButton, "click", "");
			waitForElementToLoad(payreqlistObj.comments);
			payreqlistObj.comments.sendKeys(comments);
			autoScrollandClick(payreqlistObj.submitPayment);
			waitForElementToLoad(payreqlistObj.requestRefundSuccessMsg);
			actualArraylist.add(payreqlistObj.requestRefundSuccessMsg.getText());
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			
			waitIfElementClickIsIntercepted(payreqlistObj.viewButton, "click", "");
			waitForElementToLoad(paymentinfoObj.title);
			actualArraylist.add(paymentinfoObj.title.getText());
			actualArraylist.add(paymentinfoObj.studentName.getText());
			actualArraylist.add(paymentinfoObj.status.getText());
			actualArraylist.add(paymentinfoObj.paymentMode1.getText());
			actualArraylist.add(paymentinfoObj.status1.getText());
			actualArraylist.add(paymentinfoObj.transactionDate1.getText());
			actualArraylist.add(paymentinfoObj.comments1.getText());
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test(priority=6)
	public void cancelPayment()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered cancelPayment test");	
			eTest = eReports.createTest("Cancel Payment ");
			eTest.assignCategory("Request Payment");
			
			String comments = (language.equals("Arabic"))?"إلغاء":"cancel";
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			expectedArraylist.add((language.equals("Arabic"))?"بالإنتظار":"Pending");
			expectedArraylist.add((language.equals("Arabic"))?"تم الغاء طلب الدفع بنجاح":"Payment request cancelled successfully");
			expectedArraylist.add((language.equals("Arabic"))?"الغاء":"Cancelled");
			expectedArraylist.add(paymentData[0]);
			expectedArraylist.add(studentName[1]+" "+studentName[1]+" "+studentName[1]+" "+lastnName[1]);
			expectedArraylist.add((language.equals("Arabic"))?"الغاء":"Cancelled");
			expectedArraylist.add((language.equals("Arabic"))?"غير متصل":"Offline");
			expectedArraylist.add((language.equals("Arabic"))?"إلغاء الدفعة":"Payment Cancelled");
			expectedArraylist.add(getCurrentDate());
			expectedArraylist.add(comments);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[1], (language.equals("Arabic"))?"بالإنتظار":"Pending", "", "");
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			waitIfElementClickIsIntercepted(payreqlistObj.cancelPayment, "click", "");
			waitForElementToLoad(payreqlistObj.comments);
			payreqlistObj.comments.sendKeys(comments);
			autoScrollandClick(payreqlistObj.submitPayment);
			waitForElementToLoad(payreqlistObj.requestCancelSuccessMsg);
			actualArraylist.add(payreqlistObj.requestCancelSuccessMsg.getText());
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			
			waitIfElementClickIsIntercepted(payreqlistObj.viewButton, "click", "");
			waitForElementToLoad(paymentinfoObj.title);
			actualArraylist.add(paymentinfoObj.title.getText());
			actualArraylist.add(paymentinfoObj.studentName.getText());
			actualArraylist.add(paymentinfoObj.status.getText());
			actualArraylist.add(paymentinfoObj.paymentMode1.getText());
			actualArraylist.add(paymentinfoObj.status1.getText());
			actualArraylist.add(paymentinfoObj.transactionDate1.getText());
			actualArraylist.add(paymentinfoObj.comments1.getText());
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
	}
	
	@Test(priority=7)
	public void verifyPaymentActions()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered cancelPayment test");	
			eTest = eReports.createTest("Cancel Payment By Action ");
			eTest.assignCategory("Request Payment");
			
			String comments = (language.equals("Arabic"))?"إلغاء":"cancel";
			paymentData = reqpaydataObj.getRequestPaymentData();
			groupData = reqpaydataObj.getGroupData();
			studentName = reqpaydataObj.getStudentsName();
			expectedArraylist.add((language.equals("Arabic"))?"بالإنتظار":"Pending");
			expectedArraylist.add((language.equals("Arabic"))?"تم الغاء طلب الدفع بنجاح":"Payment request cancelled successfully");
			expectedArraylist.add((language.equals("Arabic"))?"الغاء":"Cancelled");
			expectedArraylist.add(paymentData[0]);
			expectedArraylist.add(studentName[2]+" "+studentName[2]+" "+studentName[2]+" "+lastnName[2]);
			expectedArraylist.add((language.equals("Arabic"))?"الغاء":"Cancelled");
			expectedArraylist.add((language.equals("Arabic"))?"غير متصل":"Offline");
			expectedArraylist.add((language.equals("Arabic"))?"إلغاء الدفعة":"Payment Cancelled");
			expectedArraylist.add(getCurrentDate());
			expectedArraylist.add(comments);
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
			dashboardObj.paymentRequestList.click();
			payreqlistObj.filterPymentRequest(paymentData[0], groupData[0], studentName[2], (language.equals("Arabic"))?"بالإنتظار":"Pending", "", "");
			Thread.sleep(1500);
			waitForElementToLoad(payreqlistObj.stat);
			autoScrollandClick(payreqlistObj.paymentCheckBox);
			actualArraylist.add(payreqlistObj.stat.getText());
			scrollToTheElement(payreqlistObj.actions);
			payreqlistObj.actions.sendKeys((language.equals("Arabic"))?"إلغاء":"Cancel");
			autoScrollandClick(payreqlistObj.submit);
			waitForElementToLoad(payreqlistObj.comments);
			payreqlistObj.comments.sendKeys(comments);
			autoScrollandClick(payreqlistObj.submitPayment);
			waitForElementToLoad(payreqlistObj.requestCancelSuccessMsg);
			actualArraylist.add(payreqlistObj.requestCancelSuccessMsg.getText());
			waitForElementToLoad(payreqlistObj.stat);
			actualArraylist.add(payreqlistObj.stat.getText());
			
			waitIfElementClickIsIntercepted(payreqlistObj.viewButton, "click", "");
			waitForElementToLoad(paymentinfoObj.title);
			actualArraylist.add(paymentinfoObj.title.getText());
			actualArraylist.add(paymentinfoObj.studentName.getText());
			actualArraylist.add(paymentinfoObj.status.getText());
			actualArraylist.add(paymentinfoObj.paymentMode1.getText());
			actualArraylist.add(paymentinfoObj.status1.getText());
			actualArraylist.add(paymentinfoObj.transactionDate1.getText());
			actualArraylist.add(paymentinfoObj.comments1.getText());
			waitIfElementClickIsIntercepted(dashboardObj.paymentRequest, "click", "");
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
