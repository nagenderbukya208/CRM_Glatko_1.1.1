package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreatingNewOrganizationPage {
	WebDriver driver;
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
@FindBy(name="accountname")
private WebElement orgNameEdt;
@FindBy(xpath = "//input[@title='Save [Alt+S]']")
private WebElement saveBtn;
@FindBy(name="industry")
private WebElement industryDD;
@FindBy(id = "phone")
private WebElement phoneNumEdt;
@FindBy(name = "accounttype")
private WebElement typeDD;
public WebElement getTypeDD() {
	return typeDD;
}
public WebElement getPhoneNumEdt() {
	return phoneNumEdt;
}
public WebElement getOrgNameEdt() {
	return orgNameEdt;
}
public WebElement getSavebtn() {
	return saveBtn;
}
public void createOrg(String orgname) {
	orgNameEdt.sendKeys(orgname);
	saveBtn.click();
}
public void createOrg(String orgname, String industry, String type) {
	orgNameEdt.sendKeys(orgname);
	Select sel= new Select(industryDD);
	sel.selectByVisibleText(industry);
	Select sel2= new Select(typeDD);
	sel2.selectByVisibleText(type);
	saveBtn.click();
 }
public WebElement getIndustryDD() {
	return industryDD;
}

}

