package com.orca.patterns;

import java.util.*;
import java.util.concurrent.*;

/**
 * Week 2 실습: CommandInvoker 클래스 구현
 *
 * TODO: Command Pattern의 Invoker 역할을 하는 클래스를 완성하세요.
 *
 * 요구사항:
 * 1. 명령 실행 관리
 * 2. 실행 히스토리 관리
 * 3. 동기/비동기 실행 지원
 * 4. 실행 통계 제공
 */
public class CommandInvoker {

    // TODO: 필드들을 선언하세요
    // 힌트: commandHistory, executorService, eventPublisher 등이 필요할 수 있습니다

    private final List<Command> commandHistory;
    private final ExecutorService executorService;
    private final Map<String, Integer> executionStats;

    // TODO: EventPublisher 필드 추가
    private EventPublisher eventPublisher;

    /**
     * TODO: 생성자를 구현하세요
     */
    public CommandInvoker() {
        // TODO: 필드들을 초기화하세요
        this.commandHistory = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(5); // 비동기 실행용
        this.executionStats = new HashMap<>();

        // TODO: EventPublisher 초기화
        this.eventPublisher = eventPublisher;
    }

    /**
     * TODO: 동기적으로 명령을 실행하는 메소드를 구현하세요
     *
     * @param command 실행할 명령
     * @return 실행 성공 여부
     */
    public boolean executeCommand(Command command) {
        try {
            // TODO: 명령 실행 로직을 구현하세요
            // 힌트:
            // 1. 명령 실행 전 이벤트 발행
            // 2. 명령 실행
            // 3. 히스토리에 추가
            // 4. 통계 업데이트
            // 5. 실행 후 이벤트 발행

            System.out.println("Invoker executing command: " + command.getCommandId());

            // TODO: 실행 전 이벤트 발행
            // publishEvent("COMMAND_INVOKED", "Command invocation started: " + command.getCommandId());

            boolean success = command.execute();

            if (success) {
                // 히스토리에 추가
                commandHistory.add(command);

                // 통계 업데이트
                updateExecutionStats(command.getClass().getSimpleName(), true);

                System.out.println("Command executed successfully by invoker");

                // TODO: 성공 이벤트 발행
                // publishEvent("COMMAND_COMPLETED", "Command completed successfully: " + command.getCommandId());
            } else {
                // 실패 통계 업데이트
                updateExecutionStats(command.getClass().getSimpleName(), false);

                System.err.println("Command execution failed");

                // TODO: 실패 이벤트 발행
                // publishEvent("COMMAND_FAILED", "Command execution failed: " + command.getCommandId());
            }

            return success;

        } catch (Exception e) {
            System.err.println("Error in command execution: " + e.getMessage());
            updateExecutionStats(command.getClass().getSimpleName(), false);
            return false;
        }
    }

    /**
     * TODO: 비동기적으로 명령을 실행하는 메소드를 구현하세요
     *
     * @param command 실행할 명령
     * @return Future 객체 (실행 결과를 나중에 확인 가능)
     */
    public Future<Boolean> executeCommandAsync(Command command) {
        // TODO: 비동기 실행 로직을 구현하세요
        // 힌트: ExecutorService를 사용하여 별도 스레드에서 실행

        return executorService.submit(() -> {
            System.out.println("Async execution started for command: " + command.getCommandId());

            try {
                Thread.sleep(100); // 비동기 실행 시뮬레이션
                return executeCommand(command);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Async execution interrupted: " + e.getMessage());
                return false;
            }
        });
    }

    /**
     * TODO: 마지막 명령을 취소하는 메소드를 구현하세요
     *
     * @return 취소 성공 여부
     */
    public boolean undoLastCommand() {
        // TODO: 마지막 명령 취소 로직을 구현하세요
        // 힌트:
        // 1. 히스토리가 비어있지 않은지 확인
        // 2. 마지막 명령 가져오기
        // 3. 명령 취소 실행
        // 4. 히스토리에서 제거 (선택사항)

        if (commandHistory.isEmpty()) {
            System.out.println("No commands to undo");
            return false;
        }

        Command lastCommand = commandHistory.get(commandHistory.size() - 1);
        System.out.println("Undoing last command: " + lastCommand.getCommandId());

        boolean success = lastCommand.undo();

        if (success) {
            // TODO: 히스토리에서 제거할지 말지 결정
            // commandHistory.remove(commandHistory.size() - 1);

            System.out.println("Last command undone successfully");

            // TODO: 취소 이벤트 발행
            // publishEvent("COMMAND_UNDONE", "Command undone: " + lastCommand.getCommandId());
        } else {
            System.err.println("Failed to undo last command");
        }

        return success;
    }

    /**
     * TODO: 특정 명령을 취소하는 메소드를 구현하세요
     *
     * @param commandId 취소할 명령의 ID
     * @return 취소 성공 여부
     */
    public boolean undoCommand(String commandId) {
        // TODO: 특정 명령 취소 로직을 구현하세요

        for (Command command : commandHistory) {
            if (command.getCommandId().equals(commandId)) {
                System.out.println("Undoing command: " + commandId);
                return command.undo();
            }
        }

        System.out.println("Command not found: " + commandId);
        return false;
    }

    /**
     * TODO: 명령 히스토리를 반환하는 메소드를 구현하세요
     *
     * @return 명령 히스토리 (읽기 전용)
     */
    public List<Command> getCommandHistory() {
        // TODO: 읽기 전용 리스트 반환
        return new ArrayList<>(commandHistory);
    }

    /**
     * TODO: 실행 통계를 반환하는 메소드를 구현하세요
     *
     * @return 실행 통계
     */
    public Map<String, Integer> getExecutionStats() {
        // TODO: 읽기 전용 맵 반환
        return new HashMap<>(executionStats);
    }

    /**
     * TODO: 모든 대기 중인 비동기 작업을 완료하고 종료하는 메소드를 구현하세요
     */
    public void shutdown() {
        try {
            System.out.println("Shutting down CommandInvoker...");

            executorService.shutdown();

            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }

            System.out.println("CommandInvoker shut down completed");

        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    // TODO: 유틸리티 메소드들을 구현하세요

    /**
     * 실행 통계 업데이트
     */
    private void updateExecutionStats(String commandType, boolean success) {
        String key = commandType + (success ? "_SUCCESS" : "_FAILURE");
        executionStats.put(key, executionStats.getOrDefault(key, 0) + 1);
    }

    /**
     * 실행 통계 출력
     */
    public void printExecutionStats() {
        System.out.println("\n=== Execution Statistics ===");
        if (executionStats.isEmpty()) {
            System.out.println("No commands executed yet");
        } else {
            executionStats.forEach((key, value) ->
                System.out.println(key + ": " + value));
        }
        System.out.println("Total commands in history: " + commandHistory.size());
        System.out.println("============================\n");
    }

    /**
     * 명령 히스토리 출력
     */
    public void printCommandHistory() {
        System.out.println("\n=== Command History ===");
        if (commandHistory.isEmpty()) {
            System.out.println("No commands in history");
        } else {
            for (int i = 0; i < commandHistory.size(); i++) {
                Command cmd = commandHistory.get(i);
                System.out.printf("%d. %s - %s\n", i + 1, cmd.getCommandId(), cmd.getDescription());
            }
        }
        System.out.println("=======================\n");
    }

    // TODO: EventPublisher 관련 메소드 구현
    /*
    private void publishEvent(String eventType, String message) {
        if (eventPublisher != null) {
            ProcessEvent event = new ProcessEvent(
                UUID.randomUUID().toString(),
                eventType,
                message,
                System.currentTimeMillis(),
                "CommandInvoker"
            );
            eventPublisher.publishEvent(event);
        }
    }
    */
}