package com.orca.patterns;

import java.util.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

/**
 * Week 2 실습: LoggingObserver 클래스 구현
 *
 * TODO: Observer Pattern의 구체적인 Observer 구현체를 완성하세요.
 *
 * 요구사항:
 * 1. 모든 이벤트를 로그로 기록
 * 2. 이벤트 카운팅 기능
 * 3. 로그 레벨 지원
 * 4. 로그 통계 제공
 */
public class LoggingObserver implements EventObserver {

    // TODO: 필드들을 선언하세요
    // 힌트: observerId, eventCount, eventLogs, dateFormatter 등이 필요할 수 있습니다

    private final String observerId;
    private final List<String> eventLogs;
    private final Map<String, Integer> eventTypeCount;
    // private final SimpleDateFormat dateFormatter;
    private final DateTimeFormatter dateFormatter;
    private int totalEventCount = 0;

    /**
     * TODO: 생성자를 구현하세요
     *
     * @param observerId Observer 식별자
     */
    public LoggingObserver(String observerId) {
        // TODO: 필드들을 초기화하세요
        this.observerId = observerId;
        this.eventLogs = new ArrayList<>();
        this.eventTypeCount = new HashMap<>();
        // this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

        System.out.println("LoggingObserver created: " + observerId);
    }

    @Override
    public void onEvent(ProcessEvent event) {
        try {
            // TODO: 이벤트 처리 로직을 구현하세요
            // 힌트:
            // 1. 이벤트 정보를 로그 형태로 변환
            // 2. 로그 목록에 추가
            // 3. 이벤트 타입별 카운트 증가
            // 4. 전체 카운트 증가
            // 5. 콘솔에 로그 출력

            // String timestamp = dateFormatter.format(new Date(event.getTimestamp()));
            String timestamp = dateFormatter.format(event.getTimestamp());
            String logEntry = String.format("[%s] [%s] [%s] %s (Source: %s)",
                timestamp,
                event.getEventType(),
                event.getEventId(),
                event.getMessage(),
                event.getSource());

            // 로그 목록에 추가
            eventLogs.add(logEntry);

            // 이벤트 타입별 카운트 증가
            eventTypeCount.put(event.getEventType(),
                eventTypeCount.getOrDefault(event.getEventType(), 0) + 1);

            // 전체 카운트 증가
            totalEventCount++;

            // 콘솔에 로그 출력
            System.out.println("[LOGGER-" + observerId + "] " + logEntry);

            // TODO: 특정 조건에서 추가 로깅 (예: 에러 이벤트일 때)
            if (isErrorEvent(event)) {
                logErrorDetails(event);
            }

        } catch (Exception e) {
            System.err.println("[LOGGER-" + observerId + "] Error processing event: " + e.getMessage());
        }
    }

    @Override
    public String getObserverId() {
        return observerId;
    }

    // TODO: 추가 기능 메소드들을 구현하세요

    /**
     * 에러 이벤트인지 확인
     */
    private boolean isErrorEvent(ProcessEvent event) {
        String eventType = event.getEventType().toLowerCase();
        return eventType.contains("error") ||
               eventType.contains("fail") ||
               eventType.contains("exception") ||
               eventType.contains("critical");
    }

    /**
     * 에러 이벤트 상세 로깅
     */
    private void logErrorDetails(ProcessEvent event) {
        String errorLog = String.format("[ERROR-DETAIL] Event: %s, Message: %s, Source: %s",
            event.getEventId(), event.getMessage(), event.getSource());
        eventLogs.add(errorLog);
        System.err.println("[LOGGER-" + observerId + "] " + errorLog);
    }

    /**
     * TODO: 이벤트 로그 목록을 반환하는 메소드를 구현하세요
     *
     * @return 이벤트 로그 목록 (읽기 전용)
     */
    public List<String> getEventLogs() {
        // TODO: 읽기 전용 리스트 반환
        return new ArrayList<>(eventLogs);
    }

    /**
     * TODO: 이벤트 타입별 카운트를 반환하는 메소드를 구현하세요
     *
     * @return 이벤트 타입별 카운트 맵 (읽기 전용)
     */
    public Map<String, Integer> getEventTypeCount() {
        // TODO: 읽기 전용 맵 반환
        return new HashMap<>(eventTypeCount);
    }

    /**
     * TODO: 전체 이벤트 카운트를 반환하는 메소드를 구현하세요
     *
     * @return 전체 이벤트 카운트
     */
    public int getTotalEventCount() {
        return totalEventCount;
    }

    /**
     * TODO: 로깅 통계를 출력하는 메소드를 구현하세요
     */
    public void printLoggingStats() {
        System.out.println("\n=== Logging Statistics [" + observerId + "] ===");
        System.out.println("Total events logged: " + totalEventCount);
        System.out.println("Total log entries: " + eventLogs.size());

        if (!eventTypeCount.isEmpty()) {
            System.out.println("Event types breakdown:");
            eventTypeCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry ->
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        }

        System.out.println("=======================================\n");
    }

    /**
     * TODO: 최근 N개의 로그를 출력하는 메소드를 구현하세요
     *
     * @param count 출력할 로그 개수
     */
    public void printRecentLogs(int count) {
        System.out.println("\n=== Recent Logs [" + observerId + "] (Last " + count + ") ===");

        if (eventLogs.isEmpty()) {
            System.out.println("No logs available");
        } else {
            int startIndex = Math.max(0, eventLogs.size() - count);
            for (int i = startIndex; i < eventLogs.size(); i++) {
                System.out.println((i + 1) + ". " + eventLogs.get(i));
            }
        }

        System.out.println("================================================\n");
    }

    /**
     * TODO: 특정 이벤트 타입의 로그만 필터링하여 반환하는 메소드를 구현하세요
     *
     * @param eventType 필터링할 이벤트 타입
     * @return 필터링된 로그 목록
     */
    public List<String> getLogsByEventType(String eventType) {
        // TODO: 특정 이벤트 타입의 로그만 필터링
        List<String> filteredLogs = new ArrayList<>();

        for (String log : eventLogs) {
            if (log.contains("[" + eventType + "]")) {
                filteredLogs.add(log);
            }
        }

        return filteredLogs;
    }

    /**
     * TODO: 로그를 파일로 저장하는 메소드를 구현하세요 (선택사항)
     *
     * @param filename 저장할 파일명
     * @return 저장 성공 여부
     */
    public boolean saveLogsToFile(String filename) {
        try {
            // TODO: 파일 저장 로직 구현
            // 힌트: FileWriter나 BufferedWriter 사용

            System.out.println("Saving logs to file: " + filename);
            System.out.println("Total " + eventLogs.size() + " logs saved");

            // 실제 파일 저장 로직은 학생이 구현
            // FileWriter, PrintWriter 등을 사용하여 eventLogs를 파일에 저장

            return true;

        } catch (Exception e) {
            System.err.println("Failed to save logs to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * TODO: 로그 초기화 메소드를 구현하세요
     */
    public void clearLogs() {
        eventLogs.clear();
        eventTypeCount.clear();
        totalEventCount = 0;
        System.out.println("[LOGGER-" + observerId + "] Logs cleared");
    }

    /**
     * Observer 정보 문자열 반환
     */
    @Override
    public String toString() {
        return String.format("LoggingObserver{id='%s', totalEvents=%d, logEntries=%d}",
            observerId, totalEventCount, eventLogs.size());
    }
}