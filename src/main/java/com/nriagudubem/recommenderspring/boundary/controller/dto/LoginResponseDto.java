package com.nriagudubem.recommenderspring.boundary.controller.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class LoginResponseDto {
    @NotNull
    @NotBlank
    private final String token;

    @NotNull
    @NotBlank
    private static final String tokenType = "Bearer";
}

