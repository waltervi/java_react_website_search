package com.trajectsocial.codingchallenge.services.experts;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExpertSearchResponse {
    private List<List<String>> paths;
}
