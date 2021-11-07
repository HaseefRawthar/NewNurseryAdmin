package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nurseryadministrator.baseclass.SetUp;

public class RequestPaymentObject extends SetUp {

	WebDriver driver;
	
	public RequestPaymentObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//input[@id='title']")
	public WebElement title;
	@FindBy(xpath="//input[@id='amount']")
	public WebElement amount;
	@FindBy(xpath="//label[contains(text(),'Description')]//following::textarea | //label[contains(text(),'وصف')]//following::textarea")
	public WebElement description;
	@FindBy(xpath="//label[contains(text(),'Due date')]//following::span | //label[contains(text(),'تاريخ استحقاق الدفعة')]//following::span")
	public WebElement dueDate;
	@FindBy(xpath="//span[contains(text(),'Today')]")
	public WebElement today;
	@FindBy(xpath="//input[@id='pastDue']")
	public WebElement acceptPaymentCheckbox;
	@FindBy(xpath="//label[contains(text(),'Group')] | //label[contains(text(),'مجموعة')]")
	public WebElement group;
	@FindBy(xpath="//div[contains(text(),'Add Groups')] | //div[contains(text(),'اضافة مجموعة')]")
	public WebElement addGroup;
	@FindBy(xpath="//label[contains(text(),'Student')] | //label[contains(text(),'طالب')]")
	public WebElement student;
	@FindBy(xpath="//div[contains(text(),'Add Students')] | //div[contains(text(),'اضافة طالب')]")
	public WebElement addStudents;
	@FindBy(xpath="//button[contains(text(),'Add')] | //button[contains(text(),'اضافة')]")
	public WebElement addButton;
	
	@FindBy(xpath="//input[@formcontrolname='name']")
	public WebElement groupName;
	@FindBy(xpath="//select[@formcontrolname='groupType']")
	public WebElement groupType;
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	@FindBy(xpath="//button[contains(text(),'Select')] | //button[contains(text(),'اختر')]")
	public  WebElement selectButton;
	@FindBy(xpath="//tbody/tr[1]/td[1]/div[1]//child::input[1]")
	public WebElement groupCheckbox;
	
	@FindBy(xpath="//a[contains(text(),'Add Attachment')] | //a[contains(text(),'اضافة مرفق')]")
	public WebElement addAttachments; 
	@FindBy(xpath="//input[@id='fileInputField2']")
	public WebElement browseAttachments; 
	
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Payment request created successfully'] | //div[@role='alertdialog'][@aria-label='تم انشاء طلب الدفع بنجاح']")
	public WebElement SucessMsg;
	
	@FindBy(xpath="//small[contains(text(),'Title is required')] | //small[contains(text(),'المسمى الوظيفي مطلوب')]")
	public WebElement titleMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Please enter a valid amount')] | //small[contains(text(),'يرجى ادخال مبلغ صالح')]")
	public WebElement amountMandatoryMsg;
	@FindBy(xpath="//small[contains(text(),'Description is required.')] | //small[contains(text(),'الوصف مطلوب')]")
	public WebElement descriptionMandatoryMsg;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Please select atleast one recipient.'] | //div[@role='alertdialog'][@aria-label='الرجاء تحديد مستلم واحد على الأقل.']")
	public WebElement recipientMandatoryMsg;
	
	
	
	
	public void addRequestPayment(String[] test)
	{
		try
		{
			waitForElementToLoad(title);
			title.sendKeys(test[0]);
			amount.sendKeys(test[1]);
			description.sendKeys(test[2]);
			
			dueDate.click();
			waitForElementToLoad(today);
			today.click();
			autoScrollandClick(acceptPaymentCheckbox);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		
	}
	
	public void filterGroup(String nam, String typ)
	{
		try
		{
			waitForElementToLoad(groupName);
			groupName.sendKeys(nam);
			groupType.sendKeys(typ);
			filterButton.click();
			autoScrollandClick(groupCheckbox);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public void addAttachment(String[] test, int n)
	{
		try
		{
			wait600Sec = new WebDriverWait(driver, 600);
			if(language.equals("Arabic"))
			{
				for(int i=1;i<=n;i++)
				{
					autoScrollandClick(addAttachments);
					browseAttachments.sendKeys(projectFolder+test[i-1]);
					wait600Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'اضافة مرفق')]//following::img["+i+"]")));
					WebElement attachmentName = driver.findElement(By.xpath("//a[contains(text(),'اضافة مرفق')]//following::input[@placeholder='اسم المرفق']["+i+"]"));
					waitForElementToLoad(attachmentName);
					attachmentName.sendKeys(test[2+i]);
				}
			}
			else
			{
				for(int i=1;i<=n;i++)
				{
					autoScrollandClick(addAttachments);
					browseAttachments.sendKeys(projectFolder+test[i-1]);
					wait600Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[contains(text(),'Add Attachment')]//following::img["+i+"]")));
					WebElement attachmentName = driver.findElement(By.xpath("//a[contains(text(),'Add Attachment')]//following::input[@placeholder='Attachment Name']["+i+"]"));
					waitForElementToLoad(attachmentName);
					attachmentName.sendKeys(test[2+i]);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		
	}

}
