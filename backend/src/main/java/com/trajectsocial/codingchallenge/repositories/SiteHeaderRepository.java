package com.trajectsocial.codingchallenge.repositories;

import com.trajectsocial.codingchallenge.entities.SiteHeader;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RepositoryRestController
public interface SiteHeaderRepository extends CrudRepository<SiteHeader,Long> {

    Slice<SiteHeader> findByUserId(Long userId);

//    @Query("SELECT DISTINCT sh.userId FROM SiteHeader AS sh WHERE ")
//    @Query("SELECT m FROM Movie m WHERE m.title LIKE %:title%")
//    List<SiteHeader> findExpers(Set<Long> userId, String text);
//
//    @Query("SELECT new com.baeldung.aggregation.model.custom.CommentCount(c.year, COUNT(c.year)) "
//            + "FROM Comment AS c GROUP BY c.year ORDER BY c.year DESC")
//    List<SiteHeader> findExpers2(Set<Long> userId, String text);
//
//
//    List<SiteHeader> findDistinctByName(List<String> names);
    //List<SiteHeader> findDistinctByUserIdAndUserIdNotInAndTextLike(Set<Long> userId, String text);


//    List<SiteHeader> findByUserIdNotInAndTextLike(List<Long> userId , String text);

//    List<SiteHeader> findByDistinctUserIdAndUserIdNotInAndTextLike(List<Long> userId , String text);

//    @Query("SELECT DISTINCT s.id FROM SiteHeader as s")
//    List<Integer> findDistinctIds();

        List<SiteHeader> findDistinctByUserIdNotInAndTextLike(Set<Long> userId , String text);

}
