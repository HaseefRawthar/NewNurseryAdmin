package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class GrantTemporaryAdminAccessObject extends SetUp {

	WebDriver driver;
	
	public GrantTemporaryAdminAccessObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//select[@id='groupType']")
	public WebElement accessDuration;
	@FindBy(xpath="//button[contains(text(),'Submit')] | //button[contains(text(),'تقديم الطلب')]")
	public WebElement submitButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]")
	public WebElement status;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]/span[1]")
	public WebElement startDate;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]/span[1]")
	public WebElement endDate;
	@FindBy(xpath="//span[contains(text(),'Revoke')] | //span[contains(text(),'الغاء')]")
	public WebElement revoke;
	@FindBy(xpath="//button[contains(text(),'Yes')] | //button[contains(text(),'موافق')]")
	public WebElement yes;
	
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Support ticket added successfully'] |//div[@role='alertdialog'][@aria-label='تم اضافة بطاقة الدعم بنجاح']")
	public WebElement grantedMsg;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Support ticket updated successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث بطاقة الدعم بنجاح']")
	public WebElement revokedMsg;
	@FindBy(xpath="//div[@role='alertdialog']")
    public WebElement Msg;
}
