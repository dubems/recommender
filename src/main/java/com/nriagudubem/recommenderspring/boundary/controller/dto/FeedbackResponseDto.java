package com.nriagudubem.recommenderspring.boundary.controller.dto;

import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FeedbackResponseDto {

    @NotNull
    private final String title;

    @NotNull
    private final String author;

    @NotNull
    private final Long ASIN;

    @NotNull
    private final String genre;

    @NotNull
    private FeedbackType feedback;
}
