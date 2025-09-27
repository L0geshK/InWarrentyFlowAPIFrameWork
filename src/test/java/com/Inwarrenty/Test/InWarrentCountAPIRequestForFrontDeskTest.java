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
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

public class InWarrentCountAPIRequestForFrontDeskTest {

	@Test
	public void getCountAPIFrontDesl() {

		try {
			given()
			.spec(SpecUtils.getRequestSpecWithAuth(Roles.FD))
					.when()
					    .get("/dashboard/count")
					.then()
					.spec(SpecUtils.responseSpec_OK())
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
			given()
			.spec(SpecUtils.getRequestSpec())
					
					.when().get("/dashboard/count")
					.then()
					.spec(SpecUtils.responseSpec_JSON(401));
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
