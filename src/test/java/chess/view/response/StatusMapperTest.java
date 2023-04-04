package chess.view.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.domain.game.state.StatusType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
class StatusMapperTest {

    @ParameterizedTest
    @CsvSource({"PLAYING, playing", "END, end", "READY, ready"})
    void 상태에_따른_결과_가져오기_테스트(StatusType statusType, String expected) {
        // given
        // when
        String result = StatusMapper.map(statusType);
        // then
        assertEquals(expected, result);
    }
}
