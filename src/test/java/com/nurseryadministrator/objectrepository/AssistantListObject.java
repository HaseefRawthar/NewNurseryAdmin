package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class AssistantListObject extends SetUp {

	WebDriver driver;
	
	public AssistantListObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	
	@FindBy(xpath="//input[@formcontrolname='name']")
	public WebElement name;
	@FindBy(xpath="//select[@formcontrolname='groupType']")
	public WebElement groupType;
	@FindBy(xpath="//select[@formcontrolname='groupId']")
	public WebElement group;
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[1]/i[1]")
	public WebElement viewButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[7]/div[1]/span[2]/i[1]")
	public WebElement editButton;
	@FindBy(xpath="//button[contains(text(),'Save')] | //button[contains(text(),'حفظ')]")
	public WebElement saveButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Employee Updated Successfully'] | //div[@role='alertdialog'][@aria-label='تم تحديث الموظف بنجاح']")
	public WebElement updateSucessMsg;
	
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[3]/div[1]/span[2]")
	public WebElement nameSortButton;;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[5]/div[1]/span[2]")
	public WebElement phoneSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[6]/div[1]/span[2]")
	public WebElement statusSortButton;
	
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[3]/div[1]")
	public WebElement sortedName;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]")
	public WebElement sortedPhone;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[6]/div[1]")
	public WebElement sortedStatus;
	
	@FindBy(xpath="//span[@class='mobile-hidden'][contains(text(),'Total Records')] | //span[@class='mobile-hidden'][contains(text(),'مجموع السجلات')]")
	public WebElement totalRecords;
	@FindBy(xpath="//a[@aria-label='go to next page']//i")
	public WebElement goToNextPage;
	@FindBy(xpath="//li[@class='disabled']//a[@aria-label='go to next page']")
	public WebElement lastpagecheck;


	
	public void filterAssistant(String nam, String grupTyp, String grupId)
	{
		try
		{
			waitForElementToLoad(filterButton);
			name.sendKeys(nam);
			groupType.sendKeys(grupTyp);
			group.sendKeys(grupId);
			autoScrollandClick(filterButton);
			//filterButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
