package base;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelFramework {
	Workbook wb;
	public ExcelFramework(String pathWithFileName ) {
		try {
	if(pathWithFileName.endsWith(".xls")) {
		 wb = new HSSFWorkbook(new FileInputStream(pathWithFileName));
	}
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
			String data=wb.getSheet(sheetname).getRow(row).getCell(col).toString();
			return data;
		}
		public int getLastRowNum(String sheetName) {
			return wb.getSheet(sheetName).getLastRowNum();
		}
}