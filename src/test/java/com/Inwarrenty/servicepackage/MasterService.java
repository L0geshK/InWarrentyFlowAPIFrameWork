package com.Inwarrenty.servicepackage;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.response.Response;

public class MasterService {
	private static final String MASTER_ENDPOINT = "/master";
	private Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(this.getClass());

	public Response getMaster(Roles role) {
		log.info("Enter: Master API with role {} with Endpoint {}",role,MASTER_ENDPOINT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpecWithAuth(Roles.FD)).when().post(MASTER_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

	public Response getMasterwithNoAuth() {
		log.info("Enter: Master API with Endpoint {}",MASTER_ENDPOINT);

		Response response = null;

		try {
			response = given().spec(SpecUtils.getRequestSpec()).when().get(MASTER_ENDPOINT);
		} catch (IOException e) {

			e.printStackTrace();
		}
		log.info("Exit");
		return response;

	}

}
