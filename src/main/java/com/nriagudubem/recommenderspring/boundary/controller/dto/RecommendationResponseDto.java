package com.nriagudubem.recommenderspring.boundary.controller.dto;

import com.nriagudubem.recommenderspring.entity.model.Book;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class RecommendationResponseDto {
    private final UUID identifier;

    private final String title;

    private final String author;

    private final String genre;

    public static RecommendationResponseDto of(Book book) {
        return RecommendationResponseDto.builder()
                .genre(book.getGenre())
                .author(book.getAuthor())
                .title(book.getTitle())
                .identifier(book.getId())
                .build();
    }

}
