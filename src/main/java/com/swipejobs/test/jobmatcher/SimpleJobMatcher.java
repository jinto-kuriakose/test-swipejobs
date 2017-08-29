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
import java.util.stream.Collectors;

import static com.swipejobs.test.utils.LocationDistanceCalculator.distance;


/**
 * Matches Jobs for a worker based on his or her Location preference, Driving Licence Requirement and Skill Set
 */
@Component
public class SimpleJobMatcher implements JobMatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobMatcherService.class);

    @Override
    public List<Job> getMatchingJobs(Set<Job> jobs, Worker worker) {

        LOGGER.info("Number of available jobs are {}", jobs.size());
        List<Job> matchedJobs = new ArrayList<>();

        // Assuming worker need not be active to do job matching searches.

        //if (!worker.isActive()) {
        //    LOGGER.info("Worker is not Active.");
        //    return matchedJobs;
        //}

        // Filtering based on Driving Licence Requirement.
        matchedJobs = jobs.stream().filter(p -> p.isDriverLicenseRequired() == worker.isHasDriversLicense()).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Driving Licence Requirement are {}", matchedJobs.size());

        //Filtering based on jobSearchAddress.maxJobDistance.
        //TODO If the filtered results are less than the expected number of results then try suggesting next nearby jobs based on location.
        matchedJobs = matchedJobs.stream().filter(p -> distance(
                worker.getJobSearchAddress().getLatitude(),
                p.getLocation().getLatitude(),
                worker.getJobSearchAddress().getLongitude(),
                p.getLocation().getLongitude(),
                worker.getJobSearchAddress().getUnit()) <= worker.getJobSearchAddress().getMaxJobDistance())
                .collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after jobSearchAddress.maxJobDistance are {}", matchedJobs.size());

        //Looking only for at least one matching required certificate. Based on the business requirement need to update this logic.
        matchedJobs = matchedJobs.stream().filter(p -> isWorkerHasRequiredCertificates(p.getRequiredCertificates(),worker.getCertificates())== true).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after Required Certificate check are {}", matchedJobs.size());

        // Filtering based on skills. Just doing a basic case sensitive exact match checking.
        // For better results these fields should be check with Some search engine apis like Solr.
        matchedJobs = matchedJobs.stream().filter(p -> worker.getSkills().contains(p.getJobTitle())).collect(Collectors.toList());
        LOGGER.info("Number of matched jobs after skills check are {}", matchedJobs.size());

        return matchedJobs;
    }

    private boolean isWorkerHasRequiredCertificates(List requiredCertificates, List certificates){
        for(Object certificate :requiredCertificates){
            if(certificates.contains(certificate)) {
                LOGGER.info("Matched certificate={}",certificate);
                return true;
            }
        }
        return false;
    }
}
