package com.automation.pages.base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.Log4JUtility;

//HERE ARE ALL REUASABLE METHODS !!!! All selenium basic commands are here!

public class BasePage {			
	protected  static WebDriver driver;
	protected  WebDriverWait wait;
	protected Logger log;
	//protected Log4JUtility logObject=Log4JUtility.getInstance();
	protected ExtentReportsUtility report=ExtentReportsUtility.getInstance();
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
		System.out.println("driver in basePage="+driver);
		PageFactory.initElements(driver, this);
		//logObject.getLogger();
		log=LogManager.getLogger(BasePage.class);
	}
	
	//---------------------------------------------
	public void enterText(WebElement element, String data, String objectName) {
		waitFluentForVisibiity(element,objectName);
		if(element.isDisplayed()) {
			clearElement(element,objectName);
			element.sendKeys(data);
			log.info("Pass:" +objectName+"is entered to the user filed");
		    //report.logTestInfo("Pass:" +objectName+ "is entered to the username filed");
	}else {
		log.error("Fail:" +objectName+"element is not displayed");
	}
	}
	
	
	
	public void clearElement(WebElement element, String objectName) {
		if(element.isDisplayed()) {
			element.clear();
			log.info("Pass:"+objectName+"element cleared" );	
			}
		else {
			log.error("Fail:" +objectName+"element is not displayed");
		}
		
	}
	
	public void clickElement(WebElement element, String objName) {
		waitFluentForVisibiity(element,objName);
		if(element.isDisplayed()) {
			element.click();
			log.info("Pass:"+objName+"element clicked" );	
			}
		else {
			log.error("Fail:" +objName+"element is not displayed");
		}
		
	}
	
	
	public static WebDriver returnDriverInstance() {
		return driver;
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	public void refreshPage() {
		driver.navigate().refresh();
	}
	
	public String getTextFromWebElement(WebElement element, String name) {
		waitFluentForVisibiity(element,name);
		if(element.isDisplayed()) {
			log.info("Pass: "+name+" was got");
			return element.getText();
		}else {
			log.info(name+"web element is not displayed");
			return null;
		}
	}
	

	//---------- handling PopUp Windows -------------------
	// Switch to the pop-up window
	
	public  void popUpWindowHandle(WebElement elem) throws InterruptedException {
	String mainWindowHandle = driver.getWindowHandle();
	Set<String> allWindowHandles = driver.getWindowHandles();
	for (String handle : allWindowHandles) {
	if (!handle.equals(mainWindowHandle))
	{
	driver.switchTo().window(handle);
	break;
	}
	}
	Thread.sleep(3000);	
	elem.click();
	log.info("Pass: "+elem+" was successfully clicked. " );	
	// Switch back to the main window
	driver.switchTo().window(mainWindowHandle); 
	}
//--------handling DROPDOWNS ------------------------------
	public  void dropdownHandleByValue(String dropdownName, WebElement xpathOfDropDown, String dropDownOption){ 
		Select se = new Select(xpathOfDropDown);
		se.selectByVisibleText(dropDownOption); 
		waitUntilElementIsVisible(xpathOfDropDown,dropDownOption);
		log.info("Pass: "+ dropDownOption +" was successfully chosen. " );	
		
		
		}
	
	public  void dropdownHandleByValueWithClick(String dropdownName, WebElement xpathOfDropDown, String dropDownOption){ 
		Select se = new Select(xpathOfDropDown);
		se.selectByVisibleText(dropDownOption); 
		waitUntilElementIsVisible(xpathOfDropDown,dropDownOption);
		log.info("Pass: "+ dropDownOption +" was successfully chosen. " );	
		clickElement(xpathOfDropDown, dropDownOption );
		
		}
	
	
	public void getDropDownList(WebElement dropdwn, String viewNameData) {
		//WebElement originalDropdown = driver.dropdown;
		waitFluentForVisibiity(dropdwn,"viewDropDownContacts");
		Select original = new  Select(dropdwn);
		List<WebElement> originalListElements = original.getOptions();
		List<String> originalList = new ArrayList<String>();
		for (WebElement webElement : originalListElements) {
			originalList.add(webElement.getText());
			
		}
		for(String option:originalList ) {
			if(option==viewNameData) {
				log.info("FAIL: "+ viewNameData +" was added to the ViewdropDown!!!!! " );	
				break;
			}
			else
			System.out.println(option); 
			} 


	}
	
	//----------8 handling alerts reusable methods -------------------
	
	public Alert switchToAlert() {
		Alert alert= driver.switchTo().alert();
		log.info("switched to alert");
		return alert;
	}
	
	public void AcceptAlert(Alert alert) {
		System.out.println("Alert accepted");
		alert.accept();
	}
	
	public String getAlertText(Alert alert) {
		System.out.println("Extracting text in the alert");
		return alert.getText();
	}
	
	public void dismisAlert() {
		Alert alert=switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");
	}
	//----------------alerts end --------------------------
	public void waitUntilPageLoads() {
		System.out.println("waiting until page loads with 30 sec maximum");
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	public void moveTOElementAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println("cursor moved to web element" +objName);
	}
	
	public void ContextCLickAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click performed on web element" +objName);
	}
	
	public void waitUntilElementIsVisible(WebElement ele, String objName) {
		System.out.println("waiting for a web element"+objName+"for its visibility");
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public void WaitUntilPresenceOfElementLocatedBy(By locator, String objName) {
		System.out.println("waiting for a web element"+objName+"for its visibility");
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}
	
	
	public void waitUntilAlertIsPresent() {
		System.out.println("waiting for aleet to be present");
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}
	
	public void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for a web element" + objName+"to be clickable");
		wait=new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitFluentForVisibiity(WebElement ele, String objName) {
		Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(30))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	public void selectByTextData(WebElement element, String text,String objName) {
		Select selectCity=new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName+ "selected" +text);
	}
	
	public void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity=new Select(element);
		selectCity.selectByIndex(index);
		System.out.println(objName + "selected");
	}
	
	public void selectByValueData(WebElement element, String text, String objName) {
		Select selectCity=new Select(element);
		selectCity.selectByValue(text);
		System.out.println(objName + "selected");
	}
	
	
	public void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for(String handle:allWindowHandles) {
			if(!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
				
	
	public WebElement selectFromList(List<WebElement>list, String text) {
		WebElement country=null;
		for (WebElement i: list) {
			if(i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" +i.getText());
				country=i;
				break;
			}
			
		}
	
	return country;
}

	
	public WebDriver frameHandle(WebElement elem) {
		driver.switchTo().frame(elem); 
		log.info("switched to frame");
		return driver;
		
	}
	
	public void ScrollDownMethod() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)", "");
	}
	
	
	
}
