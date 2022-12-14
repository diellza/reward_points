package com.retailer.task;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootTest(properties ={"spring.liquibase.enabled=false"}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TaskApplication.class)
class TaskApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void testGetRewardPointsByCustomer() throws JSONException {

        HttpEntity<List<Object[]>> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/reward/points/month",
                HttpMethod.GET, entity, String.class);

        String expected = "[\n" +
                "    {\n" +
                "        \"customerId\": 4,\n" +
                "        \"totalRewardPoints\": 13,\n" +
                "        \"month\": 11\n" +
                "    },\n" +
                "    {\n" +
                "        \"customerId\": 5,\n" +
                "        \"totalRewardPoints\": 130,\n" +
                "        \"month\": 9\n" +
                "    }\n]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void testGetRewardPointsPerCustomerTotal() throws JSONException {

        HttpEntity<List<Object[]>> entity = new HttpEntity<>(null);

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:" + port + "/reward/points/total",
                HttpMethod.GET, entity, String.class);

        String expected = "[\n" +
                "    {\n" +
                "        \"customerId\": 4,\n" +
                "        \"totalRewardPoints\": 187\n" +
                "    },\n" +
                "    {\n" +
                "        \"customerId\": 5,\n" +
                "        \"totalRewardPoints\": 160\n" +
                "    }\n" +
                "]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }
}
