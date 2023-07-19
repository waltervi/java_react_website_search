package com.trajectsocial.codingchallenge.repositories;

import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.services.headerextraction.ExtractHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {

    @Autowired
    private ExtractHeadersService extractHeadersService;

    @HandleAfterCreate
    public void memberUserAfterCreate(User m)  {
        //TODO: send message to RabbitMQ to process the URLs in a Consumer.
        try{
            extractHeadersService.extractAndSaveHeaders(m.getWebSite(),m.getId());
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }

}
