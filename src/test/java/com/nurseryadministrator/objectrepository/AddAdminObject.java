package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AddAdminObject extends SetUp {

	WebDriver driver;
	
	public AddAdminObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//small[contains(text(),'First name is required')] | //small[contains(text(),'الاسم الاول مطلوب')]")
	public WebElement firstNameMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Email is required')] | //small[contains(text(),'البريد الالكتروني مطلوب')]")
	public WebElement emailMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Country code is required')] | //small[contains(text(),'رمز الدولة مطلوب')]")
	public WebElement countryCodeMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Please enter a valid phone number')] | //small[contains(text(),'يرجى ادخال رقم هاتف صالح')]")
	public WebElement phoneMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Nationality is required')] | //small[contains(text(),'الجنسية مطلوبة')]")
	public WebElement nationalityMandatoryMsg;
	
	@FindBy(xpath="//input[@id='firstName']")
	public WebElement firstName;
	@FindBy(xpath="//input[@id='middleName']")
	public WebElement middleName;
	@FindBy(xpath="//input[@id='lastName']")
	public WebElement lastName;
	@FindBy(xpath="//input[@id='email']")
	public WebElement email;
	@FindBy(xpath="//select[@formcontrolname='nationality']")
	public WebElement nationality;
	@FindBy(xpath="//select[@id='phoneCode']")
	public WebElement countryCode;
	@FindBy(xpath="//input[@id='phoneNumber']")
	public WebElement phone;
	@FindBy(xpath="//input[@id='title']")
	public WebElement titleEng;
	@FindBy(xpath="//input[@id='titleAr']")
	public WebElement titleArb;
	@FindBy(xpath="//button[contains(text(),'Add')] | //button[contains(text(),'اضافة')]")
	public WebElement addButton;
	
	@FindBy(xpath="//button[contains(text(),'Save')] | //button[contains(text(),'حفظ')]")
	public WebElement saveButton;
	
	@FindBy(xpath="//div[contains(text(),'Nursery Admin created successfully')]")
	public WebElement adminSuccessMsg;
	@FindBy(xpath="//div[contains(text(),'Nursery Admin updated successfully')] | //div[@aria-label='تم تحديث حالة حساب اداري الحضانة بنجاح']")
	public WebElement adminUpdateSuccessMsg;
	
	public void addAdminDetails(String[] test)
	{
		try
		{
			waitForElementToLoad(firstName);
			firstName.sendKeys(test[0]);
			middleName.sendKeys(test[1]);
			lastName.sendKeys(test[2]);
			email.sendKeys(test[3]);
			nationality.sendKeys(test[4]);
			countryCode.sendKeys(test[5]);
			phone.sendKeys(test[6]);
			titleEng.sendKeys(test[7]);
			titleArb.sendKeys(test[8]);
			autoScrollandClick(addButton);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
