package com.Inwarrenty.Constants;

public enum Problems {
	 SMARTPHONE_IS_RUNNING_SLOW(1),
	    POOR_BATTERY_LIFE(2),
	    PHONE_OR_APP_CRASHES(3),
	    SYNC_ISSUE(4),
	    MICROSD_CARD_NOT_WORKING(5),
	    OVERHEATING(6),
	    CONNECTIVITY_ISSUE(7),
	    CRACKED_SCREEN(8),
	    OTHER(9),
	    CAMERA_ISSUE(10),
	    CHARGER_NOT_WORKING(11),
	    SOFTWARE_BOOTING_ISSUE(12),
	    HEADPHONE_JACK_NOT_WORKING(13),
	    HEADPHONE_ISSUE(14),
	    RANDOM_PROBLEM(15),
	    FRONT_CAMERA_NOT_WORKING(16),
	    BATTERY_ISSUE(17),
	    SCREEN_DISPLAY_ISSUE(18),
	    APPS_NOT_DOWNLOADING(19),
	    UNRESPONSIVE_SCREEN(20),
	    BLUE_SCREEN_ERROR(21),
	    PK_TEST_PROB1(22),
	    PK_TEST_PROB2(23);
	
	int code;
	private Problems(int code) {
		this.code=code;

	}
	
	public int getproblemcode() {
		return code;
	}

}
