package com.trajectsocial.codingchallenge.repositories;

import com.trajectsocial.codingchallenge.entities.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
@RepositoryRestController
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    List<User> findAllByIdIn(List<Long> ids);

    User findOneByName(String name);

    Slice<User> findByName(String name);
}
