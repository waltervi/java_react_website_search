package com.trajectsocial.codingchallenge.tests.integration.apihelpers;

import com.trajectsocial.codingchallenge.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Service
public class UserTestAPI {

    private TestRestTemplate testRestTemplate = new TestRestTemplate();

    public User api_users_POST(User m,int port,int status){
        String url = "http://localhost:"  + port + "/api/users";
        ResponseEntity<User> response = testRestTemplate.postForEntity(url,m, User.class);
        return response.getBody();
    }

    public User api_users_ID_GET(Long id, int port, int status){
        String url = "http://localhost:"  + port + "/api/users/" + id;

        ResponseEntity<User> response = testRestTemplate.
                getForEntity(url, User.class);

        assertEquals(status, response.getStatusCodeValue()  );
        return response.getBody();
    }

}
