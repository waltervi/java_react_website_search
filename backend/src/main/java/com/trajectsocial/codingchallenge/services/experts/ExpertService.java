package com.trajectsocial.codingchallenge.services.experts;

public interface ExpertService {
    ExpertSearchResponse findExpertsOnTopic(Long userId, String topic);
}
