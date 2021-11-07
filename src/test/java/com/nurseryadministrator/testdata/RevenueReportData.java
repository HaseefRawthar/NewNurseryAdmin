package com.nurseryadministrator.testdata;

public class RevenueReportData {

	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public String[] filterData()
	{
		testData = new String[2];
		testData[0] = tdImport.getCellData(1, 0);
		testData[1] = tdImport.getCellData(1, 1);
		return testData;
	}
}
