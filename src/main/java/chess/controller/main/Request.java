package chess.controller.main;

import java.util.Optional;

public interface Request<T> {

    ActionType getActionType();

    T getData(Class<T> clazz);

    Optional<Integer> getBoardId();

    Optional<Integer> getUserId();
}
