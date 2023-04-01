package chess.view.request;

import chess.controller.main.ActionType;
import chess.controller.main.Request;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestImpl<T> implements Request<T> {

    private static final int ACTION_TYPE_INDEX = 0;
    private final ActionType actionType;
    private final Map<Class<T>, T> data;
    private final Optional<Integer> boardId;
    private final Optional<Integer> userId;

    private RequestImpl(
            ActionType actionType,
            Map<Class<T>, T> data,
            Optional<Integer> userId,
            Optional<Integer> boardId) {
        this.actionType = actionType;
        this.data = data;
        this.userId = userId;
        this.boardId = boardId;
    }

    public static <T> RequestImpl<T> of(
            List<String> commands,
            Optional<Integer> userId,
            Optional<Integer> boardId) {
        ActionType actionType = ActionTypeMapper.map(commands.get(ACTION_TYPE_INDEX));
        Map<Class<T>, T> data = DataFactory.map(commands, actionType);
        return new RequestImpl<>(actionType, data, userId, boardId);
    }

    @Override
    public ActionType getActionType() {
        return actionType;
    }

    @Override
    public T getData(Class<T> clazz) {
        return data.get(clazz);
    }

    @Override
    public Optional<Integer> getBoardId() {
        return boardId;
    }

    @Override
    public Optional<Integer> getUserId() {
        return userId;
    }
}
