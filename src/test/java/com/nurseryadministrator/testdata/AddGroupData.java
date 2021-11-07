package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.nurseryadministrator.baseclass.SetUp;

public class AddGroupData extends SetUp{

	Locale local = getLocale();
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;

	
	public void generateFakeGroupName()
	{
		tdImport.writeCell(1, 0, fake.color().name()+fake.bothify("##???"));;
	}
	
	public String getGroupName()
	{
		return tdImport.getCellData(1, 0);
	}
	
	public String[] getFilterData()
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
	
	public String[] getFilterEditData()
	{
		testData = new String[2];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(5, 0);
			testData[1] = tdImport.getCellData(5, 1);
		}
		else
		{
			testData[0] = tdImport.getCellData(4, 0);
			testData[1] = tdImport.getCellData(4, 1);
		}
		
		return testData;
	}
	
	public String[] getTeacherData()
	{
		testData = new String[2];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(3, 3);
			testData[1] = tdImport.getCellData(3, 4);
		}
		else
		{
			testData[0] = tdImport.getCellData(2, 3);
			testData[1] = tdImport.getCellData(2, 4);
		}
		return testData;
	}
	
	public String[] getAssistantName()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 5).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 5).split(",");
		}
		
		return testData;
	}
	
	public String[] getAssistantlastName()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 6).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 6).split(",");
		}
		
		return testData;
	}
	
	public String[] getAssistantNumber()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 7).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 7).split(",");
		}
		
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
	
	public String[] getParentsName()
	{
		if(language.equals("Arabic"))
		{
			testData = tdImport.getCellData(3, 10).split(",");
		}
		else
		{
			testData = tdImport.getCellData(2, 10).split(",");
		}
		
		return testData;
	}
}
