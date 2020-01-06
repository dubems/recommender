package com.nriagudubem.recommenderspring.service;

import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackRequestDto;
import com.nriagudubem.recommenderspring.boundary.controller.dto.FeedbackResponseDto;
import com.nriagudubem.recommenderspring.entity.model.FeedbackType;
import com.nriagudubem.recommenderspring.entity.model.User;
import com.nriagudubem.recommenderspring.entity.repository.FeedbackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FeedbackServiceTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    private FeedbackService feedbackService;


    @Test
    void testPersistFeedbackWhenFeedbackExist_shouldReturnFeedback() {
        //given
        List<FeedbackRequestDto> dto = new ArrayList<>();
        Stream.of(IntStream.range(1, 4))
                .forEach((i) -> dto.add(FeedbackRequestDto.builder()
                        .feedback(FeedbackType.LIKED)
                        .recommendationIdentifier(UUID.randomUUID())
                        .build()));

        //when
        when(feedbackRepository.existsByBook_ASINAndFeedbackTypeAndUser(any(), any(), any())).thenReturn(true);
        User user = mock(User.class);
        List<FeedbackResponseDto> responseDtos = feedbackService.persistFeedback(dto, user);

        //then
        assertThat(responseDtos.isEmpty()).isFalse();
        assertThat(responseDtos.size()).isEqualTo(dto.size());

    }

    @Test
    void testPersistFeedbackWhenFeedbackDoesNotExist_shouldReturnEmptyList() {
        //given
        List<FeedbackRequestDto> dto = new ArrayList<>();
        Stream.of(IntStream.range(1, 4))
                .forEach((i) -> dto.add(FeedbackRequestDto.builder()
                        .feedback(FeedbackType.LIKED)
                        .recommendationIdentifier(UUID.randomUUID())
                        .build()));

        //when
        when(feedbackRepository.existsByBook_ASINAndFeedbackTypeAndUser(any(), any(), any())).thenReturn(false);
        User user = mock(User.class);
        List<FeedbackResponseDto> responseDtos = feedbackService.persistFeedback(dto, user);

        //then
        assertThat(responseDtos.isEmpty()).isTrue();
        verify(feedbackRepository,times(dto.size())).save(any());

    }
}
