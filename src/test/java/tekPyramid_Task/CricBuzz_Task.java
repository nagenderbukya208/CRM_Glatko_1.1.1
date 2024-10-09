package tekPyramid_Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;

public class CricBuzz_Task {
public static void main(String[] args) throws Throwable {
	Driver d=new Driver();
	DriverManager.registerDriver(d);
	
	Connection conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/myspace", "root", "admin");
	Statement stat = conn.createStatement();
	WebDriver driver= new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	driver.get("https://www.cricbuzz.com/live-cricket-scorecard/87542/ned-vs-nep-7th-match-group-d-icc-mens-t20-world-cup-2024");
//	driver.findElement(By.xpath("//a[text()='MATCHES']")).click();
//	driver.findElement(By.xpath("//a[text()='Recent']")).click();
//	driver.findElement(By.xpath("//a[@class=\"cb-lv-scrs-well cb-lv-scrs-well-complete\" and @href=\"/live-cricket-scores/87542/nep-vs-ned-7th-match-group-d-icc-mens-t20-world-cup-2024\"]")).click();
//    driver.findElement(By.xpath("//a[@title=\"Netherlands vs Nepal, 7th Match, Group D Scorecard\"]")).click();
	List<WebElement> elements = driver.findElements(By.xpath("//span[contains(.,'Nepal Innings')]/../../div[@class='cb-col cb-col-100 cb-scrd-itms']/div[@class='cb-col cb-col-8 text-right text-bold']"));
	String names="//span[contains(.,'Nepal Innings')]/../following-sibling::div[@class=\"cb-col cb-col-100 cb-scrd-itms\"]/div[@class=\"cb-col cb-col-25 \"]/a";
	String strikerate="//span[contains(.,'Nepal Innings')]/../following-sibling::div[@class=\"cb-col cb-col-100 cb-scrd-itms\"]/div[@class=\"cb-col cb-col-8 text-right\" and contains(.,'.')]";
	List<WebElement> name= driver.findElements(By.xpath(names));
	List<WebElement> SR= driver.findElements(By.xpath(strikerate));
	int i=0;
	for (WebElement runs : elements) {
		 String run = runs.getText();
		//System.out.println(run);
		int n= Integer.parseInt(run);
		//System.out.println(n);
		if(n<=30)
		{
		     String pname = name.get(i).getText();
		     String psr = SR.get(i).getText();
			System.out.println(pname+" "+run+" "+psr);
			stat.executeUpdate("insert into cricbuzz2 values('"+pname+"','"+run+"','"+psr+"')");

		}
		i++;
	}
	ResultSet set = stat.executeQuery("select * from cricbuzz2 where runs > 10");
	while(set.next())
	{
	System.out.println(set.getString(1)+ " "+set.getString(2)+" "+set.getString(3));
	}
	conn.close();
	driver.quit();
}
}
