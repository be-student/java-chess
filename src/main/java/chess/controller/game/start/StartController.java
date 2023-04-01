package chess.controller.game.start;

import chess.controller.Controller;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.LoadChessGameService;
import chess.service.game.StartChessGameService;
import chess.view.resposne.PieceResponse;
import java.util.List;
import java.util.stream.Collectors;

public class StartController implements Controller {

    private final StartChessGameService startChessGameService;
    private final LoadChessGameService loadChessGameService;
    private final BoardOutput boardOutput;

    public StartController(StartChessGameService startChessGameService, LoadChessGameService loadChessGameService,
            BoardOutput boardOutput) {
        this.startChessGameService = startChessGameService;
        this.loadChessGameService = loadChessGameService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        startChessGameService.start(request.getBoardId().get());
        ChessGame chessGame = loadChessGameService.load(request.getBoardId().get()).get();
        boardOutput.printBoard(makeBoardResponse(chessGame.getPieces()));
    }

    private List<List<PieceResponse>> makeBoardResponse(List<List<Piece>> boardResult) {
        return boardResult.stream()
                .map(this::makeRankResponse)
                .collect(Collectors.toList());
    }

    private List<PieceResponse> makeRankResponse(List<Piece> row) {
        return row.stream()
                .map(PieceResponse::from)
                .collect(Collectors.toList());
    }
}
