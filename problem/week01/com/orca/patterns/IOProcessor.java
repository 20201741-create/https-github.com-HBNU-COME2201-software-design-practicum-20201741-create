package com.orca.patterns;

/**
 * Week 1 실습: IOProcessor 구현
 *
 * TODO: Processor 인터페이스를 구현하는 IOProcessor 클래스를 완성하세요.
 *
 * 요구사항:
 * 1. Processor 인터페이스를 구현해야 합니다
 * 2. type은 "IO"로 설정해야 합니다
 * 3. process 메소드에서 "I/O completed for: " + data 형태로 반환해야 합니다
 */

// TODO: 클래스 전체를 구현하세요
// 힌트: DataProcessor와 ComputeProcessor의 구조를 참고하세요
public class IOProcessor implements Processor {

    private final String type;
    private final boolean initialized;

    public IOProcessor() {
        this.type = "IO";
        this.initialized = true;
    }

    public String process(String data) {
        if (!initialized) {
            throw new IllegalStateException("프로세서가 초기화되지 않았습니다.");
        }
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 데이터는 null이거나 공백만 포함한 문자열일 수 없습니다.");
        }
        return "I/O completed for: " + data;
    }

    public String getType() {
        return this.type;
    }

    public boolean isInitialized() {
        return this.initialized;
    }
}
