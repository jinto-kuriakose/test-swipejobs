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

import java.util.List;

@RestController
public class JobMatcherController {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherController.class);

    @Autowired
    private JobMatcherService jobMatcherService;

    @RequestMapping(value = "/jobmatcher/{workerId}", method = RequestMethod.GET)
    List<Job> getMatchingJobs(@PathVariable String workerId) {

        List<Job> jobs = jobMatcherService.getMatchedJobs(workerId);

        return jobs;
    }
}
