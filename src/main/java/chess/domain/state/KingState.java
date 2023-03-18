package chess.domain.state;

import chess.domain.ColorCompareResult;

public class KingState implements MoveState {

    private static final KingState instance = new KingState();

    private KingState() {
    }

    public static KingState getInstance() {
        return instance;
    }

    @Override
    public boolean canMove(int fileDifference, int rankDifference, ColorCompareResult colorCompareResult) {
        return validMoveRequest(fileDifference, rankDifference) && colorCompareResult != ColorCompareResult.SAME_COLOR;
    }

    private boolean validMoveRequest(int x, int y) {
        if (x == 0 && y == 0) {
            return false;
        }
        return Math.abs(x) <= 1 && Math.abs(y) <= 1;
    }
}
