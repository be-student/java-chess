package chess.view.request;

import chess.controller.room.join.JoinBoardRequest;
import java.util.List;

public class JoinBoardRequestImpl implements JoinBoardRequest {

    private static final int BOARD_ID_INDEX = 1;
    private static final int JOIN_COMMAND_SIZE = 2;

    private final int boardId;

    public JoinBoardRequestImpl(List<String> commands) {
        validateCommands(commands);
        boardId = Integer.parseInt(commands.get(BOARD_ID_INDEX));
    }

    private void validateCommands(List<String> commands) {
        if (commands.size() != JOIN_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }

    @Override
    public int getBoardId() {
        return boardId;
    }
}
