package com.trajectsocial.codingchallenge.services.friendship;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import org.springframework.data.domain.Slice;

import javax.validation.constraints.NotNull;
import java.security.Principal;

public interface FriendShipService {

    Slice<FriendShipDto> findByUserId(
                                        @NotNull(message = "invalid userId") Long userId);

}
