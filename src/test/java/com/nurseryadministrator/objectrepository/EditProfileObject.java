package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class EditProfileObject extends SetUp {

	WebDriver driver;
	
	public EditProfileObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//input[@id='firstName']")
	public WebElement name;
	@FindBy(xpath="//input[@id='email']")
	public WebElement email;
	@FindBy(xpath="//select[@id='phoneCode']")
	public WebElement countryCode;
	@FindBy(xpath="//input[@id='phoneNumber']")
	public WebElement phone;
	@FindBy(xpath="//input[@type='file']")
	public WebElement profilrPic;
	@FindBy(xpath="//button[contains(text(),'Save')] | //button[contains(text(),'حفظ')]")
	public WebElement saveButton;
	
	@FindBy(xpath="//input[@Formcontrolname='otp']")
	public WebElement oneTimePassword;
	@FindBy(xpath="//button[contains(text(),'Verify')] | //button[contains(text(),'تأكيد')]")
	public WebElement verifyButton;
	@FindBy(xpath="//div[contains(text(),'Account updated successfully')] | //div[@aria-label='تم تحديث الحساب بنجاح']")
	public WebElement accountUpdateSuccessMsg;
	
}
