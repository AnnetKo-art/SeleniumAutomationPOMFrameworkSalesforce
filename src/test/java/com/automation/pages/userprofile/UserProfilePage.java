package com.automation.pages.userprofile;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class UserProfilePage extends BasePage  {
	
	@FindBy(xpath ="//a[@class='contactInfoLaunch editLink']//img[@title='Edit Profile']") WebElement editProfileLink;
	@FindBy(xpath ="//input[@id='phone']") WebElement phoneInputTextBox;	
	@FindBy(xpath="//iframe[@id='contactInfoContentId']")WebElement frameEditProfile;
	@FindBy(xpath="//input[@value='Save All']") WebElement saveAllButton;
	@FindBy(xpath="//a[@id='profileTab_sfdc.ProfilePlatformOverview']") WebElement overviewEntry;
	@FindBy(xpath="//a[@class='aboutMeLaunch editLink']//img[@title='Edit Profile']") WebElement editAboutMeInfo;	
	@FindBy(xpath="//input[@id='lastName']") WebElement lastNameTextBox;
	@FindBy(xpath="//input[@value='Save All']") WebElement saveAllButtonAboutMeEdit;	
	//frame
	@FindBy(id="aboutMeContentId") WebElement aboutMeFrame;
			
	public UserProfilePage(WebDriver driver) {
		super(driver);
		
	}	
	 public WebDriver clickLink() {		
		clickElement(editProfileLink,"editElementLink");
		return driver;
	}
	
	 public WebDriver clickEntry() {			
			clickElement(overviewEntry,"overViewEntry ");
			return driver;
		}
	 
	 public WebDriver clickEditLink() {			
			clickElement(editAboutMeInfo,"editAboutMeLink ");
			return driver;
		}
	 
	 public void frameHandling() {	
	 frameHandle(frameEditProfile);		 
	 }
	 
	 public void  frameHandlingaboutMeFrame() {
		 frameHandle(aboutMeFrame);
	 }
	 
	 
	 public void dataInputPhone(String phoneNumber) {
		enterText(phoneInputTextBox, phoneNumber, "PhoneInputTextBoxEditProfile");
	 }

	 
	 public void dataInputLastName(String lastNameOption) {
		 enterText(lastNameTextBox, lastNameOption, "LastNameInputTextBoxEditProfile");		 
	 }
	 public WebDriver clickButton() {			
			clickElement(saveAllButton," saveAll button ");
			return driver; 
		}
	 
	 public WebDriver clickSaveAllBtn() {			
			clickElement(saveAllButtonAboutMeEdit," saveAll Button from My profile AboutMe block ");
			return driver; 
		}
	 
	
	 public void windowHandle() throws InterruptedException {
	 popUpWindowHandle(overviewEntry); 
	 }
	 
	 public String getTitleOfThePAge() { //void getTitleOfThePAge() {//String getTitleOfThePAge() {
			waitUntilPageLoads();
			return getPageTitle();			
		}
}
