package com.automation.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class LoginPage extends BasePage {
	//WebDriver driver;
	
	@FindBy(id ="username") WebElement userNameElement;
	@FindBy(id = "password") WebElement password;
	@FindBy(id = "Login") WebElement loginButton;
	@FindBy(id = "error") WebElement errorMessageEnterPassword;
	
	// constructor for the public login page
	public LoginPage(WebDriver driver) { // this object should come from the automationScripts.		
		super(driver);
		//PageFactory.initElements(driver,this);// this - is the current page.
		// here we initializing,decloration elements with annotations using this function.
	}
	
	public void enterUsername(String usernamedata) {
		enterText(userNameElement, usernamedata, "username field");						
	}
	
	public void enterPassword(String passworddata) {
		enterText(password, passworddata, "password field");
		
	}
				
	public void clearPasswordField() {
		clearElement(password, "password field");
	}
	
	public WebDriver clickButton() {		
		clickElement(loginButton,"login button");
		return driver;
	}
	
	public String getTitleOfThePAge() { //void getTitleOfThePAge() {//String getTitleOfThePAge() {
		waitUntilPageLoads();
		return getPageTitle();
		//driver.getTitle();
	}
	
	public String getTextFromElement() {						
		return getTextFromWebElement(errorMessageEnterPassword, "Error message ");
	}
	

}
