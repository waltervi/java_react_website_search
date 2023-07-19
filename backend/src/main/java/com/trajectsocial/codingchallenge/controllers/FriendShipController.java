package com.trajectsocial.codingchallenge.controllers;

import com.trajectsocial.codingchallenge.services.friendship.FriendShipDto;
import com.trajectsocial.codingchallenge.services.friendship.FriendShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/friendList")
public class FriendShipController {

    @Autowired
    private FriendShipService friendShipService;

    @GetMapping
    public Slice<FriendShipDto> findFriends(@RequestParam (required = true,name = "userId") Long userId){
        return friendShipService.findByUserId(userId);
    }


}
