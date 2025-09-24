package com.orca.patterns;

import java.util.UUID;

/**
 * Week 2 실습: ProcessCommand 클래스 구현
 *
 * TODO: Command Pattern의 구체적인 Command 구현체를 완성하세요.
 *
 * 요구사항:
 * 1. Processor를 사용한 데이터 처리 명령
 * 2. 실행/취소 상태 관리
 * 3. 이벤트 발행 기능 포함
 * 4. 실행 결과 저장
 */
public class ProcessCommand implements Command {

    // TODO: 필드들을 선언하세요
    // 힌트: processor, inputData, result, executed, commandId, description, eventPublisher 등이 필요할 수 있습니다

    private final String commandId;
    private final String description;
    private String inputData;
    private String result;
    private boolean executed = false;

    // TODO: EventPublisher 필드 추가 (이벤트 발행용)
    // private EventPublisher eventPublisher;

    // TODO: Processor 필드 추가 (실제 처리 로직용)
    // 힌트: Week01에서 사용한 Processor 인터페이스를 활용하세요
    // private Processor processor;

    /**
     * TODO: 생성자를 구현하세요
     *
     * @param processor 데이터를 처리할 프로세서
     * @param inputData 처리할 입력 데이터
     * @param description 명령 설명
     * @param eventPublisher 이벤트 발행기
     */
    public ProcessCommand(String inputData, String description) {
        this.commandId = "CMD-" + UUID.randomUUID().toString().substring(0, 8);
        this.inputData = inputData;
        this.description = description;
        // TODO: 다른 필드들도 초기화하세요
    }

    @Override
    public boolean execute() {
        try {
            // TODO: 명령 실행 로직을 구현하세요
            // 힌트:
            // 1. 이미 실행된 명령인지 확인
            // 2. Processor를 사용해 데이터 처리
            // 3. 결과 저장
            // 4. 실행 상태 업데이트
            // 5. 성공 이벤트 발행

            if (executed) {
                System.out.println("Command already executed: " + commandId);
                return false;
            }

            System.out.println("Executing command: " + description);

            // TODO: 실제 processor를 사용한 처리 로직 구현
            // result = processor.process(inputData);

            // 임시 구현 (학생이 수정해야 함)
            result = "Processed: " + inputData;
            executed = true;

            // TODO: 성공 이벤트 발행
            // ProcessEvent event = new ProcessEvent(commandId, "COMMAND_EXECUTED",
            //     "Command executed successfully", System.currentTimeMillis(), this.getClass().getSimpleName());
            // eventPublisher.publishEvent(event);

            System.out.println("Command executed successfully. Result: " + result);
            return true;

        } catch (Exception e) {
            // TODO: 실패 이벤트 발행
            System.err.println("Command execution failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean undo() {
        try {
            // TODO: 명령 취소 로직을 구현하세요
            // 힌트:
            // 1. 실행된 명령인지 확인
            // 2. 취소 가능한 명령인지 확인
            // 3. 이전 상태로 복원
            // 4. 실행 상태 업데이트
            // 5. 취소 이벤트 발행

            if (!executed) {
                System.out.println("Command not executed yet: " + commandId);
                return false;
            }

            System.out.println("Undoing command: " + description);

            // TODO: 실제 취소 로직 구현
            // 힌트: 원본 데이터 복원이나 역연산 수행

            result = null;
            executed = false;

            // TODO: 취소 이벤트 발행
            // ProcessEvent event = new ProcessEvent(commandId, "COMMAND_UNDONE",
            //     "Command undone successfully", System.currentTimeMillis(), this.getClass().getSimpleName());
            // eventPublisher.publishEvent(event);

            System.out.println("Command undone successfully");
            return true;

        } catch (Exception e) {
            System.err.println("Command undo failed: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCommandId() {
        return commandId;
    }

    // TODO: 추가 유틸리티 메소드들을 구현하세요

    /**
     * 명령 실행 여부 확인
     * @return 실행 여부
     */
    public boolean isExecuted() {
        return executed;
    }

    /**
     * 실행 결과 반환
     * @return 실행 결과 (실행되지 않았으면 null)
     */
    public String getResult() {
        return result;
    }

    /**
     * 입력 데이터 반환
     * @return 입력 데이터
     */
    public String getInputData() {
        return inputData;
    }

    /**
     * 명령 정보 문자열 반환
     * @return 명령 정보
     */
    @Override
    public String toString() {
        return String.format("ProcessCommand{id='%s', description='%s', executed=%s, result='%s'}",
            commandId, description, executed, result);
    }
}