package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	public HomePage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
@FindBy(xpath ="//a[@href='index.php?module=Accounts&action=index' ]")
 private WebElement orgLink;

@FindBy(xpath ="//a[@href='index.php?module=Contacts&action=index']")
private WebElement contactLnk;
@FindBy(linkText = "Campaigns")
private WebElement campaignLnk;
@FindBy(linkText = "More")
private WebElement moreLnk;
@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']")
private WebElement adminImg;
@FindBy(linkText = "Sign Out")
private WebElement signOutLnk;
public WebElement getSignOutLnk() {
	return signOutLnk;
}
public WebElement getOrgLink() {
	return orgLink;
}
public WebElement getContactLnk() {
	return contactLnk;
}
public WebElement getCampaignLnk() {
	return campaignLnk;
}
public WebElement getMoreLnk() {
	return moreLnk;
}
public void navigateToCampaignPage() {
Actions act= new Actions(driver);
act.moveToElement(moreLnk).perform();
campaignLnk.click();	
 }
public void logout() {
	Actions act= new Actions(driver);
	act.moveToElement(adminImg).perform();
	signOutLnk.click();
}
}
