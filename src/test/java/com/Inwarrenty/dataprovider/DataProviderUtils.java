package com.Inwarrenty.dataprovider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.Inwarrenty.Utils.CSVReaderUtils;
import com.Inwarrenty.Utils.CreateJobBeanMapper;
import com.Inwarrenty.Utils.FakerDataGenerator;
import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.Inwarrenty.dataproviderbean.UserPOJO;
import com.Inwarrenty.request.model.CreateJobAPIPayload;

public class DataProviderUtils {
	
	
	@DataProvider(name ="LoginAPITestData",parallel = true)
	public static Iterator<UserPOJO> loginAPIDataProvider() {
		return CSVReaderUtils.loadCSV("TestData/LoginCredentials.csv",UserPOJO.class);
	}

	@DataProvider(name ="createJobAPIDataProvider",parallel = true)
	public static Iterator<CreateJobAPIPayload> createJobAPIDataProvider() {
		 Iterator<CreateJobBean>iterator=CSVReaderUtils.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
		 List<CreateJobAPIPayload> payloadlist = new ArrayList<CreateJobAPIPayload>();
		 CreateJobBean tempbean;
		 CreateJobAPIPayload tempCreateJob;
		 while(iterator.hasNext()) {
			 tempbean = 	iterator.next();
			 tempCreateJob=CreateJobBeanMapper.mapper(tempbean);
			 
			 payloadlist.add(tempCreateJob);
		 }
		 return payloadlist.iterator();
	}
	
	
	@DataProvider(name ="createJobAPIFakerDataProvider",parallel = true)
	public static Iterator<CreateJobAPIPayload> createJobAPIFakerDataProvider() {
		String fakercount = System.getProperty("Faker","5");
		int fakercountint = Integer.parseInt(fakercount);
		 Iterator<CreateJobAPIPayload> CreateJobAPIPayload= FakerDataGenerator.generateFakeCreateJobData(fakercountint);
		 return CreateJobAPIPayload;
		
	}
	
}
