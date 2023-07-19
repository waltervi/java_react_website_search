package com.trajectsocial.codingchallenge.tests.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.repositories.UserRepository;
import com.trajectsocial.codingchallenge.tests.integration.apihelpers.JsonUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@AutoConfigureMockMvc
public class FriendshipControllerTest {

    @Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void basicFlow() throws Exception {

        //1- Create 2 users: A, B
        //1.1 - Verify Links existence
        //2- A sends a friendship request to B
        //3- verify friendships

        //************************************************************************************
        //1- Create 2 users: A, B
        User userRequest = new User();
        userRequest.setName("userA");
        userRequest.setWebSite("http://www.infobae.com");
        User userA = userRepository.save(userRequest);

        userRequest = new User();
        userRequest.setName("userB");
        userRequest.setWebSite("http://www.data24.com");
        User userB = userRepository.save(userRequest);

        //************************************************************************************
        //1.1 - Verify Links existence

        //************************************************************************************
        //2- A sends a friendship request to B
        FriendShip request = new FriendShip();
        request.setUserId(userA.getId());
        request.setFriendId(userB.getId());
        mockMvc.perform(post("/friendships")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(request)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated());

        //3- verify friendships
        //check friendship in userA
        mockMvc.perform(get("/friendships?userId=" + userA.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("content[0].friendId", Matchers.is(userB.getId().intValue())));


        //check friendship in userB
        mockMvc.perform(get("/friendships?userId=" + userB.getId()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("content[0].friendId", Matchers.is(userA.getId().intValue())))
                .andReturn();


    }


}