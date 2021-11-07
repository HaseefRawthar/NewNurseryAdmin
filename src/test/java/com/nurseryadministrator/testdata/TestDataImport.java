package com.nurseryadministrator.testdata;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.nurseryadministrator.baseclass.SetUp;

public class TestDataImport extends SetUp {

	private static HSSFWorkbook workbook;
	private static HSSFSheet sheet;
	private static  FileInputStream inputStream;
	private static FileOutputStream outputstream;
	static Cell cell;
	static Row row;
	String cellData;
	
	String path = projectFolder+"/Excel/NurseryAdminTestData.xls";
	
	public void readSheet(String sheetName)
	{
		try {
			log.info("Entered readExcel method");
			inputStream = new FileInputStream(path);
			workbook = new HSSFWorkbook(inputStream);
			sheet = workbook.getSheet(sheetName);
		   } 
		catch (IOException e) {
			e.printStackTrace();
			log.info(e);
		}
	}
	


	public String getCellData(int rowNum, int colNum)
	{
		try {
		log.info("Entered getValue method");
		cellData = sheet.getRow(rowNum).getCell(colNum).toString();
	      }
		catch(Exception e){
			cellData = "";
			log.info(e);
		}
		return cellData;
		
	}
	
	public void writeCell(int rowNum, int colNum, String text)
	{
		try {
			log.info("Entered writeCell method");
			   outputstream=new FileOutputStream(path);
			   if(sheet.getRow(rowNum) == null)
				   row = sheet.createRow(rowNum);
			    if(sheet.getRow(rowNum).getCell(colNum)==null){
			    	row=sheet.getRow(rowNum);
			    	cell=row.createCell(colNum);
			    }
			    else {
			    	cell=sheet.getRow(rowNum).getCell(colNum);
			    }
			    cell.setCellValue(text);
			    workbook.write(outputstream);
			    outputstream.close();
			    }catch(Exception e)
				 {
			    	e.printStackTrace();
			    	System.out.println("Failed");
				 }
		
		
	}
}
