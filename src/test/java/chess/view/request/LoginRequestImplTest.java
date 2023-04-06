package chess.view.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class LoginRequestImplTest {

    @Test
    void 이름_꺼내오는_테스트() {
        //given
        List<String> commands = List.of("login", "Hyeon9mak");

        //when
        LoginRequestImpl loginRequest = new LoginRequestImpl(commands);

        //then
        assertThat(loginRequest.getUserName()).isEqualTo("Hyeon9mak");
    }

    @Test
    void 커맨드_사이즈가_다르면_예외() {
        //given
        List<String> commands = List.of("login", "Hyeon9mak", "2");

        //when
        assertThatThrownBy(() -> new LoginRequestImpl(commands))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다. login [유저 이름]을 입력해주세요. ex) login Hyeon9mak");
    }
}
