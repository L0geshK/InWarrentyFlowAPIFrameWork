package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class DashBoardCountService {

	private static final String COUNT_ENDPONT = "/dashboard/count";
	private static final String DETAILS_ENDPOINT="/dashboard/details";

	public Response getCount(Roles role) {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role)).when().get(COUNT_ENDPONT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

	public Response getCountwithNoAuth() {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec())

					.when().get(COUNT_ENDPONT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}
	
	public Response getDetails(Roles role,Object Payload) {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role,Payload)).when().post(DETAILS_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

}
