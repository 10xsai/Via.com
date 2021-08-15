package base;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFramework {
	
	// Declaration of Excel workbook
	Workbook wb;
	public ExcelFramework(String pathWithFileName ) {
		try {
	if(pathWithFileName.endsWith(".xls")) {
		
		// To read workbook for .xls files
		 wb = new HSSFWorkbook(new FileInputStream(pathWithFileName));
	}
	
	// To read workbook for .xlsx file	
	else if(pathWithFileName.endsWith(".xlsx")){
		 wb = new XSSFWorkbook(new FileInputStream(pathWithFileName));
	
	}
		}
		catch(Exception E) {
			System.out.println(E.getMessage());
		}
	}
		public String readData(String sheetname,int row,int col )
		{
			// To read cells present in the excel sheet
			String data=wb.getSheet(sheetname).getRow(row).getCell(col).toString();
			return data;
		}
		public int getLastRowNum(String sheetName) {
			return wb.getSheet(sheetName).getLastRowNum();
		}
}
