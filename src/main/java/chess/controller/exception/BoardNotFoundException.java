package chess.controller.exception;

public class BoardNotFoundException extends RuntimeException {

    public BoardNotFoundException() {
        super("존재하지 않는 방입니다");
    }
}
