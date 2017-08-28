package com.swipejobs.test.integration;

import com.swipejobs.test.config.Configs;
import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WorkerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerService.class);

    @Autowired
    private Configs configs;

    private RestTemplate restTemplate = new RestTemplate();

    public Set<Worker> getWorkers() {

        ResponseEntity<Worker[]> workersResponse = restTemplate.getForEntity(configs.getWorkerResourceUrl(), Worker[].class);

        //Checking some basic error scenarios
        if (null == workersResponse) {
            LOGGER.error("Failed to get Response from Worker service");
            return null;
        } else if (HttpStatus.OK != workersResponse.getStatusCode()) {
            LOGGER.error("Failed to get proper response from Worker Service. StatusCode={}", workersResponse.getStatusCode());
            return null;
        }
        Set<Worker> workers = Arrays.stream(workersResponse.getBody()).collect(Collectors.toSet());
        //TODO change to debug mode
        LOGGER.info("Workers={}", workers);
        return workers;
    }
}
