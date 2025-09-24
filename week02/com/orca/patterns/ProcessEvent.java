package com.orca.patterns;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Week 2 실습: ProcessEvent 클래스 구현
 *
 * TODO: 프로세스 이벤트 정보를 담는 클래스를 완성하세요.
 */
public class ProcessEvent {

    // TODO: 필요한 필드들을 선언하세요
    // eventId (String), eventType (String), message (String), timestamp (LocalDateTime), sourceId (String)
    private String eventId;
    private String eventType;
    private String message;
    private LocalDateTime timestamp;
    private String sourceId;

    // TODO: 생성자를 구현하세요
    // 파라미터: eventType, message, sourceId
    // eventId는 UUID로 자동 생성, timestamp는 현재 시간으로 설정
    public ProcessEvent(String eventType, String message, String sourceId) {
        // 구현하세요
        this.eventId = UUID.randomUUID().toString();
        this.eventType = eventType;
        this.message = message;
        this.timestamp = LocalDateTime.now();
        this.sourceId = sourceId;
    }

    // TODO: 모든 필드에 대한 getter 메소드들을 구현하세요
    public String getEventId() {
        return this.eventId;
    }
    public String getEventType() {
        return this.eventType;
    }
    public String getMessage() {
        return this.message;
    }
    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
    public String getSource() {
        return this.sourceId;
    }

    // TODO: toString 메소드를 구현하세요
    public String toString() {
        return "ProcessEvent{" +
                "eventId='" + eventId + '\'' +
                ", eventType=" + eventType + '\'' +
                ", message=" + message + '\'' +
                ", timestamp=" + timestamp + '\'' +
                ", sourceId='" + sourceId +
                '}';
    }

}