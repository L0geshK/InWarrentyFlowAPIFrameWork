package com.Inwarrenty.dataprovidertest;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.db.model.DetailsModels;
import com.Inwarrenty.servicepackage.DashBoardCountService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(com.listener.APITestListener.class)
@Epic("Job Management")
@Feature("Job Details")
public class InwarrentyDetailsAPITest {
	private DashBoardCountService dashboardservice;
	private DetailsModels details;
	
	
	@BeforeMethod(description="instantiating  the Dashboard service")
	public void setup() {
		details= new DetailsModels("created_today");
		dashboardservice = new DashBoardCountService();
		
	}
	
	@Story("Verifing the DetailsAPi")
	@Description("Verifing the DetailsAPi test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verifing the DetailsAPi test ",groups = {"api","regression","smoke"})
	public void getDetailsTest() {
		dashboardservice.getDetails(Roles.FD, details).then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message", equalTo("Success"));
		
	}
	
	

}
