package chess.view.request;

import chess.controller.game.end.EndRequest;
import chess.controller.game.games.GamesRequest;
import chess.controller.game.move.MoveRequest;
import chess.controller.game.start.StartRequest;
import chess.controller.main.ActionType;
import chess.controller.room.create.CreateRoomRequest;
import chess.controller.room.join.JoinBoardRequest;
import chess.controller.user.LoginRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public enum DataFactory {
    START(ActionType.START, StartRequest.class, StartRequestImpl::new),
    END(ActionType.END, EndRequest.class, EndRequestImpl::new),
    MOVE(ActionType.MOVE, MoveRequest.class, MoveRequestImpl::new),
    STATUS(ActionType.STATUS, StartRequest.class, StartRequestImpl::new),
    GAMES(ActionType.GAMES, GamesRequest.class, GamesRequestImpl::new),
    CREATE(ActionType.CREATE, CreateRoomRequest.class, CreateRoomRequestImpl::new),
    JOIN(ActionType.JOIN, JoinBoardRequest.class, JoinBoardRequestImpl::new),
    LOGIN(ActionType.LOGIN, LoginRequest.class, LoginRequestImpl::new);

    private final ActionType actionType;
    private final Class<?> requestType;
    private final Function<List<String>, ?> requestData;

    DataFactory(ActionType actionType, Class<?> requestType, Function<List<String>, ?> requestData) {
        this.actionType = actionType;
        this.requestType = requestType;
        this.requestData = requestData;
    }

    public static <T> Map<Class<T>, T> map(List<String> commands, ActionType actionType) {
        DataFactory dataFactory = findDataFactory(actionType);
        @SuppressWarnings("unchecked")
        Map<Class<T>, T> map = Map.of(
                (Class<T>) dataFactory.requestType,
                (T) dataFactory.requestData.apply(commands)
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
