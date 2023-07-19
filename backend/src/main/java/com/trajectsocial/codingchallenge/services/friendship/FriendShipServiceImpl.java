package com.trajectsocial.codingchallenge.services.friendship;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.repositories.FriendShipRepository;
import com.trajectsocial.codingchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FriendShipServiceImpl implements FriendShipService {
    @Autowired
    private FriendShipRepository friendShipRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Slice<FriendShipDto> findByUserId(@NotNull(message = "invalid userId") Long userId) {

        validateUser(userId);

        Slice<FriendShip> friendShipList = friendShipRepository.findByUserId(userId);
        if (friendShipList.isEmpty()) {
            return new SliceImpl<>(new ArrayList<>());
        }

        List<Long> friendIds = friendShipList.stream().map(FriendShip::getFriendId).collect(Collectors.toList());
        List<User> friendUsers = userRepository.findAllByIdIn(friendIds);

        List<FriendShipDto> responseList = new ArrayList<>(friendUsers.size());
        for (User user : friendUsers) {
            FriendShipDto friendShipDTO = new FriendShipDto();
            //we need to map the friendship id here.

            Optional<FriendShip> optionalFriendShip = friendShipList.stream()
                    .filter(p -> p.getFriendId() == user.getId()).findFirst();

            if (optionalFriendShip.isPresent()) {
                FriendShip friendShip = optionalFriendShip.get();
                friendShipDTO.setId(friendShip.getId());
                friendShipDTO.setFriendId(user.getId());
                friendShipDTO.setName(user.getName());
                friendShipDTO.setWebSite(user.getWebSite());
                friendShipDTO.setFriendsQty(user.getFriendsQty());

                responseList.add(friendShipDTO);
            }
        }

        return new SliceImpl<>(responseList);
    }

    private void validateUser(Long userId) {
        if (userId == null) {
            throw new IllegalArgumentException("invalid user id.");
        }

        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("userId invalid.");
        }
    }


}
