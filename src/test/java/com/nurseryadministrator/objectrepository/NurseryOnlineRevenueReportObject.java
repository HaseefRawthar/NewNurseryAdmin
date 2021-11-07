package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class NurseryOnlineRevenueReportObject extends SetUp {

	WebDriver driver;

	public NurseryOnlineRevenueReportObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	@FindBy(xpath="//label[contains(text(),'Transaction date from')]//following::input[1] | //label[contains(text(),'تاريخ العملية من:')]//following::input[1]")
	public WebElement fromDate;
	@FindBy(xpath="//label[contains(text(),'Transaction date to')]//following::input[1] | //label[normalize-space()='تاريخ العملية']//following::input[1]")
	public WebElement toDate;
	
	@FindBy(xpath="//button[contains(text(),'Filter')] | //button[contains(text(),'تصفية')]")
	public WebElement filterButton;
	@FindBy(xpath="//button[contains(text(),'RESET')] | //button[contains(text(),'مسح')]")
	public WebElement resetButton;
	
	@FindBy(xpath="//span[@class='mobile-hidden'][contains(text(),'Total Records')] | //span[@class='mobile-hidden'][contains(text(),'مجموع السجلات')]")
	public WebElement totalRecords;
	@FindBy(xpath="//a[@aria-label='go to next page']//i")
	public WebElement goToNextPage;
	@FindBy(xpath="//li[@class='disabled']//a[@aria-label='go to next page']")
	public WebElement lastpagecheck;
	@FindBy(xpath="//body/app-root[1]/app-private-layout[1]/div[1]/div[1]/div[1]/app-nursery-revenue-report[1]/div[3]/div[1]/div[1]/div[1]/ngx-datatable[1]/div[1]/datatable-footer[1]/div[1]/datatable-pager[1]/ul[1]/li[1]/a[1]/i[1]")
	public WebElement goToFirstPage;

}
