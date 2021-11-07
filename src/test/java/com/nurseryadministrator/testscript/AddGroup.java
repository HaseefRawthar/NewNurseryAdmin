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
import com.nurseryadministrator.testdata.TestDataImport;

public class AddGroup extends SetUp{

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddGroupObject addgroupObj;
	AddGroupData addgrpdataObj;
	GroupListObject grouplistObj;
	ViewGroupObject viewgroupObj;
	String[] teacherData;
	String[] assistantNames;
	String[] assistantNumber;
	String[] studentNames;
	String[] lastName;
	String[] parentNames;
	
	@BeforeClass
	public void initialize()
	{
		try
		{
			loginObj = new LogInObject(driver);
			dashboardObj = new DashboardObject(driver);
			tdImport = new TestDataImport();
			addgroupObj = new AddGroupObject(driver);
			addgrpdataObj = new AddGroupData();
			grouplistObj = new GroupListObject(driver);
			viewgroupObj = new ViewGroupObject(driver);
			
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
	
	@Test
	public void verifyMandatoryMsgVisible()
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered addBabiesGroup test");	
			eTest = eReports.createTest("verifyMandatoryMsgVisible");
			eTest.assignCategory("Groups - Add Groups");
			
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.addGroup.click();
			waitForElementToLoad(addgroupObj.addButton);
			autoScrollandClick(addgroupObj.addButton);
			for(int i=0;i<2;i++)
			{
				expectedBoolArray.add(true);
			}
			waitForElementToLoad(addgroupObj.groupName);
			actualBoolArray.add(isElementPresent(addgroupObj.groupNameMandatoryMsg));
			actualBoolArray.add(isElementPresent(addgroupObj.groupTypeMandatoryMsg));
			
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
	
	@Test
	public void addBabiesGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered addBabiesGroup test");	
			eTest = eReports.createTest("Add Babies Group");
			eTest.assignCategory("Groups - Add Groups");
			 
			String groupType = (language.equals("Arabic"))?"رضع":"Babies";
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم انشاء الصف الدراسي بنجاح");
			}
			else
			{
				expectedArraylist.add("Group Created Successfully");
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.addGroup.click();
			
			addgrpdataObj.generateFakeGroupName();
			String name = addgrpdataObj.getGroupName();
			System.out.println(name);
			waitForElementToLoad(addgroupObj.groupName);
			addgroupObj.groupName.sendKeys(name);
			addgroupObj.groupType.sendKeys(groupType);
			addgroupObj.addButton.click();
			expectedArraylist.add(name);
			expectedArraylist.add(groupType);
			
			waitForElementToLoad(addgroupObj.groupSuccessMsg);
			actualArraylist.add(addgroupObj.groupSuccessMsg.getText());
			
			grouplistObj.filterGroups(name, "", groupType, "");
			Thread.sleep(1500);
			grouplistObj.viewDetails.click();
			
			waitForElementToLoad(viewgroupObj.grupName);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
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
	public void addToddlersGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered addToddlersGroup test");	
			eTest = eReports.createTest("Add Toddlers Group");
			eTest.assignCategory("Groups - Add Groups");
			 
			String groupType = (language.equals("Arabic"))?"الأطفال ال":"Toddlers";
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم انشاء الصف الدراسي بنجاح");
			}
			else
			{
				expectedArraylist.add("Group Created Successfully");
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.addGroup.click();
			
			addgrpdataObj.generateFakeGroupName();
			String name = addgrpdataObj.getGroupName();
			System.out.println(name);
			tdImport.writeCell((language.equals("Arabic"))?5:4, 0, name);
			waitForElementToLoad(addgroupObj.groupName);
			addgroupObj.groupName.sendKeys(name);
			addgroupObj.groupType.sendKeys(groupType);
			addgroupObj.addButton.click();
			expectedArraylist.add(name);
			expectedArraylist.add(groupType);
			
			waitForElementToLoad(addgroupObj.groupSuccessMsg);
			actualArraylist.add(addgroupObj.groupSuccessMsg.getText());
			
			grouplistObj.filterGroups(name, "", groupType, "");
			Thread.sleep(1500);
			grouplistObj.viewDetails.click();
			
			waitForElementToLoad(viewgroupObj.grupName);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
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
	public void addPreKGGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered addPreKGGroup test");	
			eTest = eReports.createTest("Add PreKG Group");
			eTest.assignCategory("Groups - Add Groups");
			 
			String groupType = (language.equals("Arabic"))?"ماقبل الرو":"Pre-KG";
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم انشاء الصف الدراسي بنجاح");
			}
			else
			{
				expectedArraylist.add("Group Created Successfully");
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.addGroup.click();
			
			addgrpdataObj.generateFakeGroupName();
			String name = addgrpdataObj.getGroupName();
			System.out.println(name);
			waitForElementToLoad(addgroupObj.groupName);
			addgroupObj.groupName.sendKeys(name);
			addgroupObj.groupType.sendKeys(groupType);
			addgroupObj.addButton.click();
			expectedArraylist.add(name);
			expectedArraylist.add(groupType);
			
			waitForElementToLoad(addgroupObj.groupSuccessMsg);
			actualArraylist.add(addgroupObj.groupSuccessMsg.getText());
			
			grouplistObj.filterGroups(name, "", groupType, "");
			Thread.sleep(1500);
			grouplistObj.viewDetails.click();
			
			waitForElementToLoad(viewgroupObj.grupName);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
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
	public void assignStudentsAndEmployees()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered assignStudentsAndEmployees test");	
			eTest = eReports.createTest("Assign Students And Employees");
			eTest.assignCategory("Groups - Group List");
			 
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث معلومات الصف الدراسي بنجاح");
				expectedArraylist.add("تم تحديث معلومات الصف الدراسي بنجاح");
				expectedArraylist.add("تم تحديث معلومات الطالب بنجاح");
			}
			else
			{
				expectedArraylist.add("Group Updated Successfully");
				expectedArraylist.add("Group Updated Successfully");
				expectedArraylist.add("Student Updated Successfully");
			}
			teacherData = addgrpdataObj.getTeacherData();
			expectedArraylist.add(teacherData[0].split(" ")[0]);
			assistantNames = addgrpdataObj.getAssistantName();
			assistantNumber = addgrpdataObj.getAssistantNumber();
			studentNames = addgrpdataObj.getStudentsName();
			parentNames = addgrpdataObj.getParentsName();
			lastName = addgrpdataObj.getAssistantlastName();
			for(int i=0;i<assistantNames.length;i++)
			{
				expectedArraylist.add(assistantNames[i]+" "+lastName[i]);
			}
			for(int i=0;i<assistantNumber.length;i++)
			{
				expectedArraylist.add(assistantNumber[i]);
			}
			lastName = addgrpdataObj.getStudentlastName();
			for(int i=0;i<studentNames.length;i++)
			{
				expectedArraylist.add(studentNames[i]+" "+lastName[i]);
			}
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			String[] details = addgrpdataObj.getFilterData();
			expectedArraylist.add(details[0]);
			expectedArraylist.add(details[1]);
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(grouplistObj.viewDetails, "click", "");
			waitForElementToLoad(viewgroupObj.addTeacher);
			viewgroupObj.addTeacher.click();
			viewgroupObj.assignTeacher(teacherData[0], teacherData[1]);
			viewgroupObj.selectButton.click();
			waitForElementToLoad(viewgroupObj.updateSucessMsg);
			actualArraylist.add(grouplistObj.updateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.addAssistants);
			autoScrollandClick(viewgroupObj.addAssistants);
			for(int i=0;i<assistantNames.length;i++)
			{
				viewgroupObj.assignAssistants(assistantNames[i], assistantNumber[i]);
				viewgroupObj.resetButton.click();
			}
			viewgroupObj.selectButton.click();
			waitForElementToLoad(viewgroupObj.updateSucessMsg);
			actualArraylist.add(grouplistObj.updateSucessMsg.getText());
			scrollDown(300);
			waitForElementToLoad(viewgroupObj.addStudents);
			autoScrollandClick(viewgroupObj.addStudents);
			for(int i=0;i<studentNames.length;i++)
			{
				viewgroupObj.assignStudents(studentNames[i], parentNames[i], "", "");
				viewgroupObj.resetButton.click();
			}
			viewgroupObj.selectButton.click();
			waitForElementToLoad(viewgroupObj.studentUpdateSucessMsg);
			actualArraylist.add(viewgroupObj.studentUpdateSucessMsg.getText());
			actualArraylist.add(viewgroupObj.teacher.getText().split(" ")[0]);
			driver.navigate().refresh();
			waitForElementToLoad(viewgroupObj.addAssistants);
			for(int i=1;i<=assistantNames.length;i++)
			{
				actualArraylist.add(driver.findElement(By.xpath("//form[1]/div[1]/div[4]/div[2]/table[1]/tbody[1]/tr["+i+"]/td[1]")).getText());
			}
			for(int i=1;i<=assistantNumber.length;i++)
			{
				actualArraylist.add(driver.findElement(By.xpath("//form[1]/div[1]/div[4]/div[2]/table[1]/tbody[1]/tr["+i+"]/td[2]")).getText());
			}
			for(int i=1;i<=studentNames.length;i++)
			{
				actualArraylist.add(driver.findElement(By.xpath("//form[1]/div[1]/div[5]/div[2]/div[1]/div[2]/table[1]/tbody[1]/tr["+i+"]/td[2]")).getText());
			}
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			
			
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
	public void deleteStudentsAndEmployees()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered deleteStudentsAndEmployees test");	
			eTest = eReports.createTest("Delete Students And Employees");
			eTest.assignCategory("Groups - Group List");
			
			assistantNames = addgrpdataObj.getAssistantName();
			studentNames = addgrpdataObj.getStudentsName();
			
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			String[] details = addgrpdataObj.getFilterData();
			expectedArraylist.add(details[0]);
			expectedArraylist.add(details[1]);
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("غير معين");
				expectedArraylist.add("لايوجد مساعدين للمجموعة");
				expectedArraylist.add("لايوجد طلاب في المجموعة");
			}
			else
			{
				expectedArraylist.add("Unassigned");
				expectedArraylist.add("No assistants in the group");
				expectedArraylist.add("No students in the group");
			}
			
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			waitIfElementClickIsIntercepted(grouplistObj.viewDetails, "click", "");
			waitForElementToLoad(viewgroupObj.addTeacher);
			actualArraylist.add(viewgroupObj.grupName.getText());
			actualArraylist.add(viewgroupObj.grupType.getText());
			autoScrollandClick(viewgroupObj.removeTeacher);
			waitForElementToLoad(viewgroupObj.yes);
			viewgroupObj.yes.click();
			waitForElementToLoad(viewgroupObj.updateSucessMsg);
			//actualArraylist.add(viewgroupObj.updateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.teacherUnassigned);
			actualArraylist.add(viewgroupObj.teacherUnassigned.getText());
			while(!isElementPresent(viewgroupObj.noAssistantInGroup))
			{
				waitIfElementClickIsIntercepted(viewgroupObj.removeAssistants, "click", "");
				//autoScrollandClick(viewgroupObj.removeAssistants);
				waitForElementToLoad(viewgroupObj.yes);
				autoScrollandClick(viewgroupObj.yes);
				Thread.sleep(1500);
				//waitForElementToLoad(viewgroupObj.updateSucessMsg);
				//actualArraylist.add(viewgroupObj.updateSucessMsg.getText());
			}
			waitForElementToLoad(viewgroupObj.noAssistantInGroup);
			actualArraylist.add(viewgroupObj.noAssistantInGroup.getText());
			scrollDown(500);
			while(!isElementPresent(viewgroupObj.noStudentInGroup))
			{
				waitIfElementClickIsIntercepted(viewgroupObj.removeStudents, "click", details[0]);
				waitForElementToLoad(viewgroupObj.yes);
				autoScrollandClick(viewgroupObj.yes);
				Thread.sleep(2500);
				//waitForElementToLoad(viewgroupObj.studentUpdateSucessMsg);
				//actualArraylist.add(viewgroupObj.studentUpdateSucessMsg.getText());
			}
			waitForElementToLoad(viewgroupObj.noStudentInGroup);
			actualArraylist.add(viewgroupObj.noStudentInGroup.getText());
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
