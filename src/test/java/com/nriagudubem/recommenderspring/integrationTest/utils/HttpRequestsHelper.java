package com.nriagudubem.recommenderspring.integrationTest.utils;

import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.LoginRequestDto;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.stereotype.Component;

import java.util.List;

import static io.restassured.RestAssured.given;

@Component
public class HttpRequestsHelper {

    private static final String AUTHENTICATE_URL = "/api/v1/authenticate";

    private static final String COLLECT_FEEDBACK = "/api/v1/feedback";

    public Response httpGet(String url) {
        return givenAnonymous().get(url);
    }

    public Response collectFeedback(List<FeedbackRequestDto> feedbackRequestDtos, LoginRequestDto dto) {
        return givenAuthorizedUser(dto).body(feedbackRequestDtos).post(COLLECT_FEEDBACK);
    }

    private RequestSpecification givenAnonymous() {
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    private RequestSpecification givenAuthorizedUser(LoginRequestDto requestDto) {
        final String jwtToken = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestDto)
                .post(AUTHENTICATE_URL)
                .thenReturn()
                .getBody()
                .jsonPath().get("token");

        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header(new Header("Authorization", "Bearer " + jwtToken));
    }


}
