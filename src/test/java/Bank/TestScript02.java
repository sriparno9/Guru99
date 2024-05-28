/* 
 * In this script we will be using @DataProvider annotation of TestNG
 */
package Bank;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestScript02 {

	private WebDriver driver; 
	private String baseUrl; 
	
	@DataProvider(name = "GuruTest")
	public Object[][] testData() {

		Object[][] data = new Object[4][2];

		// 1st row
		data[0][0] = Util.USER_NAME;
		data[0][1] = Util.PASSWD;
		//2nd row
		data[1][0] = "invalid";
		data[1][1] = "valid";
		//3rd row
		data[2][0] = "valid";
		data[2][1] = "invalid";
		//4th row
		data[3][0] = "invalid";
		data[3][1] = "invalid";
		return data;
	}

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); 
			
		driver = new ChromeDriver();

		baseUrl = Util.BASE_URL;
		driver.manage().timeouts().implicitlyWait(Util.WAIT_TIME, TimeUnit.SECONDS);
		// Go to http://www.demo.guru99.com/V4/
		driver.get(baseUrl + "/V4/");
	}

	@Test(dataProvider = "GuruTest")
	public void testCase05(String username, String password) throws Exception {
		String actualTitle;
		String actualBoxMsg;
		// Enter valid UserId
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(username);
		// Enter valid Password
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(password);
		// Click Login
		driver.findElement(By.name("btnLogin")).click();

		 try{ 
			    
		       	Alert alt = driver.switchTo().alert();
				actualBoxMsg = alt.getText(); 
				alt.accept();
				 // Compare Error Text with Expected Error Value					
				assertEquals(actualBoxMsg,Util.EXPECT_ERROR);
				
			}    
		    catch (NoAlertPresentException Ex){ 
		    	// Get text displayes on login page 
				String pageText = driver.findElement(By.tagName("tbody")).getText();

				// Extract the dynamic text mngrXXXX on page		
				String[] parts = pageText.split(Util.PATTERN);
				String dynamicText = parts[1];

				// Check that the dynamic text is of pattern mngrXXXX
				// First 4 characters must be "mngr"
				assertTrue(dynamicText.substring(1, 5).equals(Util.FIRST_PATTERN));
				// remain stores the "XXXX" in pattern mngrXXXX
				String remain = dynamicText.substring(dynamicText.length() - 4);
				// Check remain string must be numbers;
				assertTrue(remain.matches(Util.SECOND_PATTERN));
	        } 
		}		

	@AfterMethod
	public void tearDown() throws Exception {
		driver.quit();
	}
}