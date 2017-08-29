package com.swipejobs.test.jobmatcher;

import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;

import java.util.List;
import java.util.Set;

/**
 * Implements a Job Matching based on Solr.
 */
public class SolrJobMatcher  implements JobMatcher{

    @Override
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker) {
        return null;
    }
}
