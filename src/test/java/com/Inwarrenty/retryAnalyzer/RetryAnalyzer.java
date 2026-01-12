package com.Inwarrenty.retryAnalyzer;

import org.apache.logging.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.Inwarrenty.Utils.ConfigManager;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_ATTEMPTS =
            Integer.parseInt(ConfigManager.getProperty("MAX_ATTEMPTS"));
    private  static Logger log = com.Inwarrenty.Utils.LoggerUtlity.getLogger(RetryAnalyzer.class);


    @Override
    public boolean retry(ITestResult result) {

        if (retryCount < MAX_ATTEMPTS) {
        	log.warn("Executing  the {} test ,currentyAttempst: {}",result.getName(),retryCount);
            retryCount++;
            System.out.println("Retrying Test: "
                    + result.getName()
                    + " | Attempt: " + retryCount);
            return true;
        }
        return false;
    }
}
