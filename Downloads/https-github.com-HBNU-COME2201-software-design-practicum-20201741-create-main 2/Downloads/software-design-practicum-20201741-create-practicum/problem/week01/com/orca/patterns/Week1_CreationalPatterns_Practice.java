package com.orca.patterns;

/**
 * Week 1 실습: Creational Patterns 연습 (Factory & Singleton)
 *
 * TODO: 완성된 Factory와 Singleton Pattern을 사용하여 이 클래스를 완성하세요.
 *
 * 요구사항:
 * 1. main 메소드에서 Singleton과 Factory Pattern의 올바른 동작을 확인해야 합니다
 * 2. 여러 타입의 Processor를 생성하고 사용해야 합니다
 * 3. NodeManager의 싱글톤 동작을 검증해야 합니다
 * 4. 에러 케이스도 테스트해야 합니다
 */
public class Week1_CreationalPatterns_Practice {

    public static void main(String[] args) {
        System.out.println("=== Week 1: Creational Patterns Practice ===\n");

        // TODO: 1단계 - Singleton Pattern 테스트를 구현하세요
        testSingletonPattern();

        System.out.println();

        // TODO: 2단계 - Factory Pattern 테스트를 구현하세요
        testFactoryPattern();

        System.out.println();

        // TODO: 3단계 - 통합 시나리오 테스트를 구현하세요
        testIntegrationScenario();
    }

    /**
     * TODO: Singleton Pattern 동작을 테스트하는 메소드를 구현하세요
     */
    private static void testSingletonPattern() {
        System.out.println("--- Singleton Pattern Test ---");

        // TODO: 구현해야 할 내용:
        // 1. NodeManager.getInstance()를 두 번 호출하여 같은 인스턴스인지 확인
        // 2. 두 인스턴스의 nodeId가 같은지 확인
        // 3. 한 인스턴스에 노드를 추가하고 다른 인스턴스에서 조회
        // 4. 결과를 콘솔에 출력
        NodeManager manager1 = NodeManager.getInstance();
        NodeManager manager2 = NodeManager.getInstance();

        System.out.println("같은 인스턴스인가?: " + (manager1 == manager2));
        System.out.println("Manager 1 ID: " + manager1.getNodeId());
        System.out.println("Manager 2 ID: " + manager2.getNodeId());
        System.out.println("같은 nodeId인가?: " + manager1.getNodeId().equals(manager2.getNodeId()));

        final String TEST_NODE_ID = "New-Shared-Task-Node";
        manager1.addNode(TEST_NODE_ID);
        boolean isNodeShared = manager2.getConnectedNodes().contains(TEST_NODE_ID);
        System.out.println("Manager 2 노드 [" + TEST_NODE_ID + "] 연결 확인: " + isNodeShared);

        System.out.println("Singleton test completed\n");
    }

    /**
     * TODO: Factory Pattern 동작을 테스트하는 메소드를 구현하세요
     */
    private static void testFactoryPattern() {
        System.out.println("--- Factory Pattern Test ---");

        // TODO: 구현해야 할 내용:
        // 1. ProcessorFactory.getSupportedTypes()로 지원 타입 출력
        // 2. "DATA", "COMPUTE", "IO" 타입의 processor 각각 생성
        // 3. 각 processor의 getType(), isInitialized() 호출
        // 4. 각 processor의 process() 메소드로 "test data" 처리
        // 5. 결과를 콘솔에 출력
        System.out.println(java.util.Arrays.toString(ProcessorFactory.getSupportedTypes()));
        final String TEST_INPUT = "test data";

        Processor dataProcessor = ProcessorFactory.createProcessor("DATA");
        Processor computeProcessor = ProcessorFactory.createProcessor("COMPUTE");
        Processor ioProcessor = ProcessorFactory.createProcessor("IO");

        System.out.println(dataProcessor.getType());
        System.out.println(dataProcessor.isInitialized());
        System.out.println(dataProcessor.process(TEST_INPUT));

        System.out.println(computeProcessor.getType());
        System.out.println(computeProcessor.isInitialized());
        System.out.println(computeProcessor.process(TEST_INPUT));

        System.out.println(ioProcessor.getType());
        System.out.println(ioProcessor.isInitialized());
        System.out.println(ioProcessor.process(TEST_INPUT));

        // TODO: 에러 케이스 테스트
        // 1. 지원하지 않는 타입("UNKNOWN")으로 processor 생성 시도
        // 2. null 타입으로 processor 생성 시도
        // 3. 각각의 예외를 적절히 처리하고 메시지 출력
        try {
            ProcessorFactory.createProcessor("UNKNOWN");
        } catch (IllegalArgumentException e) {
            System.err.println("Error testing UNKNOWN: " + e.getMessage());
        }
        try {
            ProcessorFactory.createProcessor(null);
        } catch (IllegalArgumentException e) {
            System.err.println("Error testing null: " + e.getMessage());
        }

        System.out.println("Factory test completed\n");
    }

    /**
     * TODO: Factory와 Singleton이 함께 동작하는 시나리오를 테스트하는 메소드를 구현하세요
     */
    private static void testIntegrationScenario() {
        System.out.println("--- Integration Scenario Test ---");

        // TODO: 구현해야 할 내용:
        // 1. NodeManager 싱글톤 인스턴스 획득
        // 2. 여러 타입의 작업을 처리하는 시나리오 구현:
        //    - "DATA:User Registration" → DataProcessor 사용
        //    - "COMPUTE:Performance Analysis" → ComputeProcessor 사용
        //    - "IO:File Processing" → IOProcessor 사용
        // 3. 각 작업마다:
        //    - 해당 타입의 processor를 Factory에서 생성
        //    - 작업 데이터를 process() 메소드로 처리
        //    - NodeManager에 작업 노드 추가 (예: "task-data", "task-compute", "task-io")
        //    - 처리 결과 출력
        // 4. 최종적으로 NodeManager의 상태 출력
        NodeManager nodeManager = NodeManager.getInstance();
        processTask(nodeManager, "DATA:User Registration");
        processTask(nodeManager, "COMPUTE:Performance Analysis");
        processTask(nodeManager, "IO:File Processing");
        processTask(nodeManager, "UNKNOWN:Error Scenario Test");

        System.out.println(nodeManager.toString());
        System.out.println(nodeManager.getConnectedNodeCount()); 

        System.out.println("Integration test completed\n");
    }

    /**
     * TODO: 특정 타입의 processor를 테스트하는 헬퍼 메소드를 구현하세요
     *
     * @param type processor 타입
     * @param testData 테스트할 데이터
     */
    private static void testProcessor(String type, String testData) {
        try {
            // TODO: 구현하세요
            // 1. ProcessorFactory로 processor 생성
            // 2. processor 정보 출력 (타입, 초기화 상태)
            // 3. testData를 process() 메소드로 처리
            // 4. 결과 출력
            Processor processor = ProcessorFactory.createProcessor(type);
            System.out.println(processor.getType());
            System.out.println(processor.isInitialized());
            String result = processor.process(testData);
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Error testing " + type + " processor: " + e.getMessage());
        }
    }

    /**
     * TODO: 작업을 처리하는 헬퍼 메소드를 구현하세요
     *
     * @param nodeManager NodeManager 인스턴스
     * @param task "타입:데이터" 형태의 작업 문자열
     */
    private static void processTask(NodeManager nodeManager, String task) {
        try {
            // TODO: 구현하세요
            // 1. task를 ":"으로 분리하여 타입과 데이터 추출
            // 2. ProcessorFactory로 해당 타입의 processor 생성
            // 3. processor로 데이터 처리
            // 4. NodeManager에 작업 노드 추가
            // 5. 처리 결과 출력
            String[] parts = task.split(":", 2);
            String type = parts[0];
            String data = parts[1];

            Processor processor = ProcessorFactory.createProcessor(type);
            String result = processor.process(data);
            nodeManager.addNode(result);

            System.out.println(type);
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Failed to process task: " + task + " - " + e.getMessage());
        }
    }
}

/*
실습 완료 후 확인사항:

1. Factory Pattern 구현 확인:
   ✓ 하드코딩된 if-else 대신 Map 사용
   ✓ 새로운 processor 타입 추가 용이
   ✓ 일관된 인터페이스 제공
   ✓ 적절한 예외 처리

2. Singleton Pattern 구현 확인:
   ✓ Thread-safe 구현
   ✓ 하나의 인스턴스만 생성
   ✓ Lazy initialization
   ✓ 비즈니스 로직 분리

3. 통합 동작 확인:
   ✓ Factory와 Singleton이 함께 동작
   ✓ 실제 사용 시나리오 구현
   ✓ 에러 처리 포함

4. 코드 품질 확인:
   ✓ SOLID 원칙 준수
   ✓ 적절한 주석과 문서화
   ✓ 확장 가능한 구조
*/
