package com.Inwarrenty.Utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.Logger;

import io.qameta.allure.Step;

public class DateTimeUtils {
	private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(DateTimeUtils.class);

	
	
	private DateTimeUtils() {
		
	}

	@Step("Load the TimeWithDaysAgo with days")
	public static String getTimeWithDaysAgo(int days) {
		log.info("Enter");
	 return Instant.now().minus(10,ChronoUnit.DAYS).toString();
		
		
	}
	
	
}
