package com.swipejobs.test.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * This test class currently do direct matching with the integration services.
 * Not mocking the integration service as of now.
 * Ideally should construct the expected response object and check against that. But for now just checking hardcoded
 * json responses.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JobMatcherControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getMatchingJobs_Empty_Response_ForNonExistingWorker() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/jobmatcher/"+ UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));
    }

    /**
     * This test case right now do an exact match with the workerId 10. For workerId 10 only 1 matching jobs are there.
     * @throws Exception
     */
    @Test
    public void getMatchingJobs_ForExistingWorker() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/jobmatcher/10").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"driverLicenseRequired\": true,\"requiredCertificates\": [\"Calm in the Eye of the Storm\",\"Healthy Living Promoter\",\"The Behind the Scenes Wonder\"],\"location\": {\"longitude\": \"14.316602\",\"latitude\": \"50.022868\"},\"billRate\": \"$17.79\",\"workersRequired\": 2,\"startDate\": 1447171645062,\"about\": \"Minim commodo amet elit Lorem fugiat non fugiat irure irure. Fugiat aute aliqua ea veniam amet qui tempor elit nisi Lorem commodo aliquip cillum. Id eiusmod ea deserunt adipisicing mollit et. Do quis laboris cupidatat occaecat aute aliqua culpa non ea reprehenderit tempor eu. Duis cillum voluptate pariatur eu eu ullamco laboris. Sit ut cillum ipsum enim aute quis ea eu laborum do ipsum. Eu adipisicing eiusmod eiusmod nostrud aute aliquip magna ad fugiat incididunt.\",\"jobTitle\": \"Chief Troublemaker\",\"company\": \"Uxmox\",\"guid\": \"562f66aa60c4be83d4b592ec\",\"jobId\": \"25\"}]"));
    }
}
