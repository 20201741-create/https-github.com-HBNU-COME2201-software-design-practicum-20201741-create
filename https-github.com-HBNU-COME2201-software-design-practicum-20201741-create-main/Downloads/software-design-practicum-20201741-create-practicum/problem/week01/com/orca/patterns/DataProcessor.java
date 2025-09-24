package com.orca.patterns;

/**
 * Week 1 실습: DataProcessor 구현
 *
 * TODO: Processor 인터페이스를 구현하는 DataProcessor 클래스를 완성하세요.
 *
 * 요구사항:
 * 1. Processor 인터페이스를 구현해야 합니다
 * 2. type은 "DATA"로 설정해야 합니다
 * 3. 생성자에서 초기화를 완료해야 합니다
 * 4. process 메소드에서 데이터를 대문자로 변환하여 "Processed: " 접두사를 붙여 반환해야 합니다
 * 5. 적절한 유효성 검사를 포함해야 합니다
 */

// TODO: Processor 인터페이스를 구현하도록 클래스를 수정하세요
public class DataProcessor implements Processor {

    // TODO: 필요한 필드들을 선언하세요
    // 힌트: type (String), initialized (boolean) 등이 필요합니다
    private final String type;
    private final boolean initialized;

    // TODO: 생성자를 구현하세요
    // 힌트: initialized를 true로 설정해야 합니다
    public DataProcessor() {
        // 구현하세요
        this.type = "DATA";
        this.initialized = true;
    }

    // TODO: process 메소드를 구현하세요
    // 파라미터가 null이거나 빈 문자열인 경우 IllegalArgumentException을 발생시키세요
    // 초기화되지 않은 경우 IllegalStateException을 발생시키세요
    // 정상적인 경우 data를 대문자로 변환하여 "Processed: " + data.toUpperCase() 형태로 반환하세요
    public String process(String data) {
        if (!initialized) {
            throw new IllegalStateException("프로세서가 초기화되지 않았습니다.");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 데이터는 null이거나 공백만 포함한 문자열일 수 없습니다.");
        }
        return "Processed: " + data.toUpperCase();
    }

    // TODO: getType 메소드를 구현하세요
    // "DATA"를 반환해야 합니다
    public String getType() {
        return this.type;
    }

    // TODO: isInitialized 메소드를 구현하세요
    // initialized 필드의 값을 반환해야 합니다
    public boolean isInitialized() {
        return this.initialized;
    }
}
