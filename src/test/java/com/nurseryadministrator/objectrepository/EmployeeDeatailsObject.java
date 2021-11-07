package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class EmployeeDeatailsObject extends SetUp{

	WebDriver driver;
	
	public EmployeeDeatailsObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//label[contains(text(),'Name:')]//following::p[1] | //label[contains(text(),'اسم:')]//following::p[1]")
	public WebElement name;
	@FindBy(xpath="//label[contains(text(),'Email:')]//following::p[1] | //label[contains(text(),'البريد الالكتروني:')]//following::p[1]")
	public WebElement email;
	@FindBy(xpath="//label[contains(text(),'Nationality:')]//following::p[1] | //label[contains(text(),'الجنسية:')]//following::p[1]")
	public WebElement nationality;
	@FindBy(xpath="//label[contains(text(),'Phone Number:')]//following::p[1] | //label[contains(text(),'رقم الهاتف:')]//following::p[1]")
	public WebElement phone;
	@FindBy(xpath="//th[contains(text(),'Role')]//following::td[1] | //th[contains(text(),'الدور')]//following::td[1]")
	public WebElement role;
	@FindBy(xpath="//th[contains(text(),'Role')]//following::td[2] | //th[contains(text(),'الدور')]//following::td[2]")
	public WebElement titleEng;
	@FindBy(xpath="//th[contains(text(),'Role')]//following::td[3] | //th[contains(text(),'الدور')]//following::td[3]")
	public WebElement titleArab;
	@FindBy(xpath="//th[contains(text(),'Role')]//following::td[4] | //th[contains(text(),'الدور')]//following::td[4]")
	public WebElement group;
	@FindBy(xpath="//label[contains(text(),'Status:')]//following::p[1] | //label[contains(text(),'حالة:')]//following::p[1]")
	public WebElement status;
	
	@FindBy(xpath="//button[contains(text(),'Deactivate')] | //button[contains(text(),'تعطيل')]")
	public WebElement deactivateButton;
	@FindBy(xpath="//button[contains(text(),'Yes')] | //button[contains(text(),'موافق')]")
	public WebElement yes;
	@FindBy(xpath="//button[contains(text(),'Activate')] | //button[contains(text(),'تفعيل')]")
	public WebElement activateButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Employee status updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث الحالة بنجاح']")
	public WebElement statUpdateSucessMsg;
	
}
