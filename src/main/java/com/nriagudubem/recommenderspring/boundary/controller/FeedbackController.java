package com.nriagudubem.recommenderspring.boundary.controller;

import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackResponseDto;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping(path = "/v1/feedback/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<FeedbackResponseDto> collectFeedback(@RequestBody @Valid List<FeedbackRequestDto> requestDtos,
                                                     @AuthenticationPrincipal User user) {
        return feedbackService.persistFeedback(requestDtos, user);
    }
}
