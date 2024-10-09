package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
public class InvoiceTest extends BaseClass {
@Test
public void createInvoiceTest() {
	System.out.println("Execute createInvoiceTest");
	String actTitle= driver.getTitle();
	Assert.assertEquals(actTitle, "Login");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
@Test
public void createInvoiceWithContactTest() {
	System.out.println("Execute createInvoiceWithContactTest");
	System.out.println("step-1");
	System.out.println("step-2");
	System.out.println("step-3");
	System.out.println("step-4");
}
}
