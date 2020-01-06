package com.nriagudubem.recommenderspring.boundary.controller;

import com.nriagudubem.recommenderspring.boundary.controller.dto.CreateUserRequestDto;
import com.nriagudubem.recommenderspring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(path = "/v1/users")
    @ResponseStatus(HttpStatus.OK)
    public void createUser(@RequestBody @Valid CreateUserRequestDto createUserRequestDto) {
        userService.createUser(createUserRequestDto);
    }

}
