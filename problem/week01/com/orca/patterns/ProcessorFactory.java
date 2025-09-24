package com.orca.patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Week 1 실습: ProcessorFactory 구현 (Factory Pattern)
 *
 * TODO: Factory Pattern을 사용하여 ProcessorFactory를 완성하세요.
 *
 * 요구사항:
 * 1. Map을 사용하여 processor 타입별 생성자를 등록해야 합니다
 * 2. createProcessor 메소드로 타입에 맞는 Processor 인스턴스를 생성해야 합니다
 * 3. 지원하지 않는 타입에 대해서는 적절한 예외를 발생시켜야 합니다
 * 4. 대소문자를 구분하지 않도록 구현해야 합니다 (DATA, data 모두 지원)
 * 5. 확장 가능한 구조여야 합니다 (새로운 타입 추가 용이)
 */
public class ProcessorFactory {

    // TODO: processor 타입별 생성자를 저장할 Map을 선언하세요
    // 힌트: Map<String, Supplier<Processor>>
    private static final Map<String, Supplier<Processor>> processorRegistry = new HashMap<>();

    // TODO: static 초기화 블록을 만들어 지원되는 타입들을 등록하세요
    static {
        // 힌트:
        processorRegistry.put("DATA", DataProcessor::new);
        processorRegistry.put("COMPUTE", ComputeProcessor::new);
        processorRegistry.put("IO", IOProcessor::new);
        // 소문자 버전도 등록: "data", "compute", "io"
        processorRegistry.put("data", DataProcessor::new);
        processorRegistry.put("compute", ComputeProcessor::new);
        processorRegistry.put("io", IOProcessor::new);
    }

    /**
     * TODO: createProcessor 메소드를 구현하세요
     *
     * @param type processor 타입 (예: "DATA", "COMPUTE", "IO")
     * @return 해당 타입의 Processor 인스턴스
     * @throws IllegalArgumentException 지원하지 않는 타입이거나 null/빈 문자열인 경우
     */
    public static Processor createProcessor(String type) {
        // TODO: 구현하세요
        // 1. type이 null이거나 비어있는지 검사
        // 2. processorRegistry에서 해당 타입의 Supplier를 찾기
        // 3. Supplier가 없으면 IllegalArgumentException 발생
        // 4. Supplier를 사용하여 인스턴스 생성 후 반환
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 데이터는 null이거나 공백만 포함한 문자열일 수 없습니다.");
        }
        Supplier<Processor> supplier = processorRegistry.get(type);
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier가 없습니다.");
        }
        return supplier.get();
        // throw new UnsupportedOperationException("아직 구현되지 않았습니다");
    }

    /**
     * TODO: registerProcessor 메소드를 구현하세요 (확장성을 위한 메소드)
     *
     * @param type processor 타입 이름
     * @param supplier processor 생성자
     * @throws IllegalArgumentException type이나 supplier가 null인 경우
     */
    public static void registerProcessor(String type, Supplier<Processor> supplier) {
        // TODO: 구현하세요
        if (type == null || type.trim().isEmpty() || supplier == null) {
            throw new IllegalArgumentException("Processor 타입과 Supplier는 null이거나 비어있을 수 없습니다.");
        }      
        String upperType = type.toUpperCase();
        String lowerType = type.toLowerCase();
        processorRegistry.put(upperType, supplier);
        processorRegistry.put(lowerType, supplier);
    }

    /**
     * TODO: getSupportedTypes 메소드를 구현하세요
     *
     * @return 지원되는 타입들의 배열
     */
    public static String[] getSupportedTypes() {
        // TODO: 구현하세요 (processorRegistry의 키들을 배열로 반환)
        // return new String[0]; // 임시 반환값
        return processorRegistry.keySet().toArray(new String[0]);
    }
}
