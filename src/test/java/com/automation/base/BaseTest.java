package com.automation.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.Constants;
import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected Logger log;
	//protected WebDriverWait wait;
	public  static WebDriver driver;//MAIN CREATION OF DRIVER STARTS FROM HERE!
	protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	@BeforeTest
	public void setUpForBeforeTest() {
		log=logObject.getLogger();
		log=LogManager.getLogger(BaseTest.class);
	}
	
	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeTestMethod(@Optional("chrome") String browName) {
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp=pro.loadFile("applicationDataProperties");
		String url=appProp.getProperty("url");
		launchBrowser(browName);
		goToUrl(url);
		
		
	}
	
	@AfterMethod
	public void tearDownAfterTestMethod() {
		driver.close();
		System.out.println("Driver ----- closed--------");
	}
	public  void launchBrowser(String browserName) {
		switch(browserName) {
		case "firefox":
			WebDriverManager.firefoxdriver().browserVersion("109.0.1").setup();
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			break;
		}
		System.out.println(browserName+ "browser opened");
	}
	
	public void goToUrl(String url) {
		driver.get(url);
		log.info(url+"is entered");
	}
	public void closeBrowser() {
		driver.close();
		log.info("current browser closed");
	}

	public File getScreenshotOfThePage(WebDriver driver) {
		String date=new SimpleDateFormat("yyy_MM_dd__hh_mm_ss").format(new Date());
		TakesScreenshot screenShot=(TakesScreenshot) driver;
		File imgFile=screenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		
		try {
			FileUtils.copyFile(imgFile, destFile);
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("screenshot captured at =" + destFile.getAbsolutePath());
		return destFile;
	}
}
