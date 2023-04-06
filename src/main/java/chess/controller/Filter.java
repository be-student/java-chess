package chess.controller;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.exception.LoginException;
import chess.controller.main.ActionType;
import chess.controller.main.Request;
import java.util.List;

public class Filter {

    private final List<ActionType> requireBoardId;
    private final List<ActionType> requireUserId;

    public Filter(List<ActionType> requireUserId, List<ActionType> requireBoardId) {
        this.requireBoardId = requireBoardId;
        this.requireUserId = requireUserId;
    }

    public void validateRequest(Request request) {
        validateUserId(request);
        validateBoardId(request);
    }

    private void validateUserId(Request request) {
        if (requireUserId.contains(request.getActionType()) && request.getUserId().isEmpty()) {
            throw new LoginException();
        }
    }

    private void validateBoardId(Request request) {
        if (requireBoardId.contains(request.getActionType()) && request.getBoardId().isEmpty()) {
            throw new BoardNotFoundException();
        }
    }
}
