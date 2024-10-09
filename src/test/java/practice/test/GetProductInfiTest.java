package practice.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfiTest {
@Test(dataProvider = "getData")
public void getProductInfoTest(String brandName, String productName) {
	WebDriver driver= new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.manage().window().maximize();
	driver.get("https://www.amazon.in/");
	// Search for Product
	driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName,Keys.ENTER);
	String xpath="//span[contains(text(),'"+productName+"')]/../../../../descendant::span[@class=\'a-price-whole\']";
  String price= driver.findElement(By.xpath(xpath)).getText();
  System.out.println(price);
  driver.quit();
}
@DataProvider
public Object[][] getData() throws Throwable{
	ExcelUtility eLib= new ExcelUtility();
int rowcount=eLib.getRowcount("Product");
	Object[][] objArr= new Object[rowcount][2];
	for(int i=0;i<rowcount;i++) {
	objArr[i][0]=eLib.getDataFromExcel("Product", i+1, 0);
	objArr[i][1]=eLib.getDataFromExcel("Product", i+1, 1);
	}
	
	return objArr;
}
}
