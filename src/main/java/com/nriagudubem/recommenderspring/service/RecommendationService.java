package com.nriagudubem.recommenderspring.service;

import com.nriagudubem.recommenderspring.boundary.controller.dto.RecommendationResponseDto;
import com.nriagudubem.recommenderspring.entity.model.Feedback;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.entity.repository.BookRepository;
import com.nriagudubem.recommenderspring.entity.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendationService {

    private final BookRepository bookRepository;

    private final FeedbackRepository feedbackRepository;

    private static final int PAGE_SIZE = 20;

    private static final int PAGE = 1;

    public List<RecommendationResponseDto> getRecommendations(User user) {
        List<Feedback> feedbacks = feedbackRepository.findAllByUser(user);
        return bookRepository.findAllByFeedbackNotIn(feedbacks, buildPageRequest())
                .map(RecommendationResponseDto::of)
                .collect(Collectors.toList());
    }

    private static PageRequest buildPageRequest() {
        return PageRequest.of(PAGE, PAGE_SIZE);
    }

}
