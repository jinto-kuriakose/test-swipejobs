package com.swipejobs.test.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Configs {

    @Value("${jobResourceUrl:http://test.swipejobs.com/api/jobs}")
    private String jobResourceUrl;

    @Value("${workerResourceUrl:http://test.swipejobs.com/api/workers}")
    private String workerResourceUrl;

    public String getJobResourceUrl() {
        return jobResourceUrl;
    }

    public String getWorkerResourceUrl() {
        return workerResourceUrl;
    }
}
