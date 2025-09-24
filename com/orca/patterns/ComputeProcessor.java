package com.orca.patterns;

/**
 * Week 1 실습: ComputeProcessor 구현
 *
 * TODO: Processor 인터페이스를 구현하는 ComputeProcessor 클래스를 완성하세요.
 *
 * 요구사항:
 * 1. Processor 인터페이스를 구현해야 합니다
 * 2. type은 "COMPUTE"로 설정해야 합니다
 * 3. process 메소드에서 문자열의 길이를 계산하여 "Computed: " + 길이 형태로 반환해야 합니다
 * 4. DataProcessor와 동일한 유효성 검사를 포함해야 합니다
 */

// TODO: 클래스 선언을 완성하세요 (인터페이스 구현 포함)
public class ComputeProcessor implements Processor {

    // TODO: 필요한 필드들을 선언하세요
    private final String type;
    private final boolean initialized;

    // TODO: 생성자를 구현하세요
    public ComputeProcessor() {
        this.type = "COMPUTE";
        this.initialized = true;
    }

    // TODO: 모든 인터페이스 메소드들을 구현하세요
    // process 메소드는 문자열의 길이를 계산하여 "Computed: " + data.length() 형태로 반환
    public String process(String data) {
        if (!initialized) {
            throw new IllegalStateException("프로세서가 초기화되지 않았습니다.");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 데이터는 null이거나 공백만 포함한 문자열일 수 없습니다.");
        }
        return "Computed: " + data.length();
    }

    public String getType() {
        return this.type;
    }

    public boolean isInitialized() {
        return this.initialized;
    }
}
