# Week 2: Behavioral Patterns 실습

## 학습 목표
- **Observer Pattern**의 개념과 구현 방법을 이해합니다
- **Command Pattern**의 개념과 실행 제어를 학습합니다
- 이벤트 기반 시스템의 느슨한 결합을 체험합니다
- 명령의 캡슐화와 실행 취소 기능을 구현합니다

## 실습 과제

### 1단계: Observer Pattern 구현
**파일**: `EventObserver.java`, `ProcessEvent.java`, `EventPublisher.java`

이벤트 기반 시스템을 구축하여 시스템 컴포넌트 간 느슨한 결합을 구현하세요.

**EventObserver 인터페이스**:
- `void onEvent(ProcessEvent event)` - 이벤트 처리
- `String getObserverId()` - Observer 식별자

**ProcessEvent 클래스**:
- 이벤트 정보 캡슐화 (ID, 타입, 메시지, 시간, 소스)
- 생성자와 getter 메소드들 구현

**EventPublisher 클래스**:
- Observer 등록/제거 기능
- 이벤트 발행 기능
- Thread-safe 구현

### 2단계: Observer 구현체들 구현
**파일**: `LoggingObserver.java`, `AlertingObserver.java`

다양한 목적의 Observer들을 구현하세요.

**LoggingObserver**:
- 모든 이벤트를 로그로 기록
- 이벤트 카운팅 기능

**AlertingObserver**:
- ERROR, CRITICAL, WARNING 이벤트만 알림
- 심각도별 다른 처리

### 3단계: Command Pattern 구현
**파일**: `Command.java`, `ProcessCommand.java`, `CommandInvoker.java`

명령을 객체로 캡슐화하여 실행 제어를 구현하세요.

**Command 인터페이스**:
- `boolean execute()` - 명령 실행
- `boolean undo()` - 명령 취소
- `String getDescription()` - 명령 설명
- `String getCommandId()` - 명령 ID

**ProcessCommand 클래스**:
- Processor를 사용한 데이터 처리 명령
- 이벤트 발행 기능 포함
- 실행/취소 상태 관리

**CommandInvoker 클래스**:
- 명령 실행 관리
- 실행 히스토리 관리
- 동기/비동기 실행 지원

### 4단계: 통합 테스트 구현
**파일**: `Week2_BehavioralPatterns_Practice.java`

Observer와 Command Pattern을 통합한 실제 시나리오를 구현하세요.

## 실행 방법

### 컴파일
```bash
javac *.java
```

### 실행
```bash
java Week2_BehavioralPatterns_Practice
```

## 완성 기준

### Observer Pattern
- [ ] EventObserver 인터페이스 정의
- [ ] ProcessEvent 데이터 클래스 구현
- [ ] EventPublisher Subject 구현
- [ ] 최소 2개 Observer 구현체 작성
- [ ] Thread-safe 구현

### Command Pattern
- [ ] Command 인터페이스 정의
- [ ] ProcessCommand 구체 구현
- [ ] CommandInvoker 실행 관리자
- [ ] 실행/취소 기능 구현
- [ ] 명령 히스토리 관리

### 통합 기능
- [ ] Observer-Command 연동
- [ ] 이벤트 기반 명령 실행
- [ ] 실시간 모니터링
- [ ] 에러 처리

## 핵심 개념

### Observer Pattern
- **Subject-Observer 관계**: 1:N 의존성 정의
- **느슨한 결합**: Subject와 Observer 독립성
- **동적 관계**: 런타임 Observer 추가/제거
- **일관성 유지**: 상태 변경 시 자동 알림

### Command Pattern
- **요청 캡슐화**: 메소드 호출을 객체로 래핑
- **매개변수화**: 다양한 요청으로 객체 구성
- **큐잉**: 요청을 큐에 저장하고 스케줄링
- **로깅**: 요청 기록 및 실행 취소

## 힌트

### Observer Pattern 구현
```java
// Publisher에서 알림
observers.forEach(observer -> {
    try {
        observer.onEvent(event);
    } catch (Exception e) {
        // 예외 처리
    }
});
```

### Command Pattern 구현
```java
// Command 실행
public boolean execute() {
    try {
        result = processor.process(data);
        executed = true;
        return true;
    } catch (Exception e) {
        return false;
    }
}
```

## 참고 자료
- [Observer Pattern](https://refactoring.guru/design-patterns/observer)
- [Command Pattern](https://refactoring.guru/design-patterns/command)
- [Event-Driven Architecture](https://microservices.io/patterns/data/event-driven-architecture.html)

## 학습 성과
이 실습을 완료하면 다음을 달성할 수 있습니다:
- 이벤트 기반 아키텍처 설계 능력
- 느슨한 결합 시스템 구축 경험
- 명령 패턴을 통한 실행 제어
- 복잡한 시스템의 모니터링과 제어