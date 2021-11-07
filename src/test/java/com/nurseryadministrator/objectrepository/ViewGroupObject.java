package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class ViewGroupObject extends SetUp {

	WebDriver driver;
	
	public ViewGroupObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//label[contains(text(),'Group Name:')]//following::span[1] | //label[contains(text(),'اسم المجموعة:')]//following::p[1]")
	public WebElement grupName;
	@FindBy(xpath="//label[contains(text(),'Group Type:')]//following::p[1] | //label[contains(text(),'نوع المجموعة:')]//following::p[1]")
	public WebElement grupType;
	@FindBy(xpath="//label[contains(text(),'Status:')]//following::p[1] | //label[contains(text(),'حالة:')]//following::p[1]")
	public WebElement status;
	
	@FindBy(xpath="//button[contains(text(),'Deactivate')] | //button[contains(text(),'تعطيل')]")
	public WebElement deactivateButton;
	@FindBy(xpath="//button[contains(text(),'Yes')] | //button[contains(text(),'موافق')]")
	public WebElement yes;
	@FindBy(xpath="//button[contains(text(),'Activate')] | //button[contains(text(),'تفعيل')]")
	public WebElement activateButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Status Updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث الحالة بنجاح']")
	public WebElement statUpdateSucessMsg;
	
	@FindBy(xpath="//label[contains(text(),'Teacher Name:')]//following::i[1] | //label[contains(text(),'اسم المدرس:')]//following::i[1]")
	public WebElement addTeacher;
	@FindBy(xpath="//input[@formcontrolname='teacherName']")
	public WebElement teacherName;
	@FindBy(xpath="//input[@formcontrolname='phoneNumber']")
	public  WebElement phone;
	@FindBy(xpath="//table[1]/tbody[1]/tr[1]/td[1]//input[1]")
	public  WebElement teacherRadButton;
	@FindBy(xpath="//label[contains(text(),'Teacher Name:')]//following::a[1] | //label[contains(text(),'اسم المدرس:')]//following::a[1]")
	public WebElement teacher;
	@FindBy(xpath="//label[contains(text(),'Teacher Name:')]//following::i[2] | //label[contains(text(),'اسم المدرس:')]//following::i[2]")
	public WebElement removeTeacher;
	@FindBy(xpath="//label[contains(text(),'Teacher Name:')]//following::i[1] | //label[contains(text(),'اسم المدرس:')]//following::i[1]")
	public WebElement changeTeacher;
	
	@FindBy(xpath="//a[contains(text(),'Add Assistants')] | //a[contains(text(),'اضافة مساعد')]")
	public  WebElement addAssistants;
	@FindBy(xpath="//input[@formcontrolname='name']")
	public  WebElement assistantName;
	@FindBy(xpath="//input[@formcontrolname='number']")
	public  WebElement phoneNo;
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[1]//child::input[1]")
	public  WebElement assistantcheckBox;
	@FindBy(xpath="//tbody/tr[1]/td[3]/i[1]")
	public WebElement removeAssistants;
	
	@FindBy(xpath="//a[contains(text(),'Add Students')] | //a[contains(text(),'اضافة طالب')]")
	public  WebElement addStudents;
	@FindBy(xpath="//input[@formcontrolname='name']")
	public  WebElement studentName;
	@FindBy(xpath="//input[@formcontrolname='parentName']")
	public  WebElement parentName;
	@FindBy(xpath="//input[@formcontrolname='civilId']")
	public  WebElement civilId;
	@FindBy(xpath="//select[@formcontrolname='gender']")
	public  WebElement gender;
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[1]//child::input[1]")
	public  WebElement studentcheckBox;
	@FindBy(xpath="//tbody/tr[1]/td[6]/i[1]")
	public WebElement removeStudents;
	
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	@FindBy(xpath="//button[contains(text(),'Select')] | //button[contains(text(),'اختر')]")
	public  WebElement selectButton;
	
	@FindBy(xpath="//label[contains(text(),'Teacher Name:')]//following::div[contains(text(),'Unassigned')] | //label[contains(text(),'اسم المدرس:')]//following::div[contains(text(),'غير معين')]")
	public WebElement teacherUnassigned;
	@FindBy(xpath="//td[contains(text(),'No assistants in the group')] | //td[contains(text(),'لايوجد مساعدين للمجموعة')]")
	public WebElement noAssistantInGroup;
	@FindBy(xpath="//td[contains(text(),'No students in the group')] | //td[contains(text(),'لايوجد طلاب في المجموعة')]")
	public WebElement noStudentInGroup;
	
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Group Updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث معلومات الصف الدراسي بنجاح']")
	public WebElement updateSucessMsg;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Student Updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث معلومات الطالب بنجاح']")
	public WebElement studentUpdateSucessMsg;

	
	
	public void assignTeacher(String nam, String number) 
	{
		try
		{
			waitForElementToLoad(filterButton);
			teacherName.sendKeys(nam);
			phone.sendKeys(number);
			filterButton.click();
			autoScrollandClick(teacherRadButton);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		
	}
	
	public void assignAssistants(String nam, String number)
	{
		try
		{
			waitForElementToLoad(assistantName);
			assistantName.sendKeys(nam);
			phoneNo.sendKeys(number);
			filterButton.click();
			autoScrollandClick(assistantcheckBox);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public void assignStudents(String nam, String parentnam, String id, String gndr)
	{
		try
		{
			waitForElementToLoad(parentName);
			studentName.sendKeys(nam);
			parentName.sendKeys(parentnam);
			civilId.sendKeys(id);
			gender.sendKeys(gndr);
			filterButton.click();
			Thread.sleep(2000);
			autoScrollandClick(studentcheckBox);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
