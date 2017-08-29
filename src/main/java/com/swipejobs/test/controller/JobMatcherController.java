package com.swipejobs.test.controller;

import com.swipejobs.test.Application;
import com.swipejobs.test.domain.Job;
import com.swipejobs.test.service.JobMatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class JobMatcherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherController.class);

    @Autowired
    private JobMatcherService jobMatcherService;

    /**
     * This api will find out the best matching jobs for a given worker.
     * @param workerId
     * @return List of matched jobs
     */
    @RequestMapping(value = "/jobmatcher/{workerId}", method = RequestMethod.GET)
    List<Job> getMatchingJobs(@PathVariable String workerId) {

        //TODO Accepts a transaction or unique id and attach that with logger so that requests can be tracked easily.

        LOGGER.info("New JobMatcher request received for workerId={}", workerId);
        try {
            List<Job> jobs = jobMatcherService.getMatchedJobs(workerId);
            LOGGER.info("Found {} matching jobs for workerId={}",jobs.size(),workerId);
            return jobs;
        }catch (Exception ex){
            LOGGER.error("Error occurred while processing job matcher.",ex);
            return new ArrayList<>();
        }
    }
}
