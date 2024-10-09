package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(className = "dvHeaderText")
	private WebElement headerMsg;
	
   @FindBy(xpath = "//span[@id='dtlview_Industry']")
   private WebElement IndustryMsg;
    
    @FindBy(id = "dtlview_Type")
    private WebElement typeMsg;
    
	public WebElement getIndustryMsg() {
		return IndustryMsg;
	}
	public WebElement getTypeMsg() {
		return typeMsg;
	}
	public WebElement getHeaderMsg() {
		return headerMsg;
	}
   }

