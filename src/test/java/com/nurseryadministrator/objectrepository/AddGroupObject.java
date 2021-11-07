package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AddGroupObject extends SetUp {

	WebDriver driver;
	
	public AddGroupObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//small[contains(text(),'Group name is required')] | //small[contains(text(),'اسم المجموعة مطلوب')]")
	public WebElement groupNameMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Group type is required')] | //small[contains(text(),'نوع المجموعة مطلوب')]")
	public WebElement groupTypeMandatoryMsg;
	
	@FindBy(xpath="//input[@id='groupName']")
	public WebElement groupName;
	@FindBy(xpath="//select[@id='groupType']")
	public WebElement groupType;
	@FindBy(xpath="//button[contains(text(),'Add')]|//button[contains(text(),'اضافة')]")
	public WebElement addButton;
	
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Group Created Successfully'] | //div[contains(text(),'تم انشاء الصف الدراسي بنجاح')]")
	public WebElement groupSuccessMsg;
}
