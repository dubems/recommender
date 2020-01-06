package com.nriagudubem.recommenderspring.boundary.controller;

import com.nriagudubem.recommenderspring.boundary.controller.dto.LoginRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.LoginResponseDto;
import com.nriagudubem.recommenderspring.boundary.controller.exceptions.InvalidCredentialsException;
import com.nriagudubem.recommenderspring.security.jwt.JwtProvider;
import com.nriagudubem.recommenderspring.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api", produces ="application/json", consumes = "application/json")
@RequiredArgsConstructor
@Log4j2
@Api
public class AuthenticationController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    @ApiOperation(value = "")
    @PostMapping(path = "/v1/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public LoginResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
        if (!userService.userExists(dto.getUsername())) {
            throw new InvalidCredentialsException(dto.getUsername());
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        return LoginResponseDto.builder().token(jwtProvider.generateToken(auth)).build();
    }
}
