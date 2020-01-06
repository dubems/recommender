package com.nriagudubem.recommenderspring.boundary.controller.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
public class LoginRequestDto {
    @NotBlank
    @NotNull
    private final String username;

    @NotBlank
    @NotNull
    private final String password;
}
