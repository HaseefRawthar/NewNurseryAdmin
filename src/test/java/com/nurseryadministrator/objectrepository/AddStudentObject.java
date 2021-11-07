package com.nurseryadministrator.objectrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nurseryadministrator.baseclass.SetUp;

public class AddStudentObject extends SetUp {

	WebDriver driver;

	public AddStudentObject(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(this.driver,this);

	}

	@FindBy(xpath="//small[contains(text(),'First name is required')] | //small[contains(text(),'الاسم الاول مطلوب')]")
	public WebElement firstNameMandtryMsg;
	@FindBy(xpath="//small[contains(text(),'Last name is required')] | //small[contains(text(),'الاسم الاخير مطلوب')]")
	public WebElement lastNameMandtryMsg;
	@FindBy(xpath="//small[contains(text(),'Gender is required')] | //small[contains(text(),'الجنس مطلوب')]")
	public WebElement genderMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::small[1] | //div[@class='form-group col-md-12 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'الاسم مطلوب')]")
	public WebElement parentnameMandtryMsg;
	@FindBy(xpath="//small[contains(text(),'Relationship is required')] | //small[contains(text(),'العلاقة مطلوبة')]")
	public WebElement relationMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::small[3] | //div[@class='form-group col-md-12 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'رمز الدولة مطلوب')]")
	public WebElement parentCodeMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Parent Information 1')]//following::small[4] | //div[@class='form-group col-md-12 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'رقم الهاتف الصالح مطلوب')]")
	public WebElement parentPhoneMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::small[1] | //div[@class='form-group col-md-6 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'الاسم مطلوب')]")
	public WebElement emergContNameMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::small[2] | //div[@class='form-group col-md-6 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'رمز الدولة مطلوب')]")
	public WebElement emergContCodeMandtryMsg;
	@FindBy(xpath="//h4[contains(text(),'Emergency Contact')]//following::small[3] | //div[@class='form-group col-md-6 col-sm-12']//small[@class='form-text text-danger'][contains(text(),'رقم الهاتف الصالح مطلوب')]")
	public WebElement emergContPhoneMandtryMsg;

	@FindBy(xpath="//input[@id='firstName']")
	public WebElement firstName;
	@FindBy(xpath="//input[@id='secondName']")
	public WebElement secondName;
	@FindBy(xpath="//input[@id='thirdName']")
	public WebElement thirdName;
	@FindBy(xpath="//input[@id='lastName']")
	public WebElement lastName;
	@FindBy(xpath="//input[@id='civilNumber']")
	public WebElement govIssuedID;
	@FindBy(xpath="//select[@id='nationality']")
	public WebElement nationality;
	@FindBy(xpath="//input[@id='nurseryName']")
	public WebElement placeOfBirth;
	@FindBy(xpath="//label[contains(text(),'Date of birth')]//following::input[1] | //label[contains(text(),'تاريخ الميلاد')]//following::input[1]")
	public WebElement dateOfBirth;
	@FindBy(xpath="//select[@id='gender']")
	public WebElement gender;
	@FindBy(xpath="//input[@id='religion']")
	public WebElement religion;
	@FindBy(xpath="//textarea[@id='address']")
	public WebElement address;
	@FindBy(xpath="//textarea[@id='allergyDetails']")
	public WebElement allergies;
	@FindBy(xpath="//textarea[@id='nurseryNotes']")
	public WebElement nurseryNotes;
	@FindBy(xpath="//textarea[@id='parentNotes']")
	public WebElement parentsNotes;
	@FindBy(xpath="//select[@id='groupName']")
	public WebElement groupName;
	@FindBy(xpath="//label[contains(text(),'First day of school')]//following::input[1] | //label[contains(text(),'اول يوم في المدرسة')]//following::input[1]")
	public WebElement firstDayOfSchool;
	@FindBy(xpath="//input[@id='fileInputField1']")
	public WebElement bowseImage;
	@FindBy(xpath="//label[contains(text(),'Image')]//following::img[@alt='image'][1] | //label[contains(text(),'صورة')]//following::img[@alt='image'][1]")
	public WebElement imagePreview;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='File uploaded successfully'] | //div[@role='alertdialog'][@aria-label='تم رفع الملف بنجاح']")
	public WebElement uploadSucessMsg;

	@FindBy(xpath="//input[@id='parent1Name']")
	public WebElement parent1Name;
	@FindBy(xpath="//input[@id='parent1Email']")
	public WebElement parent1Email;
	@FindBy(xpath="//input[@id='parent1Job']")
	public WebElement parent1Job;
	@FindBy(xpath="//input[@id='parent1CivilId']")
	public WebElement parent1GovID;
	@FindBy(xpath="//select[@id='parent1Nationality']")
	public WebElement parent1Nationality;
	@FindBy(xpath="//input[@id='parent1Religion']")
	public WebElement parent1Religion;
	@FindBy(xpath="//select[@id='parent1Relation']")
	public WebElement parent1Relation;
	@FindBy(xpath="//select[@id='parent1CountryCode']")
	public WebElement parent1CountryCode;
	@FindBy(xpath="//input[@id='parent1Phone']")
	public WebElement parent1Phone;
	@FindBy(xpath="//span[contains(text(),'Add more Parent')] | //span[contains(text(),'اضافة ولي أمر')]")
	public WebElement addMoreParent;
	@FindBy(xpath="//input[@id='parent2Name']")
	public WebElement parent2Name;
	@FindBy(xpath="//input[@id='parent2Email']")
	public WebElement parent2Email;
	@FindBy(xpath="//input[@id='parent2Job']")
	public WebElement parent2Job;
	@FindBy(xpath="//input[@id='parent2CivilId']")
	public WebElement parent2GovID;
	@FindBy(xpath="//select[@id='parent2Nationality']")
	public WebElement parent2Nationality;
	@FindBy(xpath="//input[@id='parent2Religion']")
	public WebElement parent2Religion;
	@FindBy(xpath="//select[@id='parent2Relation']")
	public WebElement parent2Relation;
	@FindBy(xpath="//select[@id='parent2CountryCode']")
	public WebElement parent2CountryCode;
	@FindBy(xpath="//input[@id='parent2Phone']")
	public WebElement parent2Phone;

	@FindBy(xpath="//input[@id='emergContName']")
	public WebElement emergencyContName;
	@FindBy(xpath="//select[@id='emergContCountryCode']")
	public WebElement emergContCountryCode;
	@FindBy(xpath="//input[@id='emergContPhone']")
	public WebElement emergContPhone;
	@FindBy(xpath="//input[@id='emergContRel']")
	public WebElement emergContRelation;

	@FindBy(xpath="//input[@id='authPickupName']")
	public WebElement authPickUpName;
	@FindBy(xpath="//input[@id='authPickupCivilId']")
	public WebElement authPickupGovID;
	@FindBy(xpath="//select[@id='authContCountryCode']")
	public WebElement authContCountryCode;
	@FindBy(xpath="//input[@id='authPickupPhone']")
	public WebElement authPickupPhone;
	@FindBy(xpath="//input[@id='authPickupRel']")
	public WebElement authPickupRelation;

	@FindBy(xpath="//label[contains(text(),'Image')]//following::span[contains(text(),'Add Attachment')][1] | //label[contains(text(),'صورة')]//following::span[contains(text(),'اضافة مرفق')][1]")
	public WebElement addAttachment1;
	@FindBy(xpath="//label[contains(text(),'Image')]//following::span[contains(text(),'Add Attachment')][2] | //label[contains(text(),'صورة')]//following::span[contains(text(),'اضافة مرفق')][2]")
	public WebElement addAttachment2;
	@FindBy(xpath="//div[@class='upload-btn-wrapper']//input[@id='fileInputField1']")
	public WebElement browseAttachments1;
	@FindBy(xpath="//input[@id='fileInputField2']")
	public WebElement browseAttachments2;

	@FindBy(xpath="//button[contains(text(),'Add')] | //button[contains(text(),'اضافة')]")
	public WebElement addButton;
	@FindBy(xpath="//div[@role='alertdialog'][@aria-label='Student Added Successfully'] | //div[@aria-label='تم اضافة الطالب بنجاح']")
	public WebElement studentSucessMsg;

	public void addStudentDetails(String[] test)
	{
		try
		{
			waitForElementToLoad(firstName);
			firstName.sendKeys(test[0]);
			secondName.sendKeys(test[1]);
			thirdName.sendKeys(test[2]);
			lastName.sendKeys(test[3]);
			govIssuedID.sendKeys(test[4]);
			nationality.sendKeys(test[5]);
			placeOfBirth.sendKeys(test[6]);
			dateOfBirth.sendKeys(test[7]);
			Thread.sleep(500);
			gender.sendKeys(test[8]);
			religion.sendKeys(test[9]);
			address.sendKeys(test[10]);
			allergies.sendKeys(test[11]);
			nurseryNotes.sendKeys(test[12]);
			parentsNotes.sendKeys(test[13]);
			groupName.sendKeys(test[14]);
			firstDayOfSchool.clear();
			firstDayOfSchool.sendKeys(test[15]);
			bowseImage.sendKeys(projectFolder+test[16]);
			waitForElementToLoad(uploadSucessMsg);
			String msg = uploadSucessMsg.getText();
			System.out.println(msg);
			waitForElementToLoad(imagePreview);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void addParentDetails(String[] test)
	{
		try
		{
			waitForElementToLoad(parent1Name);
			parent1Name.sendKeys(test[0]);
			parent1Email.sendKeys(test[1]);
			parent1Job.sendKeys(test[2]);
			parent1GovID.sendKeys(test[3]);
			parent1Nationality.sendKeys(test[4]);
			parent1Religion.sendKeys(test[5]);
			parent1Relation.sendKeys(test[6]);
			parent1CountryCode.sendKeys(test[7]);
			parent1Phone.sendKeys(test[8]);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void addParentDetails2(String[] test)
	{
		try
		{
			waitForElementToLoad(parent1Name);
			parent2Name.sendKeys(test[0]);
			parent2Email.sendKeys(test[1]);
			parent2Job.sendKeys(test[2]);
			parent2GovID.sendKeys(test[3]);
			parent2Nationality.sendKeys(test[4]);
			parent2Religion.sendKeys(test[5]);
			parent2Relation.sendKeys(test[6]);
			parent2CountryCode.sendKeys(test[7]);
			parent2Phone.sendKeys(test[8]);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void addEmergencyDetails(String[] test)
	{
		try
		{
			emergencyContName.sendKeys(test[0]);
			emergContCountryCode.sendKeys(test[1]);
			emergContPhone.sendKeys(test[2]);
			emergContRelation.sendKeys(test[3]);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void authPickupDetails(String[] test)
	{
		try
		{
			authPickUpName.sendKeys(test[0]);
			authPickupGovID.sendKeys(test[1]);
			authContCountryCode.sendKeys(test[2]);
			authPickupPhone.sendKeys(test[3]);
			authPickupRelation.sendKeys(test[4]);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}

	public void addAttachments(WebElement element1, WebElement element2, String[] test, int n ) //add attachments, n=no.of attachments(max=10)
	{
		try
		{
			String one;
			if(language.equals("Arabic"))
			{
				if(element1==addAttachment1) {
					one = "//label[contains(text(),'صورة')]//following::span[contains(text(),'اضافة مرفق')][1]";
				}
				else {
					one="//label[contains(text(),'صورة')]//following::span[contains(text(),'اضافة مرفق')][2]";
				}
				wait600Sec = new WebDriverWait(driver, 600);
				for(int i=1;i<=n;i++)
				{

					JavascriptExecutor js = ((JavascriptExecutor) driver);
					js.executeScript("arguments[0].click();", element1);

					element2.sendKeys(projectFolder+test[i - 1]);

					wait600Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(one+"//following::img["+i+"]")));
					WebElement attachmentName = driver.findElement(By.xpath(one+"//following::input[@placeholder='اسم المرفق']["+i+"]"));
					waitForElementToLoad(attachmentName);
					attachmentName.sendKeys(test[9+i]);

				}
			}
			else
			{
				if(element1==addAttachment1) {
					one = "//label[contains(text(),'Image')]//following::span[contains(text(),'Add Attachment')][1]";
				}
				else {
					one="//label[contains(text(),'Image')]//following::span[contains(text(),'Add Attachment')][2]";
				}
				wait600Sec = new WebDriverWait(driver, 600);
				for(int i=1;i<=n;i++)
				{

					JavascriptExecutor js = ((JavascriptExecutor) driver);
					js.executeScript("arguments[0].click();", element1);

					element2.sendKeys(projectFolder+test[i - 1]);

					wait600Sec.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(one+"//following::img["+i+"]")));
					WebElement attachmentName = driver.findElement(By.xpath(one+"//following::input[@placeholder='Attachment Name']["+i+"]"));
					waitForElementToLoad(attachmentName);
					attachmentName.sendKeys(test[9+i]);

				}

			}			

		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
}
