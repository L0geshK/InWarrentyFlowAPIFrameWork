package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class UserDetailsService {
	private static final String USER_DETAILS_SERVICE = "/userdetails";

	public Response Userdeatils(Roles role) {

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(role)).when().get(USER_DETAILS_SERVICE);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return response;

	}

}
