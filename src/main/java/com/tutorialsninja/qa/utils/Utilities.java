package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static final int  IMPLICIT_WAIT = 30;
	public static final int  EXPLICIT_WAIT = 30;
	public static final int  PAGE_WAIT_TIME = 30;
	 
	public static String generateTimeStamp()
	{
		Date date = new Date();
		String sdate = date.toString().replace(" ", "_").replace(":", "_");
		String emailId = "sjp1" + sdate + "@gmail.com";
		return emailId;
	}
	
	public static Object[][] getDataFromExel(String sheetName)

	{   XSSFWorkbook workbook=null;
		
	
	File file = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\TutorialsNinjaTestData.xlsx");
		try {
			FileInputStream fis = new FileInputStream(file);
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum(); //3
		int cols=  sheet.getRow(0).getLastCellNum();//2
		Object[][] data= new Object[rows][cols]; 
		for(int i = 0; i< rows;i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			for(int j =0;j<cols;j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				
				switch(cellType) {
				
				case STRING:
					data[i][j] = cell.getStringCellValue();
					System.out.println(data[i][j]);
				    break;
				case NUMERIC:
					data[i][j] = cell.getNumericCellValue();
				    break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
				    break;
				default:
					break;
				  
				}
				  
			}
		}
		return data;
		
	}
	
	public static String captureScreenshot(WebDriver driver, String testName)
	{
		File srcScreeenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);	
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+ testName+ ".png";
		try {
			FileHandler.copy(srcScreeenShot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}

}
