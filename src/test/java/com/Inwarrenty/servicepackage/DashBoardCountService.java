package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class DashBoardCountService {

	private static final String COUNT_ENDPONT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT="/dashboard/details";
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(this.getClass());

	public Response getCount(Roles role) {
		log.info("Enter: Create count api with Role {} with ENDPOINT {} :",role,COUNT_ENDPONT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role)).when().get(COUNT_ENDPONT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

	public Response getCountwithNoAuth() {
		log.info("Enter: getCountwithNoAuth ENDPOINT {} ",COUNT_ENDPONT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec())

					.when().get(COUNT_ENDPONT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}
	
	public Response getDetails(Roles role,Object Payload) {
		log.info("Enter: GetDetails api with Role {} with Payload {}  with ENDPOINT {} ",role,Payload,DETAILS_ENDPOINT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role,Payload)).when().post(DETAILS_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Enter");
		return response;

	}

}
