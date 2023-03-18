package chess.domain.piece.state;

import chess.domain.exception.IllegalPieceMoveException;
import chess.domain.piece.ColorCompareResult;

public class EmptyState implements MoveState {

    private static final EmptyState instance = new EmptyState();

    private EmptyState() {
    }

    public static EmptyState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        throw new IllegalPieceMoveException();
    }

    @Override
    public MoveState getNextState() {
        throw new IllegalPieceMoveException();
    }
}
