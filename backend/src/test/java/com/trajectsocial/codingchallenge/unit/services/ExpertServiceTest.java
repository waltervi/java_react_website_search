package com.trajectsocial.codingchallenge.unit.services;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.SiteHeader;
import com.trajectsocial.codingchallenge.entities.User;
import com.trajectsocial.codingchallenge.repositories.FriendShipRepository;
import com.trajectsocial.codingchallenge.repositories.SiteHeaderRepository;
import com.trajectsocial.codingchallenge.repositories.UserRepository;
import com.trajectsocial.codingchallenge.services.experts.ExpertSearchResponse;
import com.trajectsocial.codingchallenge.services.experts.ExpertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExpertServiceTest {
    @Autowired
    private ExpertService expertService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

    @Autowired
    private SiteHeaderRepository siteHeaderRepository;

    /*
     * This will create 5 users: userA,userB,userC,userD,userE
     *
     * userA and userB will be friends.
     * userB and userC will be friends.
     * userC and userD will be friends.
     * userB and userE will be friends.
     *
     * SiteHeaders will be added manually
     * userA: tomatoes, oranges, bananas.
     * userB: bikes, cars, trucks
     * userC: running, jogging, beverages
     * userD: yoga, mindfullness, beverages
     * userE: footbal, jogging, basketball
     * */
    @Test
    public void test() throws Exception{
        UserDto userDto = setupUsers();
        setupFriendships(userDto);
        setupSiteHeaders(userDto);

        //test1 : look for beverages. only userC and userD have that, but only userC is expected, since it is friend of userB.
        //that in turn is friend of userA
        ExpertSearchResponse response = expertService.findExpertsOnTopic(userDto.userA.getId(), "BEV");
        assertEquals(response.getPaths().get(0).get(0),userDto.userB.getName());
        assertEquals(response.getPaths().get(0).get(1),userDto.userC.getName());

        //test2 : look for jogging. only userC and usere have that, and boths are friends of userB

        response = expertService.findExpertsOnTopic(userDto.userA.getId(), "JOG");
        assertEquals(response.getPaths().get(0).get(0),userDto.userB.getName());
        assertEquals(response.getPaths().get(0).get(1),userDto.userC.getName());

        assertEquals(response.getPaths().get(1).get(0),userDto.userB.getName());
        assertEquals(response.getPaths().get(1).get(1),userDto.userE.getName());

    }

    private class UserDto {
        public User userA;
        public User userB;
        public User userC;
        public User userD;
        public User userE;
    }

    private void setupSiteHeaders(UserDto dto){
        //* SiteHeaders will be added manually
        //* userA: tomatoes, oranges, bananas.
        //* userB: bikes, cars, trucks
        //* userC: running, jogging, beverages
        //* userD: yoga, mindfullness, beverages
        //* userE: footbal, jogging, basketball
        addSiteHeader(dto.userA,"tomatoes", "oranges", "bananas");
        addSiteHeader(dto.userB,"bikes", "cars", "trucks");
        addSiteHeader(dto.userC,"running", "jogging", "beverages");
        addSiteHeader(dto.userD,"yoga", "mindfullness", "beverages");
        addSiteHeader(dto.userE,"footbal", "jogging", "basketball");

    }

    private void addSiteHeader(User user,String ... topics){

        for (String topic : topics){
            SiteHeader sh = new SiteHeader();
            sh.setUserId(user.getId());
            sh.setText(topic);
            siteHeaderRepository.save(sh);
        }

    }

    private void setupFriendships(UserDto dto){
        addFriendShipFor(dto.userA, dto.userB);
        addFriendShipFor(dto.userB, dto.userC);
        addFriendShipFor(dto.userC, dto.userD);
        addFriendShipFor(dto.userB, dto.userE);
    }

    private void addFriendShipFor(User a, User b){
        FriendShip request = new FriendShip();
        request.setUserId(a.getId());
        request.setFriendId( b.getId());

        friendShipRepository.save(request);

        request = new FriendShip();
        request.setUserId(b.getId());
        request.setFriendId( a.getId());
        friendShipRepository.save(request);
    }


    private UserDto setupUsers(){
        UserDto dto = new UserDto();

        User userRequest = new User();
        userRequest.setName("userA");
        userRequest.setWebSite("fakeurl");
        dto.userA = userRepository.save(userRequest);

        userRequest = new User();
        userRequest.setName("userB");
        userRequest.setWebSite("fakeurl");
        dto.userB = userRepository.save(userRequest);

        userRequest = new User();
        userRequest.setName("userC");
        userRequest.setWebSite("fakeurl");
        dto.userC = userRepository.save(userRequest);

        userRequest = new User();
        userRequest.setName("userD");
        userRequest.setWebSite("fakeurl");
        dto.userD = userRepository.save(userRequest);

        userRequest = new User();
        userRequest.setName("userE");
        userRequest.setWebSite("fakeurl");
        dto.userE = userRepository.save(userRequest);

        return dto;
    }
}

