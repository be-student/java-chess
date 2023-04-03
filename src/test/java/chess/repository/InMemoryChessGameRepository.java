package chess.repository;

import chess.domain.game.ChessGame;
import chess.domain.game.state.GameState;
import chess.service.ChessGameRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryChessGameRepository implements ChessGameRepository {

    private final List<InMemoryChessGame> chessGames = new ArrayList<>();

    @Override
    public int create(int userId) {
        int id = chessGames.size() + 1;
        ChessGame chessGame = new ChessGame();
        chessGames.add(new InMemoryChessGame(id, userId, chessGame));
        return id;
    }

    @Override
    public Optional<ChessGame> findById(int id) {
        return chessGames.stream()
                .filter(chessGame -> chessGame.id == id)
                .map(chessGame -> chessGame.chessGame)
                .findFirst();
    }

    @Override
    public List<Integer> findAllIdsByUserId(int userId) {
        return chessGames.stream()
                .filter(chessGame -> chessGame.userId == userId)
                .map(chessGame -> chessGame.id)
                .collect(Collectors.toList());
    }

    @Override
    public void saveGameState(int boardId, GameState gameState) {
    }

    @Override
    public void saveMoves(int boardId, String origin, String destination, int turn) {
    }

    private static class InMemoryChessGame {

        private final int id;
        private final int userId;
        private final ChessGame chessGame;

        private InMemoryChessGame(int id, int userId, ChessGame chessGame) {
            this.id = id;
            this.userId = userId;
            this.chessGame = chessGame;
        }
    }
}
