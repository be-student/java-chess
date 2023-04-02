package chess.view.request;

import chess.controller.user.LoginRequest;
import java.util.List;

public class LoginRequestImpl implements LoginRequest {

    private static final int USERNAME_INDEX = 1;
    private static final int USERNAME_COMMAND_SIZE = 2;

    private final String userName;

    public LoginRequestImpl(List<String> commands) {
        validateCommands(commands);
        userName = commands.get(USERNAME_INDEX);
    }

    private void validateCommands(List<String> commands) {
        if (commands.size() != LoginRequestImpl.USERNAME_COMMAND_SIZE) {
            throw new IllegalArgumentException("잘못된 입력입니다. login [유저 이름]을 입력해주세요. ex) login Hyeon9mak");
        }
    }

    @Override
    public String getUserName() {
        return userName;
    }
}
