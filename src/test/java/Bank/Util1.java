package Bank;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

public class Util1 {

	//public static final int WAIT_TIME = 30; /

	public static final String BASE_URL = "http://www.demo.guru99.com/";

	public static final String USER_NAME = "mngr936";
	public static final String PASSWD = "YgEqUnY";

	public static final String VALID = "valid";
	public static final String INVALID = "invalid";

	public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";
	public static final String EXPECT_ERROR = "User or Password is not valid";

	/* You can change the Path of FireFox base on your environment here */
	//public static final String FIREFOX_PATH = "C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";

	// You can change the information of your data file here
	public static final String FILE_PATH = "C:\\Users\\sriparno_das\\OneDrive - HCL TECHNOLOGIES LIMITED\\Desktop\\"; // File Path
	public static final String SHEET_NAME = "Data"; // Sheet name
	public static final String File_NAME = "testData.xls"; // Name of data table

	
	public static String[][] getDataFromExcel(String filePath, String sheetName, String fileName) throws Exception {
		
		String[][] mytestdata = null;	
		File file = new File(FILE_PATH + File_NAME);	
		FileInputStream fin = new FileInputStream(file);	
		Workbook wb = null ;	
		String filext = File_NAME.substring(File_NAME.indexOf("."));
		if (filext.equals(".xlsx")) {	
			wb = new XSSFWorkbook(fin);		
			}	
		else if (filext.equals(".xls")) {	
			wb = new HSSFWorkbook(fin);	
			}		else {		
				System.out.println("invalid ");	
				}
		Sheet sheet = wb.getSheet(SHEET_NAME);		
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();	
		Iterator rowIterator = sheet.rowIterator();			
		Row headerow = (Row) rowIterator.next();		
		int coloumncount = headerow.getPhysicalNumberOfCells();
		mytestdata = new String[rowcount+1][coloumncount+1];
//read data and store it in the array
		for (int i = 1; i < rowcount+1 ; i++)		
		{			Row row = sheet.getRow(i);	
		if(row != null) {
		for (int j = 0; j < row.getLastCellNum(); j++) {
			Cell cell = row.getCell(j);
			if(cell != null) {
			mytestdata[i - 1][j] = row.getCell(j).getStringCellValue();		
			}	else {
				mytestdata[i-1][j]="";
			}
		}
		}else{
			
		}}return mytestdata;
		
//		String[][] tabArray = null;
//
//		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
//		// get the sheet name
//		Sheet sheet = workbook.getSheet(sheetName);
//
//		int startRow, startCol, endRow, endCol, ci, cj;
//
//		// find cell position which contain first appear table name
//		Cell tableStart = sheet.findCell(tableName);
//		// Row position of FIRST appear table name
//		startRow = tableStart.getRow();
//		// Col position of FIRST appear table name
//		startCol = tableStart.getColumn();
//
//		// find cell position which contain last appear table name
//		Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1,
//				100, 64000, false);
//		// Row position of LAST appear table name
//		endRow = tableEnd.getRow();
//		// Col position of LAST appear table name
//		endCol = tableEnd.getColumn();
//
//		tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
//		ci = 0;
//
//		// Store all data in an array
//		for (int i = startRow + 1; i < endRow; i++, ci++) {
//			cj = 0;
//			for (int j = startCol + 1; j < endCol; j++, cj++) {
//				//Get content of each cell and store to each array element.
//				tabArray[ci][cj] = sheet.getCell(j, i).getContents();
//			}
//		}
//
//		return (tabArray);
		
		
		
	}

}