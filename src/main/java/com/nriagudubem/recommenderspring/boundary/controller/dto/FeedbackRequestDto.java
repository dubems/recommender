package com.nriagudubem.recommenderspring.boundary.controller.dto;

import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Getter
public class FeedbackRequestDto {

    @NotNull
    private final UUID recommendationIdentifier;

    @NotNull
    private final FeedbackType feedback;

    public FeedbackResponseDto toResponseDto(Book book) {
        return FeedbackResponseDto.builder()
                .feedback(this.getFeedback())
                .genre(book.getGenre())
                .title(book.getTitle())
                .author(book.getAuthor())
                .build();
    }
}
