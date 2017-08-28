package com.swipejobs.test.jobmatcher;

import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;
import com.swipejobs.test.service.JobMatcherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class SimpleJobMatcher implements JobMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);

    @Override
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker) {
        LOGGER.info("Finding Best 3 Matching Jobs for {}",worker);
        LOGGER.info("Total number of available jobs is {}",jobs.size());

        List<Job> matchedJobs = new ArrayList<>(3);

        return null;

    }
}
