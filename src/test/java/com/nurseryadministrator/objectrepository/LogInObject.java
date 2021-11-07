package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.nurseryadministrator.baseclass.SetUp;

public class LogInObject extends SetUp {

	WebDriver driver;

	public LogInObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);

	}

	@FindBy(xpath="//input[@placeholder = 'Email'] | //input[@placeholder='البريد الالكتروني']")
	public WebElement loginEmail;
	@FindBy(xpath="//input[@placeholder = 'Password'] | //input[@placeholder='كلمة السر']")
	public WebElement loginPassword;
	@FindBy(xpath="//button[contains(text(),'Login')] | //button[contains(text(),'تسجيل الدخول')]")
	public WebElement loginButton;

	@FindBy(xpath="//div[@class='row']//child::small[1]")
	public WebElement noEmailMsg;
	@FindBy(xpath="//div[@class='row']//child::small[2]")
	public WebElement noPasswordMsg;

	@FindBy(xpath="//div[@role='alertdialog']")
	public WebElement invalidMsg;
	@FindBy(xpath="//div[@role='alertdialog']")
	public WebElement invalidEmailMsg;

	public void logIn(String email, String password)
	{
		try {
			log.info("Entered logIn method");
			driver.navigate().refresh();
			waitForElementToLoad(loginButton);
			loginEmail.clear();
			loginEmail.sendKeys(email);
			loginPassword.clear();
			loginPassword.sendKeys(password);
			loginButton.click();
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
