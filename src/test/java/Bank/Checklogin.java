package Bank;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

public class Checklogin {
	String fileName = "TestData.xlsx";
	String filePath = "C:\\Users\\sriparno_das\\OneDrive - HCL TECHNOLOGIES LIMITED\\Desktop\\";
	String tabname = "TestData";
	WebDriver driver;
	
	@Test(dataProvider = "suppleytetstcred")
	public void validateloginfunc(String username, String password) {

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		
		//do the validation of the actual login is happening or not by title check
		String expectedTitle =  "Guru99 Bank Manager HomePage"; 
        String actualTitle = driver.getTitle();

        Assert.assertTrue(actualTitle.equals(expectedTitle), "Login failed!");
        System.out.println("Login successful!");
    
	}

	@BeforeMethod
	public void iniittest() {
		System.setProperty("webdriver.chrome.driver", "D:\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demo.guru99.com/V4/");
	}

	@AfterMethod
	public void cleantest() {
		driver.quit();

	}

	@DataProvider
	public String[][] suppleytetstcred() throws Exception {

		String[][] mytestdata = null;
		File file = new File(filePath+ fileName);
		FileInputStream fin = new FileInputStream(file);
		
		Workbook wb = null  ;
		String filext = fileName.substring(fileName.indexOf("."));

		if (filext.equals(".xlsx")) {
			wb = new XSSFWorkbook(fin);
		}
		else if (filext.equals(".xls")) {
			wb = new HSSFWorkbook(fin);
		}
		else {
			System.out.println("invalid ");
		}

		Sheet sheet = wb.getSheet(tabname);
		
		int rowcount = sheet.getLastRowNum() - sheet.getFirstRowNum();
		
		Iterator rowIterator = sheet.rowIterator();
		
		Row headerow = (Row) rowIterator.next();
		
		int coloumncount = headerow.getPhysicalNumberOfCells();

		mytestdata = new String[rowcount][coloumncount];

		for (int i = 1; i < rowcount+1 ; i++)
		{
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				mytestdata[i - 1][j] = row.getCell(j).getStringCellValue();
			}
		}
		return mytestdata;
	}
	

	}