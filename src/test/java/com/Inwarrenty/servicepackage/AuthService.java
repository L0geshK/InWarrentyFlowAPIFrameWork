package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.UserCredentials;

import io.restassured.response.Response;

public class AuthService {
	private static final String LOGIN_ENDPOINT = "/login";

	public Response login(Object userCredentials) {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec(userCredentials)).when().post(LOGIN_ENDPOINT);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;

	}

}
