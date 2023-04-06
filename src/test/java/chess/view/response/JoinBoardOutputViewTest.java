package chess.view.response;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.state.StatusType;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class JoinBoardOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void 방_참여_성공시_방_번호에_대한_정보_제공() {
        // given
        JoinBoardOutputView joinBoardOutputView = new JoinBoardOutputView();
        // when
        joinBoardOutputView.printJoinBoardSuccess(StatusType.PLAYING);
        // then
        assertThat(outputStreamCaptor)
                .hasToString("게임에 참여했습니다" + System.lineSeparator()
                        + "현재 게임 상태" + System.lineSeparator()
                        + "playing" + System.lineSeparator());
    }

    @AfterEach
    public void restoreSetOut() {
        System.setOut(standardOut);
    }
}
