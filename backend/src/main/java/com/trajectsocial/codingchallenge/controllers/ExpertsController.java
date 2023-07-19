package com.trajectsocial.codingchallenge.controllers;

import com.trajectsocial.codingchallenge.services.experts.ExpertSearchResponse;
import com.trajectsocial.codingchallenge.services.experts.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/experts")
public class ExpertsController {

    @Autowired
    private ExpertService expertService;

    @GetMapping("/byTopic")
    public ExpertSearchResponse findExpertsOnTopic(
            @RequestParam (name = "userId") Long userId,
            @RequestParam (name = "topic") String topic
            ){
        return expertService.findExpertsOnTopic(userId,topic);
    }


}
