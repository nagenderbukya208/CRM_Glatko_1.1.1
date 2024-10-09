package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
public int getRandomNum() {
	Random random= new Random();
	int randomNumber=random.nextInt(5000);
	return randomNumber;
}
public String getSystemDateYYYYDDMM() {
	Date dateonj= new Date();
	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
	String date=sdf.format(dateonj);
	return date;
}
public String getRequriedDateYYYYMMDD(int days) {
	 
	Date cdate = new Date();
	
		SimpleDateFormat sim= new SimpleDateFormat("yyyy-MM-dd");
	  	   sim.format(cdate);
	    Calendar cal= sim.getCalendar();
	    cal.add(Calendar.DAY_OF_MONTH,days);
	    String reqDate= sim.format(cal.getTime());
	    return reqDate;
}
}
