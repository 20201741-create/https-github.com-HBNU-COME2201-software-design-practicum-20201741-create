package com.orca.patterns;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Week 2 실습: AlertingObserver 클래스 구현
 *
 * TODO: Observer Pattern의 구체적인 Observer 구현체를 완성하세요.
 *
 * 요구사항:
 * 1. ERROR, CRITICAL, WARNING 이벤트만 알림
 * 2. 심각도별 다른 처리
 * 3. 알림 히스토리 관리
 * 4. 알림 통계 제공
 */
public class AlertingObserver implements EventObserver {

    // TODO: 필드들을 선언하세요
    // 힌트: observerId, alertHistory, alertCount, severityLevels 등이 필요할 수 있습니다

    private final String observerId;
    private final List<AlertInfo> alertHistory;
    private final Map<String, Integer> severityCount;
    private final Set<String> monitoredSeverities;
    private final SimpleDateFormat dateFormatter;
    private int totalAlertCount = 0;

    /**
     * TODO: 생성자를 구현하세요
     *
     * @param observerId Observer 식별자
     */
    public AlertingObserver(String observerId) {
        // TODO: 필드들을 초기화하세요
        this.observerId = observerId;
        this.alertHistory = new ArrayList<>();
        this.severityCount = new HashMap<>();
        this.dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // 모니터링할 심각도 레벨 설정
        this.monitoredSeverities = new HashSet<>();
        this.monitoredSeverities.add("ERROR");
        this.monitoredSeverities.add("CRITICAL");
        this.monitoredSeverities.add("WARNING");

        System.out.println("AlertingObserver created: " + observerId);
        System.out.println("Monitoring severities: " + monitoredSeverities);
    }

    @Override
    public void onEvent(ProcessEvent event) {
        try {
            // TODO: 이벤트 처리 로직을 구현하세요
            // 힌트:
            // 1. 이벤트가 알림 대상인지 확인 (심각도 체크)
            // 2. 심각도별로 다른 처리
            // 3. 알림 히스토리에 추가
            // 4. 알림 카운트 업데이트
            // 5. 실제 알림 발송 (시뮬레이션)

            String severity = extractSeverity(event);

            if (!shouldAlert(severity, event)) {
                // 알림 대상이 아님
                return;
            }

            // 알림 정보 생성
            AlertInfo alertInfo = new AlertInfo(event, severity);
            alertHistory.add(alertInfo);

            // 심각도별 카운트 증가
            severityCount.put(severity, severityCount.getOrDefault(severity, 0) + 1);
            totalAlertCount++;

            // 심각도별 다른 처리
            processAlertBySeverity(alertInfo);

        } catch (Exception e) {
            System.err.println("[ALERTER-" + observerId + "] Error processing alert: " + e.getMessage());
        }
    }

    @Override
    public String getObserverId() {
        return observerId;
    }

    // TODO: 추가 기능 메소드들을 구현하세요

    /**
     * 이벤트에서 심각도 추출
     */
    private String extractSeverity(ProcessEvent event) {
        String eventType = event.getEventType().toUpperCase();
        String message = event.getMessage().toUpperCase();

        // TODO: 심각도 판단 로직을 구현하세요
        // 힌트: eventType이나 message에서 키워드 추출

        if (eventType.contains("CRITICAL") || message.contains("CRITICAL")) {
            return "CRITICAL";
        } else if (eventType.contains("ERROR") || eventType.contains("FAIL") || message.contains("ERROR")) {
            return "ERROR";
        } else if (eventType.contains("WARNING") || eventType.contains("WARN") || message.contains("WARNING")) {
            return "WARNING";
        } else {
            return "INFO"; // 기본값
        }
    }

    /**
     * 알림 대상인지 확인
     */
    private boolean shouldAlert(String severity, ProcessEvent event) {
        // TODO: 알림 조건을 확인하는 로직을 구현하세요
        // 힌트: 모니터링 대상 심각도인지, 특정 소스에서 온 이벤트인지 등 확인

        return monitoredSeverities.contains(severity);
    }

    /**
     * 심각도별 알림 처리
     */
    private void processAlertBySeverity(AlertInfo alertInfo) {
        String severity = alertInfo.getSeverity();
        ProcessEvent event = alertInfo.getEvent();

        switch (severity) {
            case "CRITICAL":
                // TODO: CRITICAL 레벨 알림 처리
                sendCriticalAlert(alertInfo);
                break;

            case "ERROR":
                // TODO: ERROR 레벨 알림 처리
                sendErrorAlert(alertInfo);
                break;

            case "WARNING":
                // TODO: WARNING 레벨 알림 처리
                sendWarningAlert(alertInfo);
                break;

            default:
                // 기본 처리
                System.out.println("[ALERTER-" + observerId + "] Unknown severity: " + severity);
        }
    }

    /**
     * CRITICAL 레벨 알림 발송
     */
    private void sendCriticalAlert(AlertInfo alertInfo) {
        // TODO: CRITICAL 알림 로직을 구현하세요
        // 힌트: 즉시 알림, 여러 채널로 알림, 에스컬레이션 등

        System.err.println("\n" + "=".repeat(60));
        System.err.println("CRITICAL ALERT [" + observerId + "]");
        System.err.println("=".repeat(60));
        System.err.println("Event ID: " + alertInfo.getEvent().getEventId());
        System.err.println("Message: " + alertInfo.getEvent().getMessage());
        System.err.println("Source: " + alertInfo.getEvent().getSource());
        System.err.println("Time: " + alertInfo.getFormattedTimestamp());
        System.err.println("=".repeat(60));

        // TODO: 추가 알림 채널 (이메일, SMS, Slack 등) 시뮬레이션
        simulateNotificationChannels("CRITICAL", alertInfo);
    }

    /**
     * ERROR 레벨 알림 발송
     */
    private void sendErrorAlert(AlertInfo alertInfo) {
        // TODO: ERROR 알림 로직을 구현하세요

        System.err.println("\n[ERROR ALERT-" + observerId + "] " +
            alertInfo.getEvent().getMessage() +
            " (Source: " + alertInfo.getEvent().getSource() + ")");

        // TODO: 알림 채널 시뮬레이션
        simulateNotificationChannels("ERROR", alertInfo);
    }

    /**
     * WARNING 레벨 알림 발송
     */
    private void sendWarningAlert(AlertInfo alertInfo) {
        // TODO: WARNING 알림 로직을 구현하세요

        System.out.println("[WARNING ALERT-" + observerId + "] " +
            alertInfo.getEvent().getMessage() +
            " (Source: " + alertInfo.getEvent().getSource() + ")");

        // TODO: 필요시 알림 채널 시뮬레이션
        // simulateNotificationChannels("WARNING", alertInfo);
    }

    /**
     * 알림 채널 시뮬레이션
     */
    private void simulateNotificationChannels(String severity, AlertInfo alertInfo) {
        // TODO: 다양한 알림 채널을 시뮬레이션하세요
        // 힌트: 이메일, SMS, Slack, 팀즈, 대시보드 등

        switch (severity) {
            case "CRITICAL":
                System.err.println("[SMS Alert] Critical issue detected: " + alertInfo.getEvent().getEventId());
                System.err.println("[Email Alert] Sending to admin team...");
                System.err.println("[Slack Alert] Posted to #critical-alerts channel");
                break;

            case "ERROR":
                System.err.println("[Email Alert] Error notification sent");
                System.err.println("[Slack Alert] Posted to #errors channel");
                break;

            case "WARNING":
                System.out.println("[Dashboard Alert] Warning logged to monitoring dashboard");
                break;
        }
    }

    /**
     * TODO: 알림 히스토리를 반환하는 메소드를 구현하세요
     */
    public List<AlertInfo> getAlertHistory() {
        return new ArrayList<>(alertHistory);
    }

    /**
     * TODO: 심각도별 알림 카운트를 반환하는 메소드를 구현하세요
     */
    public Map<String, Integer> getSeverityCount() {
        return new HashMap<>(severityCount);
    }

    /**
     * TODO: 전체 알림 카운트를 반환하는 메소드를 구현하세요
     */
    public int getTotalAlertCount() {
        return totalAlertCount;
    }

    /**
     * TODO: 알림 통계를 출력하는 메소드를 구현하세요
     */
    public void printAlertStats() {
        System.out.println("\n=== Alert Statistics [" + observerId + "] ===");
        System.out.println("Total alerts sent: " + totalAlertCount);
        System.out.println("Monitored severities: " + monitoredSeverities);

        if (!severityCount.isEmpty()) {
            System.out.println("Alerts by severity:");
            severityCount.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry ->
                    System.out.println("  " + entry.getKey() + ": " + entry.getValue()));
        }

        System.out.println("=====================================\n");
    }

    /**
     * TODO: 최근 알림들을 출력하는 메소드를 구현하세요
     */
    public void printRecentAlerts(int count) {
        System.out.println("\n=== Recent Alerts [" + observerId + "] (Last " + count + ") ===");

        if (alertHistory.isEmpty()) {
            System.out.println("No alerts in history");
        } else {
            int startIndex = Math.max(0, alertHistory.size() - count);
            for (int i = startIndex; i < alertHistory.size(); i++) {
                AlertInfo alert = alertHistory.get(i);
                System.out.printf("%d. [%s] %s - %s (Source: %s)\n",
                    i + 1,
                    alert.getSeverity(),
                    alert.getFormattedTimestamp(),
                    alert.getEvent().getMessage(),
                    alert.getEvent().getSource());
            }
        }

        System.out.println("===============================================\n");
    }

    /**
     * Observer 정보 문자열 반환
     */
    @Override
    public String toString() {
        return String.format("AlertingObserver{id='%s', totalAlerts=%d, severities=%s}",
            observerId, totalAlertCount, monitoredSeverities);
    }

    // TODO: 내부 클래스 AlertInfo를 구현하세요
    /**
     * 알림 정보를 담는 내부 클래스
     */
    public static class AlertInfo {
        private final ProcessEvent event;
        private final String severity;
        private final long alertTimestamp;
        private final SimpleDateFormat formatter;

        public AlertInfo(ProcessEvent event, String severity) {
            this.event = event;
            this.severity = severity;
            this.alertTimestamp = System.currentTimeMillis();
            this.formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }

        public ProcessEvent getEvent() {
            return event;
        }

        public String getSeverity() {
            return severity;
        }

        public long getAlertTimestamp() {
            return alertTimestamp;
        }

        public String getFormattedTimestamp() {
            return formatter.format(new Date(alertTimestamp));
        }

        @Override
        public String toString() {
            return String.format("AlertInfo{severity='%s', eventId='%s', timestamp='%s'}",
                severity, event.getEventId(), getFormattedTimestamp());
        }
    }
}