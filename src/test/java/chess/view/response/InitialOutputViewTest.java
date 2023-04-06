package chess.view.response;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class InitialOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printInitialMessage() {
        // given
        InitialOutputView initialOutputView = new InitialOutputView();
        // when
        initialOutputView.printInitialMessage();
        // then
        assertThat(outputStreamCaptor)
                .hasToString("> 체스 게임을 시작합니다." + System.lineSeparator()
                        + "> 로그인 : login 유저id - 예. login user1" + System.lineSeparator()
                        + "> 게임 목록 : games" + System.lineSeparator()
                        + "> 진행중인 게임 선택 : join 게임번호 - 예. join 1" + System.lineSeparator()
                        + "> 게임 생성 : create" + System.lineSeparator()
                        + "> 게임 시작 : start" + System.lineSeparator()
                        + "> 게임 종료 : end" + System.lineSeparator()
                        + "> 게임 이동 : move source위치 target위치 - 예. move b2 b3" + System.lineSeparator());
    }

    @AfterEach
    public void restoreSetOut() {
        System.setOut(standardOut);
    }
}
