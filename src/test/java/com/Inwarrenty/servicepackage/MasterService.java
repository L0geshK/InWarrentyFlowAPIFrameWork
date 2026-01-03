package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class MasterService {
	private static final String MASTER_ENDPOINT = "/master";

	public Response getMaster(Roles role) {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(Roles.FD)).when().post(MASTER_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

	public Response getMasterwithNoAuth() {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec()).when().get(MASTER_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

}
