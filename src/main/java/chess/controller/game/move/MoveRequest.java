package chess.controller.game.move;

import chess.controller.main.RequestData;

public interface MoveRequest extends RequestData {

    String getOrigin();

    String getDestination();
}
