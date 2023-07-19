package com.trajectsocial.codingchallenge.tests.integration.apihelpers;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SiteHeaderTestAPI {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    public String findSiteHeadersByUserId(Long id, int port, int status){
        String url = "http://localhost:"  + port + "/api/siteHeaders/search/findByUserId?userId=" + id;
        ResponseEntity<String> response = testRestTemplate.getForEntity(url, String.class);
        System.out.println(response);

        return response.getBody();
    }

}
