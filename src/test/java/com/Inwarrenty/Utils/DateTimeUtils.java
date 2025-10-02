package com.Inwarrenty.Utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtils {
	
	
	private DateTimeUtils() {
		
	}

	public static String getTimeWithDaysAgo(int days) {
	 return Instant.now().minus(10,ChronoUnit.DAYS).toString();
		
		
	}
	
	
}
