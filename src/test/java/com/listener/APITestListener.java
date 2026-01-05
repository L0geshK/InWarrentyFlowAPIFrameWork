package com.listener;

import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class APITestListener implements ITestListener {

	private static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(APITestListener.class);

	public void onTestStart(ITestResult result) {
		log.info("Started the test {}", result.getName());
		log.info("test class {}", result.getMethod().getTestClass());
		log.info("test class description {}", result.getMethod().getDescription());
		log.info("test class Groups {}", Arrays.toString(result.getMethod().getGroups()));
		log.info("test class DataProvider {}", result.getMethod().getDataProviderMethod());

	}

	public void onTestSuccess(ITestResult result) {
		long starttime = result.getStartMillis();
		long endtimme = result.getEndMillis();
		log.info("Total Duraction: {} ms", (endtimme - starttime));
		log.info("{}- TestPassed !!", result.getName());

	}

	public void onTestFailure(ITestResult result) {
		log.error("{}- Failure TestCase ", result.getName());
		log.error("Error Message:", result.getThrowable().getMessage());
		log.error(result.getThrowable());

	}

	public void onTestSkipped(ITestResult result) {
		log.error("{}-  OnTestCase Skipped ", result.getName());

	}

	public void onStart(ITestContext context) {
		log.info("********* Started InWarrentyFlow API TESTCASE*************");

	}

	public void onFinish(ITestContext context) {
		log.info("********* Finish 	*************");

	}

}
