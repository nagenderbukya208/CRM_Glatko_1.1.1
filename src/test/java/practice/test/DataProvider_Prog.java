package practice.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class DataProvider_Prog {
@Test(dataProvider = "getdata")
public void getdatapro(String product, String price) {
	System.out.println("product "+product+" price "+price);
}
@DataProvider
public Object[][] getdata() throws Throwable {
	ExcelUtility eLib= new ExcelUtility();
	int row =eLib.getRowcount("Sheet1");
	System.out.println(row);
	Object[][] obj= new Object[row][2];
	for(int i=0;i<row;i++)
	{
		obj[i][0]=eLib.getDataFromExcel("Sheet1", i+1, 0);
		obj[i][1]=eLib.getDataFromExcel("Sheet1", i+1, 1);
	}
	return obj;
}
}
