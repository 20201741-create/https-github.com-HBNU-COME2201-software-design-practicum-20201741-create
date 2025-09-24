package com.orca.patterns;

/**
 * Week 2 실습: Command 인터페이스 구현
 *
 * TODO: Command Pattern의 Command 인터페이스를 완성하세요.
 *
 * 요구사항:
 * 1. 명령을 실행하는 메소드
 * 2. 명령을 취소하는 메소드 (가능한 경우)
 * 3. 명령 설명을 반환하는 메소드
 * 4. 명령 ID를 반환하는 메소드
 */
public interface Command {

    // TODO: 명령 실행 메소드를 선언하세요
    // 메소드명: execute, 반환타입: boolean (성공: true, 실패: false)
    public boolean execute();

    // TODO: 명령 취소 메소드를 선언하세요
    // 메소드명: undo, 반환타입: boolean
    public boolean undo();

    // TODO: 명령 설명 반환 메소드를 선언하세요
    // 메소드명: getDescription, 반환타입: String
    public String getDescription();

    // TODO: 명령 ID 반환 메소드를 선언하세요
    // 메소드명: getCommandId, 반환타입: String
    public String getCommandId();

}