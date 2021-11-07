package com.nurseryadministrator.testscript;

import java.util.ArrayList;

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

public class StudentList extends SetUp{

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
			
		    tdImport.readSheet("AddStudent");
		    
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
	public void deactivateStudent()
	{
		try
		{
			log.info("Entered deactivateStudent test");		
			eTest = eReports.createTest("Deactivate Student");
			eTest.assignCategory("Student - Student List");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			filterData = addstudentdataObj.getFilterData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث حالة حساب الطالب بنجاح");
				expectedArraylist.add("تفعيل");
				expectedArraylist.add("غير مفعل");
			}
			else
			{
				expectedArraylist.add("Student status updated Successfully");
				expectedArraylist.add("ACTIVATE");
				expectedArraylist.add("Inactive");
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			studentlistObj.filterStudents(filterData);
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(studentlistObj.viewButton, "click", "");
			waitForElementToLoad(studentdetailObj.deactivateButton);
			studentdetailObj.deactivateButton.click();
			waitForElementToLoad(studentdetailObj.yes);
			studentdetailObj.yes.click();
			waitForElementToLoad(studentdetailObj.statUpdateSucessMsg);
			actualArraylist.add(studentdetailObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(studentdetailObj.activateButton);
			actualArraylist.add(studentdetailObj.activateButton.getText());
			actualArraylist.add(studentdetailObj.status.getText());
			autoScrollandClick(dashboardObj.students);
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
	public void activateStudent()
	{
		try
		{
			log.info("Entered activateStudent test");		
			eTest = eReports.createTest("Activate Student");
			eTest.assignCategory("Student - Student List");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			filterData = addstudentdataObj.getFilterData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث حالة حساب الطالب بنجاح");
				expectedArraylist.add("تعطيل");
				expectedArraylist.add("مفعل");

			}
			else
			{
				expectedArraylist.add("Student status updated Successfully");
				expectedArraylist.add("DEACTIVATE");
				expectedArraylist.add("Active");

			}
						
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			studentlistObj.filterStudents(filterData);
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(studentlistObj.viewButton, "click", "");
			waitForElementToLoad(studentdetailObj.activateButton);
			studentdetailObj.activateButton.click();
			waitForElementToLoad(studentdetailObj.yes);
			studentdetailObj.yes.click();
			waitForElementToLoad(studentdetailObj.statUpdateSucessMsg);
			actualArraylist.add(studentdetailObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(studentdetailObj.deactivateButton);
			actualArraylist.add(studentdetailObj.deactivateButton.getText());
			actualArraylist.add(studentdetailObj.status.getText());
			autoScrollandClick(dashboardObj.students);
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
	
	//@Test(priority=3)
	public void editAndViewStudentDetails()
	{
		try
		{
			log.info("Entered editAndViewStudentDetails test");		
			eTest = eReports.createTest("Edit And View StudentDetails");
			eTest.assignCategory("Student - Student List");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			filterData = addstudentdataObj.getFilterData();
			editDetails = addstudentdataObj.editData();
			expectedArraylist.add((language.equals("Arabic"))?"تم تحديث معلومات الطالب بنجاح":"Student Updated Successfully");
			for(int i=0;i<editDetails.length;i++)
			{
				expectedArraylist.add(editDetails[i]);
			}
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			studentlistObj.filterStudents(filterData);
			Thread.sleep(1500);
			autoScrollandClick(studentlistObj.editButton);
			waitForElementToLoad(addstudentObj.firstName);
			if(language.equals("Arabic"))
			{
				addstudentObj.firstName.sendKeys("__تم تحريره");
				addstudentObj.lastName.sendKeys("__تم تحريره");
				addstudentObj.parent1Name.sendKeys("__تم تحريره");
				addstudentObj.parent2Name.sendKeys("__تم تحريره");
				addstudentObj.emergencyContName.sendKeys("__تم تحريره");
			}
			else
			{
				addstudentObj.firstName.sendKeys("__edited");
				addstudentObj.lastName.sendKeys("__edited");
				addstudentObj.parent1Name.sendKeys("__edited");
				addstudentObj.parent2Name.sendKeys("__edited");
				addstudentObj.emergencyContName.sendKeys("__edited");
			}
			autoScrollandClick(studentlistObj.saveButton);
			waitForElementToLoad(studentlistObj.updateSucessMsg);
			actualArraylist.add(studentlistObj.updateSucessMsg.getText());
			Thread.sleep(1000);
			waitIfElementClickIsIntercepted(studentlistObj.viewButton, "click", "");
			waitForElementToLoad(studentdetailObj.firstName);
			actualArraylist.add(studentdetailObj.firstName.getText());
			actualArraylist.add(studentdetailObj.lastName.getText());
			autoScrollandClick(studentdetailObj.parentInformation1);
			actualArraylist.add(studentdetailObj.parent1Name.getText());
			autoScrollandClick(studentdetailObj.parentInformation2);
			actualArraylist.add(studentdetailObj.parent2Name.getText());
			autoScrollandClick(studentdetailObj.emergencyContact);
			actualArraylist.add(studentdetailObj.emergName.getText());
			autoScrollandClick(dashboardObj.students);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedArraylist);
		System.out.println(actualArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion passed");
	}
	
	
	@Test(priority=10)
	public void verifySortFirstName()
	{
		try
		{
			
			log.info("Entered verifySortFirstName test");
			eTest = eReports.createTest("Verify Sort First Name");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.firstNameSortButton, studentlistObj.sortedFirstName);
			actualMsg = studentlistObj.sortedFirstName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=11)
	public void verifySortLasttName()
	{
		try
		{
			
			log.info("Entered verifySortLasttName test");
			eTest = eReports.createTest("Verify Sort Last Name");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.lastNameSortButton, studentlistObj.sortedLastName);
			actualMsg = studentlistObj.sortedLastName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=12)
	public void verifySortParent1Name()
	{
		try
		{
			
			log.info("Entered verifySortParent1Name test");
			eTest = eReports.createTest("Verify Sort Parent1 Name");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.parent1SortButton, studentlistObj.sortedParent1);
			actualMsg = studentlistObj.sortedParent1.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=13)
	public void verifySortParent2Name()
	{
		try
		{
			
			log.info("Entered verifySortParent2Name test");
			eTest = eReports.createTest("Verify Sort Parent2 Name");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.parent2SortButton, studentlistObj.sortedParent2);
			actualMsg = studentlistObj.sortedParent2.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=14)
	public void verifySortGroupName()
	{
		try
		{
			
			log.info("Entered verifySortGroupName test");
			eTest = eReports.createTest("Verify Sort GroupName");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.groupNameSortButton, studentlistObj.sortedGroupName);
			actualMsg = studentlistObj.sortedGroupName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=15)
	public void verifySortGroupType()
	{
		try
		{
			
			log.info("Entered verifySortGroupType test");
			eTest = eReports.createTest("Verify Sort GroupType");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.groupTypeSortButton, studentlistObj.sortedGroupType);
			actualMsg = studentlistObj.sortedGroupType.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	
	@Test(priority=16)
	public void verifySortForStatus()
	{
		try
		{
			
			log.info("Entered verifySortForStatus test");
			eTest = eReports.createTest("Verify Sort For Status");
			eTest.assignCategory("Student - Student List");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			expectedMsg = verifySortButton(studentlistObj.statusSortButton, studentlistObj.sortedStatus);
			actualMsg = studentlistObj.sortedStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
