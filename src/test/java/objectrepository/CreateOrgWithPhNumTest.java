package objectrepository;

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
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class CreateOrgWithPhNumTest 
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
	    String orgName= eLib.getDataFromExcel("org", 7, 2) + jLib.getRandomNum();
	    String phoneNumber= eLib.getDataFromExcel("org", 7, 3);
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
		 HomePage hp=new HomePage(driver);
		 hp.getOrgLink().click();
	 // Step 3 : Click on "Create organization" Button
		 OrganizationsPage op= new OrganizationsPage(driver);
		 op.getCreateNewOrgBtn().click();
	 // Step 4 : Enter all the details & create new organization
		 CreatingNewOrganizationPage cop= new CreatingNewOrganizationPage(driver);
		 cop.getOrgNameEdt().sendKeys(orgName);
		 cop.getPhoneNumEdt().sendKeys(phoneNumber);
		 cop.getSavebtn().click();
	 // Verify Header phone number info Expected result
         OrganizationInfoPage oip= new OrganizationInfoPage(driver);
     String actOrgName =oip.getHeaderMsg().getText();
     if(actOrgName.contains(orgName)) {
    	 System.out.println(orgName + "Org Name is verified ==>PASS");
     }else
     {
    	 System.out.println(orgName + "Org Name is Not verified ==>FAIL");

     }
    // Step 5 : logout
     hp.logout();
     driver.quit();
  }
}

