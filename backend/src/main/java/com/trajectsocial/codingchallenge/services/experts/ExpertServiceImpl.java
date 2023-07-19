package com.trajectsocial.codingchallenge.services.experts;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.SiteHeader;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.repositories.FriendShipRepository;
import com.trajectsocial.codingchallenge.repositories.SiteHeaderRepository;
import com.trajectsocial.codingchallenge.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    private SiteHeaderRepository siteHeaderRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

    @Autowired
    private UserRepository userRepository;

    /*
     * Now, looking at Kurt's profile, I want to find experts in the application who write about a certain topic
     * and are not already friends with Kurt. Results should show the path of introduction from Kurt to the expert.
     * For example, Kurt wants to get introduced to someone who writes about 'Trafalmadore'. Billy's website has
     * a heading tag "Journeys To Trafalmadore". Eliott knows Kurt and Billy. An example search result would be Kurt
     * arrow_right Eliott arrow_right Billy ("Journeys To Trafalmadore").
     * */
    @Override
    public ExpertSearchResponse findExpertsOnTopic(Long userId, @NotEmpty String topic){

        validateUser(userId);

        topic = topic.toLowerCase();

        //Find all users that are already friends with the caller to be excluded from the search
        Slice<FriendShip> friendShips = friendShipRepository.findByUserId(userId);
        Set<Long> alreadyFriendIds = friendShips.stream().map(FriendShip::getFriendId).collect(Collectors.toSet());
        alreadyFriendIds.add(userId);//this caller must not be in this list.

        //Do the text search, (we should use a textsearch database for this),
        //excluding friends and the caller user
        //We do a DISTINCT on userId field, because we are only interested in the user that has the topic(AKA header.text)
        //Note: This query can be done better with other tools, like a stored procedure, a better indexing, a
        //  a native sql, but this is what we can do with JPA.
        List<SiteHeader> siteHeaderList = siteHeaderRepository.findDistinctByUserIdNotInAndTextLike(alreadyFriendIds,"%" + topic + "%");

        //Extract the userIds from the response.
        Set<Long> expertIdsNotFriends = siteHeaderList.stream().map(SiteHeader::getUserId).collect(Collectors.toSet());

        //Now do the heavy part: Results should show the path of introduction from the user to the expert.


        List<FriendShip> pathsFound = findPathForExpert(friendShips,expertIdsNotFriends);
        //returned results in pathsFound
        //FriendShip::getUserId -> will contain the callers friend
        //FriendShip::getFriendID -> will contain the expert.

        List<List<String>> pathNames = new LinkedList<>();

        //Now we have everything to build a list of names (paths)
        if (!pathsFound.isEmpty()){
            Set<Long> userIdsFromPaths = new HashSet<>();

            for (FriendShip friendShip : pathsFound){
                userIdsFromPaths.add(friendShip.getUserId());
                userIdsFromPaths.add(friendShip.getFriendId());
            }

            //use userIdsFromPaths to get all user in 1 shot
            Iterable<User> userIterable = userRepository.findAllById(userIdsFromPaths);

            //convert to list for easier management
            List<User> userList = new LinkedList<>();
            userIterable.forEach(userList::add);

            for (FriendShip friendShip : pathsFound){
                List<String> userNames = new ArrayList<>();

                String callerFriendName = userList.stream().filter(p->p.getId().equals(friendShip.getUserId())).findFirst().get().getName();
                String expertName = userList.stream().filter(p->p.getId().equals(friendShip.getFriendId())).findFirst().get().getName();

                userNames.add(callerFriendName);
                userNames.add(expertName);

                pathNames.add(userNames);
            }
        }


        ExpertSearchResponse response = new ExpertSearchResponse();
        response.setPaths(pathNames);
        return response;
    }

    private List<FriendShip> findPathForExpert(Slice<FriendShip> friends,Set<Long> expertIdsNotFriends){
        //lets see if  experts are friends of a friend of the caller (not so easy to understand)

        //get in 1 shot, all friend relationship of friends.
        Set<Long> callerFriendIds = friends.stream().map(FriendShip::getFriendId).collect(Collectors.toSet());
        Iterable<FriendShip> it = friendShipRepository.findByUserIdIn(callerFriendIds);

        //convert to list: using LinkedList so there is no array resizing.
        List<FriendShip> allFriendsRelationshipsList = new LinkedList<>();
        it.forEach(allFriendsRelationshipsList::add);

        //returned results in path
        //FriendShip::getUserId -> will contain the callers friend
        //FriendShip::getFriendID -> will contain the expert.
        List<FriendShip> pathList = new LinkedList<>();

        for (Long expertId : expertIdsNotFriends){
            //now check if these friends are friend of any expert
            Optional<FriendShip> optionalFriendShip  = allFriendsRelationshipsList.stream()
                    .filter(p->p.getFriendId().equals(expertId)).findFirst();

            if (optionalFriendShip.isPresent()){
                //we have a match, a friend of the caller is friend of an expert
                FriendShip foundFrienship = optionalFriendShip.get();
                pathList.add(foundFrienship);
            }
        }
        return pathList;
    }



    private void validateUser(Long userId){
        if (userId == null){
            throw new IllegalArgumentException("invalid userId");
        }

        boolean exists= userRepository.existsById(userId);
        if (!exists){
            throw new IllegalArgumentException("invalid userId");
        }
    }

}
