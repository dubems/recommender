package com.nriagudubem.recommenderspring.service;

import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackResponseDto;
import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.Feedback;
import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.entity.repository.BookRepository;
import com.nriagudubem.recommenderspring.entity.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    private final BookRepository bookRepository;

    public List<FeedbackResponseDto> persistFeedback(final List<FeedbackRequestDto> dtos, User user) {
        List<FeedbackResponseDto> responseDto = new ArrayList<>();
        for (FeedbackRequestDto dto : dtos) {
            final Optional<Book> bookOptional = bookRepository.findById(dto.getRecommendationIdentifier());
            if (bookOptional.isPresent()) {
                final Book book = bookOptional.get();
                if (feedbackRepository.existsByBook_ASINAndFeedbackTypeAndUser(book.getASIN(), dto.getFeedback(), user)) {
                    log.warn("process=persistFeedback, status=warning, " +
                            "message=Same feedback from this user={} already exist", user.getId());
                    responseDto.add(dto.toResponseDto(book));
                } else {
                    feedbackRepository.save(Feedback.builder()
                            .book(Book.builder().ASIN(book.getASIN())
                                    .title(book.getTitle())
                                    .author(book.getAuthor())
                                    .createdAt(Instant.now())
                                    .genre(book.getGenre())
                                    .build())
                            .user(user)
                            .createdAt(Instant.now())
                            .build());
                    log.info("process=persistFeedback, status=success," +
                            " message=successfully saved user feedback for recommendation={}", book.getId());
                }
            }
        }
        return responseDto;
    }
}

