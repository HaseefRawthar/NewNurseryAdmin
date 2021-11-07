package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.nurseryadministrator.baseclass.SetUp;

public class AddEmployeeData extends SetUp{

	Locale local = getLocale();
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public void generateFakeEmployeeData()
	{
		if(language.equals("Arabic"))
		{
			tdImport.writeCell(2, 0, fake.name().firstName());
			tdImport.writeCell(2, 1, fake.letterify("??"));
			tdImport.writeCell(2, 2, fake.name().lastName());
			tdImport.writeCell(2, 3, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(2, 6, fake.numerify("9#######"));
		}
		else
		{
			tdImport.writeCell(1, 0, fake.name().firstName());
			tdImport.writeCell(1, 1, fake.letterify("??"));
			tdImport.writeCell(1, 2, fake.name().lastName());
			tdImport.writeCell(1, 3, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(1, 6, fake.numerify("9#######"));
		}
		
	}
	
	public String[] getEmployeeDetails()
	{
		testData = new String[7];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
			testData[6] = tdImport.getCellData(2, 6);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0);
			testData[1] = tdImport.getCellData(1, 1);
			testData[2] = tdImport.getCellData(1, 2);
			testData[3] = tdImport.getCellData(1, 3);
			testData[4] = tdImport.getCellData(1, 4);
			testData[5] = tdImport.getCellData(1, 5);
			testData[6] = tdImport.getCellData(1, 6);
		}
		
		return testData;
	}
	
	public String[] getRoleData()
	{
		testData = new String[4];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 7);
			testData[1] = tdImport.getCellData(2, 8);
			testData[2] = tdImport.getCellData(2, 9);
			testData[3] = tdImport.getCellData(2, 10);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 7);
			testData[1] = tdImport.getCellData(1, 8);
			testData[2] = tdImport.getCellData(1, 9);
			testData[3] = tdImport.getCellData(1, 10);
		}
		
		return testData;
	}
	
	public String[] editData()
	{
		testData = new String[5];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0)+"__تم تحريره";
			testData[1] = tdImport.getCellData(2, 1)+"__تم تحريره";
			testData[2] = tdImport.getCellData(2, 2)+"__تم تحريره";
			testData[3] = tdImport.getCellData(2, 8)+"__تم تحريره";
			testData[4] = tdImport.getCellData(2, 9)+"__تم تحريره";
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0)+"__edited";
			testData[1] = tdImport.getCellData(1, 1)+"__edited";
			testData[2] = tdImport.getCellData(1, 2)+"__edited";
			testData[3] = tdImport.getCellData(1, 8)+"__edited";
			testData[4] = tdImport.getCellData(1, 9)+"__edited";
		}
		
		return testData;
	}
	
	public String[] assistantFilterData()
	{
		testData = new String[3];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(21, 0);
			testData[1] = tdImport.getCellData(21, 1);
			testData[2] = tdImport.getCellData(21, 2);
			
		}
		else
		{
			testData[0] = tdImport.getCellData(20, 0);
			testData[1] = tdImport.getCellData(20, 1);
			testData[2] = tdImport.getCellData(20, 2);
			
		}
		
		return testData;
	}
	
	public String[] teacherFilterData()
	{
		testData = new String[3];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(31, 0);
			testData[1] = tdImport.getCellData(31, 1);
			testData[2] = tdImport.getCellData(31, 2);
			
		}
		else
		{
			testData[0] = tdImport.getCellData(30, 0);
			testData[1] = tdImport.getCellData(30, 1);
			testData[2] = tdImport.getCellData(30, 2);
			
		}
		
		return testData;
	}
	
	public String[] nurseFilterData()
	{
		testData = new String[3];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(41, 0);
		}
		else
		{
			testData[0] = tdImport.getCellData(40, 0);	
		}
		
		return testData;
	}
}
