package com.trajectsocial.codingchallenge.unit.services;

import com.trajectsocial.codingchallenge.services.headerextraction.ExtractHeadersService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ExtractHeadersServiceTest {
    @Autowired
    private ExtractHeadersService extractHeadersService;

    @Test
    public void test() throws Exception{
        extractHeadersService.extractAndSaveHeaders("https://www.infobae.com",1l);
        System.out.println(1);
    }
}

