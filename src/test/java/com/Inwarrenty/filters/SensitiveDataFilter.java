package com.Inwarrenty.filters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		log.info("**********Request Details*******************");
		log.info("Base URI {} :",requestSpec.getBaseUri());
		log.info("Base METHOD {} :",requestSpec.getMethod());
		log.info("Request HEADER  \n {} :",requestSpec.getHeaders());
		redactHeader(requestSpec);
		redactPayload(requestSpec);
		
		Response response = ctx.next(requestSpec, responseSpec);	

		System.out.println("***WE GOT THE RESPONSE***");
		log.info("Response STATUS {} :",response.statusCode());
		log.info("Response TIME {} :",response.timeIn(TimeUnit.MILLISECONDS));
		log.info("Response HEADER \n {} :",response.getHeaders());
		responcePayload(response);
		return response;
	}
	
	
	
	public void redactHeader(FilterableRequestSpecification requestSpec) {
		
		List<Header> headerlist = requestSpec.getHeaders().asList();
		for(Header h :headerlist) {
			if(h.getName().equalsIgnoreCase("Authorization")) {
				log.info("Header key{} : Value {}",h.getName(),"\"[REDACTED]\"");
			}else {
				log.info("Header key{} : Value {}",h.getName(),h.getValue());
			}
		}
	}
	
	

	public void responcePayload(Response response) {
		String Responcepayload = response.asPrettyString();
		Responcepayload = Responcepayload.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\":\"[REDACTED]\"");
		log.info("Response Payload {}",Responcepayload);
	}
	public void redactPayload(FilterableRequestSpecification requestSpec) {
		
		if(requestSpec.getBody()!=null) {
		String Requestpayload = requestSpec.getBody().toString();
		Requestpayload = Requestpayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\":\"[REDACTED]\"");
		log.info("Response Payload {}",Requestpayload);
	}
	}

}
