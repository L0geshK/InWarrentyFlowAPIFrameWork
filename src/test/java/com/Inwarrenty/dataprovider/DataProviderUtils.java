package com.Inwarrenty.dataprovider;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import com.Inwarrenty.Utils.CSVReaderUtils;
import com.Inwarrenty.dataproviderbean.UserPOJO;

public class DataProviderUtils {
	
	
	@DataProvider(name ="LoginAPITestData",parallel = true)
	public static Iterator<UserPOJO> loginAPIDataProvider() {
		return CSVReaderUtils.loadCSV("TestData/LoginCredentials.csv",UserPOJO.class);
	}

}
