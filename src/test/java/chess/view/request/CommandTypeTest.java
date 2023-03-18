package chess.view.request;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CommandTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"start, START", "end, END", "move a1 a2, MOVE"})
    void 커맨드_생성_테스트(String input, CommandType expect) {
        //expect
        assertThat(CommandType.from(input)).isEqualTo(expect);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Start", "End", "hello", "move a9 b4", "Move a1 b1"})
    void 커맨드_생성_예외_테스트(String input) {
        //expect
        assertThatThrownBy(() -> CommandType.from(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 커맨드 양식 입니다.");
    }
}
