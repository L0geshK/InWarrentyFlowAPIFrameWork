package com.Inwarrenty.Test;

import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;

public class InWarrentGetUserDetailsTest {
	
	
	@Test
	public void getUserDetails() throws IOException {
		
		
		
	//	Header header = new Header("Authorization","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3NTgzMDMyMzR9.BISfijo7800K2DnhJNi4eXKLrH0hoQygtqBGtBjRm4c");
		
		given()
		      .spec(SpecUtils.getRequestSpecWithAuth(Roles.FD))
	    .when()
	          .get("/userdetails")
	    .then()
	        .spec(SpecUtils.getResponceSpec())
	          .body("message", equalToIgnoringCase("Success"))
	          .body("data", notNullValue())
	          .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/UserDetailResponceSchema.json"))
	          .body("data.role_name", equalToIgnoringCase("FrontDesk"))
	          .body("data.first_name", notNullValue())
	          .body("data.last_name", notNullValue())
	          .body("data.login_id", notNullValue())
	          .body("data.mobile_number", notNullValue())
	          .body("data.email_id", notNullValue())
	          .body("data.password", notNullValue())
	          .body("data.created_at", notNullValue())
	          .body("data.modified_at", notNullValue())
	          .body("data.service_location", notNullValue())
	          .log().body().and().log().all()
	          .extract().response();
	          
		      
		
		
	}

}
