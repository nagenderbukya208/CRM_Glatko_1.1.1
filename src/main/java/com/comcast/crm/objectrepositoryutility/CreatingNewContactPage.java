package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreatingNewContactPage {
	WebDriver driver;
	public CreatingNewContactPage(WebDriver driver) {
	this.driver=driver;
	PageFactory.initElements(driver,this);
	}
	@FindBy(name = "lastname")
	private WebElement lastNameEdt;
	
	@FindBy(xpath = "//input[@title=\"Save [Alt+S]\"]")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//input[@name='account_name']/../../td[@align='left']/img")
	private WebElement LoopUpBt;
	@FindBy(name = "support_start_date")
	private WebElement supportDDSk;
	@FindBy(name = "support_end_date")
	private WebElement supportEndDDSk;
	public WebElement getSupportEndDDSk() {
		return supportEndDDSk;
	}
	public WebElement getSupportDDSk() {
		return supportDDSk;
	}
	public WebElement getLoopUpBt() {
		return LoopUpBt;
	}
	public WebElement getLastNameEdt() {
		return lastNameEdt;
	}
	public WebElement getSaveBtn() {
		return saveBtn;
	}
	public void createContact(String lastname)
	{
		lastNameEdt.sendKeys(lastname);
		saveBtn.click();
	}
	public void createStartDD(String startDate)
	{
		supportDDSk.clear();
		supportDDSk.sendKeys(startDate);
	}
	public void createEndDD(String enddate)
	{
		supportEndDDSk.clear();
		supportEndDDSk.sendKeys(enddate);
	}
}
