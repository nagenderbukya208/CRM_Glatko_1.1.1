package objectrepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateContactWithOrgTest 
{
public static void main(String[] args) throws Throwable 
{
	//read common data from properties file
		FileUtility fLib= new FileUtility();
		ExcelUtility eLib= new ExcelUtility();
		JavaUtility jLib= new JavaUtility();
		WebDriverUtility wLib= new WebDriverUtility();
		String Browser =fLib.getDataFromPropertiesFile("browser");
		String URL =fLib.getDataFromPropertiesFile("url");
		String USERNAME =fLib.getDataFromPropertiesFile("username");
	    String PASSWORD =fLib.getDataFromPropertiesFile("password");
	  //read testScript data from Excel file
		String orgName=eLib.getDataFromExcel("contact", 10, 2)+ jLib.getRandomNum();
		String contactLastName= eLib.getDataFromExcel("contact", 10, 3)+ jLib.getRandomNum();            
		WebDriver driver= null;
		if(Browser.equals("chrome")){
		driver=new ChromeDriver();  }
		else if(Browser.equals("firefox")){
		driver=new FirefoxDriver();}
		else if(Browser.equals("edge")){
		driver=new EdgeDriver();}
		else {
		driver=new ChromeDriver();
		}
		wLib.waitForPageToLoad(driver);
		driver.get(URL);
	 // Step 1 : Login to app
	    LoginPage lp= new LoginPage(driver);
		lp.loginToApp(USERNAME, PASSWORD);
	 // Step 2 : Navigate to Organization module
		HomePage hp= new HomePage(driver);
		hp.getOrgLink().click();
	// Step 3 : Click on "Create organization" Button
		OrganizationsPage op= new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
	// Step 4 : Enter all the details & create new organization
	   CreatingNewOrganizationPage cop= new CreatingNewOrganizationPage(driver);
	   cop.createOrg(orgName);
	// Verify header Msg Expected result
	   OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String actorgName= oip.getHeaderMsg().getText();
		if(actorgName.contains(orgName)) {
			System.out.println(orgName + "is created==>PASS");
		}else
		{
			System.out.println(orgName + "is Not created==>PASS");
		}
	// Step 5 : Navigate to contact module
	   hp.getContactLnk().click();
	// Step 6 : Click on "Create Contacts" Button
	  ContactPage cp= new ContactPage(driver);
	  cp.getCreateNewContactBtn().click();
   // Step 7 : Enter all the details & create new Contacts
		CreatingNewContactPage ccp= new CreatingNewContactPage(driver);
		ccp.getLastNameEdt().sendKeys(contactLastName);
		ccp.getLoopUpBt().click();
		
  // Switch to child window
	wLib.switchToTabOnURL(driver, "module=Accounts");
    driver.findElement(By.name("search_text")).sendKeys(orgName);
	driver.findElement(By.name("search")).click();
	
	
	driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
 // Switch to parent window
	wLib.switchToTabOnURL(driver, "Contacts&action");
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click(); 
 // Verify Header lastName info Expected result
	String headerinfo=driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
	 if(headerinfo.contains(contactLastName))
	 {
	 System.out.println(contactLastName+"information is verifyed==>PASS");
	 }else {		  
	 System.out.println(contactLastName+"information is Not verifyed==>FAIL");
	 }
	// Verify Header orgName info Expected result
			 String  actOrgName=driver.findElement(By.id("mouseArea_Organization Name")).getText();
			 if(actOrgName.trim().equals(orgName))
			 {
		     System.out.println(orgName+"information is verifyed==>PASS");
			 }else {  
			 System.out.println(orgName+"information is Not verifyed==>FAIL");
			      }
			  driver.quit();
  }
}
