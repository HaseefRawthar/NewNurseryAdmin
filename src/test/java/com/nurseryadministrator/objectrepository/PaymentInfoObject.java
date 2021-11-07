package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class PaymentInfoObject extends SetUp {

	WebDriver driver;
	
	public PaymentInfoObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//label[contains(text(),'Title:')]//following::p[1] | //label[contains(text(),'عنوان:')]//following::p[1]")
	public WebElement title;
	@FindBy(xpath="//label[contains(text(),'Amount:')]//following::p[1] | //label[contains(text(),'القيمة:')]//following::p[1]")
	public WebElement amount;
	@FindBy(xpath="//label[contains(text(),'Description:')]//following::p[1] | //label[contains(text(),'وصف:')]//following::p[1]")
	public WebElement description;
	@FindBy(xpath="//label[contains(text(),'Due date:')]//following::p[1] | //label[contains(text(),'تاريخ استحقاق الدفعة:')]//following::p[1]")
	public WebElement dueDate;
	@FindBy(xpath="//label[contains(text(),'Student Name:')]//following::p[1] | //label[contains(text(),'اسم الطالب:')]//following::p[1]")
	public WebElement studentName;
	@FindBy(xpath="//label[contains(text(),'Group Name:')]//following::p[1] | //label[contains(text(),'اسم المجموعة:')]//following::p[1]")
	public WebElement groupName;
	@FindBy(xpath="//label[contains(text(),'Status:')]//following::p[1] | //label[contains(text(),'حالة:')]//following::p[1]")
	public WebElement status;
	@FindBy(xpath="//label[contains(text(),'Accept past due payments:')]//following::p[1] | //label[contains(text(),'قبول المدفوعات المتأخرة:')]//following::p[1]")
	public WebElement acceptDuePayments;
	@FindBy(xpath="//label[contains(text(),'Created Date:')]//following::p[1] | //label[contains(text(),'تاريخ الانشاء:')]//following::p[1]")
	public WebElement createDate;
	@FindBy(xpath="//button[contains(text(),'Back')]")
	public WebElement backButton;
	
	
	@FindBy(xpath="//tbody/tr[1]/td[2]")
	public WebElement paymentGateway1;
	@FindBy(xpath="//tbody/tr[1]/td[3]")
	public WebElement paymentMode1;
	@FindBy(xpath="//tbody/tr[1]/td[4]")
	public WebElement paymentMethod1;
	@FindBy(xpath="//tbody/tr[1]/td[5]")
	public WebElement status1;
	@FindBy(xpath="//tbody/tr[1]/td[8]")
	public WebElement transactionDate1;
	@FindBy(xpath="//tbody/tr[1]/td[9]")
	public WebElement comments1;
	
	@FindBy(xpath="//tbody/tr[2]/td[2]")
	public WebElement paymentGateway2;
	@FindBy(xpath="//tbody/tr[2]/td[3]")
	public WebElement paymentMode2;
	@FindBy(xpath="//tbody/tr[2]/td[4]")
	public WebElement paymentMethod2;
	@FindBy(xpath="//tbody/tr[2]/td[5]")
	public WebElement status2;
	@FindBy(xpath="//tbody/tr[2]/td[8]")
	public WebElement transactionDate2;
	@FindBy(xpath="//tbody/tr[2]/td[9]")
	public WebElement comments2;
}
