package com.comcast.crm.objectrepositoryutility;
//Rule 1: Create a separte java class

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
/**
 * @author Nagender
 * Contains Login page elements & Business lib like login()
 */
public class LoginPage extends WebDriverUtility{
//Rule 2: Object creation
	WebDriver driver;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(name="user_name")
private	WebElement usernameEdt;
	
	@FindBy(name="user_password")
private	WebElement passwordEdt;
	
	@FindBy(id="submitButton")
private	WebElement loginBtn;
	//Rule 3: Object initialization
//Rule 4: Object Encapsulation

	public WebElement getUsernameEdt() {
		return usernameEdt;
	}

	public WebElement getPasswordEdt() {
		return passwordEdt;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	//Rule 5: Provide Action
	/**
	 * Login to application based on username , password arguments
	 * 
	 * @param username
	 * @param password
	 */
	public void loginToApp(String username, String password) {
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	/**
	 * Login to application based on username , password , url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	public void loginToApp(String url, String username, String password) {
		waitForPageToLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		usernameEdt.sendKeys(username);
		passwordEdt.sendKeys(password);
		loginBtn.click();
	}
	public void logout() {
		Actions act= new Actions(driver);
		act.moveToElement(loginBtn);
	}
}
