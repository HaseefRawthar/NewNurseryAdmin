package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AdminViewObject extends SetUp {

	WebDriver driver;
	
	public AdminViewObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//label[contains(text(),'Name:')]//following::p[1] | //label[contains(text(),'اسم:')]//following::p[1]")
	public WebElement name;
	@FindBy(xpath="//label[contains(text(),'Email:')]//following::p[1] | //label[contains(text(),'البريد الالكتروني:')]//following::p[1]")
	public WebElement email;
	@FindBy(xpath="//label[contains(text(),'Phone Number:')]//following::p[1] | //label[contains(text(),'رقم الهاتف:')]//following::p[1]")
	public WebElement phoneNumber;
	@FindBy(xpath="//label[contains(text(),'Status:')]//following::p[1] | //label[contains(text(),'حالة:')]//following::p[1]")
	public WebElement status;
	@FindBy(xpath="//label[contains(text(),'Role Title(En):')]//following::p[1] | //label[contains(text(),'الدور الوظيفي (انجليزي):')]//following::p[1]")
	public WebElement titleEng;
	@FindBy(xpath="//label[contains(text(),'Role Title(Ar):')]//following::p[1] | //label[contains(text(),'الدور الوظيفي (عربي):')]//following::p[1]")
	public WebElement titleArb;
	
	@FindBy(xpath="//span[contains(text(),'Deactivate')] | //span[contains(text(),'تعطيل')]")
	public WebElement deactivateButton;
	@FindBy(xpath="//button[contains(text(),'Yes')] | //button[contains(text(),'موافق')]")
	public WebElement yes;
	@FindBy(xpath="//span[contains(text(),'Activate')] | //span[contains(text(),'تفعيل')]")
	public WebElement activateButton;
	
	@FindBy(xpath="//div[contains(text(),'Admin status updated Successfully')] | //div[@aria-label='تم تحديث حالة حساب الاداري بنجاح']")
	public WebElement statusUpdateMsg;


}
