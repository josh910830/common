package com.github.suloginscene.event;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;


@Slf4j
@DisplayName("이벤트 퍼블리셔")
class EventPublisherTest {

    @Test
    @DisplayName("위임 발행")
    void publish() {
        ApplicationEventPublisher mock = mock(ApplicationEventPublisher.class);
        EventPublisher eventPublisher = new EventPublisher(mock);

        eventPublisher.publish(mock(Event.class));

        then(mock).should().publishEvent(any(Event.class));
    }

}