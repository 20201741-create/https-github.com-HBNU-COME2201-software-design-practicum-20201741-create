package com.orca.patterns;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

/**
 * Week 2 실습: Behavioral Patterns 통합 테스트
 *
 * TODO: Observer Pattern과 Command Pattern을 통합한 실제 시나리오를 구현하세요.
 *
 * 이 클래스는 다음을 포함해야 합니다:
 * 1. Observer-Command 패턴 연동 데모
 * 2. 이벤트 기반 명령 실행
 * 3. 실시간 모니터링
 * 4. 에러 처리 및 복구
 */
public class Week2_BehavioralPatterns_Practice {

    private static EventPublisher eventPublisher;
    private static CommandInvoker commandInvoker;
    private static LoggingObserver loggingObserver;
    private static AlertingObserver alertingObserver;

    public static void main(String[] args) {
        try {
            System.out.println("=".repeat(60));
            System.out.println("Week 2: Behavioral Patterns Integration Demo");
            System.out.println("Observer Pattern + Command Pattern");
            System.out.println("=".repeat(60));

            // TODO: 시스템 초기화
            initializeSystem();

            // TODO: 데모 시나리오 실행
            runDemoScenarios();

            // TODO: 대화형 모드 (선택사항)
            // runInteractiveMode();

        } catch (Exception e) {
            System.err.println("Demo execution failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // TODO: 시스템 정리
            cleanupSystem();
        }
    }

    /**
     * TODO: 시스템 초기화 메소드를 구현하세요
     */
    private static void initializeSystem() {
        System.out.println("\n1. Initializing System Components...");

        // TODO: EventPublisher 생성
        eventPublisher = new EventPublisher("EventPublisher");
        System.out.println("   - EventPublisher created");

        // TODO: CommandInvoker 생성
        commandInvoker = new CommandInvoker();
        System.out.println("   - CommandInvoker created");

        // TODO: Observer들 생성 및 등록
        loggingObserver = new LoggingObserver("MAIN-LOGGER");
        alertingObserver = new AlertingObserver("ALERT-SYSTEM");

        eventPublisher.addObserver(loggingObserver);
        eventPublisher.addObserver(alertingObserver);
        System.out.println("   - Observers registered");

        // TODO: 초기화 이벤트 발행
        publishEvent("SYSTEM_INIT", "System initialization completed", "SystemBootstrap");

        System.out.println("System initialization completed successfully!\n");
    }

    /**
     * TODO: 데모 시나리오들을 실행하는 메소드를 구현하세요
     */
    private static void runDemoScenarios() {
        System.out.println("2. Running Demo Scenarios...\n");

        // 시나리오 1: 기본 Command 실행
        runBasicCommandScenario();

        // 시나리오 2: Observer 반응 테스트
        runObserverReactionScenario();

        // 시나리오 3: 에러 처리 테스트
        runErrorHandlingScenario();

        // 시나리오 4: 비동기 명령 실행
        runAsyncCommandScenario();

        // 시나리오 5: Undo 기능 테스트
        runUndoScenario();

        // 최종 통계 출력
        printFinalStatistics();
    }

    /**
     * 시나리오 1: 기본 Command 실행
     */
    private static void runBasicCommandScenario() {
        System.out.println("--- Scenario 1: Basic Command Execution ---");

        try {
            // TODO: 여러 ProcessCommand 생성 및 실행
            ProcessCommand cmd1 = new ProcessCommand("Hello World", "Simple greeting command");
            ProcessCommand cmd2 = new ProcessCommand("Data Processing", "Process sample data");
            ProcessCommand cmd3 = new ProcessCommand("File Operation", "File handling command");

            // 명령 실행
            commandInvoker.executeCommand(cmd1);
            publishEvent("COMMAND_DEMO", "First command executed", "DemoScenario1");

            commandInvoker.executeCommand(cmd2);
            publishEvent("COMMAND_DEMO", "Second command executed", "DemoScenario1");

            commandInvoker.executeCommand(cmd3);
            publishEvent("COMMAND_DEMO", "Third command executed", "DemoScenario1");

            System.out.println("Scenario 1 completed successfully\n");

        } catch (Exception e) {
            System.err.println("Scenario 1 failed: " + e.getMessage());
            publishEvent("SCENARIO_ERROR", "Scenario 1 execution failed: " + e.getMessage(), "DemoScenario1");
        }
    }

    /**
     * 시나리오 2: Observer 반응 테스트
     */
    private static void runObserverReactionScenario() {
        System.out.println("--- Scenario 2: Observer Reaction Test ---");

        try {
            // TODO: 다양한 타입의 이벤트 발행하여 Observer 반응 테스트
            publishEvent("INFO", "Normal information message", "DemoScenario2");
            publishEvent("WARNING", "This is a warning message", "DemoScenario2");
            publishEvent("ERROR", "This is an error message", "DemoScenario2");
            publishEvent("CRITICAL", "This is a critical error message", "DemoScenario2");

            // 잠시 대기 (비동기 처리 시뮬레이션)
            Thread.sleep(100);

            System.out.println("Scenario 2 completed successfully\n");

        } catch (Exception e) {
            System.err.println("Scenario 2 failed: " + e.getMessage());
            publishEvent("SCENARIO_ERROR", "Scenario 2 execution failed: " + e.getMessage(), "DemoScenario2");
        }
    }

    /**
     * 시나리오 3: 에러 처리 테스트
     */
    private static void runErrorHandlingScenario() {
        System.out.println("--- Scenario 3: Error Handling Test ---");

        try {
            // TODO: 의도적으로 실패하는 명령 생성 및 처리
            ProcessCommand errorCmd = new ProcessCommand("null", "Intentional error command");

            boolean success = commandInvoker.executeCommand(errorCmd);
            if (!success) {
                publishEvent("COMMAND_FAILED", "Command execution failed as expected", "DemoScenario3");
            }

            // 에러 복구 시나리오
            ProcessCommand recoveryCmd = new ProcessCommand("Recovery Data", "Recovery command");
            commandInvoker.executeCommand(recoveryCmd);
            publishEvent("RECOVERY", "System recovered from error", "DemoScenario3");

            System.out.println("Scenario 3 completed successfully\n");

        } catch (Exception e) {
            System.err.println("Scenario 3 failed: " + e.getMessage());
            publishEvent("SCENARIO_ERROR", "Scenario 3 execution failed: " + e.getMessage(), "DemoScenario3");
        }
    }

    /**
     * 시나리오 4: 비동기 명령 실행
     */
    private static void runAsyncCommandScenario() {
        System.out.println("--- Scenario 4: Async Command Execution ---");

        try {
            // TODO: 비동기 명령 실행 테스트
            ProcessCommand asyncCmd1 = new ProcessCommand("Async Data 1", "First async command");
            ProcessCommand asyncCmd2 = new ProcessCommand("Async Data 2", "Second async command");

            Future<Boolean> future1 = commandInvoker.executeCommandAsync(asyncCmd1);
            Future<Boolean> future2 = commandInvoker.executeCommandAsync(asyncCmd2);

            publishEvent("ASYNC_START", "Async commands submitted", "DemoScenario4");

            // 결과 대기
            Boolean result1 = future1.get(2, TimeUnit.SECONDS);
            Boolean result2 = future2.get(2, TimeUnit.SECONDS);

            publishEvent("ASYNC_COMPLETE", "Async commands completed: " + result1 + ", " + result2, "DemoScenario4");

            System.out.println("Scenario 4 completed successfully\n");

        } catch (Exception e) {
            System.err.println("Scenario 4 failed: " + e.getMessage());
            publishEvent("SCENARIO_ERROR", "Scenario 4 execution failed: " + e.getMessage(), "DemoScenario4");
        }
    }

    /**
     * 시나리오 5: Undo 기능 테스트
     */
    private static void runUndoScenario() {
        System.out.println("--- Scenario 5: Undo Functionality Test ---");

        try {
            // TODO: Undo 기능 테스트
            ProcessCommand undoCmd = new ProcessCommand("Undo Test Data", "Command for undo testing");

            // 명령 실행
            commandInvoker.executeCommand(undoCmd);
            publishEvent("UNDO_TEST", "Command executed for undo test", "DemoScenario5");

            // 명령 취소
            boolean undoSuccess = commandInvoker.undoLastCommand();
            if (undoSuccess) {
                publishEvent("UNDO_SUCCESS", "Command undone successfully", "DemoScenario5");
            } else {
                publishEvent("UNDO_FAILED", "Command undo failed", "DemoScenario5");
            }

            System.out.println("Scenario 5 completed successfully\n");

        } catch (Exception e) {
            System.err.println("Scenario 5 failed: " + e.getMessage());
            publishEvent("SCENARIO_ERROR", "Scenario 5 execution failed: " + e.getMessage(), "DemoScenario5");
        }
    }

    /**
     * TODO: 대화형 모드를 구현하세요 (선택사항)
     */
    private static void runInteractiveMode() {
        System.out.println("--- Interactive Mode ---");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Execute Command");
            System.out.println("2. Publish Event");
            System.out.println("3. Show Statistics");
            System.out.println("4. Undo Last Command");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        // TODO: 사용자 입력으로 명령 실행
                        break;
                    case 2:
                        // TODO: 사용자 입력으로 이벤트 발행
                        break;
                    case 3:
                        printFinalStatistics();
                        break;
                    case 4:
                        commandInvoker.undoLastCommand();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.err.println("Error in interactive mode: " + e.getMessage());
                scanner.nextLine(); // clear invalid input
            }
        }
    }

    /**
     * TODO: 최종 통계를 출력하는 메소드를 구현하세요
     */
    private static void printFinalStatistics() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("FINAL STATISTICS");
        System.out.println("=".repeat(50));

        // Command 통계
        commandInvoker.printExecutionStats();
        commandInvoker.printCommandHistory();

        // Observer 통계
        loggingObserver.printLoggingStats();
        loggingObserver.printRecentLogs(5);

        alertingObserver.printAlertStats();
        alertingObserver.printRecentAlerts(3);

        System.out.println("=".repeat(50));
    }

    /**
     * TODO: 시스템 정리 메소드를 구현하세요
     */
    private static void cleanupSystem() {
        System.out.println("\n3. Cleaning up system...");

        try {
            // TODO: CommandInvoker 종료
            if (commandInvoker != null) {
                commandInvoker.shutdown();
            }

            // TODO: Observer 정리
            if (eventPublisher != null) {
                eventPublisher.removeObserver(loggingObserver);
                eventPublisher.removeObserver(alertingObserver);
            }

            // 종료 이벤트 발행
            publishEvent("SYSTEM_SHUTDOWN", "System shutdown initiated", "SystemCleanup");

            System.out.println("System cleanup completed successfully");

        } catch (Exception e) {
            System.err.println("Error during cleanup: " + e.getMessage());
        }
    }

    /**
     * TODO: 이벤트 발행 유틸리티 메소드를 구현하세요
     */
    private static void publishEvent(String eventType, String message, String source) {
        try {
            if (eventPublisher != null) {
                ProcessEvent event = new ProcessEvent(
                    // java.util.UUID.randomUUID().toString(),
                    eventType,
                    message,
                    // System.currentTimeMillis(),
                    source
                );
                eventPublisher.publishEvent(event);
            }
        } catch (Exception e) {
            System.err.println("Failed to publish event: " + e.getMessage());
        }
    }

    /**
     * TODO: 추가 유틸리티 메소드들을 구현하세요
     */

    /**
     * 시스템 상태 점검
     */
    private static boolean checkSystemHealth() {
        // TODO: 시스템 컴포넌트들의 상태 확인
        return eventPublisher != null &&
               commandInvoker != null &&
               loggingObserver != null &&
               alertingObserver != null;
    }

    /**
     * 성능 벤치마크 (선택사항)
     */
    private static void runPerformanceBenchmark() {
        // TODO: 명령 실행 성능 측정
        System.out.println("--- Performance Benchmark ---");

        long startTime = System.currentTimeMillis();

        // 여러 명령 실행
        for (int i = 0; i < 100; i++) {
            ProcessCommand cmd = new ProcessCommand("Benchmark Data " + i, "Benchmark command " + i);
            commandInvoker.executeCommand(cmd);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Executed 100 commands in " + (endTime - startTime) + " ms");
    }
}