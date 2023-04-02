package chess.view.request;

import chess.controller.main.ActionType;
import chess.controller.main.Request;
import chess.controller.main.RequestData;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RequestImpl<R extends RequestData> implements Request {

    private static final int ACTION_TYPE_INDEX = 0;
    private final ActionType actionType;
    private final Map<Class<R>, R> data;
    private final Optional<Integer> boardId;
    private final Optional<Integer> userId;

    private RequestImpl(
            ActionType actionType,
            Map<Class<R>, R> data,
            Optional<Integer> userId,
            Optional<Integer> boardId) {
        this.actionType = actionType;
        this.data = data;
        this.userId = userId;
        this.boardId = boardId;
    }

    public static <T extends RequestData> RequestImpl<T> of(
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
    public <T extends RequestData> T getData(Class<T> clazz) {
        @SuppressWarnings("unchecked")
        T t = (T) data.get(clazz);
        return t;
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
