package chess.controller.exception;

public class LoginException extends RuntimeException {

    public LoginException() {
        super("로그인 되지 않았습니다");
    }
}
