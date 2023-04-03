package chess.service.game;

import chess.domain.game.ChessGame;
import chess.service.repository.ChessGameRepository;
import java.util.Optional;

public class LoadChessGameService {

    private final ChessGameRepository chessGameRepository;

    public LoadChessGameService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public Optional<ChessGame> load(int boardId) {
        return chessGameRepository.findById(boardId);
    }
}
