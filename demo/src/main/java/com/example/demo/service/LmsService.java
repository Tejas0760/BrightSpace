package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.example.demo.Model.Enrollment;
import com.example.demo.Model.Grade;
import com.example.demo.Model.User;


@Service
public class LmsService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    public User getUserInfo(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, String userId) {
        return webClientBuilder.build()
            .get()
            .uri("https://corplms.d2l-partners.brightspace.com/d2l/api/lp/1.44/users/" + userId)
            .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
            .retrieve()
            .bodyToMono(User.class)
            .block();
    }

    public Enrollment getUserEnrollments(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, String userId) {
        return webClientBuilder.build()
            .get()
            .uri("https://corplms.d2l-partners.brightspace.com/d2l/api/lp/1.44/enrollments/users/" + userId + "/orgUnits/")
            .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
            .retrieve()
            .bodyToMono(Enrollment.class)
            .block();
    }

    public Grade getUserFinalGrade(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, String orgUnitId, String userId) {
        return webClientBuilder.build()
            .get()
            .uri("https://corplms.d2l-partners.brightspace.com/d2l/api/le/1.44/" + orgUnitId + "/grades/final/values/" + userId)
            .headers(headers -> headers.setBearerAuth(authorizedClient.getAccessToken().getTokenValue()))
            .retrieve()
            .bodyToMono(Grade.class)
            .block();
    }
}

