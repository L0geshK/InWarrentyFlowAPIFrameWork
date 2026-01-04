package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class CreateJobService {
	private static final String CREATEJOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";

	public Response getCreateJob(Roles role, Object createJobAPIPayload) {

		Response response = null;

		try {
			response = given()

					.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload)).when()
					.post(CREATEJOB_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

	public Response getSearchJob(Roles role, Object payload) {

		Response response = null;

		try {
			response = given()

					.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, payload)).when().post(SEARCH_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return response;

	}

}
