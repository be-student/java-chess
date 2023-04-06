package chess.view.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class GamesOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printGames() {
        // given
        GamesOutputView gamesOutputView = new GamesOutputView();
        // when
        gamesOutputView.printGames(List.of(1, 2, 3));
        // then
        assertThat(outputStreamCaptor)
                .hasToString("현재 유저의 게임들" + System.lineSeparator()
                        + "1" + System.lineSeparator()
                        + "2" + System.lineSeparator()
                        + "3" + System.lineSeparator()
                        + "게임을 시작하려면 join 게임번호 를 입력해주세요" + System.lineSeparator()
                        + "게임을 생성하려면 create 를 입력해주세요" + System.lineSeparator());
    }

    @AfterEach
    public void restoreSetOut() {
        System.setOut(standardOut);
    }
}
