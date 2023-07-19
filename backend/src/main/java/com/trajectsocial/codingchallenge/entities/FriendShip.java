package com.trajectsocial.codingchallenge.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity(name = "frienships")
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"userId", "friendId"})
})
public class FriendShip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long friendId;
}
