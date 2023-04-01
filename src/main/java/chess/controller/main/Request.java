package chess.controller.main;

import java.util.Optional;

public interface Request {

    ActionType getActionType();

    <T extends RequestData> T getData(Class<T> clazz);

    Optional<Integer> getBoardId();

    Optional<Integer> getUserId();
}
