package com.swipejobs.test.jobmatcher;

import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;

import java.util.List;
import java.util.Set;

public interface JobMatcher {

    /**
     * Matches Jobs for a worker based on his or her Location preference, Driving Licence Requirement and Skill Set
     * @param jobs
     * @param worker
     * @return
     */
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker);

}
