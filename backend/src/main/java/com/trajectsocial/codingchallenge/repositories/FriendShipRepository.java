package com.trajectsocial.codingchallenge.repositories;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@CrossOrigin
@Repository
public interface FriendShipRepository extends PagingAndSortingRepository<FriendShip,Long> {

    Slice<FriendShip> findByUserId(Long userId);

    Slice<FriendShip> findByUserIdIn(Set<Long> userId);

    @Query
    FriendShip findByUserIdAndFriendId(Long userId, Long friendId);

}
