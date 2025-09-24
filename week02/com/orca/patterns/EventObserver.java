package com.orca.patterns;

/**
 * Week 2 실습: EventObserver 인터페이스 구현
 *
 * TODO: Observer Pattern의 Observer 인터페이스를 완성하세요.
 *
 * 요구사항:
 * 1. 이벤트를 받아 처리하는 메소드가 있어야 합니다
 * 2. Observer의 식별자를 반환하는 메소드가 있어야 합니다
 */
public interface EventObserver {

    // TODO: 이벤트 처리 메소드를 선언하세요
    // 메소드명: onEvent, 파라미터: ProcessEvent event, 반환타입: void
    public void onEvent(ProcessEvent event);

    // TODO: Observer ID 반환 메소드를 선언하세요
    // 메소드명: getObserverId, 파라미터: 없음, 반환타입: String
    public String getObserverId();

}