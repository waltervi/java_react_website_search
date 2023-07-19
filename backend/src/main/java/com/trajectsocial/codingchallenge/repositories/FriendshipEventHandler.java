package com.trajectsocial.codingchallenge.repositories;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.services.headerextraction.ExtractHeadersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@RepositoryEventHandler
public class FriendshipEventHandler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

    @HandleAfterCreate
    public void handleFriendshipAfterCreate(FriendShip f)  {
        updateUsers(f);

        FriendShip otherUserFrienship = new FriendShip();
        otherUserFrienship.setUserId(f.getFriendId());
        otherUserFrienship.setFriendId(f.getUserId());
        friendShipRepository.save(otherUserFrienship);
    }


    private void updateUsers(FriendShip f){
        Optional<User> userOptional = userRepository.findById(f.getUserId());
        if (userOptional.isPresent()){
            User user = userOptional.get();
            user.setFriendsQty(user.getFriendsQty() + 1);
            userRepository.save(user);
        }

        Optional<User> friendOptional = userRepository.findById(f.getFriendId());
        if (friendOptional.isPresent()){
            User friend = friendOptional.get();
            friend.setFriendsQty(friend.getFriendsQty() + 1);
            userRepository.save(friend);
        }
    }
}
