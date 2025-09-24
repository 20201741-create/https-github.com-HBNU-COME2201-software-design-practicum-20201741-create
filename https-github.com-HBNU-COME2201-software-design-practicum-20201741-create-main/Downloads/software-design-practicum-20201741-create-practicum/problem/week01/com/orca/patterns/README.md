# Week 1: Creational Patterns 실습

## 🎯 학습 목표
- **Factory Pattern**의 개념과 구현 방법을 이해합니다
- **Singleton Pattern**의 개념과 Thread-safe 구현을 학습합니다
- 객체지향 설계 원칙(SOLID)을 실제 코드에 적용합니다
- 디자인 패턴이 코드 품질에 미치는 영향을 체험합니다

## 📋 실습 과제

### 1단계: Processor 인터페이스 구현 ⭐
**파일**: `Processor.java`

공통 인터페이스를 정의하여 코드 일관성을 확보하세요.

**요구사항**:
- `String process(String data)` - 데이터 처리 메소드
- `String getType()` - 타입 반환 메소드
- `boolean isInitialized()` - 초기화 상태 확인 메소드

### 2단계: Processor 구현체들 구현 ⭐⭐
**파일**: `DataProcessor.java`, `ComputeProcessor.java`, `IOProcessor.java`

각각 다른 처리 로직을 가진 구현체들을 만드세요.

**DataProcessor**:
- 타입: "DATA"
- 처리: 대문자 변환 → "Processed: " + data.toUpperCase()

**ComputeProcessor**:
- 타입: "COMPUTE"
- 처리: 길이 계산 → "Computed: " + data.length()

**IOProcessor**:
- 타입: "IO"
- 처리: I/O 완료 → "I/O completed for: " + data

### 3단계: Factory Pattern 구현 ⭐⭐⭐
**파일**: `ProcessorFactory.java`

객체 생성 로직을 캡슐화하는 Factory를 구현하세요.

**핵심 요구사항**:
- Map을 사용한 타입별 생성자 등록
- `createProcessor(String type)` 메소드 구현
- 대소문자 구분 없는 타입 지원 (DATA, data 모두 가능)
- 확장 가능한 구조 (새 타입 추가 용이)
- 적절한 예외 처리

### 4단계: Singleton Pattern 구현 ⭐⭐⭐
**파일**: `NodeManager.java`

Thread-safe한 Singleton을 구현하세요.

**핵심 요구사항**:
- Double-checked locking 패턴 사용
- private 생성자
- `getInstance()` 정적 메소드
- Lazy initialization
- 노드 관리 비즈니스 로직 포함

### 5단계: 통합 테스트 구현 ⭐⭐
**파일**: `Week1_CreationalPatterns_Practice.java`

완성된 패턴들을 조합하여 실제 사용 시나리오를 구현하세요.

## 🚀 실행 방법

### 컴파일
```bash
javac *.java
```

### 실행
```bash
java Week1_CreationalPatterns_Practice
```

### 자동 채점 (구현 완료 후)
```bash
# 채점 시스템 사용 (solution 폴더의 채점 시스템 활용)
java com.orca.patterns.grading.Week1AutoGrader com.orca.patterns
```

## ✅ 완성 기준

### Factory Pattern
- [ ] Processor 인터페이스 정의
- [ ] 최소 3개의 구현체 작성
- [ ] Map 기반 Factory 구현
- [ ] 확장 가능한 구조
- [ ] 적절한 예외 처리

### Singleton Pattern
- [ ] Thread-safe 구현
- [ ] private 생성자
- [ ] getInstance() 메소드
- [ ] 비즈니스 로직 분리
- [ ] Lazy initialization

### 통합 테스트
- [ ] Factory와 Singleton 연동
- [ ] 실제 사용 시나리오 구현
- [ ] 에러 케이스 처리
- [ ] 적절한 출력 메시지

## 🔍 자가진단 체크리스트

### 코드 품질
- [ ] SOLID 원칙 준수
- [ ] DRY 원칙 준수 (코드 중복 제거)
- [ ] 일관된 네이밍 컨벤션
- [ ] 적절한 주석과 문서화

### 패턴 구현
- [ ] Factory: 객체 생성 로직 캡슐화
- [ ] Factory: Open/Closed 원칙 준수
- [ ] Singleton: 인스턴스 유일성 보장
- [ ] Singleton: Thread-safety 확보

### 실행 결과
- [ ] 컴파일 에러 없음
- [ ] 예상된 출력 결과
- [ ] 예외 상황 적절히 처리
- [ ] 메모리 누수 없음

## 💡 힌트

### Factory Pattern 힌트
```java
// Map을 사용한 등록
Map<String, Supplier<Processor>> registry = new HashMap<>();
registry.put("DATA", DataProcessor::new);

// 생성
Supplier<Processor> supplier = registry.get(type);
return supplier.get();
```

### Singleton Pattern 힌트
```java
// Double-checked locking
if (instance == null) {
    synchronized (ClassName.class) {
        if (instance == null) {
            instance = new ClassName();
        }
    }
}
```

## 📚 참고 자료
- [Factory Pattern](https://refactoring.guru/design-patterns/factory-method)
- [Singleton Pattern](https://refactoring.guru/design-patterns/singleton)
- [SOLID Principles](https://en.wikipedia.org/wiki/SOLID)
- [Java Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)

## 🎓 학습 성과
이 실습을 완료하면 다음을 달성할 수 있습니다:
- 객체 생성 패턴의 중요성 이해
- Thread-safe 프로그래밍 경험
- 확장 가능한 코드 설계 능력
- 디자인 패턴의 실용적 활용법 습득