package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nurseryadministrator.baseclass.SetUp;

public class DashboardObject extends SetUp {

	WebDriver driver;
	
	public DashboardObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//p[contains(text(),'Happy Trails Daycare, Nursery Admin')]")
	public WebElement selectHappyTrailsDaycareNurseryAdmin;
	@FindBy(xpath="//p[contains(text(),'Nursery-Edge-test, Nursery Admin')]")
	public WebElement selectNurseryEdgetestNurseryAdmin;
	
	@FindBy(xpath="//button[contains(text(),'Select')]")
	public WebElement selectButton;
	@FindBy(xpath="//span[@class='m-10 font-weight-bold text-white']")
	public WebElement currentRoleSelected;
	
	@FindBy(xpath="//h4[contains(text(),'طلاب')] | //h4[contains(text(),'Students')]")
	public WebElement Students;
	@FindBy(xpath="//h4[contains(text(),'Parents')] | //h4[contains(text(),'أولياء الأمور')]")
	public WebElement Parents;
	@FindBy(xpath="//h4[contains(text(),'Employees')] | //h4[contains(text(),'الموظفيين')]")
	public WebElement Employees;
	
	@FindBy(xpath="//a[@id='3']")
	public WebElement switchNurseryDrop;
	@FindBy(xpath="//h5[contains(text(),'Nursery-Edge-test, Nursery Admin')]")
	public WebElement NurseryEdgetestAdmin;
	@FindBy(xpath="//h5[contains(text(),'Nursery-Edge-test, Nursery Manager')]")
	public WebElement NurseryEdgetestManager;
	@FindBy(xpath="//h5[contains(text(),'Oliver, Nursery Manager')]")
	public WebElement oliverNursery;
	@FindBy(xpath="//h5[contains(text(),'Happy Trails Daycare, Nursery Admin')]")
	public WebElement happyTrailsDaycareAdmin;
	@FindBy(xpath="//h5[contains(text(),'Happy Trails Daycare, Nursery Manager')]")
	public WebElement happyTrailsDaycareManager;
	@FindBy(xpath="//h5[contains(text(),'Experion, Nursery Manager')]")
	public WebElement experionNursery;
	
	@FindBy(xpath="//a[contains(text(),'عربي')]")
	public WebElement arabic;
	@FindBy(xpath="//a[contains(text(),'English')]")
	public WebElement english;
	
	@FindBy(xpath="//span[contains(text(),'Dashboard')]")
	public WebElement dashboard;
	
	@FindBy(xpath="//span[contains(text(),'Groups')] | //a[@class='sidebar-link waves-effect waves-dark']//span[@class='hide-menu'][contains(text(),'مجموعة')]")
	public WebElement groups;
	@FindBy(xpath="//span[contains(text(),'Add Group')] | //span[contains(text(),'اضافة مجموعة')]")
	public WebElement addGroup;
	@FindBy(xpath="//span[contains(text(),'Group List')] | //span[contains(text(),'قائمة المجموعة')]")
	public WebElement groupList;
	
	@FindBy(xpath="//span[contains(text(),'Students')] | //a[@class='sidebar-link waves-effect waves-dark']//span[@class='hide-menu'][contains(text(),'طلاب')]")
	public WebElement students;
	@FindBy(xpath="//span[contains(text(),'Add Student')] | //span[contains(text(),'اضافة طالب')]")
	public WebElement addStudent;
	@FindBy(xpath="//span[contains(text(),'Student List')] | //span[contains(text(),'قائمة الطلاب')]")
	public WebElement studentList;
	
	@FindBy(xpath="//span[contains(text(),'Employees')] | //span[contains(text(),'الموظفيين')]")
	public WebElement employees;
	@FindBy(xpath="//span[contains(text(),'Add Employee')] | //span[contains(text(),'اضافة موظف')]")
	public WebElement addEmployee;
	@FindBy(xpath="//span[contains(text(),'Teacher List')] | //span[contains(text(),'قائمة المدرسين')]")
	public WebElement teacherList;
	@FindBy(xpath="//span[contains(text(),'Nurse List')] | //span[contains(text(),'قائمة الممرضين')]")
	public WebElement nurseList;
	@FindBy(xpath="//span[contains(text(),'Assistant List')] | //span[contains(text(),'قائمة المساعدين')]")
	public WebElement assistantList;
	
	@FindBy(xpath="//span[contains(text(),'الموظفيين')]//following::span[5] | //span[normalize-space()='Payment Request']")
	public WebElement paymentRequest;
	@FindBy(xpath="//span[contains(text(),'الموظفيين')]//following::span[6] | //span[contains(text(),'Request Payment')]")
	public WebElement requestPayment;
	@FindBy(xpath="//span[contains(text(),'Payment Request List')] | //span[contains(text(),'قائمة طلبات الدفع')]")
	public WebElement paymentRequestList;
	
	@FindBy(xpath="//span[contains(text(),'Grant Temporary Admin Access')] | //span[contains(text(),'منح حق الوصول كمشرف مؤقتاً')]")
	public WebElement grantTemporaryAdminAccess;
	
	@FindBy(xpath="//span[contains(text(),'Nursery Admin')] | //span[contains(text(),'االاداري')]")
	public WebElement nurseryAdmin;
	@FindBy(xpath="//span[contains(text(),'Add Admin')] | //span[contains(text(),'اضافة اداري')]")
	public WebElement addAdmin;
	@FindBy(xpath="//span[contains(text(),'Admin List')] | //span[contains(text(),'قائمة الاداريين')]")
	public WebElement adminList;
	
	@FindBy(xpath="//a[@class='nav-link waves-effect waves-dark pro-pic dropdown-toggle']//img[@alt='user']")
	public WebElement adminImage;
	@FindBy(xpath="//ul[2]/li[2]/a[1]")
	public WebElement userAdmin;
	@FindBy(xpath="//button[@id='logout-link']")
	public WebElement logoutButton;
	@FindBy(xpath="//button[contains(text(),'Edit Profile')] | //button[contains(text(),'تعديل الملف')]")
	public WebElement editProfile;
	
	@FindBy(xpath="//span[contains(text(),'Reports')] | //span[contains(text(),'التقارير')]")
	public WebElement reports;
	@FindBy(xpath="//span[contains(text(),'Nursery Online Revenue report')] | //span[contains(text(),'تقرير عائدات الحضانة')]")
	public WebElement nurseryOnlineRevenueReport;
	
	public void logOut()
	{
		waitIfElementClickIsIntercepted(userAdmin, "click", "");
		 waitForElementToLoad(logoutButton);
		 logoutButton.click();
		 log.info("logged out successfully");
	}
	
	public void selectNursery(String name)
	{
		try
		{
			wait30Sec = new WebDriverWait(driver,10);
			wait30Sec.until(ExpectedConditions.visibilityOf(selectButton));
		}
		catch(Exception e) {
			System.out.println("only one nursery");
		}
		
		if(isElementPresent(selectButton))
		{
			waitForElementToLoad(selectButton);
			WebElement nursery = driver.findElement(By.xpath("//p[contains(text(),'"+name+"')]")); 
			autoScrollandClick(nursery);
			autoScrollandClick(selectButton);
		}
		
	}
	
	public void changeLanguage(String lang)
	{
		try
		{
			log.info("enter select language method");
			if(lang.equals("Arabic") && isElementPresent(arabic))
			{
				waitForElementToLoad(arabic);
				autoScrollandClick(arabic);
				waitForElementToLoad(english);
				log.info("language changed to arabic");

			}
			else if(lang.equals("English") && isElementPresent(english))
			{
				waitForElementToLoad(english);
				autoScrollandClick(english);
				waitForElementToLoad(arabic);
				log.info("language changed to english");
			}

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}		
	}
}
