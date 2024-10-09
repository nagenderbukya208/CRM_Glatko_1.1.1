package com.comcast.crm.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
/**
 * @author Nagender
 */
public class CreateContactTest extends BaseClass {
	@Test(groups = "smokeTest")
	public void test() throws Throwable {
		/* read testScript data from Excel file*/
		String lastName = eLib.getDataFromExcel("contact", 1, 2) + jLib.getRandomNum();
		/* Step 1 : Login to app*/
		/* Step 2 : Navigate to Contacts module*/
		HomePage hp = new HomePage(driver);
		hp.getContactLnk().click();
		/* Step 3 : Click on "Create Contacts" Button*/
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		/* Step 4 : Enter all the details & create new Contacts*/
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		/* Verify header phone number info Expected result*/
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		boolean status = actLastName.contains(lastName);
		Assert.assertEquals(status, true);
		/* logout*/
	}
	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws Throwable {
		/* read testScript data from Excel file*/
		String lastName = eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNum();
		/* Step 1 : Login to app*/
		/* Step 2 : Navigate to Contacts module*/
		HomePage hp = new HomePage(driver);
		hp.getContactLnk().click();
		/* Step 3 : Click on "Create Contacts" Button*/
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		/* Step 4 : Enter all the details & create new Contacts*/
		String startDate = jLib.getSystemDateYYYYDDMM();
		String endDate = jLib.getRequriedDateYYYYMMDD(30);

		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		/* Verify header phone number info Expected result*/
		String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actLastName.equals(lastName)) {
			System.out.println(lastName + "is created==>PASS");
		} else {
			System.out.println(lastName + "is not created==>FAIL");
		}
		/* Verify ActualStartDate info Expected result*/
		String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actStartDate, startDate);
		/* Verify ActualEndDate info Expected result*/
		String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
		sa.assertEquals(actEndDate, endDate);
		sa.assertAll();
	}
	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws Throwable {
		/* read testScript data from Excel file*/
		String orgName = eLib.getDataFromExcel("contact", 10, 2) + jLib.getRandomNum();
		String contactLastName = eLib.getDataFromExcel("contact", 10, 3);

		/* Step 1 : Login to app*/

		/* Step 2 : Navigate to Organization module*/
		driver.findElement(By.linkText("Organizations")).click();

		/* Step 3 : Click on "Create organization" Button*/
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		/* Step 4 : Enter all the details & create new organization*/
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		/* Verify header Msg Expected result*/
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		boolean contains2 = headerinfo.contains(orgName);
		Assert.assertTrue(contains2);
		/* Step 5 : Navigate to contact module*/
		driver.findElement(By.linkText("Contacts")).click();
		/* Step 6 : Click on "Create Contacts" Button*/
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		/* Step 7 : Enter all the details & create new Contacts*/
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		/* Switch to child window*/
		wLib.switchToTabOnURL(driver, "module=Accounts");
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();
		/* Switch to parent window*/
		wLib.switchToTabOnURL(driver, "Contacts&action");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		/* Verify Header phone number info Expected result*/
		headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		SoftAssert sa = new SoftAssert();
		boolean contains = headerinfo.contains(contactLastName);
		sa.assertTrue(contains);
		/* Verify Header phone number info Expected result*/
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		sa.assertNotEquals(actOrgName, orgName);
		sa.assertAll();
	}
}
