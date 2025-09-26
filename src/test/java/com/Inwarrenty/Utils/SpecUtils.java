package com.Inwarrenty.Utils;

import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static org.hamcrest.Matchers.lessThan;


import java.io.IOException;

import com.Inwarrenty.POJO.UserCredentials;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
	
	
	
	public  static RequestSpecification getRequestSpec() throws IOException {
		 RequestSpecification request=new RequestSpecBuilder()
		     .setBaseUri(getProperty("BASE_URL"))
		     .setContentType(ContentType.JSON)
		     .setAccept(ContentType.JSON)
		     .log(LogDetail.URI)
		     .log(LogDetail.HEADERS)
		     .log(LogDetail.METHOD)
		     .log(LogDetail.BODY)
		     .build();
		 return request;
	}

	public  static RequestSpecification getRequestSpec(UserCredentials userCredentials ) throws IOException {
		 RequestSpecification request=new RequestSpecBuilder()
		     .setBaseUri(getProperty("BASE_URL"))
		     .setContentType(ContentType.JSON)
		     .setAccept(ContentType.JSON)
		     .setBody(userCredentials)
		     .log(LogDetail.URI)
		     .log(LogDetail.HEADERS)
		     .log(LogDetail.METHOD)
		     .log(LogDetail.BODY)
		    
		     .build();
		 return request;
	}
	public  static ResponseSpecification getResponceSpec()  {
		    ResponseSpecification     response=   new ResponseSpecBuilder()
		             .expectContentType(ContentType.JSON)
		             .expectStatusCode(200)
		             .expectResponseTime(lessThan(1000L))
		             .log(LogDetail.ALL)
		             .build();
				return response;
				
		     
		    
		
	}
	
	
}
