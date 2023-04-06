package chess.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.main.Request;
import chess.repository.InMemoryUserRepository;
import chess.repository.user.UserDto;
import chess.service.user.LoginService;
import chess.view.request.RequestImpl;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class LoginControllerTest {

    private final LoginOutputSpy loginOutputSpy = new LoginOutputSpy();
    private final LoginSpy loginSpy = new LoginSpy();
    private final InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

    @Test
    void 로그인_성공() {
        // given
        LoginController loginController = new LoginController(loginSpy, loginOutputSpy,
                new LoginService(inMemoryUserRepository));
        // when
        loginController.run(createRequest(List.of("login", "user1")));
        // then

        Optional<UserDto> user1 = inMemoryUserRepository.findByUserName("user1");
        assertAll(
                () -> assertThat(user1).isPresent(),
                () -> assertThat(user1.get().getUserId()).isEqualTo(loginSpy.getRequestId()),
                () -> assertThat(loginOutputSpy.getCount()).isOne()
        );
    }

    private Request createRequest(List<String> commands) {
        return RequestImpl.of(commands, Optional.empty(), Optional.empty());
    }
}

class LoginSpy implements Login {

    private int userId;

    @Override
    public void login(int userId) {
        this.userId = userId;
    }

    public int getRequestId() {
        return userId;
    }
}

class LoginOutputSpy implements LoginOutput {

    private int count;

    @Override
    public void printLoginSuccess() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
