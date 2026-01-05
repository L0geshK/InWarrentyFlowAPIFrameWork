package com.Inwarrenty.Utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.LogManager;

import org.apache.logging.log4j.Logger;
import com.Inwarrenty.dataproviderbean.CreateJobBean;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;


public class Demo {
	
	private static Class<?> java;
	private static org.apache.logging.log4j.core.Logger log  = (org.apache.logging.log4j.core.Logger) org.apache.logging.log4j.LogManager.getLogger(Demo.class);

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
		
	
	
	
	int a =100;
	int b=20;
	System.out.println(a+b);
	log.info("alkls");
	}
	

	 
	 
	
	

}
