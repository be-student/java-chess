package chess.view.request;

import chess.controller.game.move.MoveRequest;
import java.util.List;

public class MoveRequestImpl implements MoveRequest {

    private static final int ORIGIN_INDEX = 1;
    private static final int DESTINATION_INDEX = 2;
    private static final int MOVE_COMMAND_SIZE = 3;

    private final String origin;
    private final String destination;

    public MoveRequestImpl(List<String> commands) {
        validateCommands(commands);
        origin = commands.get(ORIGIN_INDEX);
        destination = commands.get(DESTINATION_INDEX);
    }

    private void validateCommands(List<String> commands) {
        if (commands.size() != MOVE_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 명령어입니다.");
        }
    }

    @Override
    public String getOrigin() {
        return origin;
    }

    @Override
    public String getDestination() {
        return destination;
    }
}
