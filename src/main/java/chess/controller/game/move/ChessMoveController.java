package chess.controller.game.move;

import chess.controller.Controller;
import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.ChessLoadService;
import chess.service.game.ChessMoveService;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessMoveController implements Controller {

    private final ChessMoveService chessMoveService;
    private final ChessLoadService chessLoadService;
    private final BoardOutput boardOutput;

    public ChessMoveController(ChessMoveService chessMoveService, ChessLoadService chessLoadService,
            BoardOutput boardOutput) {
        this.chessMoveService = chessMoveService;
        this.chessLoadService = chessLoadService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        MoveRequest moveRequest = request.getData(MoveRequest.class);
        chessMoveService.move(request.getBoardId().get(), moveRequest.getOrigin(), moveRequest.getDestination());
        Optional<ChessGame> chessGame = chessLoadService.load(request.getBoardId().get());
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        boardOutput.printBoard(makeBoardResponse(chessGame.get().getPieces()));
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
