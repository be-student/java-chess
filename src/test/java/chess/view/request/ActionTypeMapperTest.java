package chess.view.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.controller.main.ActionType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class ActionTypeMapperTest {

    @ParameterizedTest
    @CsvSource({"START, start", "END, end", "MOVE, move", "STATUS, status", "LOGIN, login", "JOIN, join"})
    void 커맨드의_시작_값에_따라_액션_타입이_잘_반환됨(ActionType expected, String command) {
        //given
        //when
        ActionType actual = ActionTypeMapper.map(command);

        //then
        assertEquals(expected, actual);
    }
}
