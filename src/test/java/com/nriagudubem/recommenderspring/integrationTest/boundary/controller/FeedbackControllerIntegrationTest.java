package com.nriagudubem.recommenderspring.integrationTest.boundary.controller;

import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.LoginRequestDto;
import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.Feedback;
import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.integrationTest.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class FeedbackControllerIntegrationTest extends AbstractIntegrationTest {

    private Book book;

    private User user;

    @BeforeEach
    void setup() {
        book = Book.builder()
                .title("the best title")
                .genre("classicals")
                .genre("My genre")
                .author("Chidubem")
                .ASIN(2392L)
                .build();
    }

    @Test
    void testCollectFeedback_shouldSaveAndReturnDuplicateFeedbackFromSameUser() {
        httpRequestsHelper.collectFeedback(Arrays.asList(buildRequestDto()), LoginRequestDto.builder()
                .username(user.getUsername()).password(user.getPassword()).build())
                .then()
                .statusCode(HttpStatus.ACCEPTED.value());

        Feedback feedback = feedbackRepository.findByBook(book);

        assertThat(feedback.getFeedbackType()).isEqualTo(FeedbackType.LIKED);
    }


    private FeedbackRequestDto buildRequestDto() {
        return FeedbackRequestDto.builder()
                .recommendationIdentifier(book.getId())
                .feedback(FeedbackType.LIKED)
                .build();
    }
}
