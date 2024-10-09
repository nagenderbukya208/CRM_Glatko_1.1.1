package practice.test;
/**
 * test class for contact module
 * @author Nagender
 */

import org.testng.annotations.Test;

import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario : login()==> navigateContact==> createContact()==>verify
	 */
@Test
public void searchContactTest() {
	/*step 1 : login to app*/
	LoginPage lp= new LoginPage(driver);
	lp.loginToApp("url", "username", "password");
}
}
