package com.nurseryadministrator.testscript;

import java.util.ArrayList;

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

public class GroupList extends SetUp {

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddGroupObject addgroupObj;
	AddGroupData addgrpdataObj;
	GroupListObject grouplistObj;
	ViewGroupObject viewgroupObj;
	
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
	public void listAndViewGroupDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered listAndViewGroupDetails test");	
			eTest = eReports.createTest("List And View Group Details");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			String[] details = addgrpdataObj.getFilterData();
			expectedArraylist.add(details[0]);
			expectedArraylist.add(details[1]);
			grouplistObj.filterGroups(details[0], "", "", "");
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
		log.info("Assertion Passed");
	}
	
	@Test(priority=4)
	public void editAndViewGroupDetails()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered listAndViewGroupDetails test");	
			eTest = eReports.createTest("Edit And View GroupDetails");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			String editedName;
			String[] details = addgrpdataObj.getFilterEditData();
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث معلومات الصف الدراسي بنجاح");
				editedName = details[0]+"__تم تحريره";
				expectedArraylist.add(editedName);
			}
			else
			{
				expectedArraylist.add("Group Updated Successfully");
				editedName = details[0]+"__edited";
				expectedArraylist.add(editedName);
			}
			
			expectedArraylist.add(details[1]);
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			grouplistObj.editDetails.click();
			waitForElementToLoad(addgroupObj.groupName);
			addgroupObj.groupName.sendKeys(language.equals("Arabic")?"__تم تحريره":"__edited");
			grouplistObj.saveButton.click();
			waitForElementToLoad(grouplistObj.updateSucessMsg);
			actualArraylist.add(grouplistObj.updateSucessMsg.getText());
			waitForElementToLoad(grouplistObj.groupName);
			grouplistObj.groupName.clear();
			grouplistObj.filterGroups(editedName, "", "", "");
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
		log.info("Assertion Passed");
	}
	
	@Test(priority=2)
	public void deactivateGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered deactivateGroup test");	
			eTest = eReports.createTest("Deactivate Group");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تفعيل");
				expectedArraylist.add("غير مفعل");
			}
			else
			{
				expectedArraylist.add("Status Updated Successfully");
				expectedArraylist.add("ACTIVATE");
				expectedArraylist.add("Inactive");
			}
			
			String[] details = addgrpdataObj.getFilterData();
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			autoScrollandClick(grouplistObj.viewDetails);
			waitForElementToLoad(viewgroupObj.deactivateButton);
			viewgroupObj.deactivateButton.click();
			waitForElementToLoad(viewgroupObj.yes);
			viewgroupObj.yes.click();
			waitForElementToLoad(viewgroupObj.statUpdateSucessMsg);
			actualArraylist.add(viewgroupObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.activateButton);
			actualArraylist.add(viewgroupObj.activateButton.getText());
			actualArraylist.add(viewgroupObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	@Test(priority=3)
	public void activateGroup()
	{
		try
		{
			actualArraylist = new ArrayList<String>();
			expectedArraylist = new ArrayList<String>();
			
			log.info("Entered activateGroup test");	
			eTest = eReports.createTest("Activate Group");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			if(language.equals("Arabic"))
			{
				expectedArraylist.add("تم تحديث الحالة بنجاح");
				expectedArraylist.add("تعطيل");
				expectedArraylist.add("مفعل");
			}
			else
			{
				expectedArraylist.add("Status Updated Successfully");
				expectedArraylist.add("DEACTIVATE");
				expectedArraylist.add("Active");
			}
			
			String[] details = addgrpdataObj.getFilterData();
			grouplistObj.filterGroups(details[0], "", "", "");
			Thread.sleep(1500);
			autoScrollandClick(grouplistObj.viewDetails);
			waitForElementToLoad(viewgroupObj.activateButton);
			viewgroupObj.activateButton.click();
			waitForElementToLoad(viewgroupObj.yes);
			viewgroupObj.yes.click();
			waitForElementToLoad(viewgroupObj.statUpdateSucessMsg);
			actualArraylist.add(viewgroupObj.statUpdateSucessMsg.getText());
			waitForElementToLoad(viewgroupObj.deactivateButton);
			actualArraylist.add(viewgroupObj.deactivateButton.getText());
			actualArraylist.add(viewgroupObj.status.getText());
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(actualArraylist);
		System.out.println(expectedArraylist);
		Assert.assertEquals(actualArraylist, expectedArraylist);
		log.info("Assertion Passed");
	}
	
	
	@Test(priority=5)
	public void verifySortGrpName()
	{
		try
		{
			log.info("Entered verifySortGrpName test");	
			eTest = eReports.createTest("Verify Sort Group Name");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.grupNameSortBtn, grouplistObj.firstGrpName);
			actualMsg = grouplistObj.firstGrpName.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=6)
	public void verifySortForGroupType()
	{
		try
		{
			log.info("Entered verifySortForGroupType test");	
			eTest = eReports.createTest("Verify Sort For GroupType");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.grupTypSortBtn, grouplistObj.firstGrpTyp);
			actualMsg = grouplistObj.firstGrpTyp.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=7)
	public void verifySortForStatus()
	{
		try
		{
			log.info("Entered verifySortForStatus test");	
			eTest = eReports.createTest("Verify Sort For Status");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.statusSortBtn, grouplistObj.firstStatus);
			actualMsg = grouplistObj.firstStatus.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
	}
	
	@Test(priority=8)
	public void verifySortForTeacher()
	{
		try
		{
			log.info("Entered verifySortForTeacher test");	
			eTest = eReports.createTest("Verify Sort For Teacher");
			eTest.assignCategory("Groups - Group List");
			 
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
			dashboardObj.groupList.click();
			
			expectedMsg = verifySortButton(grouplistObj.teacherSortBtn, grouplistObj.firstTeacher);
			actualMsg = grouplistObj.firstTeacher.getText();
			waitIfElementClickIsIntercepted(dashboardObj.groups, "click", "");
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
