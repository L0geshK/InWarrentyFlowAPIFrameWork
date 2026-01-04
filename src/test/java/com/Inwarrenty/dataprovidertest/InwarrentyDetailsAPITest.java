package com.Inwarrenty.dataprovidertest;

import static org.hamcrest.Matchers.equalTo;

import org.hamcrest.Matcher;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.db.model.DetailsModels;
import com.Inwarrenty.servicepackage.DashBoardCountService;

public class InwarrentyDetailsAPITest {
	private DashBoardCountService dashboardservice;
	private DetailsModels details;
	
	
	@BeforeMethod(description="instantiating  the Dashboard service")
	public void setup() {
		details= new DetailsModels("created_today");
		dashboardservice = new DashBoardCountService();
		
	}
	
	@Test(description="Verifing the DetailsAPi test ",groups = {"api","regression","smoke"})
	public void getDetailsTest() {
		dashboardservice.getDetails(Roles.FD, details).then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message", equalTo("Success"));
		
	}
	
	

}
