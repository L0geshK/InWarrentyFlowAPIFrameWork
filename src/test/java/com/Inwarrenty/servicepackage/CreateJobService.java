package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.request.model.CreateJobAPIPayload;
import com.Inwarrenty.request.model.UserCredentials;

import io.restassured.response.Response;

public class CreateJobService {
	private static final String CREATEJOB_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(this.getClass());

	public Response getCreateJob(Roles role, Object createJobAPIPayload) {
		log.info("Enter:Createing the job with Role {} for the payload {} with  end point {}:",role,createJobAPIPayload,CREATEJOB_ENDPOINT);

		Response response = null;

		try {
			response = given()

					.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, createJobAPIPayload)).when()
					.post(CREATEJOB_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;
		
		

	}

	public Response getSearchJob(Roles role, Object payload) {
		log.info("Enter:Search the job with Role {} for the payload {} with  SearchEnd  point {}",role,payload,SEARCH_ENDPOINT);

		Response response = null;

		try {
			response = given()

					.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD, payload)).when().post(SEARCH_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

}
