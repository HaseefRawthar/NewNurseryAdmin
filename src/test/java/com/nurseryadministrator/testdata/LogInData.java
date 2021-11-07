package com.nurseryadministrator.testdata;


public class LogInData {

	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public String[] getValidLoginData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(1, 0);
		testData[1] = tdImport.getCellData(1, 1);
		return testData;
	}
	
	public String[] getInValidLoginData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(2, 0);
		testData[1] = tdImport.getCellData(2, 1);
		return testData;
	}
	
	public String[] getInValidEmailData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(3, 0);
		testData[1] = tdImport.getCellData(3, 1);
		return testData;
	}
}
