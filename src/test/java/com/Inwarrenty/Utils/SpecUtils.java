package com.Inwarrenty.Utils;

import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;
import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static org.hamcrest.Matchers.lessThan;


import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
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

	public  static RequestSpecification getRequestSpec(Object Payload ) throws IOException {
		 RequestSpecification request=new RequestSpecBuilder()
		     .setBaseUri(getProperty("BASE_URL"))
		     .setContentType(ContentType.JSON)
		     .setAccept(ContentType.JSON)
		     .setBody(Payload)
		     .log(LogDetail.URI)
		     .log(LogDetail.HEADERS)
		     .log(LogDetail.METHOD)
		     .log(LogDetail.BODY)
		    
		     .build();
		 return request;
	}
	public  static RequestSpecification getRequestSpecWithAuth(Roles role) throws IOException {
		 RequestSpecification request=new RequestSpecBuilder()
		     .setBaseUri(getProperty("BASE_URL"))
		     .setContentType(ContentType.JSON)
		     .setAccept(ContentType.JSON)
		     .addHeader("Authorization", AuthTokenProvider.getToken(role))
		     .log(LogDetail.URI)
		     .log(LogDetail.HEADERS)
		     .log(LogDetail.METHOD)
		     .log(LogDetail.BODY)
		    
		     .build();
		 return request;
	}
	public  static RequestSpecification getRequestSpecWithAuth(Roles role,Object Payload) throws IOException {
		 RequestSpecification request=new RequestSpecBuilder()
		     .setBaseUri(getProperty("BASE_URL"))
		     .setContentType(ContentType.JSON)
		     .setAccept(ContentType.JSON)
		     .addHeader("Authorization", AuthTokenProvider.getToken(role))
		     .setBody(Payload)
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
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(200)
		.expectResponseTime(lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	public static ResponseSpecification responseSpec_JSON(int statuscode) {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectContentType(ContentType.JSON)
		.expectStatusCode(statuscode)
		.expectResponseTime(lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
	
	
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification=new ResponseSpecBuilder()
		.expectStatusCode(statusCode)
		.expectResponseTime(lessThan(1000L))
		.log(LogDetail.ALL)
		.build();
		
		return responseSpecification;
	}
}
