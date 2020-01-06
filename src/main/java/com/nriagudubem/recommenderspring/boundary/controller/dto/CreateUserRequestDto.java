package com.nriagudubem.recommenderspring.boundary.controller.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateUserRequestDto {
    @NotNull
    private final String username;

    @NotNull
    private final String password;
}
