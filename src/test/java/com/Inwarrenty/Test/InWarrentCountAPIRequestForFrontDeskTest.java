package com.Inwarrenty.Test;

import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;
import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentCountAPIRequestForFrontDeskTest {

	@Test
	public void getCountAPIFrontDesl() {

		try {
			given().baseUri(getProperty("BASE_URL")).and().contentType(ContentType.JSON).and().accept(ContentType.ANY)
					.log().uri().log().method().log().body().header("Authorization", getToken(Roles.FD)).log().headers()
					.when().get("/dashboard/count").then().statusCode(200).time(lessThan(1000L))
					.body("message", equalToIgnoringCase("Success")).body("data", notNullValue())
					.body("data.size()", equalTo(3)).body("data.count", everyItem(greaterThanOrEqualTo(0)))
					.body("data.label", everyItem(not(blankOrNullString())))
					.body(JsonSchemaValidator
							.matchesJsonSchemaInClasspath("ResponceSchema/CountAPIRequestForFrontDesk.json"))
					.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
					.log().all();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	@Test
	public void countAPI_missingAuthToken() {
		try {
			given().baseUri(getProperty("BASE_URL")).and().contentType(ContentType.JSON).and().accept(ContentType.ANY)
					.log().uri().log().method().log().body().when().get("/dashboard/count").then().statusCode(401)
					.time(lessThan(1000L)).log().ifValidationFails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
