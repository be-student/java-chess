package chess.controller.room.join;

import chess.controller.Controller;
import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.BoardOutput;
import chess.controller.main.Request;
import chess.domain.game.ChessGame;
import chess.domain.piece.Piece;
import chess.service.game.ChessLoadService;
import chess.view.response.PieceResponse;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessJoinBoardController implements Controller {

    private final JoinBoard joinBoard;
    private final JoinBoardOutput joinBoardOutput;
    private final ChessLoadService chessLoadService;
    private final BoardOutput boardOutput;

    public ChessJoinBoardController(JoinBoard joinBoard, JoinBoardOutput joinBoardOutput,
            ChessLoadService chessLoadService, BoardOutput boardOutput) {
        this.joinBoard = joinBoard;
        this.joinBoardOutput = joinBoardOutput;
        this.chessLoadService = chessLoadService;
        this.boardOutput = boardOutput;
    }

    @Override
    public void run(Request request) {
        JoinBoardRequest joinBoardRequest = request.getData(JoinBoardRequest.class);
        joinBoard.join(joinBoardRequest.getBoardId());
        Optional<ChessGame> chessGame = chessLoadService.load(joinBoardRequest.getBoardId());
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        joinBoardOutput.printJoinBoardSuccess(chessGame.get().getStatusType());
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
