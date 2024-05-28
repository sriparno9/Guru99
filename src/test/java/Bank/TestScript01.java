/*
 *Read Excel sheet Data
 */
package Bank;

import static org.testng.Assert.assertEquals;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestScript01 {

	private WebDriver driver; 
	private String baseUrl; 

	@DataProvider(name = "GuruTest")
	public Object[][] testData() throws Exception {
		return Util1.getDataFromExcel(Util1.FILE_PATH, Util1.SHEET_NAME,
				Util1.File_NAME);
	}


	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); 

//		driver = new FirefoxDriver();
		driver = new ChromeDriver();

		baseUrl = Util.BASE_URL;
		driver.get(baseUrl + "/V4/");
	}


	@Test(dataProvider = "GuruTest")
	public void testCase04(String username, String password) throws Exception {
		String actualTitle;
		String actualBoxMsg;
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

	    try{ 
	    
	       	Alert alt = driver.switchTo().alert();
			actualBoxMsg = alt.getText(); // get content of the Alter Message
			alt.accept();
			 // Compare Error Text with Expected Error Value					
			assertEquals(actualBoxMsg,Util.EXPECT_ERROR);
			
		}    
	    catch (NoAlertPresentException Ex){ 
	    	actualTitle = driver.getTitle();
	    	assertEquals(actualTitle,Util.EXPECT_TITLE);
        } 
	}

	
	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
}