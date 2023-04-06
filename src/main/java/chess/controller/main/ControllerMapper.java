package chess.controller.main;

import chess.controller.Controller;
import java.util.Map;

public class ControllerMapper {

    private final Map<ActionType, Controller> actions;

    public ControllerMapper(Map<ActionType, Controller> actions) {
        this.actions = actions;
    }

    public Controller getController(ActionType type) {
        return actions.get(type);
    }
}
