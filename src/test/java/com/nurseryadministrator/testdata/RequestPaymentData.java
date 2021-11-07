package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.nurseryadministrator.baseclass.SetUp;

public class RequestPaymentData extends SetUp {

	Locale local = getLocale();
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	String title;
	String amount;
	
	public void generateFakeData()
	{
		 title = (language.equals("Arabic"))?"دفع"+fake.bothify("###?"):"Payment"+fake.bothify("###?");
		 amount = fake.numerify("2##.###");
		 tdImport.writeCell(2, 13, title);
		 tdImport.writeCell(2, 14, amount);
	}
	
	public String[] getRequestPaymentData()
	{
		testData = new String[3];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 13);
			testData[1] = tdImport.getCellData(2, 14);
			testData[2] = "لا شيء";
		}
		else
		{
			testData[0] = tdImport.getCellData(2, 13);
			testData[1] = tdImport.getCellData(2, 14);
			testData[2] = "nil";
		}
		
		return testData;
				
	}
	
	public String[] getGroupData()
	{
		testData = new String[2];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(3, 0);
			testData[1] = tdImport.getCellData(3, 1);
		}
		else
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
		}
		
		return testData;
     
	}
	
	public String[] attachments()
	{
		testData = new String[6];
		testData[0] = tdImport.getCellData(1, 12);
		testData[1] = tdImport.getCellData(2, 12);
		testData[2] = tdImport.getCellData(3, 12);
		testData[3] = tdImport.getCellData(4, 12);
		testData[4] = tdImport.getCellData(5, 12);
		testData[5] = tdImport.getCellData(6, 12);
		return testData;
		
	}
	
	public String[] getStudentsName()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 8).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 8).split(",");
		}
		
		return testData;
	}
	
	public String[] getStudentlastName()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 9).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 9).split(",");
		}
		
		return testData;
	}
	
	public String[] filterData()
	{
		if(language.equals("Arabic"))
		{
			testData = new String[6];
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
		}
		else
		{
			testData = new String[6];
			testData[0] = tdImport.getCellData(1, 0);
			testData[1] = tdImport.getCellData(1, 1);
			testData[2] = tdImport.getCellData(1, 2);
			testData[3] = tdImport.getCellData(1, 3);
			testData[4] = tdImport.getCellData(1, 4);
			testData[5] = tdImport.getCellData(1, 5);
		}
		
		return testData;
		
	}
	
	 
}
