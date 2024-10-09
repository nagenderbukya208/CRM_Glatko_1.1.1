package com.comcast.crm.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.comcast.crm.listenerutility.ListenerImpClass;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
@Listeners(com.comcast.crm.listenerutility.ListenerImpClass.class)
public class CreateOrganizationTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void createOrgTest() throws Throwable {
       UtilityClassObject.getTest().log(Status.INFO, "read data from Excel");
		// read testScript data from Excel file
		String orgname = eLib.getDataFromExcel("org", 1, 2) + jLib.getRandomNum();
		// Step 1 : Login to app
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to org page");
		// Step 3 : Click on "Create organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		UtilityClassObject.getTest().log(Status.INFO, "Navigate to create org page");
		// Step 4 : Enter all the details & create new organization
		UtilityClassObject.getTest().log(Status.INFO, "Create a new org");
		driver.findElement(By.name("accountname")).sendKeys(orgname);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		UtilityClassObject.getTest().log(Status.INFO, orgname+"===> Create a new org");
		// Verify header msg Expected result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerinfo.contains(orgname)) {
			System.out.println(orgname + "is created==>PASS");
		} else {
			System.out.println(orgname + "is not created==>FAIL");
		}
		// Verify header orgname info Expected result
		String actorgname = driver.findElement(By.id("dtlview_Organization Name")).getText();
		if (actorgname.equals(orgname)) {
			System.out.println(orgname + "is created==>PASS");
		} else {
			System.out.println(orgname + "is not created==>FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrgWithIndustry() throws Throwable {

		// read testScript data from Excel file

		String orgName = eLib.getDataFromExcel("org", 4, 2) + jLib.getRandomNum();
		String induestry = eLib.getDataFromExcel("org", 4, 3);
		String type = eLib.getDataFromExcel("org", 4, 4);

		// Step 1 : Login to app

		// Step 2 : Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step 3 : Click on "Create organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// Step 4 : Enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		WebElement wbsel = driver.findElement(By.name("industry"));
		Select sel = new Select(wbsel);
		sel.selectByVisibleText(induestry);
		WebElement wbsel2 = driver.findElement(By.name("accounttype"));
		Select sel2 = new Select(wbsel2);
		sel2.selectByVisibleText(type);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// Verify the industries and type info
		String actIndusties = driver.findElement(By.id("dtlview_Industry")).getText();
		if (actIndusties.equals(induestry)) {
			System.out.println(induestry + "information is verified==>PASS");
		} else {
			System.out.println(induestry + "information is not verified==>FAIL");
		}
		String actType = driver.findElement(By.id("dtlview_Type")).getText();
		if (actType.equals(type)) {
			System.out.println(type + "information is verified==>PASS");
		} else {
			System.out.println(type + "information is not verified==>FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createOrgWithPh() throws Throwable {

		// read testScript data from Excel file

		String orgName = eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNum();
		String phoneNumber = eLib.getDataFromExcel("org", 7, 3);
		// String type= row.getCell(4).getStringCellValue();

		// Step 1 : Login to app

		// Step 2 : Navigate to Organization module
		HomePage hp = new HomePage(driver);
		hp.getOrgLink().click();
		// Step 3 : Click on "Create organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		// Step 4 : Enter all the details & create new organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.id("phone")).sendKeys(phoneNumber);

		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		// Verify Header phone number info Expected result
		String actPhoneNum = driver.findElement(By.id("dtlview_Phone")).getText();
		if (actPhoneNum.equals(phoneNumber)) {
			System.out.println(phoneNumber + "information is verifyed==>PASS");
		} else {

			System.out.println(phoneNumber + "information is Not verifyed==>FAIL");
		}
	}
}
