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
class CreateRoomOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void 방생성_성공시_방_번호에_대한_정보_제공() {
        // given
        CreateRoomOutputView createRoomOutputView = new CreateRoomOutputView();
        // when
        createRoomOutputView.printCreateRoomSuccess(1);
        // then
        assertThat(outputStreamCaptor)
                .hasToString("게임을 생성했습니다" + System.lineSeparator()
                        + "게임 번호는 1 입니다" + System.lineSeparator()
                        + "게임을 참여하려면 join [게임 번호]를 입력하세요" + System.lineSeparator());
    }

    @AfterEach
    public void restoreSetOut() {
        System.setOut(standardOut);
    }
}
