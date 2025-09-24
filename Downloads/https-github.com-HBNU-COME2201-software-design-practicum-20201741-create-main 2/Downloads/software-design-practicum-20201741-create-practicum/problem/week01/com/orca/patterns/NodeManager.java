package com.orca.patterns;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Week 1 실습: NodeManager 구현 (Singleton Pattern)
 *
 * TODO: Thread-safe한 Singleton Pattern을 사용하여 NodeManager를 완성하세요.
 *
 * 요구사항:
 * 1. 오직 하나의 인스턴스만 존재해야 합니다
 * 2. Thread-safe해야 합니다 (Double-checked locking 사용)
 * 3. Lazy initialization을 사용해야 합니다
 * 4. 생성자는 private이어야 합니다
 * 5. getInstance() 정적 메소드로 인스턴스를 반환해야 합니다
 * 6. 노드 관리를 위한 비즈니스 로직을 포함해야 합니다
 */
public class NodeManager {

    // TODO: volatile 키워드를 사용하여 instance 필드를 선언하세요
    // 힌트: private static volatile NodeManager instance;
    private static volatile NodeManager instance;

    // TODO: 비즈니스 로직을 위한 필드들을 선언하세요
    // nodeId (String), connectedNodes (List<String>), isActive (boolean)
    private String nodeId;
    private List<String> connectedNodes;
    private boolean isActive;

    // TODO: private 생성자를 구현하세요
    // nodeId는 UUID.randomUUID().toString()으로 생성
    // connectedNodes는 CopyOnWriteArrayList로 초기화 (Thread-safe)
    // isActive는 true로 설정
    private NodeManager() {
        this.nodeId = UUID.randomUUID().toString();
        this.connectedNodes = new CopyOnWriteArrayList<>();
        this.isActive = true;
    }

    /**
     * TODO: getInstance 메소드를 구현하세요 (Double-checked locking 패턴)
     *
     * @return NodeManager의 유일한 인스턴스
     */
    public static NodeManager getInstance() {
        // TODO: Double-checked locking 패턴을 구현하세요
        // 1. instance가 null인지 확인
        // 2. null이면 synchronized 블록 진입
        // 3. synchronized 내에서 다시 한번 null 체크
        // 4. null이면 새 인스턴스 생성 및 초기화
        // 5. instance 반환
        if (instance == null) {
            synchronized(NodeManager.class) {
                if (instance == null) {
                    instance = new NodeManager();
                    instance.initialize();
                }
            }
        }
        return instance;
        // throw new UnsupportedOperationException("아직 구현되지 않았습니다");
    }

    /**
     * TODO: 지연 초기화 메소드를 구현하세요
     * 싱글톤 인스턴스 생성 후 호출되는 초기화 메소드
     */
    private void initialize() {
        // TODO: loadConfiguration(), initializeConnections() 호출
        loadConfiguration();
        initializeConnections();
    }

    // === 비즈니스 로직 메소드들 (TODO: 모두 구현하세요) ===

    /**
     * TODO: 노드 추가 메소드 (Thread-safe)
     * @param nodeId 추가할 노드 ID
     * @throws IllegalArgumentException nodeId가 null이거나 빈 문자열인 경우
     */
    public synchronized void addNode(String nodeId) {
        // TODO: 구현하세요
        // 1. nodeId 유효성 검사
        // 2. 중복이 아닌 경우에만 connectedNodes에 추가
        // 3. 성공 메시지 출력
        if (!isActive) {
            throw new IllegalStateException("프로세서가 초기화되지 않았습니다.");
        }
        if (nodeId == null || nodeId.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 데이터는 null이거나 공백만 포함한 문자열일 수 없습니다.");
        }
        if (connectedNodes.contains(nodeId)) {
            System.out.println(nodeId + "는 이미 연결되어 있습니다.");
            return;
        }
        connectedNodes.add(nodeId);
    }

    /**
     * TODO: 노드 제거 메소드 (Thread-safe)
     * @param nodeId 제거할 노드 ID
     */
    public synchronized void removeNode(String nodeId) {
        // TODO: 구현하세요
        if (connectedNodes.remove(nodeId)) {
            System.out.println(nodeId + "가 성공적으로 제거되었습니다.");
        }
    }

    /**
     * TODO: 연결된 노드 목록 반환 (방어적 복사)
     * @return 연결된 노드들의 복사본
     */
    public List<String> getConnectedNodes() {
        // TODO: 구현하세요 (방어적 복사 - new ArrayList<>(connectedNodes))
        // return null; // 임시 반환값
        return new ArrayList<>(connectedNodes);
    }

    /**
     * TODO: getter 메소드들을 구현하세요
     */
    public String getNodeId() {
        // TODO: 구현하세요
        // return null; // 임시 반환값
        return this.nodeId;
    }

    public boolean isActive() {
        // TODO: 구현하세요
        // return false; // 임시 반환값
        return this.isActive;
    }

    public int getConnectedNodeCount() {
        // TODO: 구현하세요
        // return 0; // 임시 반환값
        return this.connectedNodes.size();
    }

    /**
     * TODO: 노드 종료 메소드
     */
    public synchronized void shutdown() {
        // TODO: isActive를 false로, connectedNodes 클리어, 메시지 출력
        this.isActive = false;
        this.connectedNodes.clear();
        System.out.println(nodeId + "가 성공적으로 종료되었습니다.");
    }

    // === Private 헬퍼 메소드들 ===

    private void loadConfiguration() {
        System.out.println("Loading configuration...");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Configuration loading interrupted");
        }
        System.out.println("Configuration loaded");
    }

    private void initializeConnections() {
        System.out.println("Initializing connections...");
        // TODO: 기본 연결 노드들 추가 (default-node-1, default-node-2, default-node-3)
        connectedNodes.add("default-node-1");
        connectedNodes.add("default-node-2");
        connectedNodes.add("default-node-3");
        
        System.out.println("Initial connections established: " + connectedNodes.size());
    }

    // TODO: toString 메소드를 구현하세요
    @Override
    public String toString() {
        return "NodeManager{" +
                "nodeId='" + nodeId + '\'' +
                ", connectedNodes=" + connectedNodes.size() +
                ", isActive=" + isActive +
                '}';
    }
}
