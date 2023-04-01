package chess.view.request;

import chess.controller.game.move.MoveRequest;
import chess.controller.main.ActionType;
import chess.controller.main.RequestData;
import chess.controller.room.join.JoinBoardRequest;
import chess.controller.user.LoginRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum DataFactory {
    START(ActionType.START, EmptyRequestImpl.class, EmptyRequestImpl::new),
    END(ActionType.END, EmptyRequestImpl.class, EmptyRequestImpl::new),
    MOVE(ActionType.MOVE, MoveRequest.class, MoveRequestImpl::new),
    STATUS(ActionType.STATUS, EmptyRequestImpl.class, EmptyRequestImpl::new),
    GAMES(ActionType.GAMES, EmptyRequestImpl.class, EmptyRequestImpl::new),
    CREATE(ActionType.CREATE, EmptyRequestImpl.class, EmptyRequestImpl::new),
    JOIN(ActionType.JOIN, JoinBoardRequest.class, JoinBoardRequestImpl::new),
    LOGIN(ActionType.LOGIN, LoginRequest.class, LoginRequestImpl::new);

    private final ActionType actionType;
    private final Class<? extends RequestData> requestType;
    private final Function<List<String>, ? extends RequestData> requestData;

    <T extends RequestData, R extends T> DataFactory(ActionType actionType,
            Class<T> requestType,
            Function<List<String>, R> requestData) {
        this.actionType = actionType;
        this.requestType = requestType;
        this.requestData = requestData;
    }

    public static <T extends RequestData, R extends T> Map<Class<T>, R> map(List<String> commands,
            ActionType actionType) {
        DataFactory dataFactory = findDataFactory(actionType);
        @SuppressWarnings("unchecked")
        Map<Class<T>, R> map = Map.of(
                (Class<T>) dataFactory.requestType,
                (R) dataFactory.requestData.apply(commands)
        );
        return map;
    }

    private static DataFactory findDataFactory(ActionType actionType) {
        return Arrays.stream(values())
                .filter(dataFactory -> dataFactory.actionType == actionType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 ActionType이 없습니다."));
    }
}
