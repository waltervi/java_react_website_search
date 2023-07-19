package com.trajectsocial.codingchallenge.services.friendship;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FriendShipDto {
    private Long id;
    private Long friendId;
    private String name;
    private String webSite;
    private Integer friendsQty;
}
