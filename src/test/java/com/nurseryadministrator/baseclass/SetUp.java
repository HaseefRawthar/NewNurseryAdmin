package com.nurseryadministrator.baseclass;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.nurseryadministrator.utility.Utilities;

public class SetUp {

	public static WebDriver driver;
	public static WebDriverWait wait30Sec;
	public static WebDriverWait wait600Sec;
	public static ExtentSparkReporter esReporter;
    public static ExtentReports eReports;
    public static ExtentTest eTest;
	public static Logger log;
	public String logPath="";
	public String projectFolder = System.getProperty("user.dir");
	public String log4jConfPath =projectFolder+"/src/test/java/com/nurseryadministrator/resources/log4j.properties";
	public Date date;
	public SimpleDateFormat df;
	public static String currentDateTime;
	public static Utilities utilsObj;
	public static String[] actualArray, expectedArray;
	public static String expectedMsg, actualMsg;
	public static ArrayList<Boolean> actualBoolArray = new ArrayList<Boolean>();
	public static ArrayList<Boolean> expectedBoolArray = new ArrayList<Boolean>();
	public static ArrayList<String> actualArraylist = new ArrayList<String>();
	public static ArrayList<String> expectedArraylist = new ArrayList<String>();
	public static String userEmail;
	public static String userPassword;
	public static String language;
	public static String nurseryName = "Nursery-Edge-test, Nursery Admin";
	
	@BeforeSuite
	public void start()
	{
		try {
		 System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		 log = Logger.getLogger(SetUp.class.getName());
		 System.setProperty("logPath", projectFolder+"/Logs/LogFile");
		 PropertyConfigurator.configure(log4jConfPath);  //configure log file path
		 
		 utilsObj = new Utilities();
		 extentReport(); //initializing report
		 getValidCredentialsFromJSONfile();
		 String url = "https://qa.nursery.hellobounce.com/auth/login";
		 driver = new ChromeDriver();
	     driver.manage().window().maximize();
	     driver.get(url);
	    
	     log.info("Page opened");
	     
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	    
	}
	
	@AfterSuite(alwaysRun=true)
	public void tearDown()
	{
		eReports.flush();
		driver.quit();
	}
	
	public void extentReport()
	{
		try {
		  log.info("Report status initiated");
		  String path = projectFolder+"/Reports/nurseryAdministrator_"+getCurrentDateTime()+".html";
          esReporter = new ExtentSparkReporter(path);
          esReporter.config().setDocumentTitle("Automation Report");
          esReporter.config().setReportName("Nursery Administrator-Bounce Report");
          esReporter.config().setTheme(Theme.STANDARD);
          eReports = new ExtentReports();
          eReports.attachReporter(esReporter);
          eReports.setSystemInfo("Project Name", "Nursery Administrator");
          eReports.setSystemInfo("Platform", System.getProperty("os.name"));
          eReports.setSystemInfo("Environment", "QA");
          eReports.setSystemInfo("Browser","Google chrome");
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	@AfterMethod
	public void testReportResult(ITestResult result)
	{
		try {
			
			log.info("enter testReportResult method ");
			   if(result.getStatus() == ITestResult.SUCCESS) {
	                eTest .log(Status.PASS, "Test passed");
	            }
			   
			   else if(result.getStatus() == ITestResult.FAILURE) {
	                eTest .log(Status.FAIL, "Test failed");
	            }
			   
			   else if(result.getStatus() == ITestResult.SKIP) {
	                eTest.log(Status.SKIP, "Test skipped");
	            }
			   String screenshotPath = utilsObj.getScreenshot(driver, result.getName()+"_"+getCurrentDateTime());
               eTest.addScreenCaptureFromPath(screenshotPath);
		}
		catch(Exception e) {
            e.printStackTrace();
            log.info(e);
        } 
		
	}
	
	public String getCurrentDateTime() {
	      
		try {
           
            df= new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
            date = new Date();
            System.setProperty("currentDateTime", df.format(new Date()));
        }catch(Exception e) {
            e.printStackTrace();
        }
        
		return currentDateTime = df.format(date);
    }
	
	public void waitForElementToLoad(WebElement element)
	{
		try {
		wait30Sec = new WebDriverWait(driver,30);
		wait30Sec.until(ExpectedConditions.visibilityOf(element));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("waited for 30 seconds..");
		}
		
	}
	
	public void waitForElementTobeInvisible(WebElement element)
	{
		try {
		wait30Sec = new WebDriverWait(driver,60);
		wait30Sec.until(ExpectedConditions.invisibilityOf(element));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("waited for 30 seconds..");
		}
		
	}
	
	public void waitForElementToBeClickable(WebElement element)
	{
		try {
		wait30Sec = new WebDriverWait(driver,30);
		wait30Sec.until(ExpectedConditions.elementToBeClickable(element));
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("waited for 30 seconds..");
		}
		
	}
	
	public void fileUploadWait(WebElement element) {
		try {
			wait600Sec = new WebDriverWait(driver, 600);//for normal explicit wait
			wait600Sec.until(ExpectedConditions.visibilityOf(element));		
		}catch (Exception e) {
			System.out.println("Waited for 600 seconds");
			e.printStackTrace();
		}		
	}
	
	public void waitIfElementClickIsIntercepted(WebElement element, String action, String value) {
		wait30Sec = new WebDriverWait(driver, 10);// for normal explicit wait
		for (int i = 0; i < 8; i++) {
		try {
		wait30Sec.until(ExpectedConditions.visibilityOf(element));
		if(action.equals("click")||action.equals("checkbox"))
		element.click();
		//else if (action.equals("clear"))
		// element.clear();
		else if(action.equals("select"))
		element.sendKeys(value);
		break;
		} catch (ElementClickInterceptedException e) {
		System.out.println("Click Intercepted: waitIfElementClickIsIntercepted" + element);
		} catch (StaleElementReferenceException e) {
		System.out.println("Stale Exception: waitIfElementClickIsIntercepted"+ element);
		} catch (Exception e) {
		System.out.println("Stale Exception: waitIfElementClickIsIntercepted"+ element);
		}
		}
		}

	public boolean isElementPresent(WebElement element) //check whether an element is present or not
	{
		try {
			element.isDisplayed();
			//System.out.println(element.getText());
			return true;
		}catch(Exception e) {			
			return false;			
		}	
	}
	
	public boolean isElementClickable(WebElement element) //check whether an element is present or not
	{
		try {
			element.isEnabled();
			return true;
		}catch(Exception e) {			
			return false;			
		}	
	}
	
	public void autoScrollandClick(WebElement element) //scroll,find and click element
	{
		try {
			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].click();", element);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void scrollDown(int value) //scroll down for positive value and up for negative
	{
		try {
			log.info("scroll down");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,'" + value + "')", "");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public String verifySortButton(WebElement sortButton, WebElement sortedName)
	{
		String text = null;
		try
		{
			waitForElementToLoad(sortButton);
			autoScrollandClick(sortButton);
			Thread.sleep(3000);
			text = sortedName.getText();
			autoScrollandClick(sortButton);
			waitIfElementClickIsIntercepted(sortButton, "click", "");
			Thread.sleep(3000);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return text;
	}
		
	public String getCurrentDate()
	{
		 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d/M/yyyy");
		 LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}
	
	
	public void scrollToTheElement(WebElement element)
	{
		try
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		
	}
	
	public void getValidCredentialsFromJSONfile()
	{
		try
		{
			log.info("entered getValidCredentialsFromJSONfile");
			JSONParser jsonparser = new JSONParser();
			FileReader reader = new FileReader(projectFolder+"/LoginCredentials.json");
			Object obj = jsonparser.parse(reader);
			JSONObject jobj = (JSONObject)obj;
			userEmail = (String)jobj.get("email");
			System.out.println(userEmail);
			userPassword = (String)jobj.get("password");
			language = (String)jobj.get("language");
			//System.out.println(language);
			log.info(userEmail);
			log.info(userPassword);
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	
	public boolean checkDateIsAfter(String d1,String d2)  //check first date is after second date 
	{
		boolean value=false;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

            // after() will return true if and only if date1 is after date 2
            if(date1.after(date2)){
            	System.out.println("true");
                value = true;
            }
            // before() will return true if and only if date1 is before date2
            if(date1.before(date2)){
                System.out.println("false");
                value = false;
            }

            //equals() returns true if both the dates are equal
            if(date1.equals(date2)){
                System.out.println("true");
                value = true;
            }
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return value;
	}
	
	public boolean checkDateIsBefore(String d1,String d2) //check first date is before second date 
	{
		boolean value=false;
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = sdf.parse(d1);
            Date date2 = sdf.parse(d2);

            // after() will return true if and only if date1 is after date 2
            if(date1.after(date2)){
                System.out.println("false");
                value = false;
            }
            // before() will return true if and only if date1 is before date2
            if(date1.before(date2)){
                System.out.println("true");
                value = true;
            }

            //equals() returns true if both the dates are equal
            if(date1.equals(date2)){
                System.out.println("true");
                value = true;
            }
		}
		catch(Exception e) {
			e.printStackTrace();
			log.info(e);
		}
		return value;
	}

	public Locale getLocale()
	{
		Locale local;
		if(language.equals("Arabic"))
		{
			local = new Locale("ar-AE");
		}
		else
		{
			local = new Locale("en-IND");
		}
			
		return local;
	}
	
}
