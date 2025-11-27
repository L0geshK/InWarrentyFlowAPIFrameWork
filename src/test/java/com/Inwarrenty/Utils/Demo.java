package com.Inwarrenty.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.Inwarrenty.request.model.UserCredentials;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Demo {
	public static void main(String[] args) throws StreamReadException, DatabindException, IOException {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/demo.json");
		ObjectMapper object = new ObjectMapper();
		UserCredentials[] UserCredentials = object.readValue(is, UserCredentials[].class);
		List<UserCredentials>UserCredentialslist = Arrays.asList(UserCredentials);
		
		UserCredentialslist.iterator();
		
		
	}
	

	 
	 
	
	

}
