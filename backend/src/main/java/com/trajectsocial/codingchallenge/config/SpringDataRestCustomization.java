package com.trajectsocial.codingchallenge.config;

import com.trajectsocial.codingchallenge.entities.FriendShip;
import com.trajectsocial.codingchallenge.entities.SiteHeader;
import com.trajectsocial.codingchallenge.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        config.exposeIdsFor(User.class, FriendShip.class, SiteHeader.class);
    }
}
