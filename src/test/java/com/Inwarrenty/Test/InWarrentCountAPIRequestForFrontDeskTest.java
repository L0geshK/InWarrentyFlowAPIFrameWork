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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.servicepackage.DashBoardCountService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;


@Listeners(com.listener.APITestListener.class)
@Epic("Job Management")
@Feature("Job Count")
public class InWarrentCountAPIRequestForFrontDeskTest {
	private DashBoardCountService countAPi;
	
	
	
	@BeforeMethod
	public void setup() {
		countAPi=new DashBoardCountService();
	}
	
	
	
	
	
    @Story("Verifying the Count  api ")
    @Description("Verifying the Count  api is giving correct responce form the FrontDesk")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying the Count  api is giving correct responce form the FrontDesk",groups = {"api","regression","smoke"})
	public void getCountAPIFrontDesl() {

		countAPi.getCount(Roles.FD)
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.body("message", equalToIgnoringCase("Success")).body("data", notNullValue())
				.body("data.size()", equalTo(3)).body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("ResponceSchema/CountAPIRequestForFrontDesk.json"))
				.body("data.key", containsInAnyOrder("pending_for_delivery","created_today","pending_fst_assignment"))
				.log().all();

	}

    @Story("Verifying Count api gave Incorrect Respoce for Authtoken Missing case ")
    @Description("Verifying the Count  api is giving correct status code for invalid token")
    @Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verifying the Count  api is giving correct status code for invalid token",groups = {"api","regression","smoke","negative"})
	public void countAPI_missingAuthToken() {
		countAPi.getCountwithNoAuth()
				.then()
				.spec(SpecUtils.responseSpec_TEXT(401));

	}

}
