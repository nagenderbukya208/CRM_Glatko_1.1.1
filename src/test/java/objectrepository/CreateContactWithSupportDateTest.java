package objectrepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.ContactPage;
import com.comcast.crm.objectrepositoryutility.CreatingNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class CreateContactWithSupportDateTest
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
	  //generate the random number
	  //read testScript data from Excel file

	    String lastName=eLib.getDataFromExcel("contact", 4, 2) + jLib.getRandomNum() ;
        // achieving Polymorphism program
        WebDriver driver=null;
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
		  // Step 2 : Navigate to Contacts module
		 HomePage hp= new HomePage(driver);
		 hp.getContactLnk().click(); 
		  // Step 3 : Click on "Create Contacts" Button
		ContactPage cp = new ContactPage(driver);
		cp.getCreateNewContactBtn().click();
		  // Step 4 : Enter all the details & create new Contacts
	      String startDate=jLib.getSystemDateYYYYDDMM();
	      String endDate=jLib.getRequriedDateYYYYMMDD(30);

		CreatingNewContactPage ccp= new CreatingNewContactPage(driver);
		ccp.getLastNameEdt().sendKeys(lastName);
		 ccp.createStartDD(startDate);
		 ccp.createEndDD(endDate);
		 ccp.getSaveBtn().click();
		  // Verify header lastName info Expected result
		 String actLastName= driver.findElement(By.id("dtlview_Last Name")).getText();
		 if(actLastName.equals(lastName)) {
			 System.out.println(lastName+"is created==>PASS");
		 }else {
			 System.out.println(lastName+"is not created==>FAIL");
		 }
		  // Verify ActualStartDate info Expected result
		 String actStartDate= driver.findElement(By.id("dtlview_Support Start Date")).getText();
		 if(actStartDate.equals(startDate)) {
			 System.out.println(startDate +"is created==>PASS");
		 }else {
			 System.out.println(startDate +"is not created==>FAIL");
		 }
		  // Verify ActualEndDate info Expected result
		 String actEndDate= driver.findElement(By.id("dtlview_Support End Date")).getText();
		 if(actEndDate.equals(endDate)) {
			 System.out.println(endDate +"is created==>PASS");
		 }else {
			 System.out.println(endDate +"is not created==>FAIL");
		 }
    // logout
	 driver.quit();
}
}
