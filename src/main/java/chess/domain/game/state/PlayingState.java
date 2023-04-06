package chess.domain.game.state;

public class PlayingState implements GameState {

    private static final StatusType STATUS_TYPE = StatusType.PLAYING;
    private static final PlayingState INSTANCE = new PlayingState();

    private PlayingState() {
    }

    public static PlayingState getInstance() {
        return INSTANCE;
    }

    @Override
    public GameState start() {
        return ReadyState.getInstance();
    }

    @Override
    public GameState end() {
        return EndState.getInstance();
    }

    @Override
    public GameState run() {
        return this;
    }

    @Override
    public StatusType getStatusType() {
        return STATUS_TYPE;
    }

    @Override
    public boolean isStarted() {
        return true;
    }
}
