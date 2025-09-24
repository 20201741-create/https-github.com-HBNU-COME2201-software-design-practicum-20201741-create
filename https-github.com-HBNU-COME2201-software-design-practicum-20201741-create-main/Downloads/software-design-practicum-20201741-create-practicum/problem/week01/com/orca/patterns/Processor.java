package com.orca.patterns;

/**
 * Week 1 실습: Processor 인터페이스 구현
 *
 * TODO: 아래 요구사항에 맞게 Processor 인터페이스를 완성하세요.
 *
 * 요구사항:
 * 1. 모든 processor 구현체가 공통으로 사용할 메소드를 정의해야 합니다
 * 2. 데이터를 처리하는 메소드가 있어야 합니다 (String 입력, String 반환)
 * 3. Processor의 타입을 반환하는 메소드가 있어야 합니다
 * 4. 초기화 상태를 확인하는 메소드가 있어야 합니다
 *
 * 힌트: 인터페이스는 public abstract 메소드들로 구성됩니다
 */
public interface Processor {

    // TODO: 데이터를 처리하는 메소드를 선언하세요
    // 메소드명: process, 파라미터: String data, 반환타입: String
    String process(String data);

    // TODO: Processor 타입을 반환하는 메소드를 선언하세요
    // 메소드명: getType, 파라미터: 없음, 반환타입: String
    String getType();

    // TODO: 초기화 상태를 확인하는 메소드를 선언하세요
    // 메소드명: isInitialized, 파라미터: 없음, 반환타입: boolean
    boolean isInitialized();

}
