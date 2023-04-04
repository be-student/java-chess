package chess.view.request;

import chess.controller.game.move.MoveRequest;
import chess.controller.main.ActionType;
import chess.controller.main.EmptyRequest;
import chess.controller.main.RequestData;
import chess.controller.room.join.JoinBoardRequest;
import chess.controller.user.LoginRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum RequestDataFactory {
    START(ActionType.START, EmptyRequest.class, EmptyRequestImpl::new),
    END(ActionType.END, EmptyRequest.class, EmptyRequestImpl::new),
    MOVE(ActionType.MOVE, MoveRequest.class, MoveRequestImpl::new),
    STATUS(ActionType.STATUS, EmptyRequest.class, EmptyRequestImpl::new),
    GAMES(ActionType.GAMES, EmptyRequest.class, EmptyRequestImpl::new),
    CREATE(ActionType.CREATE, EmptyRequest.class, EmptyRequestImpl::new),
    JOIN(ActionType.JOIN, JoinBoardRequest.class, JoinBoardRequestImpl::new),
    LOGIN(ActionType.LOGIN, LoginRequest.class, LoginRequestImpl::new);

    private final ActionType actionType;
    private final Class<? extends RequestData> requestType;
    private final Function<List<String>, ? extends RequestData> requestData;

    <T extends RequestData, R extends T> RequestDataFactory(ActionType actionType,
            Class<T> requestType,
            Function<List<String>, R> requestData) {
        this.actionType = actionType;
        this.requestType = requestType;
        this.requestData = requestData;
    }

    public static <T extends RequestData, R extends T> Map<Class<T>, R> createData(List<String> commands,
            ActionType actionType) {
        RequestDataFactory requestDataFactory = findRequestFactory(actionType);
        @SuppressWarnings("unchecked")
        Map<Class<T>, R> map = Map.of(
                (Class<T>) requestDataFactory.requestType,
                (R) requestDataFactory.requestData.apply(commands)
        );
        return map;
    }

    private static RequestDataFactory findRequestFactory(ActionType actionType) {
        return Arrays.stream(values())
                .filter(requestDataFactory -> requestDataFactory.actionType == actionType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 ActionType이 없습니다."));
    }
}
