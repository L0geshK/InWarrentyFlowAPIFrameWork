package com.Inwarrenty.dataprovidertest;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Inwarrenty.Constants.Roles;
import com.Inwarrenty.Utils.SpecUtils;
import com.Inwarrenty.db.model.DetailsModels;
import com.Inwarrenty.db.model.SearchJobModel;
import com.Inwarrenty.servicepackage.CreateJobService;
import com.Inwarrenty.servicepackage.DashBoardCountService;

public class InwarrentySerachJobTest {

	private SearchJobModel searchmodel;
	private CreateJobService createjobservice;

	@BeforeMethod(description="instantiating  the Jobsearch  service")
	public void setup() {
		searchmodel= new SearchJobModel("JOB_145707");
	    createjobservice = new CreateJobService();
		
	}
	@Test(description="Verifing the JobSerachAPI test ",groups = {"api","regression","smoke"})
	public void getDetailsTest() {
		createjobservice.getSearchJob(Roles.FD, searchmodel).then()
		.spec(SpecUtils.responseSpec_OK())
		.body("message", equalTo("Success"));
		
	}
	
	

}
