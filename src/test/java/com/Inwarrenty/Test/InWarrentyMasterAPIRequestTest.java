package com.Inwarrenty.Test;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.servicepackage.MasterService;

import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static org.hamcrest.Matchers.*;

import static com.Inwarrenty.Utils.AuthTokenProvider.getToken;
import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static  io.restassured.RestAssured.*;

public class InWarrentyMasterAPIRequestTest {
	
	
	
	private MasterService masterservice;
	
	@BeforeMethod
	public void setup() {
		masterservice= new MasterService();
		
	}
	
	
	
	@Test(description = "Verifying the Master api is giving correct responce",groups = {"api","regression","smoke"})
	public void masterAPI() {
		masterservice.getMaster(Roles.FD)
  .then()
		.spec(SpecUtils.responseSpec_OK())
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
		
		
		
	}
	
	
	@Test(description = "Verifying the Master api is giving correct status code for invalid token",groups = {"api","regression","smoke","negative"})
	public void masterAPI_missingAuthToken() {
		masterservice.getMasterwithNoAuth().then()
				.spec(SpecUtils.responseSpec_TEXT(404));

	}
	

}
