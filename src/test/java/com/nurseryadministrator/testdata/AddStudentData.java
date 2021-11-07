package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.nurseryadministrator.baseclass.SetUp;

public class AddStudentData extends SetUp{

	Locale local = getLocale();
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public void generateFakeStudentData()
	{
		if(language.equals("Arabic"))
		{
			String nam = fake.name().firstName();
			tdImport.writeCell(2, 0, nam);
			tdImport.writeCell(2, 1, nam);
			tdImport.writeCell(2, 2, nam);
			tdImport.writeCell(2, 3, fake.name().lastName());
			tdImport.writeCell(2, 4, fake.numerify("############"));
			tdImport.writeCell(2, 6, fake.address().cityName());
			tdImport.writeCell(2, 10, fake.address().fullAddress());
			
			String name = fake.name().fullName();
			String no =  fake.numerify("9#######");
			tdImport.writeCell(2, 35, name);
			tdImport.writeCell(2, 37, no);
			tdImport.writeCell(2, 39, name);
			tdImport.writeCell(2, 40, fake.numerify("############"));
			tdImport.writeCell(2, 42, no);
		}
		else
		{
			String nam = fake.name().firstName();
			tdImport.writeCell(1, 0, nam);
			tdImport.writeCell(1, 1, nam);
			tdImport.writeCell(1, 2, nam);
			tdImport.writeCell(1, 3, fake.name().lastName());
			tdImport.writeCell(1, 4, fake.numerify("############"));
			tdImport.writeCell(1, 6, fake.address().cityName());
			tdImport.writeCell(1, 10, fake.address().fullAddress());
			
			String name = fake.name().fullName();
			String no =  fake.numerify("9#######");
			tdImport.writeCell(1, 35, name);
			tdImport.writeCell(1, 37, no);
			tdImport.writeCell(1, 39, name);
			tdImport.writeCell(1, 40, fake.numerify("############"));
			tdImport.writeCell(1, 42, no);
		}
		
	}
	
	public void generateFakeParentData()
	{
		if(language.equals("Arabic"))
		{
			tdImport.writeCell(2, 17, fake.name().fullName());
			tdImport.writeCell(2, 18, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(2, 20, fake.numerify("############"));
			tdImport.writeCell(2, 25, fake.numerify("9#######"));
			tdImport.writeCell(2, 26, fake.name().fullName());
			tdImport.writeCell(2, 27, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(2, 29, fake.numerify("############"));
			tdImport.writeCell(2, 34, fake.numerify("9#######"));
		}
		else
		{
			tdImport.writeCell(1, 17, fake.name().fullName());
			tdImport.writeCell(1, 18, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(1, 20, fake.numerify("############"));
			tdImport.writeCell(1, 25, fake.numerify("9#######"));
			tdImport.writeCell(1, 26, fake.name().fullName());
			tdImport.writeCell(1, 27, fake.bothify("????????#?#?@email.com"));
			tdImport.writeCell(1, 29, fake.numerify("############"));
			tdImport.writeCell(1, 34, fake.numerify("9#######"));
		}
		
	}
	
	public String[] getStudentData()
	{
		testData = new String[17];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
			testData[6] = tdImport.getCellData(2, 6);
			testData[7] = tdImport.getCellData(2, 7);
			testData[8] = tdImport.getCellData(2, 8);
			testData[9] = tdImport.getCellData(2, 9);
			testData[10] = tdImport.getCellData(2, 10);
			testData[11] = tdImport.getCellData(2, 11);
			testData[12] = tdImport.getCellData(2, 12);
			testData[13] = tdImport.getCellData(2, 13);
			testData[14] = tdImport.getCellData(2, 14);
			testData[15] = tdImport.getCellData(2, 15);
			testData[16] = tdImport.getCellData(2, 16);
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
			testData[7] = tdImport.getCellData(1, 7);
			testData[8] = tdImport.getCellData(1, 8);
			testData[9] = tdImport.getCellData(1, 9);
			testData[10] = tdImport.getCellData(1, 10);
			testData[11] = tdImport.getCellData(1, 11);
			testData[12] = tdImport.getCellData(1, 12);
			testData[13] = tdImport.getCellData(1, 13);
			testData[14] = tdImport.getCellData(1, 14);
			testData[15] = tdImport.getCellData(1, 15);
			testData[16] = tdImport.getCellData(1, 16);
		}
		
		return testData;
	}
	
	public String[] getParentDetails()
	{
		testData = new String[9];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 17);
			testData[1] = tdImport.getCellData(2, 18);
			testData[2] = tdImport.getCellData(2, 19);
			testData[3] = tdImport.getCellData(2, 20);
			testData[4] = tdImport.getCellData(2, 21);
			testData[5] = tdImport.getCellData(2, 22);
			testData[6] = tdImport.getCellData(2, 23);
			testData[7] = tdImport.getCellData(2, 24);
			testData[8] = tdImport.getCellData(2, 25);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 17);
			testData[1] = tdImport.getCellData(1, 18);
			testData[2] = tdImport.getCellData(1, 19);
			testData[3] = tdImport.getCellData(1, 20);
			testData[4] = tdImport.getCellData(1, 21);
			testData[5] = tdImport.getCellData(1, 22);
			testData[6] = tdImport.getCellData(1, 23);
			testData[7] = tdImport.getCellData(1, 24);
			testData[8] = tdImport.getCellData(1, 25);
		}
		
		return testData;
	
	}
	
	public String[] getParentDetails2()
	{
		testData = new String[9];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 26);
			testData[1] = tdImport.getCellData(2, 27);
			testData[2] = tdImport.getCellData(2, 28);
			testData[3] = tdImport.getCellData(2, 29);
			testData[4] = tdImport.getCellData(2, 30);
			testData[5] = tdImport.getCellData(2, 31);
			testData[6] = tdImport.getCellData(2, 32);
			testData[7] = tdImport.getCellData(2, 33);
			testData[8] = tdImport.getCellData(2, 34);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 26);
			testData[1] = tdImport.getCellData(1, 27);
			testData[2] = tdImport.getCellData(1, 28);
			testData[3] = tdImport.getCellData(1, 29);
			testData[4] = tdImport.getCellData(1, 30);
			testData[5] = tdImport.getCellData(1, 31);
			testData[6] = tdImport.getCellData(1, 32);
			testData[7] = tdImport.getCellData(1, 33);
			testData[8] = tdImport.getCellData(1, 34);
		}
		
		return testData;
	
	}
	
	public String[] getEmergencyCallDetails()
	{
		testData = new String[4];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 35);
			testData[1] = tdImport.getCellData(2, 36);
			testData[2] = tdImport.getCellData(2, 37);
			testData[3] = tdImport.getCellData(2, 38);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 35);
			testData[1] = tdImport.getCellData(1, 36);
			testData[2] = tdImport.getCellData(1, 37);
			testData[3] = tdImport.getCellData(1, 38);
		}
		return testData;
	}
	
	public String[] getAuthPickupDetails()
	{
		testData = new String[5];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 39);
			testData[1] = tdImport.getCellData(2, 40);
			testData[2] = tdImport.getCellData(2, 41);
			testData[3] = tdImport.getCellData(2, 42);
			testData[4] = tdImport.getCellData(2, 43);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 39);
			testData[1] = tdImport.getCellData(1, 40);
			testData[2] = tdImport.getCellData(1, 41);
			testData[3] = tdImport.getCellData(1, 42);
			testData[4] = tdImport.getCellData(1, 43);
		}
		return testData;

	}
	
	public String[] attachments()
	{
		testData = new String[20];
		testData[0] = tdImport.getCellData(1, 44);
		testData[1] = tdImport.getCellData(2, 44);
		testData[2] = tdImport.getCellData(3, 44);
		testData[3] = tdImport.getCellData(4, 44);
		testData[4] = tdImport.getCellData(5, 44);
		testData[5] = tdImport.getCellData(6, 44);
		testData[6] = tdImport.getCellData(7, 44);
		testData[7] = tdImport.getCellData(8, 44);
		testData[8] = tdImport.getCellData(9, 44);
		testData[9] = tdImport.getCellData(10, 44);
		
		testData[10] = tdImport.getCellData(1, 45);
		testData[11] = tdImport.getCellData(2, 45);
		testData[12] = tdImport.getCellData(3, 45);
		testData[13] = tdImport.getCellData(4, 45);
		testData[14] = tdImport.getCellData(5, 45);
		testData[15] = tdImport.getCellData(6, 45);
		testData[16] = tdImport.getCellData(7, 45);
		testData[17] = tdImport.getCellData(8, 45);
		testData[18] = tdImport.getCellData(9, 45);
		testData[19] = tdImport.getCellData(10, 45);
		return testData;
		
	}
	
	public String[] getFilterData()
	{
		testData = new String[5];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 3);
			testData[2] = tdImport.getCellData(2, 17);
			//String x = tdImport.getCellData(1, 14);
			//String ww=x.replaceAll("[()]", "");
			//String[] xx = ww.split(" ");
			//testData[3] = xx[0];
			//testData[4] = xx[1];
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0);
			testData[1] = tdImport.getCellData(1, 3);
			testData[2] = tdImport.getCellData(1, 17);
			//String x = tdImport.getCellData(1, 14);
			//String ww=x.replaceAll("[()]", "");
			//String[] xx = ww.split(" ");
			//testData[3] = xx[0];
			//testData[4] = xx[1];
		}
		
		return testData;
	}
	
	public String[] editData()
	{
		testData = new String[5];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0)+"__تم تحريره";
			testData[1] = tdImport.getCellData(2, 3)+"__تم تحريره";
			testData[2] = tdImport.getCellData(2, 17)+"__تم تحريره";
			testData[3] = tdImport.getCellData(2, 26)+"__تم تحريره";
			testData[4] = tdImport.getCellData(2, 35)+"__تم تحريره";
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0)+"__edited";
			testData[1] = tdImport.getCellData(1, 3)+"__edited";
			testData[2] = tdImport.getCellData(1, 17)+"__edited";
			testData[3] = tdImport.getCellData(1, 26)+"__edited";
			testData[4] = tdImport.getCellData(1, 35)+"__edited";
		}
		return testData;
		
	}
	
	public String[] filterData()
	{
		testData = new String[6];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
			testData[4] = tdImport.getCellData(2, 4);
			testData[5] = tdImport.getCellData(2, 5);
		}
		else
		{
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
