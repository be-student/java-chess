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
class ErrorOutputViewTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void 예외_발생시_메시지를_잘_출력함() {
        // given
        ErrorOutputView errorOutputView = new ErrorOutputView();
        // when
        errorOutputView.printError("예외 발생");
        // then
        assertThat(outputStreamCaptor)
                .hasToString("예외 발생" + System.lineSeparator());
    }

    @AfterEach
    public void restoreSetOut() {
        System.setOut(standardOut);
    }
}
