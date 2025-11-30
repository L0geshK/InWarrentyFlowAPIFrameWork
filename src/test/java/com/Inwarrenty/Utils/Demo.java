package com.Inwarrenty.Utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;

public class Demo {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
//		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");
//		ObjectMapper object = new ObjectMapper();
//		UserCredentials[] UserCredentials = object.readValue(is, UserCredentials[].class);
//		List<UserCredentials>UserCredentialslist = Arrays.asList(UserCredentials);
//		
//		UserCredentialslist.iterator();
//		
		
	Iterator<CreateJobBean>datalist=ExcelReaderUtils.loadTestDataUsingPoiji(
	            "TestData/TestData.xlsx",
	            "CreateJobAPI",
	             CreateJobBean.class);
	while(datalist.hasNext()) {
		System.out.println(datalist.next());
	}
		
	}
	

	 
	 
	
	

}
