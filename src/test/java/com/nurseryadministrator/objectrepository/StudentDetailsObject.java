package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class StudentDetailsObject extends SetUp {

	WebDriver driver;
	
	public StudentDetailsObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//label[contains(text(),'First Name:')]//following::p[1] | //label[contains(text(),'الاسم الاول:')]//following::p[1]")
	public WebElement firstName;
	@FindBy(xpath="//label[contains(text(),'Second Name:')]//following::p[1] | //label[contains(text(),'الاسم الثاني:')]//following::p[1]")
	public WebElement secondName;
	@FindBy(xpath="//label[contains(text(),'Third Name:')]//following::p[1] | //label[contains(text(),'الاسم الثالث:')]//following::p[1]")
	public WebElement thirdName;
	@FindBy(xpath="//label[contains(text(),'Last Name:')]//following::p[1] | //label[contains(text(),'الاسم الأخير:')]//following::p[1]")
	public WebElement lastName;
	@FindBy(xpath="//label[contains(text(),'Civil ID Number/Passport Number:')]//following::p[1] | //label[contains(text(),'رقم الهوية/رقم الجواز:')]//following::p[1]")
	public WebElement civilID;
	@FindBy(xpath="//label[contains(text(),'Nationality:')]//following::p[1] | //label[contains(text(),'الجنسية:')]//following::p[1]")
	public WebElement nationality;
	@FindBy(xpath="//label[contains(text(),'Place of birth:')]//following::p[1] | //label[contains(text(),'مكان الولادة:')]//following::p[1]")
	public WebElement PlaceOfBirth;
	@FindBy(xpath="//label[contains(text(),'Date of birth:')]//following::p[1] | //label[contains(text(),'تاريخ الميلاد:')]//following::p[1]")
	public WebElement dateOfBirth;
	@FindBy(xpath="//label[contains(text(),'Gender:')]//following::p[1] | //label[contains(text(),'الجنس:')]//following::p[1]")
	public WebElement gender;
	@FindBy(xpath="//label[contains(text(),'Religion:')]//following::p[1] | //label[contains(text(),'الديانة:')]//following::p[1]")
	public WebElement religion;
	@FindBy(xpath="//label[contains(text(),'Address:')]//following::p[1] | //label[contains(text(),'عنوان:')]//following::p[1]")
	public WebElement address;
	@FindBy(xpath="//label[contains(text(),'Allergies:')]//following::p[1] | //label[contains(text(),'الحساسية:')]//following::p[1]")
	public WebElement allergies;
	@FindBy(xpath="//label[contains(text(),'Nursery Notes:')]//following::p[1] | //label[contains(text(),'ملاحظات الحضانة:')]//following::p[1]")
	public WebElement nurseryNotes;
	@FindBy(xpath="//label[contains(text(),'Parent Notes:')]//following::p[1] | //label[contains(text(),'ملاحظات الوالدين:')]//following::p[1]")
	public WebElement parentNotes;
	@FindBy(xpath="//label[contains(text(),'Group Name:')]//following::p[1] | //label[contains(text(),'اسم المجموعة:')]//following::p[1]")
	public WebElement groupName;
	@FindBy(xpath="//label[contains(text(),'First day of school:')]//following::p[1] | //label[contains(text(),'اول يوم في المدرسة:')]//following::p[1]")
	public WebElement firstDayOfSchool;
	@FindBy(xpath="//label[contains(text(),'Status:')]//following::p[1] | //label[contains(text(),'حالة:')]//following::p[1]")
	public WebElement status;
	
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')] | //h4[contains(text(),'معلومات ولي الأمر 1')]")
	public WebElement parentInformation1;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[1] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[1]")
	public WebElement parent1Name;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[2] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[2]")
	public WebElement parent1Job;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[3] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[3]")
	public WebElement parent1CivilID;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[4] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[4]")
	public WebElement parent1Email;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[5] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[5]")
	public WebElement parent1Phone;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[6] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[6]")
	public WebElement parent1Religion;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[7] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[7]")
	public WebElement parent1Relation;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::p[8] | //h4[contains(text(),'معلومات ولي الأمر 1')]//following::p[8]")
	public WebElement parent1Nationality;
	
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')] | //h4[contains(text(),'معلومات ولي الأمر 2')]")
	public WebElement parentInformation2;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[1] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[1]")
	public WebElement parent2Name;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[2] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[2]")
	public WebElement parent2Job;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[3] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[3]")
	public WebElement parent2CivilID;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[4] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[4]")
	public WebElement parent2Email;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[5] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[5]")
	public WebElement parent2Phone;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[6] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[6]")
	public WebElement parent2Religion;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[7] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[7]")
	public WebElement parent2Relation;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 2')]//following::p[8] | //h4[contains(text(),'معلومات ولي الأمر 2')]//following::p[8]")
	public WebElement parent2Nationality;
	
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')] | //h4[contains(text(),'الاتصال بالطوارئ')]")
	public WebElement emergencyContact;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::p[1] | //h4[contains(text(),'الاتصال بالطوارئ')]//following::p[1]")
	public WebElement emergName;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::p[2] | //h4[contains(text(),'الاتصال بالطوارئ')]//following::p[2]")
	public WebElement emergPhone;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::p[3] | //h4[contains(text(),'الاتصال بالطوارئ')]//following::p[3]")
	public WebElement emergRelation;
	
	@FindBy(xpath="//h4[contains(text(),'Authorized Pick Up')] | //h4[contains(text(),'المستلم')]")
	public WebElement authorizedPickUp;
	@FindBy(xpath="//h4[contains(text(),'Authorized Pick Up')]//following::p[1] | //h4[contains(text(),'المستلم')]//following::p[1]")
	public WebElement authName;
	@FindBy(xpath="//h4[contains(text(),'Authorized Pick Up')]//following::p[2] | //h4[contains(text(),'المستلم')]//following::p[2]")
	public WebElement authCivilID;
	@FindBy(xpath="//h4[contains(text(),'Authorized Pick Up')]//following::p[3] | //h4[contains(text(),'المستلم')]//following::p[3]")
	public WebElement authPhone;
	@FindBy(xpath="//h4[contains(text(),'Authorized Pick Up')]//following::p[4] | //h4[contains(text(),'المستلم')]//following::p[4]")
	public WebElement authRelation;
	
	@FindBy(xpath="//button[contains(text(),'Deactivate')] | //button[contains(text(),'تعطيل')]")
	public WebElement deactivateButton;
	@FindBy(xpath="//button[contains(text(),'Yes')] | //button[contains(text(),'موافق')]")
	public WebElement yes;
	@FindBy(xpath="//button[contains(text(),'Activate')] | //button[contains(text(),'تفعيل')]")
	public WebElement activateButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Student status updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث حالة حساب الطالب بنجاح']")
	public WebElement statUpdateSucessMsg;

	
}
