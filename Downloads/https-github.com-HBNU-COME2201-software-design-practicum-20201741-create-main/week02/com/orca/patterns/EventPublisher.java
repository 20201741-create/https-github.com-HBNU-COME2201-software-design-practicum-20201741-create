package com.orca.patterns;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Week 2 실습: EventPublisher 클래스 구현 (Observer Pattern - Subject)
 *
 * TODO: Observer Pattern의 Subject/Publisher를 구현하세요.
 *
 * 요구사항:
 * 1. Observer들을 관리할 수 있어야 합니다 (추가/제거)
 * 2. 이벤트를 모든 Observer에게 알릴 수 있어야 합니다
 * 3. Thread-safe해야 합니다
 * 4. 예외 처리가 포함되어야 합니다
 */
public class EventPublisher {

    // TODO: Observer 목록을 저장할 필드를 선언하세요
    // 힌트: CopyOnWriteArrayList<EventObserver>를 사용하세요 (Thread-safe)
    private List<EventObserver> observerCount;

    // TODO: Publisher ID를 저장할 필드를 선언하세요
    private String publisherId;

    // TODO: 생성자를 구현하세요
    public EventPublisher(String publisherId) {
        // 구현하세요
        this.observerCount = new CopyOnWriteArrayList<>();
        this.publisherId = publisherId;
    }

    /**
     * TODO: Observer 등록 메소드를 구현하세요
     * @param observer 등록할 Observer
     */
    public synchronized void addObserver(EventObserver observer) {
        // TODO: 구현하세요
        // 1. observer가 null인지 검사
        // 2. 중복이 아닌 경우에만 추가
        // 3. 등록 완료 메시지 출력
        if (observer == null) {
            throw new IllegalArgumentException("No data to observer");
        }
        if (!observerCount.contains(observer)) {
            observerCount.add(observer);
            System.out.println("An observer has been added");
        }
    }

    /**
     * TODO: Observer 제거 메소드를 구현하세요
     * @param observer 제거할 Observer
     */
    public synchronized void removeObserver(EventObserver observer) {
        // TODO: 구현하세요
        observerCount.remove(observer);
    }

    /**
     * TODO: 이벤트 발행 메소드를 구현하세요
     * @param event 발행할 이벤트
     */
    public void publishEvent(ProcessEvent event) {
        // TODO: 구현하세요
        // 1. event가 null인지 검사
        // 2. 발행 메시지 출력
        // 3. 모든 observer에게 onEvent 호출 (예외 처리 포함)
        if (event == null) {
            throw new IllegalArgumentException("No data to event");
        }
        for (EventObserver observer : observerCount) {
            try {
                observer.onEvent(event);
            } catch (Exception e) {
                System.err.println("Failed to publish event: " + e.getMessage());
            }
        }
    }

    // TODO: 나머지 메소드들을 구현하세요
    // getObserverCount(), getPublisherId(), clearObservers()
    public int getObserverCount() {
        return this.observerCount.size();
    }
    public String getPublisherId() {
        return this.publisherId;
    }
    public void clearObservers() {
        this.observerCount.clear();
    }
}