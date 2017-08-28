package com.swipejobs.test.jobmatcher;

import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;

import java.util.List;
import java.util.Set;

public interface JobMatcher {

    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker);

}
