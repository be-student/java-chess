package chess.controller.game.end;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.ChessEndService;

public class ChessEndController implements Controller {

    private final ChessEndService chessEndService;

    public ChessEndController(ChessEndService chessEndService) {
        this.chessEndService = chessEndService;
    }

    @Override
    public void run(Request request) {
        chessEndService.end(request.getBoardId().get());
    }
}
