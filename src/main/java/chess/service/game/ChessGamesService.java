package chess.service.game;

import chess.service.ChessGameRepository;
import java.util.List;

public class ChessGamesService {

    private final ChessGameRepository chessGameRepository;

    public ChessGamesService(ChessGameRepository chessGameRepository) {
        this.chessGameRepository = chessGameRepository;
    }

    public List<Integer> findAllGamesByUserId(int userId) {
        return chessGameRepository.findAllIdsByUserId(userId);
    }
}
