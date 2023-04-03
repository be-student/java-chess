package chess.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.repository.InMemoryUserRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class LoginServiceTest {

    private final InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
    private final LoginService loginService = new LoginService(inMemoryUserRepository);

    @Test
    void 로그인_성공() {
        // given
        int userId = inMemoryUserRepository.saveUser("test");

        // when
        int result = loginService.login("test");

        //then
        assertEquals(userId, result);
    }
}
