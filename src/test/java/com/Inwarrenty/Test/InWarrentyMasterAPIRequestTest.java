package com.Inwarrenty.Test;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;

import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;
import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static  io.restassured.RestAssured.*;

public class InWarrentyMasterAPIRequestTest {
	
	
	
	@Test
	public void masterAPI() {
		try {
			given()
			  .baseUri(getProperty("BASE_URL"))
			  .and().contentType("")
			  .log().uri().log().method().log().body()
			  .header("Authorization",getToken(Roles.FD))
			  .log().headers()
  .when()
			.post("/master")
  .then()
			.statusCode(200)
			.time(lessThan(1000L))
			.body("message",  equalToIgnoringCase("Success"))
			.body("data", notNullValue())
			.body("data", hasKey("mst_oem"))
			.body("data", hasKey("mst_model"))
			.body("$", hasKey("message"))//$ means total json 
			.body("data.mst_oem.size()", greaterThan(0))
			.body("data.mst_oem.id",everyItem(notNullValue()))
			.body("data.mst_oem.name",everyItem(notNullValue()))
			.body("data.mst_oem.code",everyItem(notNullValue()))
			.body("data.mst_oem.is_active",everyItem(notNullValue()))
			.body("data.mst_oem.created_at",everyItem(notNullValue()))
			.body("data.mst_oem.modified_at",everyItem(notNullValue()))
			.body("data.mst_model.id",everyItem(notNullValue()))
			.body("data.mst_model.name",everyItem(notNullValue()))
			.body("data.mst_model.code",everyItem(notNullValue()))
			.body("data.mst_model.mst_product_id",everyItem(notNullValue()))
			.body("data.mst_model.is_active",everyItem(notNullValue()))
			.body("data.mst_model.created_at",everyItem(notNullValue()))
			.body("data.mst_model.modified_at",everyItem(notNullValue()))
			.body("data.map_fst_pincode.pincode",everyItem(notNullValue()))
			.body("data.map_fst_pincode.mst_service_location_id",everyItem(notNullValue()))
			.body("data.map_fst_pincode.mst_service_location_name",everyItem(notNullValue()))
			.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("ResponceSchema/MasterAPIResponceSchema.json"))


			
			
			.log().body();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
	@Test
	public void masterAPI_missingAuthToken() {
		try {
			given().baseUri(getProperty("BASE_URL")).and().contentType(ContentType.JSON).and().accept(ContentType.ANY).header("Authorization","")
					.log().uri().log().method().log().body().when().get("/master").then().statusCode(404)
					.time(lessThan(1000L)).log().ifValidationFails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	

}
