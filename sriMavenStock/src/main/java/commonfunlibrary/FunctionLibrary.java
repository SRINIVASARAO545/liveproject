package commonfunlibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.bytebuddy.asm.Advice.Return;
import utilities.PropertyfileUtil;

public class FunctionLibrary
{
	WebDriver driver;
	//start browser
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if(PropertyfileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "D:\\srinivas\\sriMavenStock\\ExcutableFiles\\geckodriver.exe");
			driver=new FirefoxDriver();
		}else
			if(PropertyfileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver", "D:\\srinivas\\sriMavenStock\\ExcutableFiles\\chromedriver.exe");
				driver=new ChromeDriver();
			}else
				if(PropertyfileUtil.getValueForKey("Browser").equalsIgnoreCase("ie"))
				{
					System.setProperty("webdriver.ie.driver", "D:\\srinivas\\sriMavenStock\\ExcutableFiles\\IEDriverServer.exe");
					driver=new InternetExplorerDriver();
					
				}
		    return driver;
	}
	//open Application
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyfileUtil.getValueForKey("URL"));
		driver.manage().window().maximize();
	}
	//type Action
	public static void typeAction(WebDriver driver,String locatorType,String locatorValue,String data)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
		driver.findElement(By.id(locatorValue)).clear();
		driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);
				}
				}
		//clickAction
		public static void clickAction(WebDriver driver,String locatorType,String locatorValue)
		{
			if(locatorType.equalsIgnoreCase("id"))
			{
				driver.findElement(By.id(locatorValue)).click();
			}else
				if(locatorType.equalsIgnoreCase("name"))
				{
					driver.findElement(By.name(locatorValue)).click();
				}else
					if(locatorType.equalsIgnoreCase("xpath"))
					{
						driver.findElement(By.xpath(locatorValue)).click();;
					}
		}
		//closeBrowser
		public static void closeBrowser(WebDriver driver)
		{
			driver.close();
		}
		//waitforElement
		public static void waitForElement(WebDriver driver,String locatorType,String locatorValue,String waiTtime)
		{
			WebDriverWait mywait=new WebDriverWait(driver, Integer.parseInt(waiTtime));
			if(locatorType.equalsIgnoreCase("id"))
			{
				mywait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue)));
			}else
				if(locatorType.equalsIgnoreCase("name"))
				{
					mywait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue)));
				}else
					if(locatorType.equalsIgnoreCase("xpath"))
					{
						mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue)));
					}
			}
		//scroll page down
		public static void pageDown(WebDriver driver)
		{
			Actions action=new Actions(driver);
			action.sendKeys(Keys.PAGE_DOWN).build().perform();
		}
		//capture supplier number
		public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
		{
			String data="";
			if(locatorType.equalsIgnoreCase("id"))
			{
				data=driver.findElement(By.id(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("name"))
				{
					data=driver.findElement(By.name(locatorValue)).getAttribute("value");
				}else
					if(locatorType.equalsIgnoreCase("xpath"))
					{
						data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
						
					}
			FileWriter fw=new FileWriter("D:\\srinivas\\sriMavenStock\\CaptureData\\Data.txt");
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(data);
			
		}
		//SupplierCreation Table Validation
		public static void tableValidation(WebDriver driver,String column) throws Throwable
		{
			//Reading the supplier number from text file
			FileReader fr=new FileReader("D:\\srinivas\\sriMavenStock\\CaptureData\\Data.txt");
			BufferedReader br=new BufferedReader(fr);
			String exp_data=br.readLine();
			//converting string value into integer
			int columNum=Integer.parseInt(column);
			if(driver.findElement(By.xpath(PropertyfileUtil.getValueForKey("Search.Panel"))).isDisplayed())
			{
				driver.findElement(By.xpath(PropertyfileUtil.getValueForKey("Search.Panel"))).click();
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Box"))).clear();
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Button"))).click();
			}else
			{
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Box"))).clear();
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Box"))).sendKeys(exp_data);
				driver.findElement(By.id(PropertyfileUtil.getValueForKey("Search.Button"))).click();
			}
			WebElement webTable=driver.findElement(By.xpath(PropertyfileUtil.getValueForKey("webTable")));
			//row count
			List<WebElement> rows=webTable.findElements(By.tagName("tr"));
			for (int i = 1; i <=rows.size(); i++)
			{
				String act_data=driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/form/div//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+columNum+"]/div/span")).getText();
						System.out.println((act_data));
				//validation
				Assert.assertEquals(exp_data, act_data);
				break;	
			}
		}
		//generate date
		public static String generateDate()
		{
			Date date=new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY_MM_dd_ss");
			return sdf.format(date);
		}
		
				
	}


