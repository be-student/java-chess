package chess.domain.game;

import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.GameState;
import chess.domain.game.state.PlayingState;
import chess.domain.game.state.ReadyState;
import chess.domain.game.state.StatusType;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.exception.IllegalPieceMoveException;
import java.util.List;
import java.util.Map;

public class ChessGame {

    private final Board board;
    private GameState gameState;

    public ChessGame() {
        gameState = ReadyState.getInstance();
        board = new Board();
    }

    public ChessGame(List<List<Position>> moves, GameState gameState) {
        this.gameState = PlayingState.getInstance();
        board = new Board();
        moves.forEach(move -> move(move.get(0), move.get(1)));
        this.gameState = gameState;
    }

    public void start() {
        gameState = gameState.start();
    }

    public void move(Position origin, Position destination) {
        gameState = gameState.run();
        movePiece(origin, destination);
        if (board.isKingDead()) {
            end();
        }
    }

    private void movePiece(Position origin, Position destination) {
        try {
            board.movePiece(origin, destination);
        } catch (IllegalPieceMoveException e) {
            throw new ChessGameException(e.getMessage(), e);
        }
    }

    public List<List<Piece>> getPieces() {
        return board.getPieces();
    }

    public void end() {
        gameState = gameState.end();
    }

    public Map<Color, Double> getStatus() {
        if (gameState.notStarted()) {
            throw new ChessGameException("게임이 시작되지 않았습니다.");
        }
        return board.getStatus();
    }

    public Turn getTurn() {
        gameState = gameState.run();
        return board.getTurn();
    }

    public StatusType getStatusType() {
        return gameState.getStatusType();
    }

    public GameState getGameState() {
        return gameState;
    }
}
