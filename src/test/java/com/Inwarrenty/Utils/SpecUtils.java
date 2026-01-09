package com.Inwarrenty.Utils;

import static com.Inwarrenty.Utils.ConfigManager.getProperty;
import static org.hamcrest.Matchers.lessThan;

import java.io.IOException;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.filters.SensitiveDataFilter;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {

	// Create by logesh

	@Step("Setting up the BaseURL ,ContentType as Application/Json and attaching the SensitiveDataFilter")
	public static RequestSpecification getRequestSpec() throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URL"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())

				.build();
		return request;
	}

	@Step("Setting up the BaseURL,Payload ,ContentType as Application/Json and attaching the SensitiveDataFilter")
	public static RequestSpecification getRequestSpec(Object Payload) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URL"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON).setBody(Payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())

				.build();
		return request;
	}

	@Step("Setting up the BaseURL,Role ,ContentType as Application/Json and attaching the SensitiveDataFilter")
	public static RequestSpecification getRequestSpecWithAuth(Roles role) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URL"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())

				.build();
		return request;
	}

	@Step("Setting up the BaseURL,WithAuth ,ContentType as Application/Json and attaching the SensitiveDataFilter")
	public static RequestSpecification getRequestSpecWithAuth(Roles role, Object Payload) throws IOException {
		RequestSpecification request = new RequestSpecBuilder().setBaseUri(getProperty("BASE_URL"))
				.setContentType(ContentType.JSON).setAccept(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).setBody(Payload)
				.addFilter(new SensitiveDataFilter())
				.addFilter(new AllureRestAssured())

				.build();
		return request;
	}

	@Step("Expecting the Response to have ContentType as Application/Json and statusCode 200 and Response time less than 1000ms")
	public static ResponseSpecification getResponceSpec() {
		ResponseSpecification response = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(lessThan(1000L))
				

				.build();
		return response;

	}

	@Step("Expecting the Response to have ContentType as Application/Json and statusCode 200 and Response time less than 1000ms")
	public static ResponseSpecification responseSpec_OK() {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(lessThan(1000L))

				.build();

		return responseSpecification;
	}

	@Step("Expecting the Response to have ContentType as Application/Json and  Response time less than 1000ms with StatusCode")
	public static ResponseSpecification responseSpec_JSON(int statuscode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(statuscode).expectResponseTime(lessThan(1000L))

				.build();

		return responseSpecification;
	}

	@Step("Expecting the Response to have ContentType as Text  and  Response time less than 1000ms with StatusCode")
	public static ResponseSpecification responseSpec_TEXT(int statusCode) {
		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCode)
				.expectResponseTime(lessThan(1000L))

				.build();

		return responseSpecification;
	}
}
