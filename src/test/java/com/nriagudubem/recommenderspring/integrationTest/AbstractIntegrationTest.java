package com.nriagudubem.recommenderspring.integrationTest;

import com.nriagudubem.recommenderspring.entity.model.Book;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.entity.repository.BookRepository;
import com.nriagudubem.recommenderspring.entity.repository.FeedbackRepository;
import com.nriagudubem.recommenderspring.entity.repository.UserRepository;
import com.nriagudubem.recommenderspring.integrationTest.utils.HttpRequestsHelper;
import com.nriagudubem.recommenderspring.service.FeedbackService;
import com.nriagudubem.recommenderspring.service.RecommendationService;
import com.nriagudubem.recommenderspring.service.UserService;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Tag("integration-test")
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class AbstractIntegrationTest {

    @LocalServerPort
    protected int serverPort;

    @Autowired
    protected FeedbackService feedbackService;

    @Autowired
    protected RecommendationService recommendationService;

    @Autowired
    protected UserService userService;

    @Autowired
    protected BookRepository bookRepository;

    @Autowired
    protected FeedbackRepository feedbackRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected HttpRequestsHelper httpRequestsHelper;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    protected User user;

    @BeforeAll
    protected void setupAppPort() {
        RestAssured.port = serverPort;
        user = userRepository.save(
                User.builder()
                .username("chidubem")
                .password(passwordEncoder.encode("password"))
                .createdAt(Instant.now())
                .build());

        bookRepository.save(Book.builder()
                .title("the best title")
                .genre("classicals")
                .genre("My genre")
                .author("Chidubem")
                .ASIN(2392L)
                .build());
    }

    @AfterAll
    void cleanUp() {
        userRepository.deleteAll();
        feedbackRepository.deleteAll();
        bookRepository.deleteAll();
        // clear the DB
    }

}
