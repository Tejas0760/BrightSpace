package com.example.demo.Controller;

import com.example.demo.Model.Enrollment;
import com.example.demo.Model.Grade;
import com.example.demo.Model.User;
import com.example.demo.service.LmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LmsController {

    @Autowired
    private LmsService lmsService;

    @GetMapping("https://corplms.d2l-partners.brightspace.com/d2l/api/lp/1.44/users/175")
    public User getUserInfo(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, @PathVariable String userId) {
        return lmsService.getUserInfo(authorizedClient, userId);
    }

    @GetMapping("https://corplms.d2l-partners.brightspace.com/d2l/api/lp/1.44/enrollments/users/175/orgUnits/")
    public Enrollment getUserEnrollments(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, @PathVariable String userId) {
        return lmsService.getUserEnrollments(authorizedClient, userId);
    }

    @GetMapping("https://corplms.d2l-partners.brightspace.com/d2l/api/le/1.73/(orgUnitId)/grades/final/values/175")
    public Grade getUserFinalGrade(@RegisteredOAuth2AuthorizedClient("brightspace") OAuth2AuthorizedClient authorizedClient, @PathVariable String orgUnitId, @PathVariable String userId) {
        return lmsService.getUserFinalGrade(authorizedClient, orgUnitId, userId);
    }
}

