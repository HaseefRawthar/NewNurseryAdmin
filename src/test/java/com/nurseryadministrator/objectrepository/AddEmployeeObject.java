package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AddEmployeeObject extends SetUp{

	WebDriver driver;
	
	public AddEmployeeObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//small[contains(text(),'First name is required')] | //small[contains(text(),'الاسم الاول مطلوب')]")
	public WebElement firstNameMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Country code is required')] | //small[contains(text(),'رمز الدولة مطلوب')]")
	public WebElement countryCodeMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Valid Phone Number is required')] | //small[contains(text(),'رقم الهاتف الصالح مطلوب')]")
	public WebElement phoneMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Valid Phone Number is required')] | //small[contains(text(),'رقم الهاتف الصالح مطلوب')]")
	public WebElement roleMandatoryMsg;
	@FindBy(xpath="//tbody/tr[1]/td[3]/small[1]")
	public WebElement engRoleTitleMandatoryMsg;
	@FindBy(xpath="//tbody/tr[1]/td[4]/small[1]")
	public WebElement arabRoleTitleMandatoryMsg;

	
	@FindBy(xpath="//input[@id='firstName']")
	public WebElement firstName;
	@FindBy(xpath="//input[@id='middleName']")
	public WebElement middleName;
	@FindBy(xpath="//input[@id='lastName']")
	public WebElement lastName;
	@FindBy(xpath="//input[@id='email']")
	public WebElement employeeEmail;
	@FindBy(xpath="//select[@id='nationality']")
	public WebElement nationality;
	@FindBy(xpath="//select[@id='phoneCode']")
	public WebElement countryCode;
	@FindBy(xpath="//input[@id='phoneNumber']")
	public WebElement phone;
	@FindBy(xpath="//tbody/tr[1]/td[2]/select[1]")
	public WebElement role;
	@FindBy(xpath="//tbody/tr[1]/td[3]/input[1]")
	public WebElement titleEng;
	@FindBy(xpath="//tbody/tr[1]/td[4]/input[1]")
	public WebElement titleArab;
	@FindBy(xpath="//tbody/tr[1]/td[5]/span[1]/ng-multiselect-dropdown[1]/div[1]/div[1]/span[1]")
	public WebElement group;
	@FindBy(xpath="//div[contains(text(),'cyan64tgs')]")
	public WebElement cyan64tgs;
	@FindBy(xpath="//span[contains(text(),'Add more roles')] | //span[contains(text(),'اضافة ادوار')]")
	public WebElement addMoreRoles;
	@FindBy(xpath="//button[contains(text(),'Add')] | //button[contains(text(),'اضافة')]")
	public WebElement addButton;
	
	@FindBy(xpath="//div[@role='alertdialog']")
	public WebElement employeeSuccessMsg;
	
	public void addEmployeeDetails(String[] test)
	{
		try
		{
			waitForElementToLoad(firstName);
			firstName.sendKeys(test[0]);
			middleName.sendKeys(test[1]);
			lastName.sendKeys(test[2]);
			employeeEmail.sendKeys(test[3]);
			nationality.sendKeys(test[4]);
			countryCode.sendKeys(test[5]);
			phone.sendKeys(test[6]);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		
	}
	
	public void addRoles(String[] test)
	{
		try
		{
			waitForElementToLoad(role);
			role.sendKeys(test[0]);
			titleEng.clear();
			titleEng.sendKeys(test[1]);
			titleArab.clear();
			titleArab.sendKeys(test[2]);
			//group.click();
			//driver.findElement(By.xpath("//div[contains(text(),'"+test[3]+"')]")).click();
			//cyan64tgs.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public void addRoles(String[] test, int i)
	{
		try
		{
			waitForElementToLoad(role);
			driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]/select[1]")).sendKeys(test[0]);
			WebElement titeng = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]/input[1]"));
			titeng.clear();
			titeng.sendKeys(test[1]);
			WebElement titarb = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[4]/input[1]"));
			titarb.clear();
			titarb.sendKeys(test[2]);
			//driver.findElement(By.xpath("//tbody/tr["+i+"]/td[5]/span[1]/ng-multiselect-dropdown[1]/div[1]/div[1]/span[1]")).click();
			//driver.findElement(By.xpath("//div[contains(text(),'"+test[3]+"')]")).click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
