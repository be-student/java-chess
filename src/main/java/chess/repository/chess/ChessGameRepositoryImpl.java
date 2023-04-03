package chess.repository.chess;

import chess.domain.game.ChessGame;
import chess.domain.game.Position;
import chess.domain.game.state.EndState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.PlayingState;
import chess.domain.game.state.ReadyState;
import chess.service.ChessGameRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ChessGameRepositoryImpl implements ChessGameRepository {

    private final ChessGameDao chessGameDao;
    private final MoveDao moveDao;

    public ChessGameRepositoryImpl(ChessGameDao chessGameDao, MoveDao moveDao) {
        this.chessGameDao = chessGameDao;
        this.moveDao = moveDao;
    }

    @Override
    public int create(int userId) {
        return chessGameDao.save(userId, ReadyState.getInstance());
    }

    @Override
    public Optional<ChessGame> findById(int id) {
        return loadChessGame(id);
    }

    @Override
    public List<Integer> findAllIdsByUserId(int userId) {
        return chessGameDao.findBoardIdsByUserId(userId);
    }

    private Optional<ChessGame> loadChessGame(int boardId) {
        Optional<GameState> gameState = loadGameState(boardId);
        if (gameState.isEmpty()) {
            return Optional.empty();
        }
        List<MoveDto> movesByBoardId = moveDao.findMovesByBoardId(boardId);
        List<List<Position>> movesWithPosition = convertToPosition(movesByBoardId);
        return Optional.of(new ChessGame(movesWithPosition, gameState.get()));
    }

    private Optional<GameState> loadGameState(int boardId) {
        Optional<String> status = chessGameDao.findStatusByBoardId(boardId);
        if (status.isEmpty()) {
            return Optional.empty();
        }
        return getState(status.get());
    }

    private Optional<GameState> getState(String status) {
        switch (status) {
            case "start":
                return Optional.of(ReadyState.getInstance());
            case "playing":
                return Optional.of(PlayingState.getInstance());
            case "end":
                return Optional.of(EndState.getInstance());
            default:
                return Optional.empty();
        }
    }

    private List<List<Position>> convertToPosition(List<MoveDto> moves) {
        return moves.stream()
                .map(move -> {
                    List<Position> moveWithPosition = new ArrayList<>();
                    moveWithPosition.add(move.getOrigin());
                    moveWithPosition.add(move.getDestination());
                    return moveWithPosition;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void saveGameState(int boardId, GameState gameState) {
        chessGameDao.update(boardId, gameState);
    }

    @Override
    public void saveMoves(int boardId, String origin, String destination, int turn) {
        moveDao.save(boardId, origin, destination, turn);
    }
}
