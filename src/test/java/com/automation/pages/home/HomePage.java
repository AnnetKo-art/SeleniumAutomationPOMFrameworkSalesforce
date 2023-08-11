package com.automation.pages.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;
//pages keep only webElements and functionalities
public class HomePage extends BasePage{
		
	@FindBy(xpath ="//a[@title='Contacts Tab']") WebElement contactsLinkElement;
	@FindBy(id ="userNavLabel") WebElement userNavLabelDropDown;
	@FindBy (xpath ="//a[@title='My Profile']") WebElement myProfileOption;
				
	public HomePage(WebDriver driver) {
		super(driver);
		//PageFactory.initElements(driver,this);// this - is the current page.
		// here we initializing,decloration elements with annotations using this function.
	}
			
	public String getTitleOfThePAge() { //void getTitleOfThePAge() {//String getTitleOfThePAge() {
		waitUntilPageLoads();
		return getPageTitle();
		//driver.getTitle();
	}
	
	public WebDriver clickLink() {
		//loginButton.click();
		clickElement(contactsLinkElement,"login button");
		return driver;
	}
	
	public WebDriver dropDownClick() {
		//loginButton.click();
		clickElement(userNavLabelDropDown,"User Navigation DropDown Button");
		return driver;
	}
	public WebDriver selectMyProfileInUserNavDropDown() {
		//dropdownHandleByValueWithClick("DropDown Option in UserNavMenu ", myProfileOption, "My Profile");
		clickElement(myProfileOption,"My Profile");
		return driver;
	}
	
	
	
}
