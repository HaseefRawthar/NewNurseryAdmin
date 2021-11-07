package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class StudentListObject extends SetUp {

	WebDriver driver;
	
	public StudentListObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//input[@formcontrolname='firstName']")
	public WebElement firstName;
	@FindBy(xpath="//input[@formcontrolname='lastName']")
	public WebElement lastName;
	@FindBy(xpath="//input[@formcontrolname='parentName']")
	public WebElement parentName;
	@FindBy(xpath="//select[@id='groupName']")
	public WebElement groupName;
	@FindBy(xpath="//select[@id='groupType']")
	public WebElement groupType;
	@FindBy(xpath="//select[@formcontrolname='status']")
	public WebElement status;
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	
	@FindBy(xpath="//datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[10]/div[1]/span[1]/i[1]")
	public WebElement viewButton;
	@FindBy(xpath="//datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[10]/div[1]/span[2]/i[1]")
	public WebElement editButton;
	@FindBy(xpath="//button[contains(text(),'Save')] | //button[contains(text(),'حفظ')]")
	public WebElement saveButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Student Updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث معلومات الطالب بنجاح']")
	public WebElement updateSucessMsg;
	
	@FindBy(xpath="//span[@class='mobile-hidden'][contains(text(),'Total Records')] | //span[@class='mobile-hidden'][contains(text(),'مجموع السجلات')]")
	public WebElement totalRecords;
	@FindBy(xpath="//a[@aria-label='go to next page']//i")
	public WebElement goToNextPage;
	@FindBy(xpath="//li[@class='disabled']//a[@aria-label='go to next page']")
	public WebElement lastpagecheck;
	
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[3]/div[1]/span[2]")
	public WebElement firstNameSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[4]/div[1]/span[2]")
	public WebElement lastNameSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[5]/div[1]/span[2]")
	public WebElement parent1SortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[6]/div[1]/span[2]")
	public WebElement parent2SortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[7]/div[1]/span[2]")
	public WebElement groupNameSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[8]/div[1]/span[2]")
	public WebElement groupTypeSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[9]/div[1]/span[2]")
	public WebElement statusSortButton;
	
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]//span[1]")
	public WebElement sortedFirstName;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[1]")
	public WebElement sortedLastName;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[1]")
	public WebElement sortedParent1;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]//span[1]")
	public WebElement sortedParent2;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]//span[1]")
	public WebElement sortedGroupName;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[8]/div[1]//span[1]")
	public WebElement sortedGroupType;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[9]/div[1]//span[1]")
	public WebElement sortedStatus;
	
	
	public void filterStudents(String[] test)
	{
		try
		{
			waitForElementToLoad(filterButton);
			firstName.sendKeys(test[0]);
			lastName.sendKeys(test[1]);
			parentName.sendKeys(test[2]);
			//groupName.sendKeys(test[3]);
			//groupType.sendKeys(test[4]);
			filterButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
