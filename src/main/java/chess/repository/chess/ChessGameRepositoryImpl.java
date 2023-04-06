package chess.repository.chess;

import chess.domain.game.ChessGame;
import chess.domain.game.Position;
import chess.domain.game.state.EndState;
import chess.domain.game.state.GameState;
import chess.domain.game.state.PlayingState;
import chess.domain.game.state.ReadyState;
import chess.service.ChessGameRepository;
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
        Optional<GameState> gameState = loadGameState(id);
        if (gameState.isEmpty()) {
            return Optional.empty();
        }
        List<MoveDto> movesByBoardId = moveDao.findMovesByBoardId(id);
        List<List<Position>> movesWithPosition = convertToPositions(movesByBoardId);
        return Optional.of(new ChessGame(movesWithPosition, gameState.get()));
    }

    @Override
    public List<Integer> findAllIdsByUserId(int userId) {
        return chessGameDao.findBoardIdsByUserId(userId);
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

    private List<List<Position>> convertToPositions(List<MoveDto> moves) {
        return moves.stream()
                .map(this::convertToPosition)
                .collect(Collectors.toList());
    }

    private List<Position> convertToPosition(MoveDto move) {
        return List.of(move.getOrigin(), move.getDestination());
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
