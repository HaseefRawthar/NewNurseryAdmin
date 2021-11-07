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

public class AddStudent extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddStudentObject addstudentObj;
	AddStudentData addstudentdataObj;
	StudentListObject studentlistObj;
	StudentDetailsObject studentdetailObj;
	String[] studentData;
	String[] parentData;
	String[] emergencyData;
	String[] authpickupData;
	String[] attachmentData;
	String[] filterData;
	
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
	public void verifyMandatoryMsgVisible()
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered verifyMandatoryMsgVisible test");		
			eTest = eReports.createTest("Verify Mandatory Msg Visible");
			eTest.assignCategory("Student - Add student");
			
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.addStudent.click();
			waitForElementToLoad(addstudentObj.addButton);
			autoScrollandClick(addstudentObj.addButton);
			for(int i=0;i<10;i++)
			{
				expectedBoolArray.add(true);
			}
			waitForElementToLoad(addstudentObj.firstNameMandtryMsg);
			actualBoolArray.add(isElementPresent(addstudentObj.firstNameMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.lastNameMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.genderMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.parentnameMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.parentCodeMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.parentPhoneMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.relationMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.emergContNameMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.emergContCodeMandtryMsg));
			actualBoolArray.add(isElementPresent(addstudentObj.emergContPhoneMandtryMsg));
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
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
	public void addStudent()
	{
		try
		{
			log.info("Entered addStudent test");		
			eTest = eReports.createTest("Add Student");
			eTest.assignCategory("Student - Add student");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم اضافة الطالب بنجاح");
			}
			else
			{
				expectedArraylist.add("Student Added Successfully");
			}
			
			addstudentdataObj.generateFakeStudentData();
			addstudentdataObj.generateFakeParentData();
			filterData = addstudentdataObj.getFilterData();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.addStudent.click();
			attachmentData = addstudentdataObj.attachments();
			studentData = addstudentdataObj.getStudentData();
			for(int i=0;i<studentData.length-3;i++)
			{
				expectedArraylist.add(studentData[i]);
			}
			//studentData[14] = studentData[14].split(" ")[0];
			//expectedArraylist.add(studentData[14]);
			expectedArraylist.add(studentData[15]);
			parentData = addstudentdataObj.getParentDetails();
			emergencyData = addstudentdataObj.getEmergencyCallDetails();
			authpickupData = addstudentdataObj.getAuthPickupDetails();
			
			addstudentObj.addStudentDetails(studentData);
			addstudentObj.addAttachments(addstudentObj.addAttachment1, addstudentObj.browseAttachments1, attachmentData, 1);
			//parentData[6] = "Father";
			parentData[6] = (language.equals("Arabic"))?"الاب":"Father";
			addstudentObj.addParentDetails(parentData);
			for(int i=0;i<7;i++)
			{
				expectedArraylist.add(parentData[i]);
			}
			expectedArraylist.add(parentData[7].replaceAll("[^\\d+]", "")+" "+parentData[8]);
			autoScrollandClick(addstudentObj.addMoreParent);
			parentData = addstudentdataObj.getParentDetails2();
			//parentData[6] = "Mother";
			parentData[6] = (language.equals("Arabic"))?"الام":"Mother";
			addstudentObj.addParentDetails2(parentData);
			for(int i=0;i<7;i++)
			{
				expectedArraylist.add(parentData[i]);
			}
			expectedArraylist.add(parentData[7].replaceAll("[^\\d+]", "")+" "+parentData[8]);
			addstudentObj.addEmergencyDetails(emergencyData);
			expectedArraylist.add(emergencyData[0]);
			expectedArraylist.add(emergencyData[1].replaceAll("[^\\d+]", "")+" "+emergencyData[2]);
			expectedArraylist.add(emergencyData[3]);
			addstudentObj.authPickupDetails(authpickupData);
			expectedArraylist.add(authpickupData[0]);
			expectedArraylist.add(authpickupData[1]);
			expectedArraylist.add(authpickupData[2].replaceAll("[^\\d+]", "")+" "+authpickupData[3]);
			expectedArraylist.add(authpickupData[4]);
			
			addstudentObj.addAttachments(addstudentObj.addAttachment2, addstudentObj.browseAttachments2, attachmentData, 1);
			autoScrollandClick(addstudentObj.addButton);
			waitForElementToLoad(addstudentObj.studentSucessMsg);
			actualArraylist.add(addstudentObj.studentSucessMsg.getText());
			studentlistObj.filterStudents(filterData);
			waitIfElementClickIsIntercepted(studentlistObj.viewButton, "click", "");
			
			waitForElementToLoad(studentdetailObj.firstName);
			actualArraylist.add(studentdetailObj.firstName.getText());
			actualArraylist.add(studentdetailObj.secondName.getText());
			actualArraylist.add(studentdetailObj.thirdName.getText());
			actualArraylist.add(studentdetailObj.lastName.getText());
			actualArraylist.add(studentdetailObj.civilID.getText());
			actualArraylist.add(studentdetailObj.nationality.getText());
			actualArraylist.add(studentdetailObj.PlaceOfBirth.getText());
			actualArraylist.add(studentdetailObj.dateOfBirth.getText());
			actualArraylist.add(studentdetailObj.gender.getText());
			actualArraylist.add(studentdetailObj.religion.getText());
			actualArraylist.add(studentdetailObj.address.getText());
			actualArraylist.add(studentdetailObj.allergies.getText());
			actualArraylist.add(studentdetailObj.nurseryNotes.getText());
			actualArraylist.add(studentdetailObj.parentNotes.getText());
			//actualArraylist.add(studentdetailObj.groupName.getText());
			actualArraylist.add(studentdetailObj.firstDayOfSchool.getText());
			
			autoScrollandClick(studentdetailObj.parentInformation1);
			actualArraylist.add(studentdetailObj.parent1Name.getText());
			actualArraylist.add(studentdetailObj.parent1Email.getText());
			actualArraylist.add(studentdetailObj.parent1Job.getText());
			actualArraylist.add(studentdetailObj.parent1CivilID.getText());
			actualArraylist.add(studentdetailObj.parent1Nationality.getText());
			actualArraylist.add(studentdetailObj.parent1Religion.getText());
			actualArraylist.add(studentdetailObj.parent1Relation.getText());
			actualArraylist.add(studentdetailObj.parent1Phone.getText());
			
			autoScrollandClick(studentdetailObj.parentInformation2);
			actualArraylist.add(studentdetailObj.parent2Name.getText());
			actualArraylist.add(studentdetailObj.parent2Email.getText());
			actualArraylist.add(studentdetailObj.parent2Job.getText());
			actualArraylist.add(studentdetailObj.parent2CivilID.getText());
			actualArraylist.add(studentdetailObj.parent2Nationality.getText());
			actualArraylist.add(studentdetailObj.parent2Religion.getText());
			actualArraylist.add(studentdetailObj.parent2Relation.getText());
			actualArraylist.add(studentdetailObj.parent2Phone.getText());
			
			autoScrollandClick(studentdetailObj.emergencyContact);
			actualArraylist.add(studentdetailObj.emergName.getText());
			actualArraylist.add(studentdetailObj.emergPhone.getText());
			actualArraylist.add(studentdetailObj.emergRelation.getText());
			
			autoScrollandClick(studentdetailObj.authorizedPickUp);
			actualArraylist.add(studentdetailObj.authName.getText());
			actualArraylist.add(studentdetailObj.authCivilID.getText());
			actualArraylist.add(studentdetailObj.authPhone.getText());
			actualArraylist.add(studentdetailObj.authRelation.getText());
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			
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
	public void viewStudentDetails()
	{
		try
		{
			log.info("Entered addStudent test");		
			eTest = eReports.createTest("View Student Details");
			eTest.assignCategory("Student - Add student");
			
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			filterData = addstudentdataObj.getFilterData();
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");
			dashboardObj.studentList.click();
			studentData = addstudentdataObj.getStudentData();
			for(int i=0;i<studentData.length-3;i++)
			{
				expectedArraylist.add(studentData[i]);
			}
			//studentData[14] = studentData[14].split(" ")[0];
			//expectedArraylist.add(studentData[14]);
			expectedArraylist.add(studentData[15]);
			parentData = addstudentdataObj.getParentDetails();
			emergencyData = addstudentdataObj.getEmergencyCallDetails();
			authpickupData = addstudentdataObj.getAuthPickupDetails();
			parentData[6] = (language.equals("Arabic"))?"الاب":"Father";
			for(int i=0;i<7;i++)
			{
				expectedArraylist.add(parentData[i]);
			}
			expectedArraylist.add(parentData[7].replaceAll("[^\\d+]", "")+" "+parentData[8]);
			parentData = addstudentdataObj.getParentDetails2();
			parentData[6] = (language.equals("Arabic"))?"الام":"Mother";
			for(int i=0;i<7;i++)
			{
				expectedArraylist.add(parentData[i]);
			}
			expectedArraylist.add(parentData[7].replaceAll("[^\\d+]", "")+" "+parentData[8]);
			expectedArraylist.add(emergencyData[0]);
			expectedArraylist.add(emergencyData[1].replaceAll("[^\\d+]", "")+" "+emergencyData[2]);
			expectedArraylist.add(emergencyData[3]);
			expectedArraylist.add(authpickupData[0]);
			expectedArraylist.add(authpickupData[1]);
			expectedArraylist.add(authpickupData[2].replaceAll("[^\\d+]", "")+" "+authpickupData[3]);
			expectedArraylist.add(authpickupData[4]);
			
			studentlistObj.filterStudents(filterData);
			waitIfElementClickIsIntercepted(studentlistObj.viewButton, "click", "");
			
			waitForElementToLoad(studentdetailObj.firstName);
			actualArraylist.add(studentdetailObj.firstName.getText());
			actualArraylist.add(studentdetailObj.secondName.getText());
			actualArraylist.add(studentdetailObj.thirdName.getText());
			actualArraylist.add(studentdetailObj.lastName.getText());
			actualArraylist.add(studentdetailObj.civilID.getText());
			actualArraylist.add(studentdetailObj.nationality.getText());
			actualArraylist.add(studentdetailObj.PlaceOfBirth.getText());
			actualArraylist.add(studentdetailObj.dateOfBirth.getText());
			actualArraylist.add(studentdetailObj.gender.getText());
			actualArraylist.add(studentdetailObj.religion.getText());
			actualArraylist.add(studentdetailObj.address.getText());
			actualArraylist.add(studentdetailObj.allergies.getText());
			actualArraylist.add(studentdetailObj.nurseryNotes.getText());
			actualArraylist.add(studentdetailObj.parentNotes.getText());
			//actualArraylist.add(studentdetailObj.groupName.getText());
			actualArraylist.add(studentdetailObj.firstDayOfSchool.getText());
			
			autoScrollandClick(studentdetailObj.parentInformation1);
			actualArraylist.add(studentdetailObj.parent1Name.getText());
			actualArraylist.add(studentdetailObj.parent1Email.getText());
			actualArraylist.add(studentdetailObj.parent1Job.getText());
			actualArraylist.add(studentdetailObj.parent1CivilID.getText());
			actualArraylist.add(studentdetailObj.parent1Nationality.getText());
			actualArraylist.add(studentdetailObj.parent1Religion.getText());
			actualArraylist.add(studentdetailObj.parent1Relation.getText());
			actualArraylist.add(studentdetailObj.parent1Phone.getText());
			
			autoScrollandClick(studentdetailObj.parentInformation2);
			actualArraylist.add(studentdetailObj.parent2Name.getText());
			actualArraylist.add(studentdetailObj.parent2Email.getText());
			actualArraylist.add(studentdetailObj.parent2Job.getText());
			actualArraylist.add(studentdetailObj.parent2CivilID.getText());
			actualArraylist.add(studentdetailObj.parent2Nationality.getText());
			actualArraylist.add(studentdetailObj.parent2Religion.getText());
			actualArraylist.add(studentdetailObj.parent2Relation.getText());
			actualArraylist.add(studentdetailObj.parent2Phone.getText());
			
			autoScrollandClick(studentdetailObj.emergencyContact);
			actualArraylist.add(studentdetailObj.emergName.getText());
			actualArraylist.add(studentdetailObj.emergPhone.getText());
			actualArraylist.add(studentdetailObj.emergRelation.getText());
			
			autoScrollandClick(studentdetailObj.authorizedPickUp);
			actualArraylist.add(studentdetailObj.authName.getText());
			actualArraylist.add(studentdetailObj.authCivilID.getText());
			actualArraylist.add(studentdetailObj.authPhone.getText());
			actualArraylist.add(studentdetailObj.authRelation.getText());
			waitIfElementClickIsIntercepted(dashboardObj.students, "click", "");

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
