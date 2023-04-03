package chess.domain.game.state;

import chess.domain.game.exception.ChessGameException;

public class ReadyState implements GameState {

    private static final StatusType STATUS_TYPE = StatusType.START;
    private static final ReadyState INSTANCE = new ReadyState();

    private ReadyState() {
    }

    public static ReadyState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        return PlayingState.getInstance();
    }

    @Override
    public GameState end() {
        return EndState.getInstance();
    }

    @Override
    public GameState run() {
        throw new ChessGameException("게임이 시작되지 않았습니다.");
    }

    @Override
    public StatusType getStatusType() {
        return STATUS_TYPE;
    }

    @Override
    public boolean isStarted() {
        return false;
    }
}
