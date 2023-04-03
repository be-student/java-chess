package chess.service.game;

import chess.domain.game.ChessGame;
import chess.service.ChessGameRepository;
import java.util.Optional;

public class ChessLoadService {

    private final ChessGameRepository chessGameRepository;

    public ChessLoadService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public Optional<ChessGame> load(int boardId) {
        return chessGameRepository.findById(boardId);
    }
}
