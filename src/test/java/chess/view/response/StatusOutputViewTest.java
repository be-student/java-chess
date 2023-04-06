package chess.view.response;

import static org.assertj.core.api.Assertions.assertThat;

import chess.controller.game.status.StatusResponse;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class StatusOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void 게임_진행중_상태_출력() {
        // given
        StatusOutputView statusOutputView = new StatusOutputView();
        // when
        statusOutputView.printStatus(new StatusResponse(37.5, 27.5));
        // then
        assertThat(outputStreamCaptor)
                .hasToString("현재 점수" + System.lineSeparator()
                        + "흰색 : 37.5" + System.lineSeparator()
                        + "검정색 : 27.5" + System.lineSeparator());
    }
}
