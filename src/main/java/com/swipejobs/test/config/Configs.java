package com.swipejobs.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class contains all the properties used by this application.
 * Need to expose these in a property file
 */
@Component
public class Configs {

    @Value("${jobResourceUrl:http://test.swipejobs.com/api/jobs}")
    private String jobResourceUrl;

    @Value("${workerResourceUrl:http://test.swipejobs.com/api/workers}")
    private String workerResourceUrl;

    @Value("${maxJobSearchResults:3}")
    private int maxJobSearchResults;

    public int getMaxJobSearchResults() {
        return maxJobSearchResults;
    }

    public String getJobResourceUrl() {
        return jobResourceUrl;
    }

    public String getWorkerResourceUrl() {
        return workerResourceUrl;
    }
}
