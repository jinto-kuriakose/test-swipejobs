package com.swipejobs.test.service;

import com.swipejobs.test.config.Configs;
import com.swipejobs.test.domain.Job;
import com.swipejobs.test.domain.Worker;
import com.swipejobs.test.integration.JobService;
import com.swipejobs.test.integration.WorkerService;
import com.swipejobs.test.jobmatcher.JobMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class JobMatcherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);
    @Autowired
    JobMatcher jobMatcher;
    @Autowired
    private JobService jobService;
    @Autowired
    private WorkerService workerService;
    @Autowired
    private Configs configs;

    /**
     * Currently using {@link com.swipejobs.test.jobmatcher.SimpleJobMatcher} to find the best matching jobs.
     * @param workerId
     * @return List of matched jobs
     */
    public List<Job> getMatchedJobs(String workerId) {

        List<Job> matchedJobs = new ArrayList<>();
        Set<Worker> workers = workerService.getWorkers();
        Worker worker = new Worker(workerId);
        if (workers == null || workers.isEmpty() || !workers.contains(worker)) {
            LOGGER.error("Given Worker {} not found in the system", workerId);
        } else {
            Set<Job> jobs = jobService.getJobs();
            if (jobs == null || jobs.isEmpty()) {
                LOGGER.error("No Jobs found in the system");
                return matchedJobs;
            } else {
                matchedJobs = jobMatcher.getMatchingJobs(
                        jobs,
                        workers.stream().filter(w -> w.getUserId().equals(workerId)).findFirst().get());

                // Limiting the results based on MaxJobSearchResults criteria
                if(matchedJobs.size() > configs.getMaxJobSearchResults()){
                    matchedJobs.subList(configs.getMaxJobSearchResults(), matchedJobs.size()).clear();
                }
            }
        }
        return matchedJobs;
    }
}
