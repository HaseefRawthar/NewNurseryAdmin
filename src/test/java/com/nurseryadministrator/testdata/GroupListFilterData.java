package com.nurseryadministrator.testdata;

import com.nurseryadministrator.baseclass.SetUp;

public class GroupListFilterData extends SetUp {

	TestDataImport tdImport = new TestDataImport();
	String[] testData;
	
	public String[] filterData()
	{
		testData = new String[4];
		if(language.equals("Arabic"))
		{
			testData[0] = tdImport.getCellData(2, 0);
			testData[1] = tdImport.getCellData(2, 1);
			testData[2] = tdImport.getCellData(2, 2);
			testData[3] = tdImport.getCellData(2, 3);
		}
		else
		{
			testData[0] = tdImport.getCellData(1, 0);
			testData[1] = tdImport.getCellData(1, 1);
			testData[2] = tdImport.getCellData(1, 2);
			testData[3] = tdImport.getCellData(1, 3);
		}
		
		return testData;
	}
}
