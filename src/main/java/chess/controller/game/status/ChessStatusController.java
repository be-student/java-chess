package chess.controller.game.status;

import chess.controller.Controller;
import chess.controller.main.Request;
import chess.service.game.ChessStatusService;

public class ChessStatusController implements Controller {

    private final StatusOutput statusOutput;
    private final ChessStatusService chessStatusService;

    public ChessStatusController(StatusOutput statusOutput, ChessStatusService chessStatusService) {
        this.statusOutput = statusOutput;
        this.chessStatusService = chessStatusService;
    }

    @Override
    public void run(Request request) {
        StatusResponse statusResponse = chessStatusService.status(request.getBoardId().get());
        statusOutput.printStatus(statusResponse);
    }
}
