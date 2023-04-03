package chess.controller.game.start;

import chess.controller.Controller;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.ChessLoadService;
import chess.service.game.ChessStartService;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.stream.Collectors;

public class ChessStartController implements Controller {

    private final ChessStartService chessStartService;
    private final ChessLoadService chessLoadService;
    private final BoardOutput boardOutput;

    public ChessStartController(ChessStartService chessStartService, ChessLoadService chessLoadService,
            BoardOutput boardOutput) {
        this.chessStartService = chessStartService;
        this.chessLoadService = chessLoadService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        chessStartService.start(request.getBoardId().get());
        ChessGame chessGame = chessLoadService.load(request.getBoardId().get()).get();
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
