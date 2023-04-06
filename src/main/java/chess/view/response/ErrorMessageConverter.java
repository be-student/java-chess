package chess.view.response;

import java.util.Arrays;

public enum ErrorMessageConverter {
    LOGIN_FAIL("로그인 되지 않았습니다", "로그인이 필요합니다. login [id] 형식으로 입력해주세요."),
    BOARD_NOT_FOUND("존재하지 않는 방입니다",
            "방 잘못된 방번호를 입력하셨습니다 -join [방 번호] 형식으로 입력해주세요. -games 로 방 목록을 확인할 수 있습니다."),
    NOT_STARTED("게임이 시작되지 않았습니다.", "게임이 시작되지 않았습니다. -start 명령어로 게임을 시작해주세요.");


    private final String originalMessage;
    private final String message;

    ErrorMessageConverter(String originalMessage, String message) {
        this.originalMessage = originalMessage;
        this.message = message;
    }

    public static String convert(String originalMessage) {
        return Arrays.stream(values())
                .filter(value -> value.originalMessage.equals(originalMessage))
                .findFirst()
                .map(value -> value.message)
                .orElse(originalMessage);
    }
}
