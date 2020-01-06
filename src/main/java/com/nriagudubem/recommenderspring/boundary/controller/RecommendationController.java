package com.nriagudubem.recommenderspring.boundary.controller;

import com.nriagudubem.recommenderspring.boundary.controller.dto.RecommendationResponseDto;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping(path = "/v1/recommendations")
    @ResponseStatus(HttpStatus.OK)
    public List<RecommendationResponseDto> getRecommendations(@AuthenticationPrincipal User user) {
        return recommendationService.getRecommendations(user);
    }
}
