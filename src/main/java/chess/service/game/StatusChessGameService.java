package chess.service.game;

import chess.controller.exception.BoardNotFoundException;
import chess.controller.game.status.StatusResponse;
import chess.domain.game.ChessGame;
import chess.domain.piece.Color;
import chess.service.repository.ChessGameRepository;
import java.util.Map;
import java.util.Optional;

public class StatusChessGameService {

    private final ChessGameRepository chessGameRepository;

    public StatusChessGameService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public StatusResponse status(int boardId) {
        Optional<ChessGame> chessGame = chessGameRepository.findById(boardId);
        if (chessGame.isEmpty()) {
            throw new BoardNotFoundException();
        }
        Map<Color, Double> status = chessGame.get().getStatus();
        return new StatusResponse(status.get(Color.WHITE), status.get(Color.BLACK));
    }
}
