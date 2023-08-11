package com.automation.pages.contacts;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.automation.pages.base.BasePage;

public class ContactsPage extends BasePage {
	@FindBy(xpath ="(//input[@title='New'])[1]") WebElement newButtonContacts;
	@FindBy(id="tryLexDialogX")WebElement closeButtonOfPopUpWindow;
	@FindBy(xpath ="//input[@id='name_lastcon2']") WebElement lastNameTextBox;
	@FindBy(xpath ="//input[@id='con4']") WebElement accountNameTextBox;
	@FindBy(xpath ="//td[@id='bottomButtonRow']//input[@title='Save']") WebElement buttonSaveNewContact;
	@FindBy(xpath ="//select[@id='hotlist_mode']") WebElement selectDropDownRecentlyModified;	
	@FindBy(linkText ="Create New View") WebElement createNewViewLink;
	@FindBy(xpath ="//input[@id='fname']") WebElement inputUniqueNameTextBox;		
	@FindBy(xpath ="//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[1]")WebElement saveButtonCreateNewView;
	@FindBy(xpath ="//*[@id=\"fname\"]") WebElement ViewNameTextBox;
	@FindBy(xpath ="//*[@id=\"editPage\"]/div[3]/table/tbody/tr/td[2]/input[2]") WebElement canceleButtonCreateNewView;	
	@FindBy(xpath ="//*[@id=\"fcf\"]") WebElement viewDropDownContactsPage;
				
	public ContactsPage(WebDriver driver) {
		super(driver);		
	}		
		public WebDriver newButtonClick() {
			//loginButton.click();
			clickElement(newButtonContacts,"New buttono on Contacts page");
			return driver;
		}
	
		public  void popUpWindowHandle() throws InterruptedException {
			popUpWindowHandle(closeButtonOfPopUpWindow);
		}		
		public void lastNameInsert(String lastNameOption) {
			enterText(lastNameTextBox, lastNameOption, "TextBox To Input LastName " );			
		}
		
		public void uniqueNameInsert(String lastNameOption) {
			enterText(inputUniqueNameTextBox, lastNameOption, "TextBox To Input UniqueName " );
			
		}
		
		public void accountNameInsert(String accountNameOption) {
			enterText(accountNameTextBox, accountNameOption, "Account Name Textbox " );
		}
		
		public void viewNameInsertData(String name) {
			enterText(ViewNameTextBox, name, "View Name Textbox " );
		}
		
		public WebDriver clickButton() {			
			clickElement(buttonSaveNewContact, "Save button from Contacts Page ");
			return driver;
		}
		
		public WebDriver clickSaveButtonFromCreateNewView() {			
			clickElement(saveButtonCreateNewView, "Save button from Create New View Contacts Page ");
			return driver;
		}
		
		public WebDriver clickCancelButtonFromCreateNewView() {			
			clickElement(canceleButtonCreateNewView, "Cancel button from Create New View Contacts Page ");
			return driver;
		}
		
		public void dropDownHandleContactsPage(String viewNameData) {
			getDropDownList(viewDropDownContactsPage,viewNameData );
		}
				
		public void scrollDownCreateNewView() {
			ScrollDownMethod();
		}
		
		public WebDriver clickCreateNewViewLink() {
			clickElement(createNewViewLink, "Create New View Link ");
			return driver;
		}
		
		public void pageRefresh() {
			refreshPage();
		}
						
		public void selectDropDownRecentlyCreated() {
			dropdownHandleByValue(" View DropDown Option in Recently Modified ", selectDropDownRecentlyModified, "Recently Created");
		}
		
		
		

		
}
