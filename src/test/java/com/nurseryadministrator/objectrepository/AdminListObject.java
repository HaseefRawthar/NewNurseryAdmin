package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AdminListObject extends SetUp {

	WebDriver driver;
	
	public AdminListObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	@FindBy(xpath="//input[@formcontrolname='name']")
	public WebElement name;
	@FindBy(xpath="//select[@formcontrolname='status']")
	public WebElement status;
	@FindBy(xpath="//input[@formcontrolname='email']")
	public WebElement email;
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/i[1]")
	public WebElement viewButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[2]/i[1]")
	public WebElement editButton;
	
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[3]/div[1]/span[2]")
	public WebElement nameSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[4]/div[1]/span[2]")
	public WebElement phoneSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[5]/div[1]/span[2]")
	public WebElement emailSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[6]/div[1]/span[2]")
	public WebElement statusSortButton;
	
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]")
	public WebElement sortedName;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]")
	public WebElement sortedPhone;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]")
	public WebElement sortedEmail;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]")
	public WebElement sortedStatus;
	
	@FindBy(xpath="//span[@class='mobile-hidden'][contains(text(),'Total Records')] | //span[@class='mobile-hidden'][contains(text(),'مجموع السجلات')]")
	public WebElement totalRecords;
	@FindBy(xpath="//a[@aria-label='go to next page']//i")
	public WebElement goToNextPage;
	@FindBy(xpath="//li[@class='disabled']//a[@aria-label='go to next page']")
	public WebElement lastpagecheck;
	@FindBy(xpath="//body/app-root[1]/app-private-layout[1]/div[1]/div[1]/div[1]/app-admin-list[1]/div[2]/div[1]/div[1]/div[1]/ngx-datatable[1]/div[1]/datatable-footer[1]/div[1]/datatable-pager[1]/ul[1]/li[1]/a[1]/i[1]")
	public WebElement goToFirstPage;
	

	
	public void filterAdmin(String nam, String stat, String mail)
	{
		try
		{
			waitForElementToLoad(filterButton);
			name.sendKeys(nam);
			status.sendKeys(stat);
			email.sendKeys(mail);
			filterButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
