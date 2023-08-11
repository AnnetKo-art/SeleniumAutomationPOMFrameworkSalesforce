package com.automation.tests;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.contacts.ContactsPage;
import com.automation.pages.home.HomePage;
import com.automation.pages.login.LoginPage;
import com.automation.pages.userprofile.UserProfilePage;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
//Validation and reading the property data should be in AutomationScript.

public class AutomationScripts extends BaseTest{
	PropertiesUtility pro=new PropertiesUtility();
	Properties appProp= pro.loadFile("applicationDataProperties");	
	
	//reusable method to login
	public void loginStandardMethod() {
		log.info("inside the login_to_salesforce_testscript test method");		
		//PropertiesUtility pro=new PropertiesUtility();
		//Properties appProp= pro.loadFile("applicationDataProperties");	
		String userId=appProp.getProperty("login.valid.userid");
		String password=appProp.getProperty("login.valid.password");				
		String expectedTitle = "Login | Salesforce";		
		//Validation part		
		LoginPage loginpage=new LoginPage(driver);
		String actualTitle= loginpage.getTitleOfThePAge();		
		Assert.assertEquals(actualTitle, expectedTitle);
		loginpage.enterUsername(userId);						
		loginpage.enterPassword(password);
		driver= loginpage.clickButton();										
		
	}
//------TEST 1-----LOGIN PAGE--- 
//Launch salesforce,check PageTitle, enter username,clear password field, enter password, click LoginButton, check title of Homepage
	
	
	@Test (priority=1)//(enabled=false ) 
	public void login_to_salesforce_login_success_testscript() throws InterruptedException {				
		log.info("inside the login_to_salesforce_testscript test method");		
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");	
		String userId=appProp.getProperty("login.valid.userid");       //("login.valid.userid");adminannet@gmail.com
		String password=appProp.getProperty("login.valid.password");				
		String expectedTitle = "Login | Salesforce";		
		//Validation part		
		LoginPage loginpage=new LoginPage(driver);
		String actualTitle= loginpage.getTitleOfThePAge();		
		Assert.assertEquals(actualTitle, expectedTitle);
		loginpage.enterUsername(userId);		
		loginpage.clearPasswordField();		
		loginpage.enterPassword(password);
		driver= loginpage.clickButton();								
		String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
		HomePage homepage=new HomePage(driver);				
		String actualTitleHomePage=homepage.getTitleOfThePAge();
		Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);																		
	}
	
	
//------TEST 2-----LOGIN PAGE---Launch salesforce,check PageTitle, enter username,clear password field,
//click LoginButton, check error message
	
	@Test (priority=2)//(enabled=false ) 
	public void login_to_salesforce_no_password_error_message_check_testscript() throws InterruptedException {
		log.info("inside the login_to_salesforce_testscript test method");		
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");	
		String userId=appProp.getProperty("login.valid.userid");
		//String password=appProp.getProperty("login.valid.password");				
		String expectedTitle = "Login | Salesforce";		
		//Validation part		
		LoginPage loginpage=new LoginPage(driver);
		String actualTitle= loginpage.getTitleOfThePAge();		
		Assert.assertEquals(actualTitle, expectedTitle);
		loginpage.enterUsername(userId);		
		loginpage.clearPasswordField();
		driver= loginpage.clickButton();
		String actualErrorMessage=loginpage.getTextFromElement();
		String expactedErrorMessage="Please enter your password.";
		Assert.assertEquals(actualErrorMessage, expactedErrorMessage);
		
	}

	
//--------TEST3------CONTACTS PAGE---- 
//Login to salesforce, click Contacts Tub,  Click New Button,
//Enter Last Name, Enter Account Name,Click on Save
	
	@Test (priority=3)//(enabled=false ) 
	public void create_new_contact_to_salesforce_testscript() throws InterruptedException {		
		loginStandardMethod();
		String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
		HomePage homepage=new HomePage(driver);				
		String actualTitleHomePage=homepage.getTitleOfThePAge();
		Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);		
		homepage.clickLink();
		ContactsPage contactspage=new ContactsPage(driver);	
		contactspage.popUpWindowHandle();
		contactspage.newButtonClick();		
		String lastNameOption=appProp.getProperty("contacts.lastname1"); //changable data
		String accountNameOption=appProp.getProperty("contacts.accountname3");//changable data
		contactspage.lastNameInsert(lastNameOption);
		contactspage.accountNameInsert(accountNameOption);
		contactspage.clickButton();
						
	}
	
//--------TEST4------CONTACTS PAGE---- 
//Login to salesforce, click Contacts Tub,  Click on Create New View hyperlink ,
// Enter the View Unique Name(Unique Name : EFGH), Click on Save
		
		@Test (priority=4)//(enabled=false )
		public void create_new_view_in_contacts_to_salesforce_testscript() throws InterruptedException {
			loginStandardMethod();
			String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
			HomePage homepage=new HomePage(driver);				
			String actualTitleHomePage=homepage.getTitleOfThePAge();
			Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);		
			homepage.clickLink();
			ContactsPage contactspage=new ContactsPage(driver);	
			contactspage.popUpWindowHandle();			
			contactspage.clickCreateNewViewLink();
			String uniqueNameContacts=appProp.getProperty("contacts.uniquename2");
			contactspage.uniqueNameInsert(uniqueNameContacts);
			contactspage.scrollDownCreateNewView();
			contactspage.clickSaveButtonFromCreateNewView();																
		}
	
	
//--------TEST5------CONTACTS PAGE---- 
//Login to salesforce, click Contacts Tub,  Click on Create New View hyperlink ,
// Enter the View Unique Name(Unique Name : EFGH),Name ABCD,  Click Cancel
				
				@Test (priority=5)//(enabled=false ) 
				public void cancelClickInCreateNewContacts_in_contacts_to_salesforce_testscript() throws InterruptedException {
					loginStandardMethod();
					String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
					HomePage homepage=new HomePage(driver);				
					String actualTitleHomePage=homepage.getTitleOfThePAge();
					Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);		
					homepage.clickLink();
					
					ContactsPage contactspage=new ContactsPage(driver);	
					contactspage.popUpWindowHandle();			
					contactspage.clickCreateNewViewLink();
					String uniqueNameContacts=appProp.getProperty("contacts.uniquename4");					
					contactspage.uniqueNameInsert(uniqueNameContacts);
					
					String nameContacts=appProp.getProperty("contacts.name1");
					contactspage.viewNameInsertData(nameContacts);
					contactspage.scrollDownCreateNewView();
					contactspage.clickCancelButtonFromCreateNewView();
					//Thread.sleep(3000);
					contactspage.dropDownHandleContactsPage(nameContacts);
				}
											
		
//--------TEST6------CONTACTS PAGE----
//Login to salesforce, click Contacts Tub, Select "Recently Modified"
				
	@Test (priority=6)//(enabled=false )
	public void recentlyCreatedContactCheck_to_salesforce_testscript() throws InterruptedException {
	loginStandardMethod();
	String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
	HomePage homepage=new HomePage(driver);				
	String actualTitleHomePage=homepage.getTitleOfThePAge();
	Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);		
	homepage.clickLink();
	ContactsPage contactspage=new ContactsPage(driver);	
	contactspage.popUpWindowHandle();	
	contactspage.selectDropDownRecentlyCreated();
	
	}
		
	
//--------TEST7 ------USERPROFILE PAGE----
//Login to salesforce, click UserMenu, Select "My Profile" and click,click Edit, add phone number, click SaveAll button
	@Test  (priority=7) //(enabled=false )
	public void phoneAddInUserProfilePage_to_salesforce_testscript() throws InterruptedException  {
		loginStandardMethod();						
		String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
		HomePage homepage=new HomePage(driver);				
		String actualTitleHomePage=homepage.getTitleOfThePAge();
		Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);
		homepage.dropDownClick();
		homepage.selectMyProfileInUserNavDropDown();
		UserProfilePage userprofilepage= new UserProfilePage(driver);		
		userprofilepage.clickLink();
		userprofilepage.frameHandling();
		String phone=appProp.getProperty("userprofile.phonenumber3");
		userprofilepage.dataInputPhone(phone);
		userprofilepage.clickButton(); 
		
	}
	
//--------TEST8 ------USERPROFILE PAGE----
//Login to salesforce, click UserMenu, Select "My Profile" and click,click Overview,Click Edit Profile, add Last Name, click SaveAll button
		
	@Test  (priority=8) //(enabled=false )
		public void myProfilePageOverviewEditLastName_to_salesforce_testscript() throws InterruptedException  {
			loginStandardMethod();							
			String expectedTitleHomePage = "Home Page ~ Salesforce - Developer Edition";					
			HomePage homepage=new HomePage(driver);				
			String actualTitleHomePage=homepage.getTitleOfThePAge();
			System.out.println("Actual title of a HOmepage is" + actualTitleHomePage);
			Assert.assertEquals(actualTitleHomePage,expectedTitleHomePage);
			homepage.dropDownClick();		
			driver=homepage.selectMyProfileInUserNavDropDown();			
			UserProfilePage userprofilepage= new UserProfilePage(driver);						
			userprofilepage.windowHandle();//clickEditLink();
			Thread.sleep(3000);
			userprofilepage.clickEditLink();
			Thread.sleep(2000);
			userprofilepage.frameHandlingaboutMeFrame();
			Thread.sleep(2000);
			String lastName=appProp.getProperty("contacts.lastname3");
			userprofilepage.dataInputLastName(lastName);
			userprofilepage.clickSaveAllBtn(); 
			
			
		}				
}
