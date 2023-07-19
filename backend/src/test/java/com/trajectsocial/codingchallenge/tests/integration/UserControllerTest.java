package com.trajectsocial.codingchallenge.tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.tests.integration.apihelpers.SiteHeaderTestAPI;
import com.trajectsocial.codingchallenge.tests.integration.apihelpers.UserTestAPI;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

    @LocalServerPort
    protected int serverPort;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserTestAPI userTestAPI;

    @Autowired
    private SiteHeaderTestAPI siteHeaderTestAPI;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Test
    void basicFlow() throws Exception {

        //1- Create a member
        //2- Verify the user exists
        //3- check the site_headers

        //************************************************************************
        //1- create a member
        String username = "11111";
        String password = "test4321";
        User user = new User();
        user.setName(username);
        user.setWebSite("http://www.infobae.com");

        User createdUser = userTestAPI.api_users_POST(user, serverPort, 201);


        //************************************************************************
        //2- Verify the user exists
        User userToCheck = userTestAPI.api_users_ID_GET(createdUser.getId(), serverPort, 200);

        assertEquals(user.getFriendsQty(), userToCheck.getFriendsQty());
        assertEquals(user.getName(), userToCheck.getName());
        assertEquals(user.getWebSite(), userToCheck.getWebSite());

        //************************************************************************
        //3- check the site_headers
        siteHeaderTestAPI.findSiteHeadersByUserId(createdUser.getId(), serverPort, 200);
    }


}