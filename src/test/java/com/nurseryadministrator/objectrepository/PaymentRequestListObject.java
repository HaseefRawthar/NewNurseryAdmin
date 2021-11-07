package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class PaymentRequestListObject extends SetUp {

	WebDriver driver;
	
	public PaymentRequestListObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);
		
	}
	

    @FindBy(xpath="//input[@formcontrolname='title']")
	public WebElement title; 
	@FindBy(xpath="//select[@formcontrolname='groupId']")
	public WebElement groupName; 
	@FindBy(xpath="//input[@formcontrolname='studentName']")
	public WebElement studentName; 
	@FindBy(xpath="//select[@formcontrolname='status']")
	public WebElement status; 
	@FindBy(xpath="//label[contains(text(),'Due Date From')]//following::input[1] | //label[contains(text(),'تاريخ الاستحقاق من')]//following::input[1]")
	public WebElement dueDateFrom; 
	@FindBy(xpath="//label[contains(text(),'Due Date To')]//following::input[1] | //label[contains(text(),'تاريخ الاستحقاق الى')]//following::input[1]")
	public WebElement dueDateTo;
	
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]/span[1]")
	public WebElement student;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]/span[1]")
	public WebElement stat;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/i[1]")
	public WebElement viewButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/span[1]/i[1]")
	public WebElement acceptPayment;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/span[1]/i[2]")
    public WebElement cancelPayment;
	
	@FindBy(xpath="//select[@formcontrolname='paymentMethod']")
	public WebElement paymentMethod;
	@FindBy(xpath="//textarea[@id='comments-area']")
	public WebElement comments;
	@FindBy(xpath="//button[@type='submit'][normalize-space()='Submit'] | //button[@type='submit'][contains(text(),'تقديم الطلب')]")
	public WebElement submitPayment;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Payment request accepted successfully'] | //div[@role='alertdialog'][@aria-label='تم قبول طلب الدفع بنجاح']")
	public WebElement requestAcceptSuccessMsg;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Payment request cancelled successfully'] | //div[@role='alertdialog'][@aria-label='تم الغاء طلب الدفع بنجاح']")
	public WebElement requestCancelSuccessMsg;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Payment request refunded successfully'] | //div[@role='alertdialog'][@aria-label='تم استرجاع المبلغ بنجاح']")
	public WebElement requestRefundSuccessMsg;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/span[1]/i[3]")
	public WebElement refundButton;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/span[1]/i[1]")
	public WebElement downloadInvoice;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[13]/div[1]/div[1]/span[1]/i[2]")
	public WebElement printInvoice;
	@FindBy(xpath="//label[contains(text(),'English')]")
	public WebElement english;
	@FindBy(xpath="//button[contains(text(),'Download')]")
	public WebElement downloadButton;
	@FindBy(xpath="//button[contains(text(),'Print')]")
	public WebElement printButton;
	
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[4]/div[1]/span[2]")
	public WebElement titleSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[5]/div[1]/span[2]")
	public WebElement studentSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[11]/div[1]/span[2]")
	public WebElement dueDateSortButton;
	@FindBy(xpath="//datatable-header[1]/div[1]/div[2]/datatable-header-cell[12]/div[1]/span[2]")
	public WebElement statusSortButton;
	
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[4]/div[1]//span[1]")
	public WebElement sortedTitle;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[5]/div[1]//span[1]")
	public WebElement sortedStudent;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[11]/div[1]//span[1]")
	public WebElement sortedDueDate;
	@FindBy(xpath="//datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[12]/div[1]//span[1]")
	public WebElement sortedStatus;
	
	@FindBy(xpath="//select[@name='actions']")
	public WebElement actions;
	@FindBy(xpath="//button[contains(text(),'Submit')] | //button[contains(text(),'تقديم الطلب')]")
	public WebElement submit;
	@FindBy(xpath="//datatable-body[1]/datatable-selection[1]/datatable-scroller[1]/datatable-row-wrapper[1]/datatable-body-row[1]/div[2]/datatable-body-cell[2]/div[1]/div[1]//child::input[1]")
	public WebElement paymentCheckBox;
	
	@FindBy(xpath="//span[@class='mobile-hidden'][contains(text(),'Total Records')] | //span[@class='mobile-hidden'][contains(text(),'مجموع السجلات')]")
	public WebElement totalRecords;
	@FindBy(xpath="//a[@aria-label='go to next page']//i")
	public WebElement goToNextPage;
	@FindBy(xpath="//li[@class='disabled']//a[@aria-label='go to next page']")
	public WebElement lastpagecheck;
	
	
	public void filterPymentRequest(String tile, String grpnam, String studnam, String stat, String date1, String date2)
	{
		try
		{
			waitForElementToLoad(filterButton);
			title.sendKeys(tile);
			groupName.sendKeys(grpnam);
			studentName.sendKeys(studnam);
			status.sendKeys(stat);
			dueDateFrom.sendKeys(date1);
			dueDateTo.sendKeys(date2);
			waitIfElementClickIsIntercepted(filterButton, "click", "");
			//filterButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
}
