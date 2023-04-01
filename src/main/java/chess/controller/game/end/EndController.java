package chess.controller.game.end;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.EndChessGameService;

public class EndController implements Controller {

    private final EndChessGameService endChessGameService;

    public EndController(EndChessGameService endChessGameService) {
        this.endChessGameService = endChessGameService;
    }

    @Override
    public void run(Request request) {
        endChessGameService.end(request.getBoardId().get());
    }
}
