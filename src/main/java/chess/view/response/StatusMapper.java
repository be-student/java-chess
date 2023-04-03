package chess.view.response;

import chess.domain.game.state.StatusType;
import java.util.Arrays;

public enum StatusMapper {
    START("ready", StatusType.START),
    PLAYING("playing", StatusType.PLAYING),
    END("end", StatusType.END);

    private final String status;
    private final StatusType statusType;

    StatusMapper(String status, StatusType statusType) {
        this.status = status;
        this.statusType = statusType;
    }

    public static String map(StatusType statusType) {
        return Arrays.stream(values())
                .filter(statusMapper -> statusMapper.statusType == statusType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 status 입니다."))
                .status;
    }
}
