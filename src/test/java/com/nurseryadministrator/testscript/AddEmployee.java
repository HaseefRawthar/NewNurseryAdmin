package com.nurseryadministrator.testscript;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nurseryadministrator.baseclass.SetUp;
import com.nurseryadministrator.objectrepository.AddEmployeeObject;
import com.nurseryadministrator.objectrepository.DashboardObject;
import com.nurseryadministrator.objectrepository.EmployeeDeatailsObject;
import com.nurseryadministrator.objectrepository.LogInObject;
import com.nurseryadministrator.testdata.AddEmployeeData;
import com.nurseryadministrator.testdata.TestDataImport;

public class AddEmployee extends SetUp{

	LogInObject loginObj;
	DashboardObject dashboardObj;
	TestDataImport tdImport;
	AddEmployeeObject addemployeeObj;
	AddEmployeeData addemployeedataObj;
	EmployeeDeatailsObject employeedetailObj;
	String[] employeeData;
	String[] roleData;
	
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
	public void verifyMandatoryMsgVisibile()
	{
		try
		{
			actualBoolArray = new ArrayList<Boolean>();
			expectedBoolArray = new ArrayList<Boolean>();
			
			log.info("Entered verifyMandatoryMsgVisibile test");		
			eTest = eReports.createTest("Verify Mandatory Msg Visibile");
			eTest.assignCategory("Employee - Add Employee");
			
			for(int i=0;i<6;i++)
			{
				expectedBoolArray.add(true);
			}
			
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			waitForElementToLoad(addemployeeObj.addButton);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.firstNameMandatoryMsg);
			actualBoolArray.add(isElementPresent(addemployeeObj.firstNameMandatoryMsg));
			actualBoolArray.add(isElementPresent(addemployeeObj.countryCodeMandatoryMsg));
			actualBoolArray.add(isElementPresent(addemployeeObj.phoneMandatoryMsg));
			actualBoolArray.add(isElementPresent(addemployeeObj.roleMandatoryMsg));
			actualBoolArray.add(isElementPresent(addemployeeObj.engRoleTitleMandatoryMsg));
			actualBoolArray.add(isElementPresent(addemployeeObj.arabRoleTitleMandatoryMsg));
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
	
	@Test(priority=2)
	public void addEmployeeAsTeacher()
	{
		try
		{
			log.info("Entered addEmployeeAsTeacher test");		
			eTest = eReports.createTest("Add Employee As Teacher");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مدرس (مدرس)":"Teacher (Teacher)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=3)
	public void addEmployeeAsAssistant()
	{
		try
		{
			log.info("Entered addEmployeeAsAssistant test");		
			eTest = eReports.createTest("Add Employee As Assistant");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مساعد (مساعد)":"Assistant (Assistant)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=4)
	public void addEmployeeAsNurse()
	{
		try
		{
			log.info("Entered addEmployeeAsNurse test");		
			eTest = eReports.createTest("Add Employee As Nurse");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"ممرض (ممرض)":"Nurse (Nurse)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=5)
	public void addEmployeeAsTeacherAndAssistant()
	{
		try
		{
			log.info("Entered addEmployeeAsNurse test");		
			eTest = eReports.createTest("Add Employee As Teacher And Assistant");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مدرس (مدرس)":"Teacher (Teacher)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData,1);
			autoScrollandClick(addemployeeObj.addMoreRoles);
			roleData[0] = (language.equals("Arabic"))?"مساعد (مساعد)":"Assistant (Assistant)";
			addemployeeObj.addRoles(roleData,2);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=6)
	public void addEmployeeAsTeacherAndNurse()
	{
		try
		{
			log.info("Entered addEmployeeAsTeacherAndNurse test");		
			eTest = eReports.createTest("Add Employee As Teacher And Nurse");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مدرس (مدرس)":"Teacher (Teacher)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData,1);
			autoScrollandClick(addemployeeObj.addMoreRoles);
			roleData[0] = (language.equals("Arabic"))?"ممرض (ممرض)":"Nurse (Nurse)";
			addemployeeObj.addRoles(roleData,2);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=7)
	public void addEmployeeAsAssistantAndNurse()
	{
		try
		{
			log.info("Entered addEmployeeAsTeacherAndNurse test");		
			eTest = eReports.createTest("Add Employee As Asiistant And Nurse");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مساعد (مساعد)":"Assistant (Assistant)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData,1);
			autoScrollandClick(addemployeeObj.addMoreRoles);
			roleData[0] = (language.equals("Arabic"))?"ممرض (ممرض)":"Nurse (Nurse)";
			addemployeeObj.addRoles(roleData,2);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@Test(priority=8)
	public void addEmployeeWithAllRoles()
	{
		try
		{
			log.info("Entered addEmployeeAsTeacherAndNurse test");		
			eTest = eReports.createTest("Add Employee With All Roles");
			eTest.assignCategory("Employee - Add Employee");
			
			String role = (language.equals("Arabic"))?"مساعد (مساعد)":"Assistant (Assistant)";
			expectedMsg = (language.equals("Arabic"))?"تم اضافة الموظف بنجاح":"Employee Added Successfully";
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			dashboardObj.addEmployee.click();
			addemployeedataObj.generateFakeEmployeeData();
			employeeData = addemployeedataObj.getEmployeeDetails();
			addemployeeObj.addEmployeeDetails(employeeData);
			roleData = addemployeedataObj.getRoleData();
			roleData[0] = role;
			addemployeeObj.addRoles(roleData,1);
			autoScrollandClick(addemployeeObj.addMoreRoles);
			roleData[0] = (language.equals("Arabic"))?"ممرض (ممرض)":"Nurse (Nurse)";
			addemployeeObj.addRoles(roleData,2);
			autoScrollandClick(addemployeeObj.addMoreRoles);
			roleData[0] = (language.equals("Arabic"))?"مدرس (مدرس)":"Teacher (Teacher)";
			addemployeeObj.addRoles(roleData,3);
			autoScrollandClick(addemployeeObj.addButton);
			waitForElementToLoad(addemployeeObj.employeeSuccessMsg);
			actualMsg = addemployeeObj.employeeSuccessMsg.getText();
			waitIfElementClickIsIntercepted(dashboardObj.employees, "click", "");
			
		}
		catch(Exception e){
			e.printStackTrace();
			log.info(e);
		}
		System.out.println(expectedMsg);
		System.out.println(actualMsg);
		Assert.assertEquals(expectedMsg, actualMsg);
		log.info("Assertion Passed");
	}
	
	@AfterClass
	public void signOut()
	{
		dashboardObj.logOut();
	}
}
