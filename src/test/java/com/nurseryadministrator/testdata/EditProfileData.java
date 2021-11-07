package com.nurseryadministrator.testdata;

import java.util.Locale;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

public class EditProfileData {
	Locale local = new Locale("en-IND");
	Faker fake = new Faker(local);
	FakeValuesService fakeService = new FakeValuesService(local, new RandomService());
	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public String[] editData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(1, 0);
		testData[1] = tdImport.getCellData(1, 1);
		return testData;
	}
	
	public String[] editPhoneNumberData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(1, 2);
		testData[1] = tdImport.getCellData(1, 3);
		return testData;
	}
}
